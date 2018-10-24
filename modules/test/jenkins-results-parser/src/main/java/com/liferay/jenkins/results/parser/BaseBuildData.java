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

package com.liferay.jenkins.results.parser;

import java.io.File;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public abstract class BaseBuildData implements BuildData {

	public static String getJobName(String buildURL) {
		if (buildURL == null) {
			return null;
		}

		Matcher matcher = _buildURLPattern.matcher(buildURL);

		if (!matcher.find()) {
			return null;
		}

		return matcher.group("jobName");
	}

	@Override
	public String getBuildDescription() {
		return getString("build_description");
	}

	@Override
	public Long getBuildDuration() {
		return optLong("build_duration", Long.valueOf(0));
	}

	@Override
	public String getBuildDurationString() {
		return JenkinsResultsParserUtil.toDurationString(getBuildDuration());
	}

	@Override
	public Integer getBuildNumber() {
		return optInt("build_number");
	}

	@Override
	public String getBuildResult() {
		return optString("build_result", "");
	}

	@Override
	public String getBuildStatus() {
		return optString("build_status", "");
	}

	@Override
	public String getBuildURL() {
		return optString("build_url");
	}

	@Override
	public String getCohortName() {
		return optString("cohort_name");
	}

	@Override
	public String getHostname() {
		return optString("hostname");
	}

	@Override
	public String getJenkinsGitHubURL() {
		return getString("jenkins_github_url");
	}

	@Override
	public String getJobName() {
		return optString("job_name");
	}

	@Override
	public JSONObject getJSONObject() {
		return _jsonObject;
	}

	@Override
	public String getMasterHostname() {
		return optString("master_hostname");
	}

	@Override
	public String getRunID() {
		return getString("run_id");
	}

	@Override
	public Long getStartTime() {
		return getLong("start_time");
	}

	@Override
	public String getStartTimeString() {
		return getFormattedDate(getStartTime());
	}

	@Override
	public String getUserContentRelativePath() {
		return JenkinsResultsParserUtil.combine(
			"jobs/", getTopLevelJobName(), "/builds/",
			String.valueOf(getTopLevelBuildNumber()), "/");
	}

	@Override
	public File getWorkspaceDir() {
		return getFile("workspace_dir");
	}

	@Override
	public void setBuildDescription(String buildDescription) {
		put("build_description", buildDescription);
	}

	@Override
	public void setBuildDuration(Long buildDuration) {
		put("build_duration", buildDuration);
	}

	@Override
	public void setBuildResult(String buildResult) {
		put("build_result", buildResult);
	}

	@Override
	public void setBuildStatus(String buildStatus) {
		put("build_status", buildStatus);
	}

	@Override
	public void setBuildURL(String buildURL) {
		String currentBuildURL = getBuildURL();

		if ((currentBuildURL != null) && !currentBuildURL.isEmpty() &&
			!currentBuildURL.equals(buildURL)) {

			throw new IllegalStateException("Build URL may not be changed");
		}

		Matcher matcher = _buildURLPattern.matcher(buildURL);

		if (!matcher.find()) {
			throw new RuntimeException("Invalid build URL " + buildURL);
		}

		put("build_number", Integer.valueOf(matcher.group("buildNumber")));
		put("build_url", buildURL);
		put("cohort_name", matcher.group("cohortName"));
		put("hostname", _getHostname());
		put("master_hostname", matcher.group("masterHostname"));
		put("start_time", _getStartTime());
		put("type", getType());
	}

	@Override
	public void setInvocationTime(Long invocationTime) {
		put("invocation_time", invocationTime);
	}

	@Override
	public void setJenkinsGitHubURL(String jenkinsGitHubURL) {
		put("jenkins_github_url", jenkinsGitHubURL);
	}

	@Override
	public void setWorkspaceDir(File workspaceDir) {
		try {
			put("workspace_dir", workspaceDir.getCanonicalPath());
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	protected static boolean isValidJSONObject(
		JSONObject jsonObject, String type) {

		if (jsonObject == null) {
			return false;
		}

		if (type == null) {
			return false;
		}

		if (jsonObject.has("type")) {
			if (type.equals(jsonObject.getString("type"))) {
				return true;
			}
		}

		return false;
	}

	protected BaseBuildData(String runID, String jobName, String buildURL) {
		JSONObject jsonObject = buildDatabase.getBuildDataJSONObject(runID);

		String jsonObjectString = jsonObject.toString();

		if (jsonObjectString.equals("{}") && (buildURL != null)) {
			try {
				jsonObject = buildDatabase.getBuildDataJSONObject(
					new URL(buildURL));

				if (jsonObject.has("run_id")) {
					runID = jsonObject.getString("run_id");
				}
			}
			catch (MalformedURLException murle) {
				throw new RuntimeException(murle);
			}
		}

		_jsonObject = jsonObject;

		put("run_id", runID);

		if (jobName == null) {
			throw new RuntimeException("Please set a job name");
		}

		put("job_name", jobName);

		if (buildURL == null) {
			return;
		}

		setBuildURL(buildURL);

		if (!has("build_description")) {
			setBuildDescription(_getDefaultBuildDescription());
		}

		setJenkinsGitHubURL(DEFAULT_JENKINS_GITHUB_URL);
		setWorkspaceDir(DEFAULT_WORKSPACE_DIR);

		validateKeys(_REQUIRED_KEYS);
	}

	protected File getFile(String key) {
		return new File(getString(key));
	}

	protected String getFormattedDate(Long timestamp) {
		return JenkinsResultsParserUtil.toDateString(
			new Date(timestamp), "MMM dd, yyyy h:mm:ss a z", "US/Pacific");
	}

	protected JSONArray getJSONArray(String key) {
		return _jsonObject.getJSONArray(key);
	}

	protected JSONObject getJSONObject(String key) {
		return _jsonObject.getJSONObject(key);
	}

	protected List<String> getList(String key) {
		JSONArray jsonArray = getJSONArray(key);

		List<String> list = new ArrayList<>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			list.add(jsonArray.getString(i));
		}

		return list;
	}

	protected Long getLong(String key) {
		return _jsonObject.getLong(key);
	}

	protected String getString(String key) {
		return _jsonObject.getString(key);
	}

	protected abstract String getType();

	protected boolean has(String key) {
		return _jsonObject.has(key);
	}

	protected Integer optInt(String key) {
		return _jsonObject.optInt(key);
	}

	protected Long optLong(String key, Long defaultValue) {
		return _jsonObject.optLong(key, defaultValue);
	}

	protected String optString(String key) {
		return _jsonObject.optString(key);
	}

	protected String optString(String key, String defaultValue) {
		return _jsonObject.optString(key, defaultValue);
	}

	protected void put(String key, Object value) {
		_jsonObject.put(key, value);

		BuildDatabase buildDatabase = BuildDatabaseUtil.getBuildDatabase();

		buildDatabase.putBuildData(getRunID(), this);
	}

	protected void validateKeys(String[] requiredKeys) {
		for (String requiredKey : requiredKeys) {
			if (!has(requiredKey)) {
				throw new RuntimeException("Missing " + requiredKey);
			}
		}
	}

	protected static final BuildDatabase buildDatabase =
		BuildDatabaseUtil.getBuildDatabase();

	private JSONObject _getBuildURLJSONObject() {
		String buildURL = getBuildURL();

		if (buildURL == null) {
			return null;
		}

		try {
			return JenkinsResultsParserUtil.toJSONObject(
				JenkinsResultsParserUtil.getLocalURL(buildURL + "/api/json"));
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	private String _getDefaultBuildDescription() {
		return JenkinsResultsParserUtil.combine(
			"<a href=\"https://", getTopLevelMasterHostname(),
			".liferay.com/userContent/", getUserContentRelativePath(),
			"jenkins-report.html\">Jenkins Report</a>");
	}

	private String _getHostname() {
		JSONObject buildURLJSONObject = _getBuildURLJSONObject();

		if (buildURLJSONObject == null) {
			throw new RuntimeException("Please set the build URL");
		}

		return buildURLJSONObject.getString("builtOn");
	}

	private long _getStartTime() {
		JSONObject buildURLJSONObject = _getBuildURLJSONObject();

		if (buildURLJSONObject == null) {
			throw new RuntimeException("Please set the build URL");
		}

		return buildURLJSONObject.getLong("timestamp");
	}

	private static final String[] _REQUIRED_KEYS = {
		"build_description", "build_number", "build_url", "cohort_name",
		"hostname", "jenkins_github_url", "job_name", "master_hostname",
		"run_id", "workspace_dir"
	};

	private static final Pattern _buildURLPattern = Pattern.compile(
		JenkinsResultsParserUtil.combine(
			"https?://(?<masterHostname>(?<cohortName>test-\\d+)-\\d+)",
			"(\\.liferay\\.com)?/job/(?<jobName>[^/]+)/(.*/)?",
			"(?<buildNumber>\\d+)/?"));

	private final JSONObject _jsonObject;

}