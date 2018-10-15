package com.team.controller;

import com.team.dao.UserRepository;
import com.team.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserRepository userRepository;
    /**
     * Log in
     */
    @RequestMapping("login")
    public String Login(ModelMap model, UserEntity user){
        String name = user.getName();
        String password = user.getPassword();

        if(userRepository.findByNameAndPassword(name, password) != null) {
            model.addAttribute("message", name);
        }
        return "/index";
    }
    /**
     * Sign up
     */
    @RequestMapping(value = "signup", method=RequestMethod.POST)
    public String Signup(Model model, UserEntity user) {
        userRepository.save(user);
        return "/index";
    }
}
