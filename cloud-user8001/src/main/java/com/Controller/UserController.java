package com.controller;

import com.service.UserService;
import com.constant.CommonResult;
import com.constant.ErrorCode;
import com.entity.TblUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@Controller
//使其不返回页面
@ResponseBody
public class UserController {
    @Autowired
    UserService userService;

    //注入config配置的manager
    @Autowired
    DefaultWebSecurityManager manager;

    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public CommonResult register(@RequestBody Map<String,Object> data){

        //获取请求体的参数
        String account;
        String password;
        try {
            account = data.get("account").toString();
            password = data.get("password").toString();
        }catch (Exception e){
            return CommonResult.fail(ErrorCode.PARSE_REQUEST_ERROR);
        }

        if (account==null||password==null)
            return CommonResult.fail(ErrorCode.REQUEST_NULL);

        //判断用户是否已经存在
        TblUser userExist;
        try {
            userExist = userService.selectUserByAccount(account);
            if (userExist!=null)
                return CommonResult.fail(ErrorCode.ACCOUNT_EXIST);
        }catch (Exception e){
            return CommonResult.fail(ErrorCode.SELECT_ERROR);
        }

        //设置加密的盐值
        String salt="g{/,"+account.substring(account.length()-3,account.length())+"|`5{:'";
        Md5Hash saltPassword=new Md5Hash(password,salt,1024);

        //创建一个新用户准备insert
        TblUser user=new TblUser();
        user.setAccount(account);
        user.setPassword(saltPassword.toString());
        user.setPasswordSalt(salt);

        //insert新用户
        try {
            int i = userService.insertUser(user);
            if (i==1)
                return CommonResult.success();
            else
                return CommonResult.fail(ErrorCode.INSERT_ERROR);
        }catch (Exception e){
            return CommonResult.fail(ErrorCode.INSERT_ERROR);
        }
    }


    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    public CommonResult login(@RequestBody Map<String,Object> data){

        //获取用户名和密码
        String account;
        String password;
        try {
            account = data.get("account").toString();
            password=data.get("password").toString();
        }catch (Exception e){
            return CommonResult.fail(ErrorCode.PARSE_REQUEST_ERROR);
        }

        //判断获得的数据是否为空
        if (account==null||password==null)
            return CommonResult.fail(ErrorCode.REQUEST_NULL);

        //shiro框架解析实现认证
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(account, password);
        try {
            subject.login(usernamePasswordToken);
            return CommonResult.success();
        }catch (UnknownAccountException e){
            return CommonResult.fail(ErrorCode.ACCOUNT_NOT_EXIST);
        }catch (IncorrectCredentialsException e){
            return CommonResult.fail(ErrorCode.PASSWORD_ERROR);
        }
    }
}
