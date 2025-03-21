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

package com.liferay.notification.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.notification.model.NotificationQueueEntry;
import com.liferay.notification.model.NotificationQueueEntryModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the NotificationQueueEntry service. Represents a row in the &quot;NotificationQueueEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>NotificationQueueEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NotificationQueueEntryImpl}.
 * </p>
 *
 * @author Gabriel Albuquerque
 * @see NotificationQueueEntryImpl
 * @generated
 */
@JSON(strict = true)
public class NotificationQueueEntryModelImpl
	extends BaseModelImpl<NotificationQueueEntry>
	implements NotificationQueueEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a notification queue entry model instance should use the <code>NotificationQueueEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "NotificationQueueEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"notificationQueueEntryId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"notificationTemplateId", Types.BIGINT}, {"bcc", Types.VARCHAR},
		{"body", Types.CLOB}, {"cc", Types.VARCHAR},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"from_", Types.VARCHAR}, {"fromName", Types.VARCHAR},
		{"priority", Types.DOUBLE}, {"sent", Types.BOOLEAN},
		{"sentDate", Types.TIMESTAMP}, {"subject", Types.VARCHAR},
		{"to_", Types.VARCHAR}, {"toName", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("notificationQueueEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("notificationTemplateId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("bcc", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("body", Types.CLOB);
		TABLE_COLUMNS_MAP.put("cc", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("from_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fromName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("priority", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("sent", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("sentDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("subject", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("to_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("toName", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table NotificationQueueEntry (mvccVersion LONG default 0 not null,notificationQueueEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,notificationTemplateId LONG,bcc VARCHAR(75) null,body TEXT null,cc VARCHAR(75) null,classNameId LONG,classPK LONG,from_ VARCHAR(75) null,fromName VARCHAR(75) null,priority DOUBLE,sent BOOLEAN,sentDate DATE null,subject VARCHAR(75) null,to_ VARCHAR(75) null,toName VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table NotificationQueueEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY notificationQueueEntry.notificationQueueEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY NotificationQueueEntry.notificationQueueEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NOTIFICATIONTEMPLATEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SENT_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SENTDATE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NOTIFICATIONQUEUEENTRYID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public NotificationQueueEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _notificationQueueEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setNotificationQueueEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _notificationQueueEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return NotificationQueueEntry.class;
	}

	@Override
	public String getModelClassName() {
		return NotificationQueueEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<NotificationQueueEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<NotificationQueueEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<NotificationQueueEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((NotificationQueueEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<NotificationQueueEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<NotificationQueueEntry, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(NotificationQueueEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<NotificationQueueEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<NotificationQueueEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<NotificationQueueEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<NotificationQueueEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<NotificationQueueEntry, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<NotificationQueueEntry, Object>>();
		Map<String, BiConsumer<NotificationQueueEntry, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<NotificationQueueEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", NotificationQueueEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<NotificationQueueEntry, Long>)
				NotificationQueueEntry::setMvccVersion);
		attributeGetterFunctions.put(
			"notificationQueueEntryId",
			NotificationQueueEntry::getNotificationQueueEntryId);
		attributeSetterBiConsumers.put(
			"notificationQueueEntryId",
			(BiConsumer<NotificationQueueEntry, Long>)
				NotificationQueueEntry::setNotificationQueueEntryId);
		attributeGetterFunctions.put(
			"companyId", NotificationQueueEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<NotificationQueueEntry, Long>)
				NotificationQueueEntry::setCompanyId);
		attributeGetterFunctions.put(
			"userId", NotificationQueueEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<NotificationQueueEntry, Long>)
				NotificationQueueEntry::setUserId);
		attributeGetterFunctions.put(
			"userName", NotificationQueueEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<NotificationQueueEntry, String>)
				NotificationQueueEntry::setUserName);
		attributeGetterFunctions.put(
			"createDate", NotificationQueueEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<NotificationQueueEntry, Date>)
				NotificationQueueEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", NotificationQueueEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<NotificationQueueEntry, Date>)
				NotificationQueueEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"notificationTemplateId",
			NotificationQueueEntry::getNotificationTemplateId);
		attributeSetterBiConsumers.put(
			"notificationTemplateId",
			(BiConsumer<NotificationQueueEntry, Long>)
				NotificationQueueEntry::setNotificationTemplateId);
		attributeGetterFunctions.put("bcc", NotificationQueueEntry::getBcc);
		attributeSetterBiConsumers.put(
			"bcc",
			(BiConsumer<NotificationQueueEntry, String>)
				NotificationQueueEntry::setBcc);
		attributeGetterFunctions.put("body", NotificationQueueEntry::getBody);
		attributeSetterBiConsumers.put(
			"body",
			(BiConsumer<NotificationQueueEntry, String>)
				NotificationQueueEntry::setBody);
		attributeGetterFunctions.put("cc", NotificationQueueEntry::getCc);
		attributeSetterBiConsumers.put(
			"cc",
			(BiConsumer<NotificationQueueEntry, String>)
				NotificationQueueEntry::setCc);
		attributeGetterFunctions.put(
			"classNameId", NotificationQueueEntry::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<NotificationQueueEntry, Long>)
				NotificationQueueEntry::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", NotificationQueueEntry::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<NotificationQueueEntry, Long>)
				NotificationQueueEntry::setClassPK);
		attributeGetterFunctions.put("from", NotificationQueueEntry::getFrom);
		attributeSetterBiConsumers.put(
			"from",
			(BiConsumer<NotificationQueueEntry, String>)
				NotificationQueueEntry::setFrom);
		attributeGetterFunctions.put(
			"fromName", NotificationQueueEntry::getFromName);
		attributeSetterBiConsumers.put(
			"fromName",
			(BiConsumer<NotificationQueueEntry, String>)
				NotificationQueueEntry::setFromName);
		attributeGetterFunctions.put(
			"priority", NotificationQueueEntry::getPriority);
		attributeSetterBiConsumers.put(
			"priority",
			(BiConsumer<NotificationQueueEntry, Double>)
				NotificationQueueEntry::setPriority);
		attributeGetterFunctions.put("sent", NotificationQueueEntry::getSent);
		attributeSetterBiConsumers.put(
			"sent",
			(BiConsumer<NotificationQueueEntry, Boolean>)
				NotificationQueueEntry::setSent);
		attributeGetterFunctions.put(
			"sentDate", NotificationQueueEntry::getSentDate);
		attributeSetterBiConsumers.put(
			"sentDate",
			(BiConsumer<NotificationQueueEntry, Date>)
				NotificationQueueEntry::setSentDate);
		attributeGetterFunctions.put(
			"subject", NotificationQueueEntry::getSubject);
		attributeSetterBiConsumers.put(
			"subject",
			(BiConsumer<NotificationQueueEntry, String>)
				NotificationQueueEntry::setSubject);
		attributeGetterFunctions.put("to", NotificationQueueEntry::getTo);
		attributeSetterBiConsumers.put(
			"to",
			(BiConsumer<NotificationQueueEntry, String>)
				NotificationQueueEntry::setTo);
		attributeGetterFunctions.put(
			"toName", NotificationQueueEntry::getToName);
		attributeSetterBiConsumers.put(
			"toName",
			(BiConsumer<NotificationQueueEntry, String>)
				NotificationQueueEntry::setToName);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getNotificationQueueEntryId() {
		return _notificationQueueEntryId;
	}

	@Override
	public void setNotificationQueueEntryId(long notificationQueueEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_notificationQueueEntryId = notificationQueueEntryId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
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
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getNotificationTemplateId() {
		return _notificationTemplateId;
	}

	@Override
	public void setNotificationTemplateId(long notificationTemplateId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_notificationTemplateId = notificationTemplateId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalNotificationTemplateId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("notificationTemplateId"));
	}

	@JSON
	@Override
	public String getBcc() {
		if (_bcc == null) {
			return "";
		}
		else {
			return _bcc;
		}
	}

	@Override
	public void setBcc(String bcc) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_bcc = bcc;
	}

	@JSON
	@Override
	public String getBody() {
		if (_body == null) {
			return "";
		}
		else {
			return _body;
		}
	}

	@Override
	public void setBody(String body) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_body = body;
	}

	@JSON
	@Override
	public String getCc() {
		if (_cc == null) {
			return "";
		}
		else {
			return _cc;
		}
	}

	@Override
	public void setCc(String cc) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_cc = cc;
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@JSON
	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classNameId = classNameId;
	}

	@JSON
	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classPK = classPK;
	}

	@JSON
	@Override
	public String getFrom() {
		if (_from == null) {
			return "";
		}
		else {
			return _from;
		}
	}

	@Override
	public void setFrom(String from) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_from = from;
	}

	@JSON
	@Override
	public String getFromName() {
		if (_fromName == null) {
			return "";
		}
		else {
			return _fromName;
		}
	}

	@Override
	public void setFromName(String fromName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fromName = fromName;
	}

	@JSON
	@Override
	public double getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(double priority) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_priority = priority;
	}

	@JSON
	@Override
	public boolean getSent() {
		return _sent;
	}

	@JSON
	@Override
	public boolean isSent() {
		return _sent;
	}

	@Override
	public void setSent(boolean sent) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sent = sent;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalSent() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("sent"));
	}

	@JSON
	@Override
	public Date getSentDate() {
		return _sentDate;
	}

	@Override
	public void setSentDate(Date sentDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sentDate = sentDate;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public Date getOriginalSentDate() {
		return getColumnOriginalValue("sentDate");
	}

	@JSON
	@Override
	public String getSubject() {
		if (_subject == null) {
			return "";
		}
		else {
			return _subject;
		}
	}

	@Override
	public void setSubject(String subject) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_subject = subject;
	}

	@JSON
	@Override
	public String getTo() {
		if (_to == null) {
			return "";
		}
		else {
			return _to;
		}
	}

	@Override
	public void setTo(String to) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_to = to;
	}

	@JSON
	@Override
	public String getToName() {
		if (_toName == null) {
			return "";
		}
		else {
			return _toName;
		}
	}

	@Override
	public void setToName(String toName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_toName = toName;
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), NotificationQueueEntry.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public NotificationQueueEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, NotificationQueueEntry>
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
		NotificationQueueEntryImpl notificationQueueEntryImpl =
			new NotificationQueueEntryImpl();

		notificationQueueEntryImpl.setMvccVersion(getMvccVersion());
		notificationQueueEntryImpl.setNotificationQueueEntryId(
			getNotificationQueueEntryId());
		notificationQueueEntryImpl.setCompanyId(getCompanyId());
		notificationQueueEntryImpl.setUserId(getUserId());
		notificationQueueEntryImpl.setUserName(getUserName());
		notificationQueueEntryImpl.setCreateDate(getCreateDate());
		notificationQueueEntryImpl.setModifiedDate(getModifiedDate());
		notificationQueueEntryImpl.setNotificationTemplateId(
			getNotificationTemplateId());
		notificationQueueEntryImpl.setBcc(getBcc());
		notificationQueueEntryImpl.setBody(getBody());
		notificationQueueEntryImpl.setCc(getCc());
		notificationQueueEntryImpl.setClassNameId(getClassNameId());
		notificationQueueEntryImpl.setClassPK(getClassPK());
		notificationQueueEntryImpl.setFrom(getFrom());
		notificationQueueEntryImpl.setFromName(getFromName());
		notificationQueueEntryImpl.setPriority(getPriority());
		notificationQueueEntryImpl.setSent(isSent());
		notificationQueueEntryImpl.setSentDate(getSentDate());
		notificationQueueEntryImpl.setSubject(getSubject());
		notificationQueueEntryImpl.setTo(getTo());
		notificationQueueEntryImpl.setToName(getToName());

		notificationQueueEntryImpl.resetOriginalValues();

		return notificationQueueEntryImpl;
	}

	@Override
	public NotificationQueueEntry cloneWithOriginalValues() {
		NotificationQueueEntryImpl notificationQueueEntryImpl =
			new NotificationQueueEntryImpl();

		notificationQueueEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		notificationQueueEntryImpl.setNotificationQueueEntryId(
			this.<Long>getColumnOriginalValue("notificationQueueEntryId"));
		notificationQueueEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		notificationQueueEntryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		notificationQueueEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		notificationQueueEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		notificationQueueEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		notificationQueueEntryImpl.setNotificationTemplateId(
			this.<Long>getColumnOriginalValue("notificationTemplateId"));
		notificationQueueEntryImpl.setBcc(
			this.<String>getColumnOriginalValue("bcc"));
		notificationQueueEntryImpl.setBody(
			this.<String>getColumnOriginalValue("body"));
		notificationQueueEntryImpl.setCc(
			this.<String>getColumnOriginalValue("cc"));
		notificationQueueEntryImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		notificationQueueEntryImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));
		notificationQueueEntryImpl.setFrom(
			this.<String>getColumnOriginalValue("from_"));
		notificationQueueEntryImpl.setFromName(
			this.<String>getColumnOriginalValue("fromName"));
		notificationQueueEntryImpl.setPriority(
			this.<Double>getColumnOriginalValue("priority"));
		notificationQueueEntryImpl.setSent(
			this.<Boolean>getColumnOriginalValue("sent"));
		notificationQueueEntryImpl.setSentDate(
			this.<Date>getColumnOriginalValue("sentDate"));
		notificationQueueEntryImpl.setSubject(
			this.<String>getColumnOriginalValue("subject"));
		notificationQueueEntryImpl.setTo(
			this.<String>getColumnOriginalValue("to_"));
		notificationQueueEntryImpl.setToName(
			this.<String>getColumnOriginalValue("toName"));

		return notificationQueueEntryImpl;
	}

	@Override
	public int compareTo(NotificationQueueEntry notificationQueueEntry) {
		long primaryKey = notificationQueueEntry.getPrimaryKey();

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
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NotificationQueueEntry)) {
			return false;
		}

		NotificationQueueEntry notificationQueueEntry =
			(NotificationQueueEntry)object;

		long primaryKey = notificationQueueEntry.getPrimaryKey();

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

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<NotificationQueueEntry> toCacheModel() {
		NotificationQueueEntryCacheModel notificationQueueEntryCacheModel =
			new NotificationQueueEntryCacheModel();

		notificationQueueEntryCacheModel.mvccVersion = getMvccVersion();

		notificationQueueEntryCacheModel.notificationQueueEntryId =
			getNotificationQueueEntryId();

		notificationQueueEntryCacheModel.companyId = getCompanyId();

		notificationQueueEntryCacheModel.userId = getUserId();

		notificationQueueEntryCacheModel.userName = getUserName();

		String userName = notificationQueueEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			notificationQueueEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			notificationQueueEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			notificationQueueEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			notificationQueueEntryCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			notificationQueueEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		notificationQueueEntryCacheModel.notificationTemplateId =
			getNotificationTemplateId();

		notificationQueueEntryCacheModel.bcc = getBcc();

		String bcc = notificationQueueEntryCacheModel.bcc;

		if ((bcc != null) && (bcc.length() == 0)) {
			notificationQueueEntryCacheModel.bcc = null;
		}

		notificationQueueEntryCacheModel.body = getBody();

		String body = notificationQueueEntryCacheModel.body;

		if ((body != null) && (body.length() == 0)) {
			notificationQueueEntryCacheModel.body = null;
		}

		notificationQueueEntryCacheModel.cc = getCc();

		String cc = notificationQueueEntryCacheModel.cc;

		if ((cc != null) && (cc.length() == 0)) {
			notificationQueueEntryCacheModel.cc = null;
		}

		notificationQueueEntryCacheModel.classNameId = getClassNameId();

		notificationQueueEntryCacheModel.classPK = getClassPK();

		notificationQueueEntryCacheModel.from = getFrom();

		String from = notificationQueueEntryCacheModel.from;

		if ((from != null) && (from.length() == 0)) {
			notificationQueueEntryCacheModel.from = null;
		}

		notificationQueueEntryCacheModel.fromName = getFromName();

		String fromName = notificationQueueEntryCacheModel.fromName;

		if ((fromName != null) && (fromName.length() == 0)) {
			notificationQueueEntryCacheModel.fromName = null;
		}

		notificationQueueEntryCacheModel.priority = getPriority();

		notificationQueueEntryCacheModel.sent = isSent();

		Date sentDate = getSentDate();

		if (sentDate != null) {
			notificationQueueEntryCacheModel.sentDate = sentDate.getTime();
		}
		else {
			notificationQueueEntryCacheModel.sentDate = Long.MIN_VALUE;
		}

		notificationQueueEntryCacheModel.subject = getSubject();

		String subject = notificationQueueEntryCacheModel.subject;

		if ((subject != null) && (subject.length() == 0)) {
			notificationQueueEntryCacheModel.subject = null;
		}

		notificationQueueEntryCacheModel.to = getTo();

		String to = notificationQueueEntryCacheModel.to;

		if ((to != null) && (to.length() == 0)) {
			notificationQueueEntryCacheModel.to = null;
		}

		notificationQueueEntryCacheModel.toName = getToName();

		String toName = notificationQueueEntryCacheModel.toName;

		if ((toName != null) && (toName.length() == 0)) {
			notificationQueueEntryCacheModel.toName = null;
		}

		return notificationQueueEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<NotificationQueueEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<NotificationQueueEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<NotificationQueueEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(NotificationQueueEntry)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

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
		Map<String, Function<NotificationQueueEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<NotificationQueueEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<NotificationQueueEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((NotificationQueueEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, NotificationQueueEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					NotificationQueueEntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _notificationQueueEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _notificationTemplateId;
	private String _bcc;
	private String _body;
	private String _cc;
	private long _classNameId;
	private long _classPK;
	private String _from;
	private String _fromName;
	private double _priority;
	private boolean _sent;
	private Date _sentDate;
	private String _subject;
	private String _to;
	private String _toName;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<NotificationQueueEntry, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((NotificationQueueEntry)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put(
			"notificationQueueEntryId", _notificationQueueEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"notificationTemplateId", _notificationTemplateId);
		_columnOriginalValues.put("bcc", _bcc);
		_columnOriginalValues.put("body", _body);
		_columnOriginalValues.put("cc", _cc);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("from_", _from);
		_columnOriginalValues.put("fromName", _fromName);
		_columnOriginalValues.put("priority", _priority);
		_columnOriginalValues.put("sent", _sent);
		_columnOriginalValues.put("sentDate", _sentDate);
		_columnOriginalValues.put("subject", _subject);
		_columnOriginalValues.put("to_", _to);
		_columnOriginalValues.put("toName", _toName);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("from_", "from");
		attributeNames.put("to_", "to");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("notificationQueueEntryId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("notificationTemplateId", 128L);

		columnBitmasks.put("bcc", 256L);

		columnBitmasks.put("body", 512L);

		columnBitmasks.put("cc", 1024L);

		columnBitmasks.put("classNameId", 2048L);

		columnBitmasks.put("classPK", 4096L);

		columnBitmasks.put("from_", 8192L);

		columnBitmasks.put("fromName", 16384L);

		columnBitmasks.put("priority", 32768L);

		columnBitmasks.put("sent", 65536L);

		columnBitmasks.put("sentDate", 131072L);

		columnBitmasks.put("subject", 262144L);

		columnBitmasks.put("to_", 524288L);

		columnBitmasks.put("toName", 1048576L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private NotificationQueueEntry _escapedModel;

}