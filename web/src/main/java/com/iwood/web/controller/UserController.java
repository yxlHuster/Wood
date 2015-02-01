package com.iwood.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 14-1-15
 * Time: 下午3:42
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/")
    public String index() {
        return "/user/login";
    }

    @RequestMapping(value = "/login")
    public String userLogin(Model model,
                            HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "username") String username,
                            @RequestParam(value = "passwd") String password) {
        return "/user/login";
    }

    @RequestMapping("/reg")
    public String reg(Model model) {
        return "/user/reg";
    }

    @RequestMapping(value = "/ureg")
    public String userReg( Model model,
                             HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "username") String username,
                             @RequestParam(value = "passwd") String password) {
        return "redirect:/user/";
    }

    @RequestMapping(value = "/loginout")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/user/";
    }
}
