package com.cwa.server.foyer;

/**
 * 服务器使用状态
 * 
 * @author mausmars
 * 
 */
public enum ServerUseStateEnum {
	New(0), // 新服
	Busy(1), // 繁忙
	Full(2), // 爆满
	;

	private int value;

	ServerUseStateEnum(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}
}
