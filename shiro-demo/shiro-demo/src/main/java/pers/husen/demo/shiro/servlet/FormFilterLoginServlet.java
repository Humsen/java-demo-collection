package pers.husen.demo.shiro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

/**
 * @Desc 过滤
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月27日 下午3:42:30
 * 
 * @Version 1.0.0
 */
@WebServlet(name = "formFilterLoginServlet", urlPatterns = "/formfilterlogin")
public class FormFilterLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String errorClassName = (String) req.getAttribute("shiroLoginFailure");

		if (UnknownAccountException.class.getName().equals(errorClassName)) {
			req.setAttribute("error", "用户名/密码错误");
		} else if (IncorrectCredentialsException.class.getName().equals(errorClassName)) {
			req.setAttribute("error", "用户名/密码错误");
		} else if (errorClassName != null) {
			req.setAttribute("error", "未知错误：" + errorClassName);
		}

		req.getRequestDispatcher("/WEB-INF/jsp/formfilterlogin.jsp").forward(req, resp);
	}
}