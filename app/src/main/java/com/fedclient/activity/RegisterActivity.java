package com.fedclient.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fedclient.R;
import com.fedclient.util.Util;
import com.fedclient.util.CommonRequest;
import com.fedclient.util.CommonResponse;
import com.fedclient.util.HttpUtil;
import com.fedclient.util.Consts;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;


/**
 * 注册页面
 */
public class RegisterActivity extends AppCompatActivity {
    private Button btn_register;//注册按钮
    private Button btn_to_login; //返回按钮
    private EditText et_username, et_pwd1, et_pwd2;
    private String user_name, pwd1, pwd2;



    @Override
    protected void onCreate(Bundle savedIdnstanceState) {
        super.onCreate(savedIdnstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        initComponents();
        setListeners();
    }

    /**
     * 初始化组件
     */
    void initComponents() {
        btn_register = findViewById(R.id.btn_register);
        btn_to_login = findViewById(R.id.to_login);
        et_username = findViewById(R.id.et_username);
        et_pwd1 = findViewById(R.id.et_pwd1);
        et_pwd2 = findViewById(R.id.et_pwd2);
    }

    /**
     * 设置监听
     */
    void setListeners(){
        /**
         * 返回登陆页面
         */
        btn_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /**
         * 注册
         */
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }


    /**
     * 注册逻辑
     */
    private void register(){
        CommonRequest request = new CommonRequest();

        String Username = Util.StringHandle(et_username.getText().toString());
        String pwd = Util.StringHandle(et_pwd1.getText().toString());
        String pwd_confirm = Util.StringHandle(et_pwd2.getText().toString());

        String resMsg = checkDataValid(Username,pwd,pwd_confirm);
        if(!resMsg.equals("")){
            showResponse(resMsg);
            return;
        }

        request.addRequestParam("Username",Username);
        request.addRequestParam("pwd",pwd);

        HttpUtil.sendPost(Consts.URL_Register, request.getJsonStr(), new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                CommonResponse res = new CommonResponse(response.body().string());
                String resCode = res.getResCode();
                String resMsg = res.getResMsg();
                // 显示注册结果
                showResponse(resMsg);
                // 注册成功
                if (resCode.equals(Consts.SUCCESSCODE_REGISTER)) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                showResponse("Network ERROR");
            }
        });
    }


    /**
     *  展示返回信息
     * @param msg 消息
     */
    private void showResponse(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 前端检测注册信息
     * @param Username
     * @param pwd
     * @param pwd_confirm
     * @return
     */
    private String checkDataValid(String Username,String pwd,String pwd_confirm){
        if(TextUtils.isEmpty(Username) | TextUtils.isEmpty(pwd) | TextUtils.isEmpty(pwd_confirm))
            return getResources().getString(R.string.null_hint);
        if(!pwd.equals(pwd_confirm))
            return getResources().getString(R.string.not_equal_hint);
//        if(Username.length() != 11 && !Username.contains("@"))
//            return getResources().getString(R.string.account_invalid_hint);
        return "";
    }
}