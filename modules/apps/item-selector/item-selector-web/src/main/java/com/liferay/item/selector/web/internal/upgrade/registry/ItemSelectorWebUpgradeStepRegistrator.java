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

package com.liferay.item.selector.web.internal.upgrade.registry;

import com.liferay.item.selector.constants.ItemSelectorPortletKeys;
import com.liferay.portal.kernel.upgrade.BasePortletIdUpgradeProcess;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jose A. Jimenez
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class ItemSelectorWebUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.0.0", new DummyUpgradeStep());

		UpgradeStep upgradePortletId = new BasePortletIdUpgradeProcess() {

			@Override
			protected String[][] getRenamePortletIdsArray() {
				return new String[][] {
					{"200", ItemSelectorPortletKeys.ITEM_SELECTOR}
				};
			}

		};

		registry.register("0.0.1", "1.0.0", upgradePortletId);
	}

}