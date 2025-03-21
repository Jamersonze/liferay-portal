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

package com.liferay.commerce.inventory.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceInventoryReplenishmentItemLocalService}.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryReplenishmentItemLocalService
 * @generated
 */
public class CommerceInventoryReplenishmentItemLocalServiceWrapper
	implements CommerceInventoryReplenishmentItemLocalService,
			   ServiceWrapper<CommerceInventoryReplenishmentItemLocalService> {

	public CommerceInventoryReplenishmentItemLocalServiceWrapper() {
		this(null);
	}

	public CommerceInventoryReplenishmentItemLocalServiceWrapper(
		CommerceInventoryReplenishmentItemLocalService
			commerceInventoryReplenishmentItemLocalService) {

		_commerceInventoryReplenishmentItemLocalService =
			commerceInventoryReplenishmentItemLocalService;
	}

	/**
	 * Adds the commerce inventory replenishment item to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceInventoryReplenishmentItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceInventoryReplenishmentItem the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item that was added
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
			addCommerceInventoryReplenishmentItem(
				com.liferay.commerce.inventory.model.
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

		return _commerceInventoryReplenishmentItemLocalService.
			addCommerceInventoryReplenishmentItem(
				commerceInventoryReplenishmentItem);
	}

	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
				addCommerceInventoryReplenishmentItem(
					String externalReferenceCode, long userId,
					long commerceInventoryWarehouseId, String sku,
					java.util.Date availabilityDate, int quantity)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemLocalService.
			addCommerceInventoryReplenishmentItem(
				externalReferenceCode, userId, commerceInventoryWarehouseId,
				sku, availabilityDate, quantity);
	}

	/**
	 * Creates a new commerce inventory replenishment item with the primary key. Does not add the commerce inventory replenishment item to the database.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key for the new commerce inventory replenishment item
	 * @return the new commerce inventory replenishment item
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
			createCommerceInventoryReplenishmentItem(
				long commerceInventoryReplenishmentItemId) {

		return _commerceInventoryReplenishmentItemLocalService.
			createCommerceInventoryReplenishmentItem(
				commerceInventoryReplenishmentItemId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemLocalService.
			createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the commerce inventory replenishment item from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceInventoryReplenishmentItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceInventoryReplenishmentItem the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item that was removed
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
			deleteCommerceInventoryReplenishmentItem(
				com.liferay.commerce.inventory.model.
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

		return _commerceInventoryReplenishmentItemLocalService.
			deleteCommerceInventoryReplenishmentItem(
				commerceInventoryReplenishmentItem);
	}

	/**
	 * Deletes the commerce inventory replenishment item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceInventoryReplenishmentItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item that was removed
	 * @throws PortalException if a commerce inventory replenishment item with the primary key could not be found
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
				deleteCommerceInventoryReplenishmentItem(
					long commerceInventoryReplenishmentItemId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemLocalService.
			deleteCommerceInventoryReplenishmentItem(
				commerceInventoryReplenishmentItemId);
	}

	@Override
	public void deleteCommerceInventoryReplenishmentItems(
		long commerceInventoryWarehouseId) {

		_commerceInventoryReplenishmentItemLocalService.
			deleteCommerceInventoryReplenishmentItems(
				commerceInventoryWarehouseId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemLocalService.
			deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _commerceInventoryReplenishmentItemLocalService.dslQuery(
			dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _commerceInventoryReplenishmentItemLocalService.dslQueryCount(
			dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceInventoryReplenishmentItemLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _commerceInventoryReplenishmentItemLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryReplenishmentItemModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _commerceInventoryReplenishmentItemLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryReplenishmentItemModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _commerceInventoryReplenishmentItemLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _commerceInventoryReplenishmentItemLocalService.
			dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _commerceInventoryReplenishmentItemLocalService.
			dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
			fetchCommerceInventoryReplenishmentItem(
				long commerceInventoryReplenishmentItemId) {

		return _commerceInventoryReplenishmentItemLocalService.
			fetchCommerceInventoryReplenishmentItem(
				commerceInventoryReplenishmentItemId);
	}

	/**
	 * Returns the commerce inventory replenishment item with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the commerce inventory replenishment item's external reference code
	 * @return the matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
			fetchCommerceInventoryReplenishmentItemByExternalReferenceCode(
				long companyId, String externalReferenceCode) {

		return _commerceInventoryReplenishmentItemLocalService.
			fetchCommerceInventoryReplenishmentItemByExternalReferenceCode(
				companyId, externalReferenceCode);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #fetchCommerceInventoryReplenishmentItemByExternalReferenceCode(long, String)}
	 */
	@Deprecated
	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
			fetchCommerceInventoryReplenishmentItemByReferenceCode(
				long companyId, String externalReferenceCode) {

		return _commerceInventoryReplenishmentItemLocalService.
			fetchCommerceInventoryReplenishmentItemByReferenceCode(
				companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce inventory replenishment item with the matching UUID and company.
	 *
	 * @param uuid the commerce inventory replenishment item's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
			fetchCommerceInventoryReplenishmentItemByUuidAndCompanyId(
				String uuid, long companyId) {

		return _commerceInventoryReplenishmentItemLocalService.
			fetchCommerceInventoryReplenishmentItemByUuidAndCompanyId(
				uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceInventoryReplenishmentItemLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce inventory replenishment item with the primary key.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item
	 * @throws PortalException if a commerce inventory replenishment item with the primary key could not be found
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
				getCommerceInventoryReplenishmentItem(
					long commerceInventoryReplenishmentItemId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemLocalService.
			getCommerceInventoryReplenishmentItem(
				commerceInventoryReplenishmentItemId);
	}

	/**
	 * Returns the commerce inventory replenishment item with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the commerce inventory replenishment item's external reference code
	 * @return the matching commerce inventory replenishment item
	 * @throws PortalException if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
				getCommerceInventoryReplenishmentItemByExternalReferenceCode(
					long companyId, String externalReferenceCode)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemLocalService.
			getCommerceInventoryReplenishmentItemByExternalReferenceCode(
				companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce inventory replenishment item with the matching UUID and company.
	 *
	 * @param uuid the commerce inventory replenishment item's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce inventory replenishment item
	 * @throws PortalException if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
				getCommerceInventoryReplenishmentItemByUuidAndCompanyId(
					String uuid, long companyId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemLocalService.
			getCommerceInventoryReplenishmentItemByUuidAndCompanyId(
				uuid, companyId);
	}

	/**
	 * Returns a range of all the commerce inventory replenishment items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryReplenishmentItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @return the range of commerce inventory replenishment items
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.inventory.model.
			CommerceInventoryReplenishmentItem>
				getCommerceInventoryReplenishmentItems(int start, int end) {

		return _commerceInventoryReplenishmentItemLocalService.
			getCommerceInventoryReplenishmentItems(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.inventory.model.
			CommerceInventoryReplenishmentItem>
				getCommerceInventoryReplenishmentItemsByCommerceInventoryWarehouseId(
					long commerceInventoryWarehouseId, int start, int end) {

		return _commerceInventoryReplenishmentItemLocalService.
			getCommerceInventoryReplenishmentItemsByCommerceInventoryWarehouseId(
				commerceInventoryWarehouseId, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.inventory.model.
			CommerceInventoryReplenishmentItem>
				getCommerceInventoryReplenishmentItemsByCompanyIdAndSku(
					long companyId, String sku, int start, int end) {

		return _commerceInventoryReplenishmentItemLocalService.
			getCommerceInventoryReplenishmentItemsByCompanyIdAndSku(
				companyId, sku, start, end);
	}

	/**
	 * Returns the number of commerce inventory replenishment items.
	 *
	 * @return the number of commerce inventory replenishment items
	 */
	@Override
	public int getCommerceInventoryReplenishmentItemsCount() {
		return _commerceInventoryReplenishmentItemLocalService.
			getCommerceInventoryReplenishmentItemsCount();
	}

	@Override
	public long getCommerceInventoryReplenishmentItemsCount(
		long commerceInventoryWarehouseId, String sku) {

		return _commerceInventoryReplenishmentItemLocalService.
			getCommerceInventoryReplenishmentItemsCount(
				commerceInventoryWarehouseId, sku);
	}

	@Override
	public int
		getCommerceInventoryReplenishmentItemsCountByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId) {

		return _commerceInventoryReplenishmentItemLocalService.
			getCommerceInventoryReplenishmentItemsCountByCommerceInventoryWarehouseId(
				commerceInventoryWarehouseId);
	}

	@Override
	public int getCommerceInventoryReplenishmentItemsCountByCompanyIdAndSku(
		long companyId, String sku) {

		return _commerceInventoryReplenishmentItemLocalService.
			getCommerceInventoryReplenishmentItemsCountByCompanyIdAndSku(
				companyId, sku);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _commerceInventoryReplenishmentItemLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceInventoryReplenishmentItemLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceInventoryReplenishmentItemLocalService.
			getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemLocalService.
			getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the commerce inventory replenishment item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceInventoryReplenishmentItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceInventoryReplenishmentItem the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item that was updated
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
			updateCommerceInventoryReplenishmentItem(
				com.liferay.commerce.inventory.model.
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

		return _commerceInventoryReplenishmentItemLocalService.
			updateCommerceInventoryReplenishmentItem(
				commerceInventoryReplenishmentItem);
	}

	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
				updateCommerceInventoryReplenishmentItem(
					String externalReferenceCode,
					long commerceInventoryReplenishmentItemId,
					java.util.Date availabilityDate, int quantity,
					long mvccVersion)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemLocalService.
			updateCommerceInventoryReplenishmentItem(
				externalReferenceCode, commerceInventoryReplenishmentItemId,
				availabilityDate, quantity, mvccVersion);
	}

	@Override
	public CommerceInventoryReplenishmentItemLocalService getWrappedService() {
		return _commerceInventoryReplenishmentItemLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceInventoryReplenishmentItemLocalService
			commerceInventoryReplenishmentItemLocalService) {

		_commerceInventoryReplenishmentItemLocalService =
			commerceInventoryReplenishmentItemLocalService;
	}

	private CommerceInventoryReplenishmentItemLocalService
		_commerceInventoryReplenishmentItemLocalService;

}