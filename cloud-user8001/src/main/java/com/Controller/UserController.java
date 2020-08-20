package com.Controller;

import com.Service.UserService;
import com.entity.TblUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/user")
    public String insertUser(){
        ModelAndView view=new ModelAndView();
        TblUser user=new TblUser();
        user.setAccount(new BigDecimal(2018302110326L));
        user.setPassword("123");
        user.setPasswordSalt("etgewtg");
        view.addObject("user",user);
        userService.insertUser(user);
        return user.toString();
    }
}
