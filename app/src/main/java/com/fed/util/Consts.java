package com.fed.util;

public class Consts {
    private static String URL = "http://^^^^";
    public static String URL_Register = URL + "RegisterServlet";
    public static String URL_Login = URL + "LoginServlet";
    public static String URL_GetInfo = URL + "GetInfoServlet";
    public static String URL_SetInfo = URL + "SetInfoServlet";

    // 服务器代码
    public static String ERRORCODE_NULL = "200";
    public static String ERRORCODE_PWD = "201";
    public static String ERRORCODE_ACCOUNTNOTEXIST = "202";
    public static String ERRORCODE_ACCOUNTEXIST = "203";

    public static String SUCCESSCODE_LOGIN = "100";
    public static String SUCCESSCODE_REGISTER = "101";

    public static String REQUESTCODE_NICKNAME = "1";

    // 代码对应信息
    public static String ERRORMSG_NULL = "不能为空";
    public static String ERRORMSG_PWD = "密码错误";
    public static String ERRORMSG_ACCOUNTNOTEXIST = "账号不存在，请注册";
    public static String ERRORMSG_ACCOUNTEXIST = "账号已存在，请登录";

    public static String SUCCESSMSG_LOGIN = "登录成功";
    public static String SUCCESSMSG_REGISTER = "注册成功";

    // 客户端提示信息
    public static String ERROR_FORMAT = "输入数据格式错误";
}
