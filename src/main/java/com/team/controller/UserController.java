package com.team.controller;

import com.team.dao.UserDao;
import com.team.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    //创建User集合
    List list = new ArrayList();
    @Resource
    private UserDao userDao;

    /**
     * 登录
     */
    @RequestMapping("login")
    public String Login(ModelMap model, UserEntity user){
        String name = user.getName();
        String password = user.getPassword();

        if(userDao.isValidUser(name, password)) {
            model.addAttribute("message", user.getName());
        }
        return "/index";
    }
    /**
     * 注册
     */
    @RequestMapping(value = "signup", method=RequestMethod.POST)
    public String Signup(Model model, UserEntity user) {
        userDao.save(user);
//        List<UserEntity> list = userDao.list();
//        model.addAttribute("list",list);

        return "/index";
    }
}
