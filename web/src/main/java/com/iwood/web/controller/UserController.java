package com.iwood.web.controller;

import com.iwood.data.model.User;
import com.iwood.web.api.AbstractController;
import com.iwood.web.model.AuthedUser;
import com.iwood.web.service.UserService;
import com.iwood.web.util.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@Autowired
	private UserService userService;

    @RequestMapping(value =  "/login", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "/user/login";
    }

    @RequestMapping(value = "/ulogin", method = RequestMethod.POST)
    public String userLogin(Model model,
                            HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value = "ru", required = false) String ru,
                            @RequestParam(value = "phone") String phone,
                            @RequestParam(value = "passwd") String password) {
		User user = userService.getUserByPhoneAndPassWd(phone, password);
		if (user == null) {
			model.addAttribute("errmsg", "用户名或者密码错误！");
			return "/user/login";
		}
		AuthedUser authedUser = new AuthedUser();
		authedUser.setName(user.getPhone());
		authedUser.setId(user.getUserId());
		CookieUtils.setLoginCookie(response, authedUser);
		if (StringUtils.isNotBlank(ru) && !ru.equals("#")) {
			 return "redirect:" + ru;
		}
		return "/home/index";
    }

    @RequestMapping(value = "/loginout")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.removeLoginCookie(response);
        return "redirect:/home/index";
    }

	@RequestMapping(value = "/reg")
	public String userReg(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "/user/reg";
	}
}
