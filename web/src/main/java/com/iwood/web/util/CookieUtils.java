package com.iwood.web.util;

import com.iwood.web.model.AuthedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieUtils.class);

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, String domain,
                                 String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void addMemCookie(HttpServletResponse response, String name, String value, String domain, String path) {
        addCookie(response, name, value, -1, domain, path);
    }

    public static void removeCookie(HttpServletResponse response, String name, String domain, String path) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setDomain(domain);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                LOGGER.info("get cookies for name = {}", name);
                return cookie.getValue();
            }
        }
        return null;
    }

    public static final String LOGIN_COOKIE_ENCRYPT_KEY = "ruOu$x*JS*^hxV6jFA25";
    private static final String LOGIN_COOKIE_NAME = "wood-user";
    private static final String DOMAIN = "";
	//TODO
    private static final String PATH = "/"; //need set to domain path later

    public static void setLoginCookie(HttpServletResponse response, AuthedUser user) {
        try {
            String v = DES.encrypt(user.toJson(), LOGIN_COOKIE_ENCRYPT_KEY);
            addMemCookie(response, LOGIN_COOKIE_NAME, v, DOMAIN, PATH);
        } catch (Exception e) {
            LOGGER.warn("set login cookie failed! user = {} error = {}", user.toJson(), e);
            throw new RuntimeException(e);
        }
    }

    public static void removeLoginCookie(HttpServletResponse response) {
        removeCookie(response, LOGIN_COOKIE_NAME, DOMAIN, PATH);
    }

    public static AuthedUser getLoginUser(HttpServletRequest request) {
        try {
            String v = getCookieValue(request, LOGIN_COOKIE_NAME);
            if (v == null) {
                return null;
            }
            return AuthedUser.parse(DES.decrypt(v, LOGIN_COOKIE_ENCRYPT_KEY));
        } catch (Exception e) {
            return null;
        }
    }
}
