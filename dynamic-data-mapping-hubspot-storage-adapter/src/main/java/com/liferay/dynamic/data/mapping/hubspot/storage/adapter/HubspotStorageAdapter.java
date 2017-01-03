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

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.BaseStorageAdapter;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.StorageAdapter;
import com.liferay.dynamic.data.mapping.storage.StorageAdapterRegistry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MapUtil;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(
	property = {
		"baseURL=https://forms.hubspot.com/uploads/form/v2/",
		"portalId=299703",
		"formId=142c2345-6626-4995-8206-b69d066862fd",
	},
	service = StorageAdapter.class
)
public class HubspotStorageAdapter extends BaseStorageAdapter {

	@Override
	public String getStorageType() {
		return "hubspot";
	}

	@Override
	protected long doCreate(
			long companyId, long ddmStructureId, DDMFormValues ddmFormValues,
			ServiceContext serviceContext)
		throws Exception {

		StorageAdapter delegateStorageAdapter =
			_storageAdapterRegistry.getDefaultStorageAdapter();

		submitRequest(ddmFormValues);

		return delegateStorageAdapter.create(
			companyId, ddmStructureId, ddmFormValues, serviceContext);
	}

	@Override
	protected void doDeleteByClass(long classPK) throws Exception {
		StorageAdapter delegateStorageAdapter =
			_storageAdapterRegistry.getDefaultStorageAdapter();

		delegateStorageAdapter.deleteByClass(classPK);
	}

	@Override
	protected void doDeleteByDDMStructure(long ddmStructureId)
		throws Exception {

		StorageAdapter delegateStorageAdapter =
			_storageAdapterRegistry.getDefaultStorageAdapter();

		delegateStorageAdapter.deleteByDDMStructure(ddmStructureId);
	}

	@Override
	protected DDMFormValues doGetDDMFormValues(long classPK) throws Exception {
		StorageAdapter delegateStorageAdapter =
			_storageAdapterRegistry.getDefaultStorageAdapter();

		return delegateStorageAdapter.getDDMFormValues(classPK);
	}

	@Override
	protected void doUpdate(
			long classPK, DDMFormValues ddmFormValues,
			ServiceContext serviceContext)
		throws Exception {

		StorageAdapter delegateStorageAdapter =
			_storageAdapterRegistry.getDefaultStorageAdapter();

		delegateStorageAdapter.update(classPK, ddmFormValues, serviceContext);
	}

	protected void submitRequest(DDMFormValues ddmFormValues) {
		CompletableFuture.supplyAsync(new SenderSupplier(ddmFormValues))
						 .thenAccept(
								response -> {
									if (_log.isDebugEnabled()) {
										_log.info("Response received with status code  " + response.statusCode());
									}
								});
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		String baseURL = MapUtil.getString(properties, "baseURL");
		String portalId = MapUtil.getString(properties, "portalId");
		String formId = MapUtil.getString(properties, "formId");

		_url = baseURL + portalId + "/" + formId;
	}

	private static class SenderSupplier implements Supplier<HttpResponse> {

		public SenderSupplier(DDMFormValues ddmFormValues) {
			_ddmFormValues = ddmFormValues;
			_locale = ddmFormValues.getDefaultLocale();
		}

		@Override
		public HttpResponse get() {
			Map<String, Object> formMap =
				_ddmFormValues.getDDMFormFieldValues()
					.stream()
					.collect(
						Collectors.toMap(
								DDMFormFieldValue::getName,
								ddmFormFieldValue -> ddmFormFieldValue.getValue().getString(_locale))
					);

			if (_log.isDebugEnabled()) {
				_log.info("Submitting request " + formMap);
			}

			return HttpRequest.post(_url).form(formMap).send();
		}

		private Locale _locale;
		private DDMFormValues _ddmFormValues;

	}

	private static final Log _log = LogFactoryUtil.getLog(
		HubspotStorageAdapter.class);

	@Reference
	private StorageAdapterRegistry _storageAdapterRegistry;

	private static String _url;

}