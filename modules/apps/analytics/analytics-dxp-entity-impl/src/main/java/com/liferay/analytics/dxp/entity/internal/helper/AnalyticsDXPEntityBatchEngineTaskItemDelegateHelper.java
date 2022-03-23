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

package com.liferay.analytics.dxp.entity.internal.helper;

import com.liferay.analytics.dxp.entity.rest.converter.DXPEntityDTOConverter;
import com.liferay.analytics.dxp.entity.rest.dto.v1_0.DXPEntity;
import com.liferay.analytics.dxp.entity.retriever.AnalyticsDXPEntityRetriever;
import com.liferay.analytics.dxp.entity.retriever.AnalyticsDXPEntityRetrieverTracker;
import com.liferay.batch.engine.pagination.Page;
import com.liferay.batch.engine.pagination.Pagination;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;

import java.io.Serializable;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcos Martins
 */
@Component(
	immediate = true,
	service = AnalyticsDXPEntityBatchEngineTaskItemDelegateHelper.class
)
public class AnalyticsDXPEntityBatchEngineTaskItemDelegateHelper {

	public Page<DXPEntity> getDXPEntities(
			String entryClassName, long companyId, Filter filter,
			Pagination pagination, Sort[] sorts,
			Map<String, Serializable> parameters, String search)
		throws Exception {

		AnalyticsDXPEntityRetriever analyticsDXPEntityRetriever =
			_analyticsDXPEntityRetrieverTracker.getAnalyticsDXPEntityRetriever(
				entryClassName);

		com.liferay.portal.vulcan.pagination.Pagination vulcanPagination =
			com.liferay.portal.vulcan.pagination.Pagination.of(
				pagination.getPage(), pagination.getPageSize());

		com.liferay.portal.vulcan.pagination.Page<DXPEntity> dxpEntitiesPage =
			analyticsDXPEntityRetriever.getDXPEntitiesPage(
				companyId, filter, vulcanPagination,
				baseModel -> _dxpEntityDTOConverter.toDTO(baseModel));

		return Page.of(
			dxpEntitiesPage.getItems(),
			Pagination.of(pagination.getPage(), (int)pagination.getPageSize()),
			dxpEntitiesPage.getTotalCount());
	}

	@Reference
	private AnalyticsDXPEntityRetrieverTracker
		_analyticsDXPEntityRetrieverTracker;

	@Reference
	private DXPEntityDTOConverter _dxpEntityDTOConverter;

}