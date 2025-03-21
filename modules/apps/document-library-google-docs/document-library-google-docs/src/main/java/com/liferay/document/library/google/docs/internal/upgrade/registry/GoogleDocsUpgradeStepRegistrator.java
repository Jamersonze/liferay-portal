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

package com.liferay.document.library.google.docs.internal.upgrade.registry;

import com.liferay.document.library.google.docs.internal.upgrade.v1_0_0.DLFileEntryTypeNameUpgradeProcess;
import com.liferay.document.library.google.docs.internal.upgrade.v1_0_0.PortletPreferencesUpgradeProcess;
import com.liferay.document.library.google.docs.internal.upgrade.v2_0_0.DLFileEntryTypeUpgradeProcess;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.PrefsProps;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class GoogleDocsUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"0.0.0", "1.0.0",
			new DLFileEntryTypeNameUpgradeProcess(
				_dlFileEntryTypeLocalService));

		registry.register(
			"1.0.0", "1.0.1",
			new PortletPreferencesUpgradeProcess(
				_configurationProvider, _prefsProps));

		registry.register(
			"1.0.1", "2.0.0",
			new DLFileEntryTypeUpgradeProcess(
				_classNameLocalService, _ddmStructureLocalService,
				_dlFileEntryTypeLocalService));
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private DLFileEntryTypeLocalService _dlFileEntryTypeLocalService;

	@Reference
	private PrefsProps _prefsProps;

}