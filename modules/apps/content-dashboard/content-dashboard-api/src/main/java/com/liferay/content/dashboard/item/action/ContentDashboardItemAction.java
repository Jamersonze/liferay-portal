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

package com.liferay.content.dashboard.item.action;

import java.util.Locale;

/**
 * @author David Arques
 */
public interface ContentDashboardItemAction {

	public String getIcon();

	public String getLabel(Locale locale);

	public String getName();

	public Type getType();

	public String getURL();

	public String getURL(Locale locale);

	public enum Type {

		DELETE, DOWNLOAD, EDIT, PREVIEW, PREVIEW_IMAGE, SHARING_BUTTON,
		SHARING_COLLABORATORS, VIEW, VIEW_IN_PANEL

	}

}