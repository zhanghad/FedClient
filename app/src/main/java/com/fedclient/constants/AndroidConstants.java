package com.fedclient.constants;

/**
 * 客户端通信常量
 *
 * @author fedserver
 */
public class AndroidConstants {

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "success";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "fail";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "login_success";

    /**
     * 参与者密码错误
     */
    public static final String REGISTER_SUCCESS = "register_success";

    /**
     * 参与者密码错误
     */
    public static final String REGISTER_FAIL = "register_fail";

    /**
     * 参与者账户不存在
     */
    public static final String CLIENT_NOT_EXIST = "client_not_exist";

    /**
     * 参与者账户不存在
     */
    public static final String CLIENT_ALREADY_EXIST = "client_already_exist";

    /**
     * 参与者密码错误
     */
    public static final String PASSWORD_WRONG = "password_wrong";

    /**
     * 登录名为空
     */
    public static final String LOGIN_NAME_NULL = "login_name_null";

    /**
     * 登录名长度错误
     */
    public static final String LOGIN_NAME_LENGTH_WRONG = "login_name_length_wrong";

    /**
     * 密码为空
     */
    public static final String PASSWORD_NULL = "password_null";

    /**
     * 密码长度错误
     */
    public static final String PASSWORD_LENGTH_WRONG = "password_length_wrong";


    /**
     * 密码长度限制
     */
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 20;

    /**
     * 用户名长度限制
     */
    public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 20;

    /**
     * 手机号码格式限制
     */
    public static final String MOBILE_PHONE_NUMBER_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";

    /**
     * 邮箱格式限制
     */
    public static final String EMAIL_PATTERN = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";

}
