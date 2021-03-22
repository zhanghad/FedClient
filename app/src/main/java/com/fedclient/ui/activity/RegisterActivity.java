package com.fedclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fedclient.R;
import com.fedclient.constants.AndroidConstants;
import com.fedclient.constants.UrlConstants;
import com.fedclient.domain.Client;
import com.fedclient.util.CommonUtil;
import com.fedclient.util.CommonRequest;
import com.fedclient.util.HttpUtil;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

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
    private static final String TAG = "RegisterActivity";

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
        et_pwd1.setInputType(129);
        et_pwd2.setInputType(129);
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
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
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

        String loginName = CommonUtil.StringHandle(et_username.getText().toString());
        String password = CommonUtil.StringHandle(et_pwd1.getText().toString());
        String pwd_confirm = CommonUtil.StringHandle(et_pwd2.getText().toString());

        String resMsg = checkDataValid(loginName,password,pwd_confirm);
        if(!resMsg.equals("")){
            showResponse(resMsg);
            return;
        }

        Client client=new Client();
        client.setLoginName(loginName);
        client.setPassword(password);

        HttpUtil.sendPost(UrlConstants.REGISTER_URL, client, new okhttp3.Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String data = response.body().string();
                Log.i(TAG, "onResponse: "+data);
                if (data.equals(AndroidConstants.REGISTER_SUCCESS)) {
                    showResponse("注册成功");
                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else if(data.equals(AndroidConstants.REGISTER_FAIL)){
                    showResponse("注册失败");
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
        return "";
    }
}