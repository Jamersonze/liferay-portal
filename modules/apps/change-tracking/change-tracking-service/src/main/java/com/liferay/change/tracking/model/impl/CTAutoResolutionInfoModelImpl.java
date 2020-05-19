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

package com.liferay.change.tracking.model.impl;

import com.liferay.change.tracking.model.CTAutoResolutionInfo;
import com.liferay.change.tracking.model.CTAutoResolutionInfoModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CTAutoResolutionInfo service. Represents a row in the &quot;CTAutoResolutionInfo&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CTAutoResolutionInfoModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CTAutoResolutionInfoImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTAutoResolutionInfoImpl
 * @generated
 */
public class CTAutoResolutionInfoModelImpl
	extends BaseModelImpl<CTAutoResolutionInfo>
	implements CTAutoResolutionInfoModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ct auto resolution info model instance should use the <code>CTAutoResolutionInfo</code> interface instead.
	 */
	public static final String TABLE_NAME = "CTAutoResolutionInfo";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctAutoResolutionInfoId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"ctCollectionId", Types.BIGINT}, {"modelClassNameId", Types.BIGINT},
		{"sourceModelClassPK", Types.BIGINT},
		{"targetModelClassPK", Types.BIGINT},
		{"conflictIdentifier", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctAutoResolutionInfoId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modelClassNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sourceModelClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("targetModelClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("conflictIdentifier", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CTAutoResolutionInfo (mvccVersion LONG default 0 not null,ctAutoResolutionInfoId LONG not null primary key,companyId LONG,createDate DATE null,ctCollectionId LONG,modelClassNameId LONG,sourceModelClassPK LONG,targetModelClassPK LONG,conflictIdentifier VARCHAR(500) null)";

	public static final String TABLE_SQL_DROP =
		"drop table CTAutoResolutionInfo";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ctAutoResolutionInfo.createDate ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CTAutoResolutionInfo.createDate ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CTCOLLECTIONID_COLUMN_BITMASK = 1L;

	public static final long MODELCLASSNAMEID_COLUMN_BITMASK = 2L;

	public static final long SOURCEMODELCLASSPK_COLUMN_BITMASK = 4L;

	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public CTAutoResolutionInfoModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _ctAutoResolutionInfoId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCtAutoResolutionInfoId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ctAutoResolutionInfoId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CTAutoResolutionInfo.class;
	}

	@Override
	public String getModelClassName() {
		return CTAutoResolutionInfo.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CTAutoResolutionInfo, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CTAutoResolutionInfo, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTAutoResolutionInfo, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CTAutoResolutionInfo)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CTAutoResolutionInfo, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CTAutoResolutionInfo, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CTAutoResolutionInfo)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CTAutoResolutionInfo, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CTAutoResolutionInfo, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CTAutoResolutionInfo>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CTAutoResolutionInfo.class.getClassLoader(),
			CTAutoResolutionInfo.class, ModelWrapper.class);

		try {
			Constructor<CTAutoResolutionInfo> constructor =
				(Constructor<CTAutoResolutionInfo>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<CTAutoResolutionInfo, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CTAutoResolutionInfo, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CTAutoResolutionInfo, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CTAutoResolutionInfo, Object>>();
		Map<String, BiConsumer<CTAutoResolutionInfo, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CTAutoResolutionInfo, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", CTAutoResolutionInfo::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CTAutoResolutionInfo, Long>)
				CTAutoResolutionInfo::setMvccVersion);
		attributeGetterFunctions.put(
			"ctAutoResolutionInfoId",
			CTAutoResolutionInfo::getCtAutoResolutionInfoId);
		attributeSetterBiConsumers.put(
			"ctAutoResolutionInfoId",
			(BiConsumer<CTAutoResolutionInfo, Long>)
				CTAutoResolutionInfo::setCtAutoResolutionInfoId);
		attributeGetterFunctions.put(
			"companyId", CTAutoResolutionInfo::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CTAutoResolutionInfo, Long>)
				CTAutoResolutionInfo::setCompanyId);
		attributeGetterFunctions.put(
			"createDate", CTAutoResolutionInfo::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CTAutoResolutionInfo, Date>)
				CTAutoResolutionInfo::setCreateDate);
		attributeGetterFunctions.put(
			"ctCollectionId", CTAutoResolutionInfo::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<CTAutoResolutionInfo, Long>)
				CTAutoResolutionInfo::setCtCollectionId);
		attributeGetterFunctions.put(
			"modelClassNameId", CTAutoResolutionInfo::getModelClassNameId);
		attributeSetterBiConsumers.put(
			"modelClassNameId",
			(BiConsumer<CTAutoResolutionInfo, Long>)
				CTAutoResolutionInfo::setModelClassNameId);
		attributeGetterFunctions.put(
			"sourceModelClassPK", CTAutoResolutionInfo::getSourceModelClassPK);
		attributeSetterBiConsumers.put(
			"sourceModelClassPK",
			(BiConsumer<CTAutoResolutionInfo, Long>)
				CTAutoResolutionInfo::setSourceModelClassPK);
		attributeGetterFunctions.put(
			"targetModelClassPK", CTAutoResolutionInfo::getTargetModelClassPK);
		attributeSetterBiConsumers.put(
			"targetModelClassPK",
			(BiConsumer<CTAutoResolutionInfo, Long>)
				CTAutoResolutionInfo::setTargetModelClassPK);
		attributeGetterFunctions.put(
			"conflictIdentifier", CTAutoResolutionInfo::getConflictIdentifier);
		attributeSetterBiConsumers.put(
			"conflictIdentifier",
			(BiConsumer<CTAutoResolutionInfo, String>)
				CTAutoResolutionInfo::setConflictIdentifier);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@Override
	public long getCtAutoResolutionInfoId() {
		return _ctAutoResolutionInfoId;
	}

	@Override
	public void setCtAutoResolutionInfoId(long ctAutoResolutionInfoId) {
		_ctAutoResolutionInfoId = ctAutoResolutionInfoId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@Override
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		_columnBitmask |= CTCOLLECTIONID_COLUMN_BITMASK;

		if (!_setOriginalCtCollectionId) {
			_setOriginalCtCollectionId = true;

			_originalCtCollectionId = _ctCollectionId;
		}

		_ctCollectionId = ctCollectionId;
	}

	public long getOriginalCtCollectionId() {
		return _originalCtCollectionId;
	}

	@Override
	public long getModelClassNameId() {
		return _modelClassNameId;
	}

	@Override
	public void setModelClassNameId(long modelClassNameId) {
		_columnBitmask |= MODELCLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalModelClassNameId) {
			_setOriginalModelClassNameId = true;

			_originalModelClassNameId = _modelClassNameId;
		}

		_modelClassNameId = modelClassNameId;
	}

	public long getOriginalModelClassNameId() {
		return _originalModelClassNameId;
	}

	@Override
	public long getSourceModelClassPK() {
		return _sourceModelClassPK;
	}

	@Override
	public void setSourceModelClassPK(long sourceModelClassPK) {
		_columnBitmask |= SOURCEMODELCLASSPK_COLUMN_BITMASK;

		if (!_setOriginalSourceModelClassPK) {
			_setOriginalSourceModelClassPK = true;

			_originalSourceModelClassPK = _sourceModelClassPK;
		}

		_sourceModelClassPK = sourceModelClassPK;
	}

	public long getOriginalSourceModelClassPK() {
		return _originalSourceModelClassPK;
	}

	@Override
	public long getTargetModelClassPK() {
		return _targetModelClassPK;
	}

	@Override
	public void setTargetModelClassPK(long targetModelClassPK) {
		_targetModelClassPK = targetModelClassPK;
	}

	@Override
	public String getConflictIdentifier() {
		if (_conflictIdentifier == null) {
			return "";
		}
		else {
			return _conflictIdentifier;
		}
	}

	@Override
	public void setConflictIdentifier(String conflictIdentifier) {
		_conflictIdentifier = conflictIdentifier;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CTAutoResolutionInfo.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CTAutoResolutionInfo toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CTAutoResolutionInfo>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CTAutoResolutionInfoImpl ctAutoResolutionInfoImpl =
			new CTAutoResolutionInfoImpl();

		ctAutoResolutionInfoImpl.setMvccVersion(getMvccVersion());
		ctAutoResolutionInfoImpl.setCtAutoResolutionInfoId(
			getCtAutoResolutionInfoId());
		ctAutoResolutionInfoImpl.setCompanyId(getCompanyId());
		ctAutoResolutionInfoImpl.setCreateDate(getCreateDate());
		ctAutoResolutionInfoImpl.setCtCollectionId(getCtCollectionId());
		ctAutoResolutionInfoImpl.setModelClassNameId(getModelClassNameId());
		ctAutoResolutionInfoImpl.setSourceModelClassPK(getSourceModelClassPK());
		ctAutoResolutionInfoImpl.setTargetModelClassPK(getTargetModelClassPK());
		ctAutoResolutionInfoImpl.setConflictIdentifier(getConflictIdentifier());

		ctAutoResolutionInfoImpl.resetOriginalValues();

		return ctAutoResolutionInfoImpl;
	}

	@Override
	public int compareTo(CTAutoResolutionInfo ctAutoResolutionInfo) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), ctAutoResolutionInfo.getCreateDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CTAutoResolutionInfo)) {
			return false;
		}

		CTAutoResolutionInfo ctAutoResolutionInfo = (CTAutoResolutionInfo)obj;

		long primaryKey = ctAutoResolutionInfo.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		CTAutoResolutionInfoModelImpl ctAutoResolutionInfoModelImpl = this;

		ctAutoResolutionInfoModelImpl._originalCtCollectionId =
			ctAutoResolutionInfoModelImpl._ctCollectionId;

		ctAutoResolutionInfoModelImpl._setOriginalCtCollectionId = false;

		ctAutoResolutionInfoModelImpl._originalModelClassNameId =
			ctAutoResolutionInfoModelImpl._modelClassNameId;

		ctAutoResolutionInfoModelImpl._setOriginalModelClassNameId = false;

		ctAutoResolutionInfoModelImpl._originalSourceModelClassPK =
			ctAutoResolutionInfoModelImpl._sourceModelClassPK;

		ctAutoResolutionInfoModelImpl._setOriginalSourceModelClassPK = false;

		ctAutoResolutionInfoModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CTAutoResolutionInfo> toCacheModel() {
		CTAutoResolutionInfoCacheModel ctAutoResolutionInfoCacheModel =
			new CTAutoResolutionInfoCacheModel();

		ctAutoResolutionInfoCacheModel.mvccVersion = getMvccVersion();

		ctAutoResolutionInfoCacheModel.ctAutoResolutionInfoId =
			getCtAutoResolutionInfoId();

		ctAutoResolutionInfoCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			ctAutoResolutionInfoCacheModel.createDate = createDate.getTime();
		}
		else {
			ctAutoResolutionInfoCacheModel.createDate = Long.MIN_VALUE;
		}

		ctAutoResolutionInfoCacheModel.ctCollectionId = getCtCollectionId();

		ctAutoResolutionInfoCacheModel.modelClassNameId = getModelClassNameId();

		ctAutoResolutionInfoCacheModel.sourceModelClassPK =
			getSourceModelClassPK();

		ctAutoResolutionInfoCacheModel.targetModelClassPK =
			getTargetModelClassPK();

		ctAutoResolutionInfoCacheModel.conflictIdentifier =
			getConflictIdentifier();

		String conflictIdentifier =
			ctAutoResolutionInfoCacheModel.conflictIdentifier;

		if ((conflictIdentifier != null) &&
			(conflictIdentifier.length() == 0)) {

			ctAutoResolutionInfoCacheModel.conflictIdentifier = null;
		}

		return ctAutoResolutionInfoCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CTAutoResolutionInfo, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CTAutoResolutionInfo, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTAutoResolutionInfo, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((CTAutoResolutionInfo)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<CTAutoResolutionInfo, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CTAutoResolutionInfo, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTAutoResolutionInfo, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((CTAutoResolutionInfo)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CTAutoResolutionInfo>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private long _ctAutoResolutionInfoId;
	private long _companyId;
	private Date _createDate;
	private long _ctCollectionId;
	private long _originalCtCollectionId;
	private boolean _setOriginalCtCollectionId;
	private long _modelClassNameId;
	private long _originalModelClassNameId;
	private boolean _setOriginalModelClassNameId;
	private long _sourceModelClassPK;
	private long _originalSourceModelClassPK;
	private boolean _setOriginalSourceModelClassPK;
	private long _targetModelClassPK;
	private String _conflictIdentifier;
	private long _columnBitmask;
	private CTAutoResolutionInfo _escapedModel;

}