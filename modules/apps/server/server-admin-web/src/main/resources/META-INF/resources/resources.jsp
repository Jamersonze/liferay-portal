<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String[] installedPatches = PatcherUtil.getInstalledPatches();

Date modifiedDate = PortalUtil.getUptime();

long uptimeDiff = System.currentTimeMillis() - modifiedDate.getTime();

long days = uptimeDiff / Time.DAY;
long hours = (uptimeDiff / Time.HOUR) % 24;
long minutes = (uptimeDiff / Time.MINUTE) % 60;
long seconds = (uptimeDiff / Time.SECOND) % 60;

Runtime runtime = Runtime.getRuntime();

long totalMemory = runtime.totalMemory();

long usedMemory = totalMemory - runtime.freeMemory();
%>

<aui:fieldset-group markupView="lexicon">
	<aui:fieldset>
		<div class="alert alert-info">
			<strong><liferay-ui:message key="info" /></strong>: <%= ReleaseInfo.getReleaseInfo() %>
			<c:if test="<%= (installedPatches != null) && (installedPatches.length > 0) %>">
				<strong><liferay-ui:message key="patch" /></strong>: <%= StringUtil.merge(installedPatches, StringPool.COMMA_AND_SPACE) %>
			</c:if>

			<strong><liferay-ui:message key="uptime" /></strong>:

			<c:if test="<%= days > 0 %>">
				<%= days %> <%= LanguageUtil.get(request, ((days > 1) ? "days" : "day")) %>,
			</c:if>

			<%
			NumberFormat timeNumberFormat = NumberFormat.getInstance();

			timeNumberFormat.setMaximumIntegerDigits(2);
			timeNumberFormat.setMinimumIntegerDigits(2);
			%>

			<%= timeNumberFormat.format(hours) %>:<%= timeNumberFormat.format(minutes) %>:<%= timeNumberFormat.format(seconds) %>
		</div>

		<div class="meter-wrapper text-center">
			<portlet:resourceURL id="/server_admin/view_chart" var="totalMemoryChartURL">
				<portlet:param name="type" value="total" />
				<portlet:param name="totalMemory" value="<%= String.valueOf(totalMemory) %>" />
				<portlet:param name="usedMemory" value="<%= String.valueOf(usedMemory) %>" />
			</portlet:resourceURL>

			<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="memory-used-vs-total-memory" />" src="<%= totalMemoryChartURL %>" />

			<portlet:resourceURL id="/server_admin/view_chart" var="maxMemoryChartURL">
				<portlet:param name="type" value="max" />
				<portlet:param name="maxMemory" value="<%= String.valueOf(runtime.maxMemory()) %>" />
				<portlet:param name="usedMemory" value="<%= String.valueOf(usedMemory) %>" />
			</portlet:resourceURL>

			<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="memory-used-vs-max-memory" />" src="<%= maxMemoryChartURL %>" />
		</div>

		<br />

		<%
		NumberFormat basicNumberFormat = NumberFormat.getInstance(locale);
		%>

		<table class="lfr-table memory-status-table">
			<tr>
				<td>
					<span class="font-weight-semi-bold"><liferay-ui:message key="used-memory" /></span>
				</td>
				<td>
					<span class="text-muted"><%= basicNumberFormat.format(usedMemory) %> <liferay-ui:message key="bytes" /></span>
				</td>
			</tr>
			<tr>
				<td>
					<span class="font-weight-semi-bold"><liferay-ui:message key="total-memory" /></span>
				</td>
				<td>
					<span class="text-muted"><%= basicNumberFormat.format(runtime.totalMemory()) %> <liferay-ui:message key="bytes" /></span>
				</td>
			</tr>
			<tr>
				<td>
					<span class="font-weight-semi-bold"><liferay-ui:message key="maximum-memory" /></span>
				</td>
				<td>
					<span class="text-muted"><%= basicNumberFormat.format(runtime.maxMemory()) %> <liferay-ui:message key="bytes" /></span>
				</td>
			</tr>
		</table>
	</aui:fieldset>

	<aui:fieldset collapsed="<%= false %>" collapsible="<%= true %>" label="system-actions">
		<ul class="list-group system-action-group">
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate">
						<liferay-ui:message key="run-the-garbage-collector-to-free-up-memory" />
					</p>
				</div>

				<div class="autofit-col">
					<aui:button cssClass="save-server-button" data-cmd="gc" value="execute" />
				</div>
			</li>
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate">
						<liferay-ui:message key="generate-thread-dump" />
					</p>
				</div>

				<div class="autofit-col">
					<aui:button cssClass="save-server-button" data-cmd="threadDump" value="execute" />
				</div>
			</li>
		</ul>
	</aui:fieldset>

	<aui:fieldset collapsed="<%= false %>" collapsible="<%= true %>" label="cache-actions">
		<ul class="list-group system-action-group">
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate">
						<liferay-ui:message key="clear-content-cached-by-this-vm" />
					</p>
				</div>

				<div class="autofit-col">
					<aui:button cssClass="save-server-button" data-cmd="cacheSingle" value="execute" />
				</div>
			</li>
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate">
						<liferay-ui:message key="clear-content-cached-across-the-cluster" />
					</p>
				</div>

				<div class="autofit-col">
					<aui:button cssClass="save-server-button" data-cmd="cacheMulti" value="execute" />
				</div>
			</li>
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate">
						<liferay-ui:message key="clear-the-database-cache" />
					</p>
				</div>

				<div class="autofit-col">
					<aui:button cssClass="save-server-button" data-cmd="cacheDb" value="execute" />
				</div>
			</li>
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate">
						<liferay-ui:message key="clear-the-direct-servlet-cache" />
					</p>
				</div>

				<div class="autofit-col">
					<aui:button cssClass="save-server-button" data-cmd="cacheServlet" value="execute" />
				</div>
			</li>
		</ul>
	</aui:fieldset>

	<aui:fieldset collapsed="<%= false %>" collapsible="<%= true %>" label="verification-actions">
		<ul class="list-group system-action-group">
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate">
						<liferay-ui:message key="verify-database-tables-of-all-plugins" />
					</p>
				</div>

				<div class="autofit-col">
					<aui:button cssClass="save-server-button" data-cmd="verifyPluginTables" value="execute" />
				</div>
			</li>
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate">
						<liferay-ui:message key="verify-membership-policies" />
					</p>
				</div>

				<div class="autofit-col">
					<aui:button cssClass="save-server-button" data-cmd="verifyMembershipPolicies" value="execute" />
				</div>
			</li>
		</ul>
	</aui:fieldset>

	<aui:fieldset collapsed="<%= false %>" collapsible="<%= true %>" label="clean-up-actions">
		<ul class="list-group system-action-group">
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate">
						<liferay-ui:message key="reset-preview-and-thumbnail-files-for-documents-and-media" /> <liferay-ui:icon-help message="reset-preview-and-thumbnail-files-for-documents-and-media-help" />
					</p>
				</div>

				<div class="autofit-col">
					<aui:button cssClass="save-server-button" data-cmd="dlPreviews" value="execute" />
				</div>
			</li>
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate">
						<liferay-ui:message key="clean-up-permissions" /> <liferay-ui:icon-help message="clean-up-permissions-help" />
					</p>
				</div>

				<div class="autofit-col">
					<aui:button cssClass="save-server-button" data-cmd="cleanUpAddToPagePermissions" value="execute" />
				</div>
			</li>
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate">
						<liferay-ui:message key="clean-up-orphaned-page-revision-portlet-preferences" /> <liferay-ui:icon-help message="clean-up-orphaned-page-revision-portlet-preferences-help" />
					</p>
				</div>

				<div class="autofit-col">
					<aui:button cssClass="save-server-button" data-cmd="cleanUpLayoutRevisionPortletPreferences" value="execute" />
				</div>
			</li>
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate">
						<liferay-ui:message key="clean-up-orphaned-theme-portlet-preferences" /> <liferay-ui:icon-help message="clean-up-orphaned-theme-portlet-preferences-help" />
					</p>
				</div>

				<div class="autofit-col">
					<aui:button cssClass="save-server-button" data-cmd="cleanUpOrphanedPortletPreferences" value="execute" />
				</div>
			</li>
		</ul>
	</aui:fieldset>
</aui:fieldset-group>