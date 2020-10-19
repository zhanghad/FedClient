package com.fed.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.fedclient.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        EditText user_name = findViewById(R.id.user_name);
        EditText password = findViewById(R.id.password);
        Button btn_register = findViewById(R.id.btn_register);
        Button btn_login=findViewById(R.id.btnLogin); //获取xml中的元素

        //注册控件的点击事件
        btn_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //为了跳转到注册界面，并实现注册功能
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        //登录按钮的点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = user_name.getText().toString().trim();
                String Password =password.getText().toString().trim();

                String init_name = "name";
                String init_passwd = "123456";

                if(Username.equals(init_name) && Password.equals(init_passwd)){
                    Toast.makeText(MainActivity.this,"登陆成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,"密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
