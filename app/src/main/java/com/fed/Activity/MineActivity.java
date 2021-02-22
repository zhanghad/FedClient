package com.fed.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.fedclient.R;
import org.deeplearning4j.nn.workspace.LayerWorkspaceMgr;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fed.util.PhotoUtil;
import com.fed.Data.User;
import com.fed.util.ACache;
import com.fed.util.Server;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

public class MineActivity extends Activity {
    private LayerWorkspaceMgr.Builder Glide;

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;

    private ImageView iv_head;//头像显示
    private Button btn_takephoto;//拍照
    private Button btn_fromalbum;//相册
    private Uri imageUri;

    private TextView tx_account;
    private TextView tx_Userscore;
    private Button bt_nickname;
    private Button bt_password;

    private ArrayList<String> heightList = new ArrayList<String>();
    private PhotoUtil photoUtil = new PhotoUtil();
    private ACache aCache;
    private Handler handler;
    private Server server = new Server(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        initView();
        setListeners();
        initData();
    }

    void initView(){

    }
}
