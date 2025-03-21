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

package com.liferay.content.dashboard.web.internal.instance.lifecycle;

import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.model.AssetVocabularyConstants;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.asset.util.AssetVocabularySettingsHelper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(service = PortalInstanceLifecycleListener.class)
public class AddDefaultAssetVocabulariesPortalInstanceLifecycleListener
	extends BasePortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		_addAssetVocabulary(
			company, PropsValues.ASSET_VOCABULARY_DEFAULT,
			AssetVocabularyConstants.VISIBILITY_TYPE_PUBLIC, null);
		_addAssetVocabulary(
			company, "audience",
			AssetVocabularyConstants.VISIBILITY_TYPE_INTERNAL,
			JournalArticle.class, DLFileEntry.class);
		_addAssetVocabulary(
			company, "stage", AssetVocabularyConstants.VISIBILITY_TYPE_INTERNAL,
			JournalArticle.class, DLFileEntry.class);
	}

	private void _addAssetVocabulary(
			Company company, String name, int visibilityType,
			Class<?>... assetVocabularyTypeClasses)
		throws Exception {

		AssetVocabulary assetVocabulary =
			_assetVocabularyLocalService.fetchGroupVocabulary(
				company.getGroupId(),
				StringUtil.toLowerCase(GetterUtil.getString(name)));

		if (assetVocabulary != null) {
			return;
		}

		User defaultUser = company.getDefaultUser();

		Map<Locale, String> titleMap = new HashMap<>();

		for (Locale locale :
				_language.getCompanyAvailableLocales(company.getCompanyId())) {

			titleMap.put(locale, _language.get(locale, name));
		}

		AssetVocabularySettingsHelper assetVocabularySettingsHelper =
			new AssetVocabularySettingsHelper();

		if (assetVocabularyTypeClasses != null) {
			long[] classNameIds = new long[assetVocabularyTypeClasses.length];
			long[] classTypePKs = new long[assetVocabularyTypeClasses.length];
			boolean[] requireds =
				new boolean[assetVocabularyTypeClasses.length];

			for (int i = 0; i < assetVocabularyTypeClasses.length; i++) {
				classNameIds[i] = _portal.getClassNameId(
					assetVocabularyTypeClasses[i]);
				classTypePKs[i] = AssetCategoryConstants.ALL_CLASS_TYPE_PK;
				requireds[i] = false;
			}

			assetVocabularySettingsHelper.setClassNameIdsAndClassTypePKs(
				classNameIds, classTypePKs, requireds);
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		_assetVocabularyLocalService.addVocabulary(
			null, defaultUser.getUserId(), company.getGroupId(), name,
			StringPool.BLANK, titleMap, Collections.emptyMap(),
			assetVocabularySettingsHelper.toString(), visibilityType,
			serviceContext);
	}

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

}