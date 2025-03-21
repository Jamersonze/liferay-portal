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

package com.liferay.contacts.internal.upgrade.registry;

import com.liferay.contacts.internal.upgrade.v2_0_0.EntryUpgradeProcess;
import com.liferay.contacts.internal.upgrade.v2_0_2.EmailAddressUpgradeProcess;
import com.liferay.contacts.internal.upgrade.v3_0_0.util.EntryTable;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.upgrade.BaseSQLServerDatetimeUpgradeProcess;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan Lee
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class ContactsServiceUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.1", "1.0.0", new DummyUpgradeStep());

		// See LPS-65946

		registry.register(
			"1.0.0", "2.0.1", new EntryUpgradeProcess(_userLocalService));

		registry.register("2.0.0", "2.0.1", new DummyUpgradeStep());

		registry.register("2.0.1", "2.0.2", new EmailAddressUpgradeProcess());

		registry.register(
			"2.0.2", "3.0.0",
			new BaseSQLServerDatetimeUpgradeProcess(
				new Class<?>[] {EntryTable.class}));
	}

	@Reference
	private UserLocalService _userLocalService;

}