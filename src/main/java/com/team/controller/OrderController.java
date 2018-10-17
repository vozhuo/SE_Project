//package com.team.controller;
//
//import com.team.dao.OrderRepository;
//import com.team.entity.OrderEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.annotation.Resource;
//
//@Controller
//public class OrderController {
//    @Resource
//    private OrderRepository orderRepository;
//    /**
//     * View order
//     */
//    @RequestMapping(value = "view")
//    public String View(Model model) {
//        model.addAttribute("order_list", orderRepository.findAll());
//        return "/order_list";
//    }
//    /**
//     * Submit order
//     */
//    @RequestMapping(value = "report", method= RequestMethod.POST)
//    public String Report(Model model, OrderEntity order) {
//        orderRepository.save(order);
//        return "/index";
//    }
//    /**
//     * Updating order
//     */
//    @RequestMapping(value = "report", method= RequestMethod.POST)
//    public String Update(Model model, OrderEntity order) {
//        orderRepository.saveAndFlush(order);
//        return "redirect:/order_list";
//    }
//    /**
//     * Cancelling order
//     */
//    public String Cancell(Model model, int id) {
//        orderRepository.deleteById((long) id);
//        model.addAttribute("order_list", orderRepository.findAll());
//        return "redirect:/order_list";
//    }
//}
