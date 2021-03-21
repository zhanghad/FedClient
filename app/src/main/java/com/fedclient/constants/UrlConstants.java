package com.fedclient.constants;


/**
 *  通信接口常量
 *
 */
public class UrlConstants {

    /**
     * 服务端基址，本地测试时使用 http://localhost:8080/android/
     *
     * android 将本机地址设为 10.0.2.2
     */
    private static final String URL_BASE = "http://10.0.2.2:8080/android/";

    /**
     * 登录
     */
    public static final String LOGIN_URL = URL_BASE+"login/post";

    /**
     * 注册
     */
    public static final String REGISTER_URL = URL_BASE+"register/post";


    /**
     * 用户信息url基址
     */
    private static final String CLIENTINFO = "clientinfo/";

    /**
     * 添加用户信息
     */
    public static final String URL_CLIENTINFO_ADD = URL_BASE+ CLIENTINFO+"add";

    /**
     * 添加用户设备信息
     */
    public static final String URL_CLIENTINFO_ADD_DEVICE = URL_BASE+ CLIENTINFO+"adddevice";

    /**
     * 下载用户信息
     */
    public static final String URL_CLIENTINFO_GET = URL_BASE+ CLIENTINFO+"get";

    /**
     * 下载用户设备信息
     */
    public static final String URL_CLIENTINFO_GET_DEVICE = URL_BASE+ CLIENTINFO+"getdevice";

    /**
     * 更新用户信息
     */
    public static final String URL_CLIENTINFO_UPDATE = URL_BASE+ CLIENTINFO+"update";

    /**
     * 更新用户设备信息
     */
    public static final String URL_CLIENTINFO_UPDATE_DEVICE = URL_BASE+ CLIENTINFO+"updatedevice";



    /**
     * 任务管理url基址
     */
    private static final String TASK = "task/";

    /**
     * 下载全部任务信息
     */
    public static final String URL_TASK_GET_ALL_TASK = URL_BASE+ TASK+"getalltask";

    /**
     * 下载全部任务配置信息
     */
    public static final String URL_TASK_GET_ALL_TASK_CONFIG = URL_BASE+ TASK+"getalltaskconfig";

    /**
     * 查询任务信息
     */
    public static final String URL_TASK_GET_TASK = URL_BASE+ TASK+"gettask";

    /**
     * 下载任务配置信息
     */
    public static final String URL_TASK_GET_TASK_CONFIG = URL_BASE+ TASK+"gettaskconfig";

    /**
     * 参与任务
     */
    public static final String URL_TASK_JOIN = URL_BASE+ TASK+"jointask";

    /**
     * 退出任务
     */
    public static final String URL_TASK_QUIT = URL_BASE+ TASK+"quittask";


    /**
     * 参与历史信息
     */
    public static final String URL_HISTORY_GET = URL_BASE+ "history/get";


}
