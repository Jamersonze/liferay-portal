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

package com.liferay.commerce.product.content.web.internal.product.compare.mini.frontend.taglib.form.navigator;

import com.liferay.commerce.product.content.web.internal.constants.CPCompareContentMiniConstants;
import com.liferay.frontend.taglib.form.navigator.FormNavigatorCategory;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	enabled = false, property = "form.navigator.category.order:Integer=100",
	service = FormNavigatorCategory.class
)
public class RenderSelectionFormNavigatorCategory
	implements FormNavigatorCategory {

	@Override
	public String getFormNavigatorId() {
		return CPCompareContentMiniConstants.FORM_NAVIGATOR_ID_CONFIGURATION;
	}

	@Override
	public String getKey() {
		return CPCompareContentMiniConstants.CATEGORY_KEY_RENDER_SELECTION;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return _language.get(resourceBundle, "render-selection");
	}

	@Reference
	private Language _language;

}