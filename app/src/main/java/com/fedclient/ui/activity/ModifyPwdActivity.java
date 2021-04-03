package com.fedclient.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fedclient.constants.AndroidConstants;
import com.fedclient.constants.UrlConstants;
import com.fedclient.domain.Client;
import com.fedclient.util.CommonRequest;
import com.fedclient.util.CommonResponse;
import com.fedclient.trash.Consts;
import com.fedclient.util.HttpUtil;
import com.fedclient.manager.ClientManager;
import com.fedclient.util.CommonUtil;
import com.fedclient.R;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class ModifyPwdActivity extends Activity {

    private ImageView back;
    private EditText oldPwdEdit;
    private EditText newPwdEdit;
    private EditText confirmPwdEdit;
    private Button confirmBtn;
    private Button cancelBtn;

    private static final String TAG = "ModifyPwdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifypwd);
        initComponents();
        setListeners();
    }

    private void initComponents(){
        back = findViewById(R.id.back);
        oldPwdEdit = findViewById(R.id.modify_pwd_old);
        newPwdEdit = findViewById(R.id.modify_pwd_new);
        confirmPwdEdit = findViewById(R.id.modify_pwd_confirm);
        confirmBtn = findViewById(R.id.modify_confirm);
        cancelBtn = findViewById(R.id.modify_cancel);
    }

    private void setListeners(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 输入为空
                String oldPwd = oldPwdEdit.getText().toString();
                final String newPwd = newPwdEdit.getText().toString();
                String confirmPwd = confirmPwdEdit.getText().toString();
                if(TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd) || TextUtils.isEmpty(confirmPwd)){
                    showResponse(getResources().getString(R.string.modify_not_null));
                    return;
                }
                // 两次输入的密码不一致
                if(!newPwd.equals(confirmPwd)){
                    showResponse(getResources().getString(R.string.modify_not_equal));
                    return;
                }
                // 原密码错误
                final Client client = ClientManager.getCurrentClient();
                if(!oldPwd.equals(client.getPassword())){
                    showResponse(getResources().getString(R.string.modify_not_right));
                    return;
                }
                // 提交到服务器
                Client newClient=new Client();
                client.setLoginName(client.getLoginName());
                client.setPassword(newPwd);


                // POST请求
                HttpUtil.sendPost(UrlConstants.URL_CLIENTINFO_UPDATE, newClient, new okhttp3.Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String resMsg = response.body().string();
                        Log.d(TAG, "onResponse: "+resMsg);
                        // 修改密码成功
                        if (resMsg.equals(AndroidConstants.SUCCESS)) {
                            // 保存到本地
                            Log.d(TAG, "onResponse: "+newPwd);
                            client.setPassword(newPwd);
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
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void showResponse(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ModifyPwdActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
