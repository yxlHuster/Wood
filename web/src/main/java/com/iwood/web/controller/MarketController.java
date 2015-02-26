package com.iwood.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tangyan on 2/8/15.
 */
@Controller
@RequestMapping("/market")
public class MarketController {

  // Market home
  @RequestMapping("")
  public String index() {
    return "/market/index";
  }

  // Market home
  @RequestMapping("/")
  public String index2() {
    return "/market/index";
  }
}
