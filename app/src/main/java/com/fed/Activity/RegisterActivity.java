package com.fed.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fedclient.R;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private Button btn_register;//注册按钮
    private Button to_login; //返回按钮
    private EditText et_user_name, et_psw1, et_psw2;
    private String user_name, psw1, psw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        btn_register=findViewById(R.id.btn_register);
        to_login=findViewById(R.id.to_login);

        et_user_name=findViewById(R.id.et_username);
        et_psw1=findViewById(R.id.et_psw1);
        et_psw2=findViewById(R.id.et_psw2);

        //返回
        to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //为了跳转到登录界面
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //注册按钮
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if(!psw1.equals(psw2)){
                    Toast.makeText(RegisterActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                }else if(isExistUserName(user_name)){
                    Toast.makeText(RegisterActivity.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    saveRegisterInfo(user_name, psw1);
                    //注册成功后把账号传递到LoginActivity.java中
                    // 返回值到loginActivity显示
                    Intent data = new Intent();
                    data.putExtra("userName", user_name);
                    setResult(RESULT_OK, data);
                    //RESULT_OK为Activity系统常量，状态码为-1，
                    // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                    RegisterActivity.this.finish();
                }
            }
        });
    }

    private void getEditString() {
        user_name=et_user_name.getText().toString().trim();
        psw1=et_psw1.getText().toString().trim();
        psw2=et_psw2.getText().toString().trim();
    }

    private void saveRegisterInfo(String user_name, String psw1) {
        String md5Psw = MD5Utils.md5(psw1);//把密码用MD5加密
        //loginInfo表示文件名, mode_private SharedPreferences sp = getSharedPreferences( );
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器， SharedPreferences.Editor  editor -> sp.edit();
        SharedPreferences.Editor editor=sp.edit();
        //以用户名为key，密码为value保存在SharedPreferences中
        //key,value,如键值对，editor.putString(用户名，密码）;
        editor.putString(user_name, md5Psw);
        //提交修改 editor.commit();
        editor.commit();
    }

    private boolean isExistUserName(String user_name) {
        boolean has_userName=false;
        //mode_private SharedPreferences sp = getSharedPreferences( );
        // "loginInfo", MODE_PRIVATE
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取密码
        String spPsw=sp.getString(user_name, "");//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }
}
