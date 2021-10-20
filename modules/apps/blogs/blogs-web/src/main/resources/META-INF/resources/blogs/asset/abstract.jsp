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
BlogsEntry entry = (BlogsEntry)request.getAttribute(WebKeys.BLOGS_ENTRY);
%>

<div class="asset-summary">

	<%
	String coverImageURL = entry.getCoverImageURL(themeDisplay);
	%>

	<c:if test="<%= Validator.isNotNull(coverImageURL) %>">

		<%
		String coverImageCaption = HtmlUtil.escapeAttribute(HtmlUtil.stripHtml(entry.getCoverImageCaption()));
		%>

		<div <c:if test="<%= Validator.isNotNull(coverImageCaption) %>">aria-label="<%= coverImageCaption %>" role="img"</c:if> class="aspect-ratio aspect-ratio-8-to-3 aspect-ratio-bg-cover cover-image mb-4" style="background-image: url(<%= coverImageURL %>);"></div>
	</c:if>

	<%
	AssetRenderer<?> assetRenderer = (AssetRenderer<?>)request.getAttribute(WebKeys.ASSET_RENDERER);
	%>

	<%= HtmlUtil.stripHtml(assetRenderer.getSummary(renderRequest, renderResponse)) %>
</div>