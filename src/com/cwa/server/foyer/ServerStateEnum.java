package com.cwa.server.foyer;

/**
 * 服务器状态
 * 
 * @author mausmars
 *
 */
public enum ServerStateEnum {
	Available(0), // 可用
	UnAvailable(1), // 不可用
	;

	private int value;

	ServerStateEnum(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}

}
