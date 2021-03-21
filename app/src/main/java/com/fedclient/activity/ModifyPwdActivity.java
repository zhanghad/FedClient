package com.fedclient.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fedclient.domain.Client;
import com.fedclient.util.CommonRequest;
import com.fedclient.util.CommonResponse;
import com.fedclient.trash.Consts;
import com.fedclient.util.HttpUtil;
import com.fedclient.manager.ClientManager;
import com.fedclient.util.Util;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifypwd);

        initView();
        setListeners();
    }

    void initView(){
        back = findViewById(R.id.back);
        oldPwdEdit = findViewById(R.id.modify_pwd_old);
        newPwdEdit = findViewById(R.id.modify_pwd_new);
        confirmPwdEdit = findViewById(R.id.modify_pwd_confirm);
        confirmBtn = findViewById(R.id.modify_confirm);
        cancelBtn = findViewById(R.id.modify_cancel);
    }

    void setListeners(){
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
                    Util.makeToast(ModifyPwdActivity.this,getResources().getString(R.string.modify_not_null));
                    return;
                }
                // 两次输入的密码不一致
                if(!newPwd.equals(confirmPwd)){
                    Util.makeToast(ModifyPwdActivity.this,getResources().getString(R.string.modify_not_equal));
                    return;
                }
                // 原密码错误
                final Client client = ClientManager.getCurrentClient();
                if(!oldPwd.equals(client.getPassword())){
                    Util.makeToast(ModifyPwdActivity.this,getResources().getString(R.string.modify_not_right));
                    return;
                }
                // 提交到服务器
                CommonRequest request = new CommonRequest();
                request.addRequestParam("loginName",client.getLoginName());
                request.addRequestParam("password",newPwd);
                // POST请求
                HttpUtil.sendPost(Consts.URL_ModifyPwd, request.getJsonStr(), new okhttp3.Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        CommonResponse res = new CommonResponse(response.body().string());
                        String resCode = res.getResCode();
                        String resMsg = res.getResMsg();
                        // 修改密码成功
                        if (resCode.equals(Consts.SUCCESSCODE_MODIFYPWD)) {
                            // 保存到本地数据库
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
