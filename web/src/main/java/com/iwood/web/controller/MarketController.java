package com.iwood.web.controller;

import com.iwood.web.api.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tangyan on 2/8/15.
 */
@Controller
@RequestMapping("/market")
public class MarketController extends AbstractController {

  // Market home
  @RequestMapping("/")
  public ModelAndView index(HttpServletRequest request) {
	  	Map<String, Object> objects = getModel(request);
  		return modelAndView("/market/index");
  }

  // Market post resource
  @RequestMapping("/post")
  public ModelAndView post(HttpServletRequest request) {
	  Map<String, Object> objects = getModel(request);
      return modelAndView("/market/post", objects);
  }
}
