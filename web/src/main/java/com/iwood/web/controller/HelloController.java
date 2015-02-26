package com.iwood.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tangyan on 2/4/15.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

  @RequestMapping(value = "/hello")
  public String world() { return "/hello/world"; }

}
