package com.iwood.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.iwood.data.model.User;
import com.iwood.web.api.AbstractController;
import com.iwood.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 15-3-1
 * Time: 下午2:04
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/passport")
public class PassPortController extends AbstractController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/verification_code", method = RequestMethod.GET)
	@ResponseBody
	public String getValidCode(HttpServletRequest request, HttpServletResponse response) {
		return getrandomCode();
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public void doReg(HttpServletRequest request, HttpServletResponse response,
						@RequestParam(value = "ru", required = false) String ru,
						@RequestParam(value = "regStep", required = true) int regStep,
						@RequestParam(value = "openId", required = true) String openId,
						@RequestParam(value = "type", required = true) String type,
						@RequestParam(value = "registerType", required = true) String registerType,
						@RequestParam(value = "phone", required = true) String phone,
						@RequestParam(value = "name", required = true) String name,
						@RequestParam(value = "company", required = true) String company,
						@RequestParam(value = "mainProducts", required = true) String mainProducts,
						@RequestParam(value = "flag", required = true) String flag,
						@RequestParam(value = "password", required = true) String password,
						@RequestParam(value = "rePassword", required = true) String rePassword,
						@RequestParam(value = "validCode", required = true) String validCode) {
		JSONObject object = new JSONObject();
		if (regStep == 1) {
			//check phone number exists
			User user = userService.getUserByPhone(phone);
			if (user != null) {
				object.put("msg", "exist");
				this.sendJsonResponse(response, object);
			}
			object.put("msg", "notexist");
			this.sendJsonResponse(response, object);

		} else if (regStep == 2) {
	   		object.put("msg", "succ");
			this.sendJsonResponse(response, object);
		}
	}

	private String getrandomCode() {
		int a = 100000;
		int b = 999999;
		int code = (int)Math.rint(b - a) + a;
		return String.valueOf(code);
	}

}
