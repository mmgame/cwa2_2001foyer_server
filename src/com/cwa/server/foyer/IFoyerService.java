package com.cwa.server.foyer;

import com.cwa.service.IModuleServer;

import serverice.account.ChooseRegionResult;
import serverice.account.LoginResult;

public interface IFoyerService extends IModuleServer {
	String getRegionListJson();

	LoginResult login(String account, int channel, String password);

	ChooseRegionResult chooseRegion(String token, int rid, int asid);

	void newRegion(int rid);
}
