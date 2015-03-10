package com.iwood.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iwood.web.filter.AuthFilter;
import com.iwood.web.model.AuthedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 15-3-1
 * Time: 上午11:13
 * To change this template use File | Settings | File Templates.
 */
public class AbstractController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

	protected void sendJsonResponse(HttpServletResponse response, JSON json) {
		try {
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().print(json.toJSONString());
		} catch (Exception e) {
			LOGGER.warn("send json response error! e = {}", e);
		}
	}

	protected void sendErrorMsg(HttpServletResponse response, String errMsg) {
		JSONObject object = new JSONObject();
		object.put("status", 1);
		object.put("msg", errMsg);
		sendJsonResponse(response, object);
	}

	protected ModelAndView modelAndView(String view) {
		return new ModelAndView(view);
	}

	protected ModelAndView modelAndView(String view, Map<String, Object> model) {
		return new ModelAndView(view, model);
	}

	protected Map<String, Object> getModel(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		AuthedUser user = getLoginUser(request);
		model.put("_user", user);
		return model;
	}

	protected AuthedUser getLoginUser(HttpServletRequest request) {
		return (AuthedUser) request.getAttribute(AuthFilter.LOGIN_USER_ATTR);
	}

	protected ModelAndView redirect(HttpServletResponse response, String url) {
		try {
			response.sendRedirect(url);
		} catch (Exception e) {
			LOGGER.warn("send redirect error! e = {}", e);
		}
		return null;
	}



}
