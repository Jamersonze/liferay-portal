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

package com.liferay.portal.vulcan.internal.extension;

import com.liferay.petra.lang.CentralizedThreadLocal;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Javier de Arcos
 */
public class EntityExtensionThreadLocal {

	public static Map<String, Serializable> getExtendedProperties() {
		return _entityExtensionThreadLocal.get();
	}

	public static void setExtendedProperties(
		Map<String, Serializable> extendedProperties) {

		_entityExtensionThreadLocal.set(extendedProperties);
	}

	private static final ThreadLocal<Map<String, Serializable>>
		_entityExtensionThreadLocal = new CentralizedThreadLocal<>(
			EntityExtensionThreadLocal.class + "._entityExtensionThreadLocal");

}