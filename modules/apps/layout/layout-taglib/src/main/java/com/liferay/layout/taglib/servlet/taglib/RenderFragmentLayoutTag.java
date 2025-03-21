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

package com.liferay.layout.taglib.servlet.taglib;

import com.liferay.fragment.constants.FragmentEntryLinkConstants;
import com.liferay.layout.constants.LayoutWebKeys;
import com.liferay.layout.page.template.util.LayoutStructureUtil;
import com.liferay.layout.taglib.internal.servlet.ServletContextUtil;
import com.liferay.layout.taglib.internal.util.SegmentsExperienceUtil;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Víctor Galán
 */
public class RenderFragmentLayoutTag extends IncludeTag {

	public String getMainItemId() {
		return _mainItemId;
	}

	public String getMode() {
		return _mode;
	}

	public boolean getShowPreview() {
		return _showPreview;
	}

	public void setMainItemId(String mainItemId) {
		_mainItemId = mainItemId;
	}

	public void setMode(String mode) {
		_mode = mode;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		setServletContext(ServletContextUtil.getServletContext());
	}

	public void setShowPreview(boolean showPreview) {
		_showPreview = showPreview;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_layoutStructure = null;
		_mainItemId = null;
		_mode = FragmentEntryLinkConstants.VIEW;
		_showPreview = false;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		super.setAttributes(httpServletRequest);

		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:layoutStructure",
			_getLayoutStructure(httpServletRequest));
		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:mainItemId", _mainItemId);
		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:mode", _mode);
		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:showPreview", _showPreview);
	}

	private LayoutStructure _getLayoutStructure(
		HttpServletRequest httpServletRequest) {

		if (_layoutStructure != null) {
			return _layoutStructure;
		}

		_layoutStructure = (LayoutStructure)httpServletRequest.getAttribute(
			LayoutWebKeys.LAYOUT_STRUCTURE);

		if (_layoutStructure != null) {
			return _layoutStructure;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		_layoutStructure = LayoutStructureUtil.getLayoutStructure(
			themeDisplay.getPlid(),
			SegmentsExperienceUtil.getSegmentsExperienceId(httpServletRequest));

		return _layoutStructure;
	}

	private static final String _PAGE = "/render_fragment_layout/page.jsp";

	private LayoutStructure _layoutStructure;
	private String _mainItemId;
	private String _mode = FragmentEntryLinkConstants.VIEW;
	private boolean _showPreview;

}