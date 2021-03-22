package com.fedclient.util;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class CommonUtil {

    /**
     * @description 处理字符串
     * @param input
     * @return
     */
    public static String StringHandle(String input){
        String output;
        //将包含 单引号(')，分号(;) 和 注释符号(--)的语句替换掉
        output = input.trim().replaceAll(".*([';]+|(--)+).*", " ");
        return output;
    }

    /**
     * Toast封装
     * @param context
     * @param msg
     */
    public static void makeToast(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 判断sd卡是否存在
     * @return
     */
    public static boolean isSdcardExisting() {
        final String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
