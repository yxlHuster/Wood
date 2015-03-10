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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 14-1-15
 * Time: 下午3:42
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;

    @RequestMapping(value =  "/login", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        return modelAndView("/user/login");
    }

    @RequestMapping(value = "/ulogin", method = RequestMethod.POST)
    public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value = "ru", required = false) String ru,
                            @RequestParam(value = "phone") String phone,
                            @RequestParam(value = "passwd") String password) {
		Map<String, Object> objects = new HashMap<String, Object>();
		User user = userService.getUserByPhoneAndPassWd(phone, password);
		if (user == null) {
			objects.put("errmsg", "用户名或者密码错误！");
			return modelAndView("/user/login", objects);
		}
		AuthedUser authedUser = new AuthedUser();
		authedUser.setName(user.getRealName());
		authedUser.setId(user.getUserId());
		authedUser.setTime(System.currentTimeMillis());
		CookieUtils.setLoginCookie(response, authedUser);
		objects.put("_user", authedUser);
		if (StringUtils.isNotBlank(ru) && !ru.equals("#")) {
			 return this.redirect(response, ru);
		}
		return modelAndView("/home/index", objects);
    }

    @RequestMapping(value = "/logout")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.removeLoginCookie(response);
        return "redirect:/";
    }

	@RequestMapping(value = "/reg")
	public String userReg(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "/user/reg";
	}
}
