package com.iwood.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tangyan on 2/6/15.
 */
@Controller
@RequestMapping("/")
public class HomeController {

  @RequestMapping("/")
  public String index() {
    return "/home/index";
  }

  @RequestMapping("/reg")
  public String reg() {
    return "/home/reg";
  }

  @RequestMapping("/login")
  public String login() {
    return "/home/login";
  }
}
