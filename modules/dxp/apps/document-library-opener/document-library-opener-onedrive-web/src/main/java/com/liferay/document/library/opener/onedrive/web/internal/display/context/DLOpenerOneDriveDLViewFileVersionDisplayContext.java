/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.document.library.opener.onedrive.web.internal.display.context;

import com.liferay.document.library.display.context.BaseDLViewFileVersionDisplayContext;
import com.liferay.document.library.display.context.DLUIItemKeys;
import com.liferay.document.library.display.context.DLViewFileVersionDisplayContext;
import com.liferay.document.library.opener.constants.DLOpenerFileEntryReferenceConstants;
import com.liferay.document.library.opener.constants.DLOpenerMimeTypes;
import com.liferay.document.library.opener.model.DLOpenerFileEntryReference;
import com.liferay.document.library.opener.onedrive.web.internal.DLOpenerOneDriveManager;
import com.liferay.document.library.opener.onedrive.web.internal.constants.DLOpenerOneDriveConstants;
import com.liferay.document.library.opener.onedrive.web.internal.constants.DLOpenerOneDriveMimeTypes;
import com.liferay.document.library.opener.service.DLOpenerFileEntryReferenceLocalService;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemBuilder;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseUIItem;
import com.liferay.portal.kernel.servlet.taglib.ui.JavaScriptUIItem;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.ToolbarItem;
import com.liferay.portal.kernel.servlet.taglib.ui.URLMenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.URLToolbarItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Cristina González
 */
public class DLOpenerOneDriveDLViewFileVersionDisplayContext
	extends BaseDLViewFileVersionDisplayContext {

	public DLOpenerOneDriveDLViewFileVersionDisplayContext(
		DLViewFileVersionDisplayContext parentDLDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, FileVersion fileVersion,
		ResourceBundle resourceBundle,
		ModelResourcePermission<FileEntry> fileEntryModelResourcePermission,
		DLOpenerFileEntryReferenceLocalService
			dlOpenerFileEntryReferenceLocalService,
		DLOpenerOneDriveManager dlOpenerOneDriveManager, Portal portal) {

		super(
			_UUID, parentDLDisplayContext, httpServletRequest,
			httpServletResponse, fileVersion);

		_resourceBundle = resourceBundle;
		_fileEntryModelResourcePermission = fileEntryModelResourcePermission;
		_dlOpenerFileEntryReferenceLocalService =
			dlOpenerFileEntryReferenceLocalService;
		_dlOpenerOneDriveManager = dlOpenerOneDriveManager;
		_portal = portal;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		_permissionChecker = themeDisplay.getPermissionChecker();
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() throws PortalException {
		if (!isActionsVisible() ||
			!DLOpenerOneDriveMimeTypes.isOffice365MimeTypeSupported(
				fileVersion.getMimeType()) ||
			!_dlOpenerOneDriveManager.isConfigured(
				fileVersion.getCompanyId()) ||
			!_fileEntryModelResourcePermission.contains(
				_permissionChecker, fileVersion.getFileEntry(),
				ActionKeys.UPDATE)) {

			return super.getActionDropdownItems();
		}

		List<DropdownItem> dropdownItems = super.getActionDropdownItems();

		if (_isCheckedOutInOneDrive()) {
			FileEntry fileEntry = fileVersion.getFileEntry();

			if (fileEntry.hasLock()) {
				_updateCancelCheckoutAndCheckinDropdownItems(dropdownItems);

				_addEditInOffice365DropdownItem(
					dropdownItems,
					_createEditInOffice365DropdownItem(Constants.EDIT));
			}

			return dropdownItems;
		}

		_addEditInOffice365DropdownItem(
			dropdownItems,
			_createEditInOffice365DropdownItem(Constants.CHECKOUT));

		return dropdownItems;
	}

	@Override
	public Menu getMenu() throws PortalException {
		if (!isActionsVisible() ||
			!DLOpenerOneDriveMimeTypes.isOffice365MimeTypeSupported(
				fileVersion.getMimeType()) ||
			!_dlOpenerOneDriveManager.isConfigured(
				fileVersion.getCompanyId()) ||
			!_fileEntryModelResourcePermission.contains(
				_permissionChecker, fileVersion.getFileEntry(),
				ActionKeys.UPDATE)) {

			return super.getMenu();
		}

		Menu menu = super.getMenu();

		if (_isCheckedOutInOneDrive()) {
			FileEntry fileEntry = fileVersion.getFileEntry();

			if (fileEntry.hasLock()) {
				List<MenuItem> menuItems = menu.getMenuItems();

				_updateCancelCheckoutAndCheckinMenuItems(menuItems);

				_addEditInOffice365UIItem(
					menuItems, _createEditInOffice365MenuItem(Constants.EDIT));
			}

			return menu;
		}

		_addEditInOffice365UIItem(
			menu.getMenuItems(),
			_createEditInOffice365MenuItem(Constants.CHECKOUT));

		return menu;
	}

	@Override
	public List<ToolbarItem> getToolbarItems() throws PortalException {
		if (!isActionsVisible() ||
			!DLOpenerOneDriveMimeTypes.isOffice365MimeTypeSupported(
				fileVersion.getMimeType()) ||
			!_dlOpenerOneDriveManager.isConfigured(
				fileVersion.getCompanyId()) ||
			!_fileEntryModelResourcePermission.contains(
				_permissionChecker, fileVersion.getFileEntry(),
				ActionKeys.UPDATE)) {

			return super.getToolbarItems();
		}

		List<ToolbarItem> toolbarItems = super.getToolbarItems();

		if (_isCheckedOutInOneDrive()) {
			FileEntry fileEntry = fileVersion.getFileEntry();

			if (fileEntry.hasLock()) {
				_updateCancelCheckoutAndCheckinToolbarItems(toolbarItems);
			}
		}

		return toolbarItems;
	}

	private List<DropdownItem> _addEditInOffice365DropdownItem(
		List<DropdownItem> dropdownItems,
		DropdownItem editInOffice365DropdownItem) {

		if (_addEditInOffice365DropdownItemGroup(
				dropdownItems, editInOffice365DropdownItem)) {

			return dropdownItems;
		}

		dropdownItems.add(editInOffice365DropdownItem);

		return dropdownItems;
	}

	private boolean _addEditInOffice365DropdownItemGroup(
		List<DropdownItem> dropdownItems,
		DropdownItem editInOffice365DropdownItem) {

		int i = 1;

		for (DropdownItem dropdownItem : dropdownItems) {
			if (Objects.equals(dropdownItem.get("type"), "group")) {
				if (_addEditInOffice365DropdownItemGroup(
						(List<DropdownItem>)dropdownItem.get("items"),
						editInOffice365DropdownItem)) {

					return true;
				}
			}
			else if (Objects.equals(
						DLUIItemKeys.EDIT, dropdownItem.get("key"))) {

				break;
			}

			i++;
		}

		if (i < dropdownItems.size()) {
			dropdownItems.add(i, editInOffice365DropdownItem);

			return true;
		}

		return false;
	}

	/**
	 * @see com.liferay.sharing.document.library.internal.display.context.SharingDLViewFileVersionDisplayContext#_addSharingUIItem(List, BaseUIItem)
	 */
	private <T extends BaseUIItem> List<T> _addEditInOffice365UIItem(
		List<T> uiItems, T editInOffice365UIItem) {

		int i = 1;

		for (T uiItem : uiItems) {
			if (DLUIItemKeys.EDIT.equals(uiItem.getKey())) {
				break;
			}

			i++;
		}

		if (i >= uiItems.size()) {
			uiItems.add(editInOffice365UIItem);
		}
		else {
			uiItems.add(i, editInOffice365UIItem);
		}

		return uiItems;
	}

	private DropdownItem _createEditInOffice365DropdownItem(String cmd)
		throws PortalException {

		if (Objects.equals(
				request.getParameter("mvcRenderCommandName"),
				"/document_library/view_file_entry")) {

			return DropdownItemBuilder.putData(
				"senna-off", "true"
			).setHref(
				_getEditURL(
					cmd, "/document_library/edit_in_one_drive_and_redirect")
			).setLabel(
				LanguageUtil.get(_resourceBundle, _getLabelKey())
			).build();
		}

		return DropdownItemBuilder.putData(
			"action", "editOfficeDocument"
		).putData(
			"editURL", _getEditURL(cmd, "/document_library/edit_in_one_drive")
		).setLabel(
			LanguageUtil.get(_resourceBundle, _getLabelKey())
		).build();
	}

	private MenuItem _createEditInOffice365MenuItem(String cmd)
		throws PortalException {

		URLMenuItem urlMenuItem = new URLMenuItem();

		urlMenuItem.setLabel(LanguageUtil.get(_resourceBundle, _getLabelKey()));
		urlMenuItem.setMethod(HttpMethods.POST);

		if (Objects.equals(
				request.getParameter("mvcRenderCommandName"),
				"/document_library/view_file_entry")) {

			urlMenuItem.setData(Collections.singletonMap("senna-off", "true"));
			urlMenuItem.setURL(
				_getEditURL(
					cmd, "/document_library/edit_in_one_drive_and_redirect"));
		}
		else {
			urlMenuItem.setURL(
				StringBundler.concat(
					"javascript:",
					_portal.getPortletNamespace(_portal.getPortletId(request)),
					"editOfficeDocument(\"",
					_getEditURL(cmd, "/document_library/edit_in_one_drive"),
					"\");"));
		}

		return urlMenuItem;
	}

	private String _getCancelCheckOutURL() throws PortalException {
		LiferayPortletURL liferayPortletURL = PortletURLFactoryUtil.create(
			request, _portal.getPortletId(request),
			PortletRequest.ACTION_PHASE);

		liferayPortletURL.setParameter(
			ActionRequest.ACTION_NAME,
			"/document_library/cancel_check_out_in_one_drive");
		liferayPortletURL.setParameter(
			"fileEntryId", String.valueOf(fileVersion.getFileEntryId()));

		FileEntry fileEntry = fileVersion.getFileEntry();

		liferayPortletURL.setParameter(
			"folderId", String.valueOf(fileEntry.getFolderId()));

		return liferayPortletURL.toString();
	}

	private String _getCheckInURL() throws PortalException {
		LiferayPortletURL liferayPortletURL = PortletURLFactoryUtil.create(
			request, _portal.getPortletId(request),
			PortletRequest.ACTION_PHASE);

		liferayPortletURL.setParameter(
			ActionRequest.ACTION_NAME,
			"/document_library/check_in_in_one_drive");
		liferayPortletURL.setParameter(
			"fileEntryId", String.valueOf(fileVersion.getFileEntryId()));

		FileEntry fileEntry = fileVersion.getFileEntry();

		liferayPortletURL.setParameter(
			"folderId", String.valueOf(fileEntry.getFolderId()));

		return liferayPortletURL.toString();
	}

	private String _getEditURL(String cmd, String url) throws PortalException {
		LiferayPortletURL liferayPortletURL = PortletURLFactoryUtil.create(
			request, _portal.getPortletId(request),
			PortletRequest.ACTION_PHASE);

		liferayPortletURL.setParameter(ActionRequest.ACTION_NAME, url);
		liferayPortletURL.setParameter(Constants.CMD, cmd);
		liferayPortletURL.setParameter(
			"fileEntryId", String.valueOf(fileVersion.getFileEntryId()));

		FileEntry fileEntry = fileVersion.getFileEntry();

		liferayPortletURL.setParameter(
			"folderId", String.valueOf(fileEntry.getFolderId()));

		return liferayPortletURL.toString();
	}

	private String _getLabelKey() {
		String office365MimeType =
			DLOpenerOneDriveMimeTypes.getOffice365MimeType(
				fileVersion.getMimeType());

		if (DLOpenerMimeTypes.APPLICATION_VND_PPTX.equals(office365MimeType) ||
			DLOpenerMimeTypes.APPLICATION_VND_XLSX.equals(office365MimeType)) {

			return "edit-in-office365";
		}

		return "edit-in-office365";
	}

	private LiferayPortletResponse _getLiferayPortletResponse() {
		PortletResponse portletResponse = (PortletResponse)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE);

		return PortalUtil.getLiferayPortletResponse(portletResponse);
	}

	private String _getNamespace() {
		LiferayPortletResponse liferayPortletResponse =
			_getLiferayPortletResponse();

		return liferayPortletResponse.getNamespace();
	}

	private boolean _isCheckedOutInOneDrive() throws PortalException {
		FileEntry fileEntry = fileVersion.getFileEntry();

		if (fileEntry.isCheckedOut() &&
			_dlOpenerOneDriveManager.isOneDriveFile(fileEntry)) {

			return true;
		}

		return false;
	}

	private boolean _isCheckingInNewFile() throws PortalException {
		DLOpenerFileEntryReference dlOpenerFileEntryReference =
			_dlOpenerFileEntryReferenceLocalService.
				getDLOpenerFileEntryReference(
					DLOpenerOneDriveConstants.ONE_DRIVE_REFERENCE_TYPE,
					fileVersion.getFileEntry());

		if (dlOpenerFileEntryReference.getType() ==
				DLOpenerFileEntryReferenceConstants.TYPE_NEW) {

			return true;
		}

		return false;
	}

	private void _updateCancelCheckoutAndCheckinDropdownItems(
			List<DropdownItem> dropdownItems)
		throws PortalException {

		for (DropdownItem dropdownItem : dropdownItems) {
			if (Objects.equals(dropdownItem.get("type"), "group")) {
				_updateCancelCheckoutAndCheckinDropdownItems(
					(List<DropdownItem>)dropdownItem.get("items"));
			}
			else if (DLUIItemKeys.CHECKIN.equals(dropdownItem.get("key"))) {
				if (_isCheckingInNewFile()) {
					dropdownItem.setHref(_getCheckInURL());
				}
				else {
					dropdownItem.setData(
						HashMapBuilder.<String, Object>put(
							"action", "checkin"
						).put(
							"checkinURL", _getCheckInURL()
						).build());
				}
			}
			else if (DLUIItemKeys.CANCEL_CHECKOUT.equals(
						dropdownItem.get("key"))) {

				dropdownItem.setHref(_getCancelCheckOutURL());
			}
		}
	}

	private void _updateCancelCheckoutAndCheckinMenuItems(
			Collection<MenuItem> menuItems)
		throws PortalException {

		for (MenuItem menuItem : menuItems) {
			if (DLUIItemKeys.CHECKIN.equals(menuItem.getKey())) {
				if (menuItem instanceof JavaScriptUIItem) {
					JavaScriptUIItem javaScriptUIItem =
						(JavaScriptUIItem)menuItem;

					if (_isCheckingInNewFile()) {
						javaScriptUIItem.setOnClick(
							StringBundler.concat(
								"window.location.href = '",
								HtmlUtil.escapeJS(_getCheckInURL()), "'"));
					}
					else {
						javaScriptUIItem.setOnClick(
							StringBundler.concat(
								_getNamespace(), "showVersionDetailsDialog('",
								HtmlUtil.escapeJS(_getCheckInURL()), "');"));
					}
				}
			}
			else if (DLUIItemKeys.CANCEL_CHECKOUT.equals(menuItem.getKey())) {
				if (menuItem instanceof URLMenuItem) {
					URLMenuItem urlMenuItem = (URLMenuItem)menuItem;

					urlMenuItem.setData(
						Collections.singletonMap("senna-off", "true"));
					urlMenuItem.setMethod(HttpMethods.POST);
					urlMenuItem.setURL(_getCancelCheckOutURL());
				}
			}
		}
	}

	private void _updateCancelCheckoutAndCheckinToolbarItems(
			List<ToolbarItem> toolbarItems)
		throws PortalException {

		ListIterator<ToolbarItem> listIterator = toolbarItems.listIterator();

		while (listIterator.hasNext()) {
			ToolbarItem toolbarItem = listIterator.next();

			if (DLUIItemKeys.CHECKIN.equals(toolbarItem.getKey())) {
				if (toolbarItem instanceof JavaScriptUIItem) {
					JavaScriptUIItem javaScriptUIItem =
						(JavaScriptUIItem)toolbarItem;

					if (_isCheckingInNewFile()) {
						javaScriptUIItem.setOnClick(
							StringBundler.concat(
								"window.location.href = '",
								HtmlUtil.escapeJS(_getCheckInURL()), "'"));
					}
					else {
						javaScriptUIItem.setOnClick(
							StringBundler.concat(
								_getNamespace(), "showVersionDetailsDialog('",
								HtmlUtil.escapeJS(_getCheckInURL()), "');"));
					}
				}
			}
			else if (DLUIItemKeys.CANCEL_CHECKOUT.equals(
						toolbarItem.getKey())) {

				listIterator.remove();

				URLToolbarItem urlToolbarItem = new URLToolbarItem();

				urlToolbarItem.setData(
					Collections.singletonMap("senna-off", "true"));
				urlToolbarItem.setKey(DLUIItemKeys.CANCEL_CHECKOUT);
				urlToolbarItem.setLabel(
					LanguageUtil.get(
						_resourceBundle, "cancel-checkout[document]"));
				urlToolbarItem.setURL(_getCancelCheckOutURL());

				listIterator.add(urlToolbarItem);
			}
		}
	}

	private static final UUID _UUID = UUID.fromString(
		"9ad58b57-aa3c-44f9-91bd-9385f0a925bf");

	private final DLOpenerFileEntryReferenceLocalService
		_dlOpenerFileEntryReferenceLocalService;
	private final DLOpenerOneDriveManager _dlOpenerOneDriveManager;
	private final ModelResourcePermission<FileEntry>
		_fileEntryModelResourcePermission;
	private final PermissionChecker _permissionChecker;
	private final Portal _portal;
	private final ResourceBundle _resourceBundle;

}