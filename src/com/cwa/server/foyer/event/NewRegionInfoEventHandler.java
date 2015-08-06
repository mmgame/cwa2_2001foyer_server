package com.cwa.server.foyer.event;

import serverice.config.NewRegionEvent;
import baseice.event.IEvent;

import com.cwa.component.event.IEventHandler;
import com.cwa.server.foyer.IFoyerService;

/**
 * 新区信息事件相应
 * 
 * @author mausmars
 *
 */
public class NewRegionInfoEventHandler implements IEventHandler {
	private IFoyerService service;

	@Override
	public void eventHandler(IEvent event) {
		NewRegionEvent e = new NewRegionEvent();
		service.newRegion(e.rid);
	}

	// --------------------------------------------
	public void setService(IFoyerService service) {
		this.service = service;
	}
}
