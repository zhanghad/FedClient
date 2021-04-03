package com.fedclient.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.fedclient.R;
import com.fedclient.constants.AndroidConstants;
import com.fedclient.constants.UrlConstants;
import com.fedclient.domain.Client;
import com.fedclient.domain.TaskPublished;
import com.fedclient.manager.DeviceManager;
import com.fedclient.util.SharedPreferencesUtil;
import com.fedclient.util.CommonUtil;
import com.fedclient.util.CommonRequest;
import com.fedclient.util.HttpUtil;
import com.fedclient.manager.ClientManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
//import org.litepal.LitePal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Response;


/**
 * 登录页面
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private Button btn_register;
    private CheckBox isRememberPwd;
    private String loginName,password,spPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initComponents();
        setListeners();

        SharedPreferencesUtil spu = new SharedPreferencesUtil(this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean isRemember = (Boolean) spu.getParam("isRememberPwd",false);
        String account = (String) spu.getParam("account","");
        String password = (String) spu.getParam("password","");
        if(!account.equals("") && !password.equals("")) {
            if (isRemember) {
                et_username.setText(account);
                et_password.setText(password);
                isRememberPwd.setChecked(true);
            }
        }

        DeviceManager.setMcontext(getApplicationContext());
        try {
            Log.i(TAG, "onCreate: "+ DeviceManager.getDeviceInstance().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始化组件
     */
    private void initComponents(){
        et_username = findViewById(R.id.et_Username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_Login);
        btn_register = findViewById(R.id.btn_register);
        et_password.setInputType(129); //密码不可视
        isRememberPwd = findViewById(R.id.isRememberPwd);
        ClientManager.clear();
    }

    /**
     * 设置监听
     */
    private void setListeners(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    /**
     * 登录逻辑
     */
    private void Login(){
        loginName = CommonUtil.StringHandle(et_username.getText().toString());
        password = CommonUtil.StringHandle(et_password.getText().toString());

        if(loginName.equals("")&&password.equals("")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        String resMsg = checkDataValid(loginName, password);
        if (!resMsg.equals((""))) {
            showResponse(resMsg);
            return;
        }

        //暂未考虑加密
        CommonRequest request = new CommonRequest();
        HttpUrl url=HttpUrl.parse(UrlConstants.LOGIN_URL).newBuilder()
                .addQueryParameter("loginName", loginName)
                .addQueryParameter("password",password)
                .build();

        /**
         * 发送登录请求
         */
        HttpUtil.sendPost(url.toString(), request.toString(), new okhttp3.Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String data = response.body().string();
                Type clientType = new TypeToken<Client>(){}.getType();
                Client client= new Gson().fromJson(data,clientType);
                // 登录成功
                if (client!=null) {
                    Log.d(TAG, "onResponse: "+client.toString());
                    ClientManager.setCurrentClient(client);
                    showResponse(AndroidConstants.SUCCESS);
                    DeviceManager.getDeviceInstance().setClientId(client.getClientId());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Log.d(TAG, "onResponse: LOGIN FAIL");
                    showResponse(AndroidConstants.FAIL);
                }

            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                showResponse("Network ERROR");
            }
        });
    }

    private void showResponse(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *  登录信息前端检测
     * @param username
     * @param pwd
     * @return
     */
    private String checkDataValid(String username,String pwd){
        if(TextUtils.isEmpty(username) | TextUtils.isEmpty(pwd))
            return getResources().getString(R.string.null_hint);
        return "";
    }

}
