package com.cwa.server.foyer;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import serverice.account.ChooseRegionResult;
import serverice.account.IAccountServicePrx;
import serverice.account.LoginResult;
import baseice.service.FunctionMenu;
import baseice.service.FunctionTypeEnum;

import com.cwa.component.data.IDBSession;
import com.cwa.component.functionmanage.IFunctionCluster;
import com.cwa.component.functionmanage.IFunctionService;
import com.cwa.component.functionmanage.node.FunctionLeafNode;
import com.cwa.data.entity.domain.RegionEntity;
import com.cwa.server.foyer.dataFunction.RegionDataFunction;
import com.cwa.service.constant.ServiceConstant;
import com.cwa.service.context.IGloabalContext;
import com.cwa.util.prototype.JsonUtil;

public class FoyerService implements IFoyerService {
	protected static final Logger logger = LoggerFactory.getLogger(IFoyerService.class);

	private IGloabalContext gloabalContext;

	// 区信息json串
	private volatile String regionListJson = "";

	private RegionDataFunction rdataFunction;

	@Override
	public void startup(IGloabalContext gloabalContext) {
		this.gloabalContext = gloabalContext;

		// 获得公共区
		IDBSession dbSession = gloabalContext.getCurrentDBService().getDBSession(ServiceConstant.General_Rid);
		rdataFunction = new RegionDataFunction(dbSession);
		rdataFunction.initData(false);
	}

	@Override
	public void shutdown() throws Exception {
	}

	@Override
	public LoginResult login(String account, int channel, String password) {
		try {
			// 选取逻辑服
			IFunctionService functionService = gloabalContext.getCurrentFunctionService();
			IFunctionCluster functionCluster = functionService.getFunctionCluster(gloabalContext.getGid(), FunctionTypeEnum.Account);
			if (functionCluster == null) {
				return null;
			}
			// 随机服务
			FunctionMenu functionMenu = functionCluster.getRandomFunctionMenu(account);
			if (functionMenu == null) {
				return null;
			}
			IAccountServicePrx servicePrx = FunctionLeafNode.getService(functionMenu, IAccountServicePrx.class);
			if (servicePrx == null) {
				return null;
			}
			return servicePrx.login(account, channel, password);
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return null;
	}

	@Override
	public ChooseRegionResult chooseRegion(String token, int rid, int asid) {
		try {
			// 选取逻辑服
			IFunctionService functionService = gloabalContext.getCurrentFunctionService();
			IFunctionCluster functionCluster = functionService.getFunctionCluster(gloabalContext.getGid(), FunctionTypeEnum.Account);
			if (functionCluster == null) {
				return null;
			}
			// 指定id的服务
			IAccountServicePrx servicePrx = functionCluster.getSlaveService(asid, IAccountServicePrx.class);
			if (servicePrx == null) {
				return null;
			}
			return servicePrx.chooseRegion(token, rid);
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return null;
	}

	@Override
	public String getRegionListJson() {
		if (regionListJson.isEmpty()) {
			List<RegionEntity> regionList = new LinkedList<RegionEntity>();
			regionList.addAll(rdataFunction.getEntitys());
			regionListJson = JsonUtil.transferListToJson(regionList);
		}
		return regionListJson;
	}

	@Override
	public void newRegion(int rid) {
		if(rdataFunction!=null){
			rdataFunction.newRegionEntity(rid);
		}
	}
}
