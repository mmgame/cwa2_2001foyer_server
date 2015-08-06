package com.cwa.server.foyer.service;

import serverice.foyer._IFoyerServiceDisp;

import com.cwa.server.foyer.IFoyerService;

public class FoyerServiceI extends _IFoyerServiceDisp {
	private static final long serialVersionUID = 1L;
	private IFoyerService foyerService;

	// --------------------------------------------------------
	public void setFoyerService(IFoyerService foyerService) {
		this.foyerService = foyerService;
	}
}
