package com.Controller;

import com.Service.UserService;
import com.constant.CommonResult;
import com.constant.ErrorCode;
import com.entity.TblUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    DefaultWebSecurityManager manager;

    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public CommonResult register(@RequestParam("account") String account,
                                 @RequestParam("password") String password){
        System.out.println("account="+account);
        System.out.println("password="+password);
        //获取用户名和密码
//        String account;
//        String password;
//        try {
//            account = request.getParameter("account");
//
//            password = request.getParameter("password");
//
//        }catch (Exception e){
//            return CommonResult.fail(ErrorCode.REQUEST_NULL);
//        }

        //判断用户是否存在
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
            if (i==1){
                System.out.println("插入成功");
                return CommonResult.success();
            }
            else
                return CommonResult.fail(ErrorCode.INSERT_ERROR);
        }catch (Exception e){
            return CommonResult.fail(ErrorCode.INSERT_ERROR);
        }
    }


    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    public CommonResult login(@RequestParam("account") String account,
                              @RequestParam("password") String password){

//        //获取用户名和密码
//        String account;
//        String password;
//        try {
//            account = request.getParameter("account");
//            password = request.getParameter("password");
//        }catch (Exception e){
//            return CommonResult.fail(ErrorCode.REQUEST_NULL);
//        }

        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(account, password);
        System.out.println("执行到这里");
        try {
            System.out.println("开始验证");
            subject.login(usernamePasswordToken);
            System.out.println("验证成功");
            return CommonResult.success();
        }catch (UnknownAccountException e){
            System.out.println("用户名不存在");
            return CommonResult.fail(ErrorCode.ACCOUNT_NOT_EXIST);
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
            return CommonResult.fail(ErrorCode.PASSWORD_ERROR);
        }
    }
}
