/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.product.menu.sample;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=300",
		"panel.category.key=site_administration.custom"
	},
	service = PanelApp.class
)
public class CustomPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return "com_liferay_hello_world_web_portlet_HelloWorldPortlet";
	}

}