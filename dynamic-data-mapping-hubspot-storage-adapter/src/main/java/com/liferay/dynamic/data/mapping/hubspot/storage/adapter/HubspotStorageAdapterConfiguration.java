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

package com.liferay.dynamic.data.mapping.hubspot.storage.adapter;

import aQute.bnd.annotation.metatype.Meta;

/**
 * @author Marcellus Tavares
 */
@Meta.OCD(
	id = "com.liferay.dynamic.data.mapping.storage.HubspotStorageAdapterConfiguration",
	localization = "content/Language", name = "dynamic.data.mapping.storage.hubspot.adapter.configuration.name"
)
public interface HubspotStorageAdapterConfiguration {

	@Meta.AD(
		deflt ="299703", description = "portal-id-description",
		name = "portal-id-name", required = true
	)
	public int portalId();

	@Meta.AD(
		deflt ="142c2345-6626-4995-8206-b69d066862fd",
		description = "form-guid-description", name = "form-guid-name",
		required = true
	)
	public String formGuid();

	@Meta.AD(
		deflt ="https://forms.hubspot.com/uploads/form/v2/",
		description = "url-description", name = "url-name", required = true
	)
	public String url();

}