package com.fed.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.fedclient.R;

import com.fed.Data.User;
import com.fed.util.SharedPreferencesUtil;
import com.fed.util.Util;
import com.fed.util.CommonRequest;
import com.fed.util.CommonResponse;
import com.fed.util.HttpUtil;
import com.fed.util.Consts;
import com.fed.util.UserManager;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText et_Username;
    private EditText et_password;
    private Button btn_login;
    private Button btn_register;
    private CheckBox isRememberPwd;
    private String username,password,spPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initComponents();
        setListeners();
        LitePal.initialize(this);

        SharedPreferencesUtil spu = new SharedPreferencesUtil(this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean isRemember = (Boolean) spu.getParam("isRememberPwd",false);
        String account = (String) spu.getParam("account","");
        String password = (String) spu.getParam("password","");
        if(!account.equals("") && !password.equals("")) {
            if (isRemember) {
                et_Username.setText(account);
                et_password.setText(password);
                isRememberPwd.setChecked(true);
            }
        }
    }

    void initComponents(){
        et_Username = findViewById(R.id.et_Username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_Login);
        btn_register = findViewById(R.id.btn_register);
        et_password.setInputType(129); //密码不可视
        isRememberPwd = findViewById(R.id.isRememberPwd);

        LitePal.getDatabase();
        UserManager.clear();
    }

    void setListeners(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
                //denglu
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Login(){
        CommonRequest request = new CommonRequest();

        username = Util.StringHandle(et_Username.getText().toString());
        password = Util.StringHandle(et_password.getText().toString());

        String resMsg = checkDataValid(username, password);
        if (!resMsg.equals((""))) {
            showResponse(resMsg);
            return;
        }

        request.addRequestParam("username",username);
        request.addRequestParam("pwd",password);

        // POST请求
        HttpUtil.sendPost(Consts.URL_Login, request.getJsonStr(), new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                CommonResponse res = new CommonResponse(response.body().string());
                String resCode = res.getResCode();
                String resMsg = res.getResMsg();
                // 登录成功
                if (resCode.equals(Consts.SUCCESSCODE_LOGIN)) {
                    // 查找本地数据库中是否已存在当前用户,不存在则新建用户并写入
                    User user = DataSupport.where("username=?",username).findFirst(User.class);
                    if(user == null){
                        user = new User();
                        user.setUsername(username);
                        user.setPassword(password);
                    }
                    //UserManager.setCurrentUser(user);// 设置当前用户
                }
                showResponse(resMsg);
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
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String checkDataValid(String username,String pwd){
        if(TextUtils.isEmpty(username) | TextUtils.isEmpty(pwd))
            return getResources().getString(R.string.null_hint);
        if(username.length() != 11 && !username.contains("@"))
            return getResources().getString(R.string.account_invalid_hint);
        return "";
    }

}
