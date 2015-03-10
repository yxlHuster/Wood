package com.iwood.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tangyan on 2/6/15.
 */
@Controller
@RequestMapping("/member")
public class MemberController {

  @RequestMapping("/info")
  public String info() {
    return "/member/info";
  }

  @RequestMapping("/company")
  public String company() {
    return "/member/company";
  }
  @RequestMapping("/password")
  public String password() {
    return "/member/password";
  }

  @RequestMapping("/resources")
  public String resources() {
    return "/member/resources";
  }
}
