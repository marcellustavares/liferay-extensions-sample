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
package com.liferay.dynamic.data.lists.service.override;

import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class DDLRecordSetLocalServiceHook
	extends DDLRecordSetLocalServiceWrapper {

	public DDLRecordSetLocalServiceHook() {
		super(null);
	}

	@Override
	public DDLRecordSet addRecordSet(
			long userId, long groupId, long ddmStructureId, String recordSetKey,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			int minDisplayRows, int scope, ServiceContext serviceContext)
		throws PortalException {

		System.out.println("DDL Record Set Service Override!");

		return super.addRecordSet(
			userId, groupId, ddmStructureId, recordSetKey, nameMap,
			descriptionMap, minDisplayRows, scope, serviceContext);
	}

}