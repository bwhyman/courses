package com.se.courses.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.se.courses.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 管理员操作验证
 * @author BO
 *
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");
		if (user.getLevel() >= 10) {
			return true;
		}
		return false;
	}

}
