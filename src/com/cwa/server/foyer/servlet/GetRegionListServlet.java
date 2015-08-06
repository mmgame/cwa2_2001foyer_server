package com.cwa.server.foyer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cwa.server.foyer.IFoyerService;

/**
 * 获取区列表
 * 
 * @author mausmars
 *
 */
public class GetRegionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger logger = LoggerFactory.getLogger(HttpServlet.class);

	private IFoyerService foyerService;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regionListJson = foyerService.getRegionListJson();

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.getWriter().write(regionListJson);
	}

	// -------------------------------------
	public void setFoyerService(IFoyerService foyerService) {
		this.foyerService = foyerService;
	}
}