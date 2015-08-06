package com.cwa.server.foyer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import serverice.account.LoginResult;
import serverice.account.StateEnum;

import com.cwa.server.foyer.IFoyerService;
import com.cwa.util.prototype.JsonUtil;

/**
 * 登录servlet
 * 
 * @author mausmars
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger logger = LoggerFactory.getLogger(HttpServlet.class);

	private IFoyerService foyerService;

	private LoginResult loginResult = new LoginResult();

	public LoginServlet() {
		this.loginResult.state = StateEnum.Fail;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String channel = request.getParameter("channel");
		String password = request.getParameter("password");

		LoginResult loginResult = null;
		try {
			loginResult = foyerService.login(account, Integer.valueOf(channel), password);
		} catch (Exception e) {
			logger.error("", e);
		}
		if (loginResult == null) {
			loginResult = this.loginResult;
		}
		String resultStr = JsonUtil.obj2String(loginResult);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.getWriter().write(resultStr);
	}

	// -------------------------------------
	public void setFoyerService(IFoyerService foyerService) {
		this.foyerService = foyerService;
	}
}