package com.cwa.server.foyer.dataFunction;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import baseice.basedao.IEntity;

import com.cwa.component.data.IDBSession;
import com.cwa.component.data.function.IDataFunction;
import com.cwa.data.entity.IRegionEntityDao;
import com.cwa.data.entity.domain.RegionEntity;
import com.cwa.service.constant.ServiceConstant;

public class RegionDataFunction implements IDataFunction {
	private IRegionEntityDao dao;
	private IDBSession dbSession;

	// {区id:RegionEntity}
	private ConcurrentHashMap<Integer, RegionEntity> entityMap = new ConcurrentHashMap<Integer, RegionEntity>();

	private boolean isInited;

	public RegionDataFunction(IDBSession dbSession) {
		this.dbSession = dbSession;
		dao = (IRegionEntityDao) dbSession.getEntityDao(RegionEntity.class);
	}

	@Override
	public boolean initData(boolean newRegister) {
		if (isInited) {
			return false;
		}
		isInited = true;

		List<? extends IEntity> entitys = dao.selectAllEntity(createParams());
		for (IEntity e : entitys) {
			RegionEntity entity = (RegionEntity) e;
			entityMap.put(entity.rid, entity);
		}
		return false;
	}

	@Override
	public boolean isInited() {
		return isInited;
	}

	public void newRegionEntity(int rid) {
		if (entityMap.containsKey(rid)) {
			return;
		}
		RegionEntity entity = (RegionEntity) dao.selectEntityByRid(rid, createParams());
		if (entity == null) {
			return;
		}
		entityMap.put(rid, entity);
	}

	public Collection<RegionEntity> getEntitys() {
		return entityMap.values();
	}

	private Map<String, Object> createParams() {
		return dbSession.getParams(ServiceConstant.General_Rid);
	}
}
