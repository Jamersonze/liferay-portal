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

package com.liferay.journal.content.asset.addon.entry.comments.internal;

import com.liferay.journal.configuration.JournalServiceConfiguration;
import com.liferay.journal.content.asset.addon.entry.ContentMetadataAssetAddonEntry;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.servlet.taglib.ui.AssetAddonEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPAssetAddonEntry;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 */
@Component(
	configurationPid = "com.liferay.journal.configuration.JournalServiceConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	service = ContentMetadataAssetAddonEntry.class
)
public class CommentsContentMetadataAssetAddonEntry
	extends BaseJSPAssetAddonEntry implements ContentMetadataAssetAddonEntry {

	@Override
	public String getIcon() {
		return "comments";
	}

	@Override
	public String getJspPath() {
		return "/comments.jsp";
	}

	@Override
	public String getKey() {
		return "enableComments";
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "comments");
	}

	@Override
	public Double getWeight() {
		return 3.0;
	}

	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		httpServletRequest.setAttribute(WebKeys.ASSET_ADDON_ENTRY, this);

		super.include(httpServletRequest, httpServletResponse);
	}

	public boolean isCommentsRatingsSelected(
		HttpServletRequest httpServletRequest) {

		if (_commentRatingsContentMetadataAssetAddonEntry != null) {
			List<AssetAddonEntry> assetAddonEntries =
				(List<AssetAddonEntry>)httpServletRequest.getAttribute(
					WebKeys.ASSET_ADDON_ENTRIES);

			if ((assetAddonEntries != null) &&
				assetAddonEntries.contains(
					_commentRatingsContentMetadataAssetAddonEntry)) {

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isEnabled() {
		if (!_journalServiceConfiguration.articleCommentsEnabled()) {
			return false;
		}

		return super.isEnabled();
	}

	@Reference(unbind = "-")
	public void setCommentRatingsContentMetadataAssetAddonEntry(
		CommentRatingsContentMetadataAssetAddonEntry
			commentRatingsContentMetadataAssetAddonEntry) {

		_commentRatingsContentMetadataAssetAddonEntry =
			commentRatingsContentMetadataAssetAddonEntry;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.journal.content.asset.addon.entry.comments)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_journalServiceConfiguration = ConfigurableUtil.createConfigurable(
			JournalServiceConfiguration.class, properties);
	}

	private CommentRatingsContentMetadataAssetAddonEntry
		_commentRatingsContentMetadataAssetAddonEntry;
	private volatile JournalServiceConfiguration _journalServiceConfiguration;

	@Reference
	private Language _language;

}