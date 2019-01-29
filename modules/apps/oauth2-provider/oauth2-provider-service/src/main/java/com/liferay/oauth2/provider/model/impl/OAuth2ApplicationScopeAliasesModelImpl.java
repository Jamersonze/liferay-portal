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

package com.liferay.oauth2.provider.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.oauth2.provider.model.OAuth2ApplicationScopeAliases;
import com.liferay.oauth2.provider.model.OAuth2ApplicationScopeAliasesModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the OAuth2ApplicationScopeAliases service. Represents a row in the &quot;OAuth2ApplicationScopeAliases&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link OAuth2ApplicationScopeAliasesModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OAuth2ApplicationScopeAliasesImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuth2ApplicationScopeAliasesImpl
 * @see OAuth2ApplicationScopeAliases
 * @see OAuth2ApplicationScopeAliasesModel
 * @generated
 */
@ProviderType
public class OAuth2ApplicationScopeAliasesModelImpl extends BaseModelImpl<OAuth2ApplicationScopeAliases>
	implements OAuth2ApplicationScopeAliasesModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a o auth2 application scope aliases model instance should use the {@link OAuth2ApplicationScopeAliases} interface instead.
	 */
	public static final String TABLE_NAME = "OAuth2ApplicationScopeAliases";
	public static final Object[][] TABLE_COLUMNS = {
			{ "oA2AScopeAliasesId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "oAuth2ApplicationId", Types.BIGINT },
			{ "scopeAliases", Types.CLOB },
			{ "scopeAliasesHash", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("oA2AScopeAliasesId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("oAuth2ApplicationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("scopeAliases", Types.CLOB);
		TABLE_COLUMNS_MAP.put("scopeAliasesHash", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table OAuth2ApplicationScopeAliases (oA2AScopeAliasesId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,oAuth2ApplicationId LONG,scopeAliases TEXT null,scopeAliasesHash LONG)";
	public static final String TABLE_SQL_DROP = "drop table OAuth2ApplicationScopeAliases";
	public static final String ORDER_BY_JPQL = " ORDER BY oAuth2ApplicationScopeAliases.oAuth2ApplicationScopeAliasesId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY OAuth2ApplicationScopeAliases.oA2AScopeAliasesId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.oauth2.provider.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.oauth2.provider.model.OAuth2ApplicationScopeAliases"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.oauth2.provider.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.oauth2.provider.model.OAuth2ApplicationScopeAliases"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.oauth2.provider.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.oauth2.provider.model.OAuth2ApplicationScopeAliases"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long OAUTH2APPLICATIONID_COLUMN_BITMASK = 2L;
	public static final long SCOPEALIASESHASH_COLUMN_BITMASK = 4L;
	public static final long OAUTH2APPLICATIONSCOPEALIASESID_COLUMN_BITMASK = 8L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.oauth2.provider.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.oauth2.provider.model.OAuth2ApplicationScopeAliases"));

	public OAuth2ApplicationScopeAliasesModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _oAuth2ApplicationScopeAliasesId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOAuth2ApplicationScopeAliasesId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _oAuth2ApplicationScopeAliasesId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return OAuth2ApplicationScopeAliases.class;
	}

	@Override
	public String getModelClassName() {
		return OAuth2ApplicationScopeAliases.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<OAuth2ApplicationScopeAliases, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<OAuth2ApplicationScopeAliases, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<OAuth2ApplicationScopeAliases, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(attributeName,
				attributeGetterFunction.apply(
					(OAuth2ApplicationScopeAliases)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<OAuth2ApplicationScopeAliases, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<OAuth2ApplicationScopeAliases, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((OAuth2ApplicationScopeAliases)this,
					entry.getValue());
			}
		}
	}

	public Map<String, Function<OAuth2ApplicationScopeAliases, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<OAuth2ApplicationScopeAliases, Object>> getAttributeSetterBiConsumers() {
		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<OAuth2ApplicationScopeAliases, Object>> _attributeGetterFunctions;
	private static final Map<String, BiConsumer<OAuth2ApplicationScopeAliases, Object>> _attributeSetterBiConsumers;

	static {
		Map<String, Function<OAuth2ApplicationScopeAliases, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<OAuth2ApplicationScopeAliases, Object>>();
		Map<String, BiConsumer<OAuth2ApplicationScopeAliases, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<OAuth2ApplicationScopeAliases, ?>>();


		attributeGetterFunctions.put(
			"oAuth2ApplicationScopeAliasesId",
			new Function<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public Object apply(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases) {
					return oAuth2ApplicationScopeAliases.getOAuth2ApplicationScopeAliasesId();
				}

			});
		attributeSetterBiConsumers.put(
			"oAuth2ApplicationScopeAliasesId",
			new BiConsumer<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public void accept(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases, Object oAuth2ApplicationScopeAliasesId) {
					oAuth2ApplicationScopeAliases.setOAuth2ApplicationScopeAliasesId((Long)oAuth2ApplicationScopeAliasesId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public Object apply(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases) {
					return oAuth2ApplicationScopeAliases.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public void accept(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases, Object companyId) {
					oAuth2ApplicationScopeAliases.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public Object apply(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases) {
					return oAuth2ApplicationScopeAliases.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public void accept(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases, Object userId) {
					oAuth2ApplicationScopeAliases.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public Object apply(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases) {
					return oAuth2ApplicationScopeAliases.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public void accept(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases, Object userName) {
					oAuth2ApplicationScopeAliases.setUserName((String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public Object apply(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases) {
					return oAuth2ApplicationScopeAliases.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public void accept(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases, Object createDate) {
					oAuth2ApplicationScopeAliases.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"oAuth2ApplicationId",
			new Function<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public Object apply(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases) {
					return oAuth2ApplicationScopeAliases.getOAuth2ApplicationId();
				}

			});
		attributeSetterBiConsumers.put(
			"oAuth2ApplicationId",
			new BiConsumer<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public void accept(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases, Object oAuth2ApplicationId) {
					oAuth2ApplicationScopeAliases.setOAuth2ApplicationId((Long)oAuth2ApplicationId);
				}

			});
		attributeGetterFunctions.put(
			"scopeAliases",
			new Function<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public Object apply(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases) {
					return oAuth2ApplicationScopeAliases.getScopeAliases();
				}

			});
		attributeSetterBiConsumers.put(
			"scopeAliases",
			new BiConsumer<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public void accept(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases, Object scopeAliases) {
					oAuth2ApplicationScopeAliases.setScopeAliases((String)scopeAliases);
				}

			});
		attributeGetterFunctions.put(
			"scopeAliasesHash",
			new Function<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public Object apply(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases) {
					return oAuth2ApplicationScopeAliases.getScopeAliasesHash();
				}

			});
		attributeSetterBiConsumers.put(
			"scopeAliasesHash",
			new BiConsumer<OAuth2ApplicationScopeAliases, Object>() {

				@Override
				public void accept(OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases, Object scopeAliasesHash) {
					oAuth2ApplicationScopeAliases.setScopeAliasesHash((Long)scopeAliasesHash);
				}

			});


		_attributeGetterFunctions = Collections.unmodifiableMap(attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap((Map)attributeSetterBiConsumers);
	}

	@Override
	public long getOAuth2ApplicationScopeAliasesId() {
		return _oAuth2ApplicationScopeAliasesId;
	}

	@Override
	public void setOAuth2ApplicationScopeAliasesId(
		long oAuth2ApplicationScopeAliasesId) {
		_oAuth2ApplicationScopeAliasesId = oAuth2ApplicationScopeAliasesId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public long getOAuth2ApplicationId() {
		return _oAuth2ApplicationId;
	}

	@Override
	public void setOAuth2ApplicationId(long oAuth2ApplicationId) {
		_columnBitmask |= OAUTH2APPLICATIONID_COLUMN_BITMASK;

		if (!_setOriginalOAuth2ApplicationId) {
			_setOriginalOAuth2ApplicationId = true;

			_originalOAuth2ApplicationId = _oAuth2ApplicationId;
		}

		_oAuth2ApplicationId = oAuth2ApplicationId;
	}

	public long getOriginalOAuth2ApplicationId() {
		return _originalOAuth2ApplicationId;
	}

	@Override
	public String getScopeAliases() {
		if (_scopeAliases == null) {
			return "";
		}
		else {
			return _scopeAliases;
		}
	}

	@Override
	public void setScopeAliases(String scopeAliases) {
		_scopeAliases = scopeAliases;
	}

	@Override
	public long getScopeAliasesHash() {
		return _scopeAliasesHash;
	}

	@Override
	public void setScopeAliasesHash(long scopeAliasesHash) {
		_columnBitmask |= SCOPEALIASESHASH_COLUMN_BITMASK;

		if (!_setOriginalScopeAliasesHash) {
			_setOriginalScopeAliasesHash = true;

			_originalScopeAliasesHash = _scopeAliasesHash;
		}

		_scopeAliasesHash = scopeAliasesHash;
	}

	public long getOriginalScopeAliasesHash() {
		return _originalScopeAliasesHash;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			OAuth2ApplicationScopeAliases.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public OAuth2ApplicationScopeAliases toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (OAuth2ApplicationScopeAliases)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		OAuth2ApplicationScopeAliasesImpl oAuth2ApplicationScopeAliasesImpl = new OAuth2ApplicationScopeAliasesImpl();

		oAuth2ApplicationScopeAliasesImpl.setOAuth2ApplicationScopeAliasesId(getOAuth2ApplicationScopeAliasesId());
		oAuth2ApplicationScopeAliasesImpl.setCompanyId(getCompanyId());
		oAuth2ApplicationScopeAliasesImpl.setUserId(getUserId());
		oAuth2ApplicationScopeAliasesImpl.setUserName(getUserName());
		oAuth2ApplicationScopeAliasesImpl.setCreateDate(getCreateDate());
		oAuth2ApplicationScopeAliasesImpl.setOAuth2ApplicationId(getOAuth2ApplicationId());
		oAuth2ApplicationScopeAliasesImpl.setScopeAliases(getScopeAliases());
		oAuth2ApplicationScopeAliasesImpl.setScopeAliasesHash(getScopeAliasesHash());

		oAuth2ApplicationScopeAliasesImpl.resetOriginalValues();

		return oAuth2ApplicationScopeAliasesImpl;
	}

	@Override
	public int compareTo(
		OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases) {
		long primaryKey = oAuth2ApplicationScopeAliases.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OAuth2ApplicationScopeAliases)) {
			return false;
		}

		OAuth2ApplicationScopeAliases oAuth2ApplicationScopeAliases = (OAuth2ApplicationScopeAliases)obj;

		long primaryKey = oAuth2ApplicationScopeAliases.getPrimaryKey();

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
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		OAuth2ApplicationScopeAliasesModelImpl oAuth2ApplicationScopeAliasesModelImpl =
			this;

		oAuth2ApplicationScopeAliasesModelImpl._originalCompanyId = oAuth2ApplicationScopeAliasesModelImpl._companyId;

		oAuth2ApplicationScopeAliasesModelImpl._setOriginalCompanyId = false;

		oAuth2ApplicationScopeAliasesModelImpl._originalOAuth2ApplicationId = oAuth2ApplicationScopeAliasesModelImpl._oAuth2ApplicationId;

		oAuth2ApplicationScopeAliasesModelImpl._setOriginalOAuth2ApplicationId = false;

		oAuth2ApplicationScopeAliasesModelImpl._originalScopeAliasesHash = oAuth2ApplicationScopeAliasesModelImpl._scopeAliasesHash;

		oAuth2ApplicationScopeAliasesModelImpl._setOriginalScopeAliasesHash = false;

		oAuth2ApplicationScopeAliasesModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<OAuth2ApplicationScopeAliases> toCacheModel() {
		OAuth2ApplicationScopeAliasesCacheModel oAuth2ApplicationScopeAliasesCacheModel =
			new OAuth2ApplicationScopeAliasesCacheModel();

		oAuth2ApplicationScopeAliasesCacheModel.oAuth2ApplicationScopeAliasesId = getOAuth2ApplicationScopeAliasesId();

		oAuth2ApplicationScopeAliasesCacheModel.companyId = getCompanyId();

		oAuth2ApplicationScopeAliasesCacheModel.userId = getUserId();

		oAuth2ApplicationScopeAliasesCacheModel.userName = getUserName();

		String userName = oAuth2ApplicationScopeAliasesCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			oAuth2ApplicationScopeAliasesCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			oAuth2ApplicationScopeAliasesCacheModel.createDate = createDate.getTime();
		}
		else {
			oAuth2ApplicationScopeAliasesCacheModel.createDate = Long.MIN_VALUE;
		}

		oAuth2ApplicationScopeAliasesCacheModel.oAuth2ApplicationId = getOAuth2ApplicationId();

		oAuth2ApplicationScopeAliasesCacheModel.scopeAliases = getScopeAliases();

		String scopeAliases = oAuth2ApplicationScopeAliasesCacheModel.scopeAliases;

		if ((scopeAliases != null) && (scopeAliases.length() == 0)) {
			oAuth2ApplicationScopeAliasesCacheModel.scopeAliases = null;
		}

		oAuth2ApplicationScopeAliasesCacheModel.scopeAliasesHash = getScopeAliasesHash();

		return oAuth2ApplicationScopeAliasesCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<OAuth2ApplicationScopeAliases, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler((4 * attributeGetterFunctions.size()) +
				2);

		sb.append("{");

		for (Map.Entry<String, Function<OAuth2ApplicationScopeAliases, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<OAuth2ApplicationScopeAliases, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply(
					(OAuth2ApplicationScopeAliases)this));
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
		Map<String, Function<OAuth2ApplicationScopeAliases, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler((5 * attributeGetterFunctions.size()) +
				4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<OAuth2ApplicationScopeAliases, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<OAuth2ApplicationScopeAliases, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply(
					(OAuth2ApplicationScopeAliases)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = OAuth2ApplicationScopeAliases.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			OAuth2ApplicationScopeAliases.class, ModelWrapper.class
		};
	private long _oAuth2ApplicationScopeAliasesId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _oAuth2ApplicationId;
	private long _originalOAuth2ApplicationId;
	private boolean _setOriginalOAuth2ApplicationId;
	private String _scopeAliases;
	private long _scopeAliasesHash;
	private long _originalScopeAliasesHash;
	private boolean _setOriginalScopeAliasesHash;
	private long _columnBitmask;
	private OAuth2ApplicationScopeAliases _escapedModel;
}