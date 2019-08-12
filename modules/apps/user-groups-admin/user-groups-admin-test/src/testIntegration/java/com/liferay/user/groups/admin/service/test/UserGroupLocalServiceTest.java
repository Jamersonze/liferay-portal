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

package com.liferay.user.groups.admin.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.UserGroupTestUtil;
import com.liferay.portal.service.persistence.constants.UserGroupFinderConstants;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;
import com.liferay.portal.util.PropsValues;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;

import java.lang.reflect.Field;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Drew Brokke
 */
@RunWith(Arquillian.class)
public class UserGroupLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_role = RoleTestUtil.addRole(RoleConstants.TYPE_REGULAR);

		_companyId = _role.getCompanyId();

		_count = UserGroupLocalServiceUtil.searchCount(
			_companyId, null, new LinkedHashMap<String, Object>());

		_userGroup1 = UserGroupTestUtil.addUserGroup();
		_userGroup2 = UserGroupTestUtil.addUserGroup();

		GroupLocalServiceUtil.addRoleGroup(
			_role.getRoleId(), _userGroup1.getGroupId());
	}

	@Test
	public void testDatabaseSearchWithInvalidParamKey() {
		String keywords = null;

		LinkedHashMap<String, Object> userGroupParams = new LinkedHashMap<>();

		userGroupParams.put(
			UserGroupFinderConstants.PARAM_KEY_USER_GROUPS_ROLES,
			Long.valueOf(_role.getRoleId()));

		userGroupParams.put("invalidParamKey", "invalidParamValue");

		List<UserGroup> userGroups = _search(keywords, userGroupParams);

		Assert.assertEquals(userGroups.toString(), 1, userGroups.size());
	}

	@Test
	public void testSearchRoleUserGroups() {
		String keywords = null;

		LinkedHashMap<String, Object> userGroupParams = new LinkedHashMap<>();

		userGroupParams.put(
			UserGroupFinderConstants.PARAM_KEY_USER_GROUPS_ROLES,
			Long.valueOf(_role.getRoleId()));

		List<UserGroup> userGroups = _search(keywords, userGroupParams);

		Assert.assertEquals(userGroups.toString(), 1, userGroups.size());
	}

	@Test
	public void testSearchRoleUserGroupsWithKeywords() {
		String keywords = _userGroup2.getName();

		LinkedHashMap<String, Object> userGroupParams = new LinkedHashMap<>();

		userGroupParams.put(
			UserGroupFinderConstants.PARAM_KEY_USER_GROUPS_ROLES,
			Long.valueOf(_role.getRoleId()));

		List<UserGroup> userGroups = _search(keywords, userGroupParams);

		Assert.assertEquals(userGroups.toString(), 0, userGroups.size());
	}

	@Test
	public void testSearchUserGroups() {
		LinkedHashMap<String, Object> emptyParams = new LinkedHashMap<>();

		String keywords = null;

		List<UserGroup> userGroups = _search(keywords, emptyParams);

		Assert.assertEquals(
			userGroups.toString(), _count + 2, userGroups.size());
	}

	@Test
	public void testSearchUserGroupsWithKeywords() {
		LinkedHashMap<String, Object> emptyParams = new LinkedHashMap<>();

		String keywords = _userGroup1.getName();

		List<UserGroup> userGroups = _search(keywords, emptyParams);

		Assert.assertEquals(userGroups.toString(), 1, userGroups.size());
	}

	@Test
	public void testSearchUserGroupsWithNullParamsAndIndexerDisabled()
		throws Exception {

		Field field = ReflectionUtil.getDeclaredField(
			PropsValues.class, "USER_GROUPS_SEARCH_WITH_INDEX");

		Object value = field.get(null);

		try {
			field.set(null, Boolean.FALSE);

			LinkedHashMap<String, Object> nullParams = null;

			String keywords = null;

			List<UserGroup> userGroups = _search(keywords, nullParams);

			Assert.assertEquals(
				userGroups.toString(), _count + 2, userGroups.size());
		}
		finally {
			field.set(null, value);
		}
	}

	@Test
	public void testSearchUserGroupWithDescendingOrder()
		throws PortalException {

		Hits hits = UserGroupLocalServiceUtil.search(
			_companyId, null, new LinkedHashMap<>(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS,
			SortFactoryUtil.getSort(UserGroup.class, "name", "desc"));

		List<UserGroup> expectedUserGroups = UsersAdminUtil.getUserGroups(hits);

		List<UserGroup> userGroups = UserGroupLocalServiceUtil.search(
			_companyId, null, new LinkedHashMap<>(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS,
			UsersAdminUtil.getUserGroupOrderByComparator("name", "desc"));

		Assert.assertEquals(expectedUserGroups, userGroups);
	}

	private List<UserGroup> _search(
		String keywords, LinkedHashMap<String, Object> params) {

		return UserGroupLocalServiceUtil.search(
			_companyId, keywords, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			UsersAdminUtil.getUserGroupOrderByComparator("name", "asc"));
	}

	private static long _companyId;
	private static int _count;

	@DeleteAfterTestRun
	private static Role _role;

	@DeleteAfterTestRun
	private static UserGroup _userGroup1;

	@DeleteAfterTestRun
	private static UserGroup _userGroup2;

}