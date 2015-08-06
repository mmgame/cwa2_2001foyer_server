package com.cwa.server.foyer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import serverice.account.ChooseRegionResult;
import serverice.account.StateEnum;

import com.cwa.server.foyer.IFoyerService;
import com.cwa.util.prototype.JsonUtil;

/**
 * 选区
 * 
 * @author mausmars
 *
 */
public class ChooseRegionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger logger = LoggerFactory.getLogger(HttpServlet.class);

	private IFoyerService foyerService;

	private ChooseRegionResult chooseRegionResult = new ChooseRegionResult();

	public ChooseRegionServlet() {
		this.chooseRegionResult.state = StateEnum.Fail;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");
		String rid = request.getParameter("rid");
		String asid = request.getParameter("asid");

		ChooseRegionResult chooseRegionResult = null;
		try {
			chooseRegionResult = foyerService.chooseRegion(token, Integer.parseInt(rid), Integer.parseInt(asid));
		} catch (Exception e) {
			logger.error("", e);
		}
		if (chooseRegionResult == null) {
			chooseRegionResult = this.chooseRegionResult;
		}
		String resultStr = JsonUtil.obj2String(chooseRegionResult);

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.getWriter().write(resultStr);
	}

	// --------------------------------------
	public void setFoyerService(IFoyerService foyerService) {
		this.foyerService = foyerService;
	}
}
