package com.lima.apiserver.currentCenter.entity;

import java.util.Date;

/**
 * Database Table Remarks:
 *   平台商户授权记录表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table platform_oauth_record
 *
 * @mbg.generated do_not_delete_during_merge Fri Apr 19 13:55:47 CST 2019
 */
public class PlatformOauthRecord {
    /**
     * Database Column Remarks:
     *   主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   授权客户端
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.oauth_client
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String oauthClient;

    /**
     * Database Column Remarks:
     *   用户id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.user_id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private Long userId;

    /**
     * Database Column Remarks:
     *   商户id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.org_id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private Long orgId;

    /**
     * Database Column Remarks:
     *   登录类型：0-用户名登录；1-工号登录；2-手机号登录
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.login_type
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private Integer loginType;

    /**
     * Database Column Remarks:
     *   授权用户名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.oauth_username
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String oauthUsername;

    /**
     * Database Column Remarks:
     *   授权类型：客户端授权，密码授权，授权码授权，简要授权
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.oauth_type
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String oauthType;

    /**
     * Database Column Remarks:
     *   授权回调地址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.redirect_uri
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String redirectUri;

    /**
     * Database Column Remarks:
     *   授权来源：各模块约定好类型
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.oauth_source
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String oauthSource;

    /**
     * Database Column Remarks:
     *   授权的token信息
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.token_info
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String tokenInfo;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.create_time
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private Date createTime;

    /**
     * Database Column Remarks:
     *   删除标识：0-启用；1-禁用
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.is_del
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private Integer isDel;

    /**
     * Database Column Remarks:
     *   来源平台
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.platform
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String platform;

    /**
     * Database Column Remarks:
     *   应用标识,保保出单-bbcd、心元宝-xyb、开放平台-open_platform等
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.app_id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String appId;

    /**
     * Database Column Remarks:
     *   应用版本
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.app_version
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String appVersion;

    /**
     * Database Column Remarks:
     *   请求方ip
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.request_ip
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String requestIp;

    /**
     * Database Column Remarks:
     *   设备标识，比如mac地址等
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.device_id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String deviceId;

    /**
     * Database Column Remarks:
     *   操作类型:1-登录;2-退出
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.handle_type
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private Integer handleType;

    /**
     * Database Column Remarks:
     *   操作结果:success, failure
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.handle_result
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String handleResult;

    /**
     * Database Column Remarks:
     *   操作类型:1-登录;2-退出
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.handle_type_desc
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String handleTypeDesc;

    /**
     * Database Column Remarks:
     *   耗时
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.timing
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private Integer timing;

    /**
     * Database Column Remarks:
     *   请求地址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.referer
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String referer;

    /**
     * Database Column Remarks:
     *   请求地址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.accept
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String accept;

    /**
     * Database Column Remarks:
     *   请求域名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.origin
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String origin;

    /**
     * Database Column Remarks:
     *   请求客户端
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_oauth_record.user_agent
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    private String userAgent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.id
     *
     * @return the value of platform_oauth_record.id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.id
     *
     * @param id the value for platform_oauth_record.id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.oauth_client
     *
     * @return the value of platform_oauth_record.oauth_client
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getOauthClient() {
        return oauthClient;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.oauth_client
     *
     * @param oauthClient the value for platform_oauth_record.oauth_client
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setOauthClient(String oauthClient) {
        this.oauthClient = oauthClient == null ? null : oauthClient.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.user_id
     *
     * @return the value of platform_oauth_record.user_id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.user_id
     *
     * @param userId the value for platform_oauth_record.user_id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.org_id
     *
     * @return the value of platform_oauth_record.org_id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.org_id
     *
     * @param orgId the value for platform_oauth_record.org_id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.login_type
     *
     * @return the value of platform_oauth_record.login_type
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public Integer getLoginType() {
        return loginType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.login_type
     *
     * @param loginType the value for platform_oauth_record.login_type
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.oauth_username
     *
     * @return the value of platform_oauth_record.oauth_username
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getOauthUsername() {
        return oauthUsername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.oauth_username
     *
     * @param oauthUsername the value for platform_oauth_record.oauth_username
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setOauthUsername(String oauthUsername) {
        this.oauthUsername = oauthUsername == null ? null : oauthUsername.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.oauth_type
     *
     * @return the value of platform_oauth_record.oauth_type
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getOauthType() {
        return oauthType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.oauth_type
     *
     * @param oauthType the value for platform_oauth_record.oauth_type
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setOauthType(String oauthType) {
        this.oauthType = oauthType == null ? null : oauthType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.redirect_uri
     *
     * @return the value of platform_oauth_record.redirect_uri
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getRedirectUri() {
        return redirectUri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.redirect_uri
     *
     * @param redirectUri the value for platform_oauth_record.redirect_uri
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri == null ? null : redirectUri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.oauth_source
     *
     * @return the value of platform_oauth_record.oauth_source
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getOauthSource() {
        return oauthSource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.oauth_source
     *
     * @param oauthSource the value for platform_oauth_record.oauth_source
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setOauthSource(String oauthSource) {
        this.oauthSource = oauthSource == null ? null : oauthSource.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.token_info
     *
     * @return the value of platform_oauth_record.token_info
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getTokenInfo() {
        return tokenInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.token_info
     *
     * @param tokenInfo the value for platform_oauth_record.token_info
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo == null ? null : tokenInfo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.create_time
     *
     * @return the value of platform_oauth_record.create_time
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.create_time
     *
     * @param createTime the value for platform_oauth_record.create_time
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.is_del
     *
     * @return the value of platform_oauth_record.is_del
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.is_del
     *
     * @param isDel the value for platform_oauth_record.is_del
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.platform
     *
     * @return the value of platform_oauth_record.platform
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.platform
     *
     * @param platform the value for platform_oauth_record.platform
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.app_id
     *
     * @return the value of platform_oauth_record.app_id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.app_id
     *
     * @param appId the value for platform_oauth_record.app_id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.app_version
     *
     * @return the value of platform_oauth_record.app_version
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.app_version
     *
     * @param appVersion the value for platform_oauth_record.app_version
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.request_ip
     *
     * @return the value of platform_oauth_record.request_ip
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getRequestIp() {
        return requestIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.request_ip
     *
     * @param requestIp the value for platform_oauth_record.request_ip
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp == null ? null : requestIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.device_id
     *
     * @return the value of platform_oauth_record.device_id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.device_id
     *
     * @param deviceId the value for platform_oauth_record.device_id
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.handle_type
     *
     * @return the value of platform_oauth_record.handle_type
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public Integer getHandleType() {
        return handleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.handle_type
     *
     * @param handleType the value for platform_oauth_record.handle_type
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setHandleType(Integer handleType) {
        this.handleType = handleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.handle_result
     *
     * @return the value of platform_oauth_record.handle_result
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getHandleResult() {
        return handleResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.handle_result
     *
     * @param handleResult the value for platform_oauth_record.handle_result
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult == null ? null : handleResult.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.handle_type_desc
     *
     * @return the value of platform_oauth_record.handle_type_desc
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getHandleTypeDesc() {
        return handleTypeDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.handle_type_desc
     *
     * @param handleTypeDesc the value for platform_oauth_record.handle_type_desc
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setHandleTypeDesc(String handleTypeDesc) {
        this.handleTypeDesc = handleTypeDesc == null ? null : handleTypeDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.timing
     *
     * @return the value of platform_oauth_record.timing
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public Integer getTiming() {
        return timing;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.timing
     *
     * @param timing the value for platform_oauth_record.timing
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setTiming(Integer timing) {
        this.timing = timing;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.referer
     *
     * @return the value of platform_oauth_record.referer
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getReferer() {
        return referer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.referer
     *
     * @param referer the value for platform_oauth_record.referer
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setReferer(String referer) {
        this.referer = referer == null ? null : referer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.accept
     *
     * @return the value of platform_oauth_record.accept
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getAccept() {
        return accept;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.accept
     *
     * @param accept the value for platform_oauth_record.accept
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setAccept(String accept) {
        this.accept = accept == null ? null : accept.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.origin
     *
     * @return the value of platform_oauth_record.origin
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.origin
     *
     * @param origin the value for platform_oauth_record.origin
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_oauth_record.user_agent
     *
     * @return the value of platform_oauth_record.user_agent
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_oauth_record.user_agent
     *
     * @param userAgent the value for platform_oauth_record.user_agent
     *
     * @mbg.generated Fri Apr 19 13:55:47 CST 2019
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }
}