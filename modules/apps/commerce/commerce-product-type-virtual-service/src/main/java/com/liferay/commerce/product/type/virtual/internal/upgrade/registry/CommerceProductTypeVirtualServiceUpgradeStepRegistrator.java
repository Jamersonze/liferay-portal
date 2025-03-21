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

package com.liferay.commerce.product.type.virtual.internal.upgrade.registry;

import com.liferay.commerce.product.type.virtual.internal.upgrade.v1_1_0.CPDefinitionVirtualSettingUpgradeProcess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.MVCCVersionUpgradeProcess;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	enabled = false, immediate = true, service = UpgradeStepRegistrator.class
)
public class CommerceProductTypeVirtualServiceUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		if (_log.isInfoEnabled()) {
			_log.info(
				"Commerce product type virtual upgrade step registrator " +
					"STARTED");
		}

		registry.register(
			"1.0.0", "1.1.0", new CPDefinitionVirtualSettingUpgradeProcess());

		registry.register(
			"1.1.0", "1.1.1",
			new com.liferay.commerce.product.type.virtual.internal.upgrade.
				v1_1_1.CPDefinitionVirtualSettingUpgradeProcess());

		registry.register(
			"1.1.1", "1.2.0",
			new MVCCVersionUpgradeProcess() {

				@Override
				protected String[] getModuleTableNames() {
					return new String[] {"CPDefinitionVirtualSetting"};
				}

			});

		if (_log.isInfoEnabled()) {
			_log.info(
				"Commerce product type virtual upgrade step registrator " +
					"FINISHED");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceProductTypeVirtualServiceUpgradeStepRegistrator.class);

}