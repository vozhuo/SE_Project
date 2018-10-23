package com.team.controller;

import com.team.dao.UserRepository;
import com.team.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Resource
    private UserRepository userRepository;
    final static String prefix = "{noop}";
    /**
     * Log in
     */
//    @RequestMapping(value = "log_in", method=RequestMethod.POST)
//    public String Login(RedirectAttributes model, @Valid UserEntity user, BindingResult bindingResult){
//        String name = user.getName();
//        String password = user.getPassword();
//
////        if (bindingResult.hasErrors()) {
////            for (ObjectError objectError : bindingResult.getAllErrors()) {
////                FieldError fieldError = (FieldError) objectError;
////                model.addAttribute(fieldError.getField() + "Valid", objectError.getDefaultMessage());
////            }
////        }
//
//        if(userRepository.findByNameAndPassword(name, password) != null) {
//            model.addAttribute("message", name);
//            return "content";
//        } else {
//            model.addFlashAttribute("error", "用户名或密码错误");
//            return "redirect:/login";
//        }
//    }
    /**
     * Modify password
     */
    @RequestMapping(value = "modify", method= RequestMethod.POST)
    public String ModifyPassword(RedirectAttributes model, @RequestParam("oldPassword")String oldPassword, @RequestParam("newPassword")String newPassword) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findByName(principal.getUsername());

        if ((prefix + oldPassword).equals(user.getPassword())) {
            if(newPassword.length() == 0) {
                model.addFlashAttribute("length_error", "新密码不能为空");
                return "redirect:/user_info";
            }
            user.setPassword(prefix + newPassword);
            userRepository.save(user);
            model.addFlashAttribute("success", "密码修改成功");
        } else {
            model.addFlashAttribute("error", "旧密码错误");
        }
        return "redirect:/user_info";
    }
}
