package com.fed.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import com.fedclient.R;
import org.deeplearning4j.nn.workspace.LayerWorkspaceMgr;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MineActivity extends Activity {
    private RadioButton RB_home;
    private RadioButton RB_history;
    private RadioButton RB_now;
    private RadioButton RB_shezhi;
    private LayerWorkspaceMgr.Builder Glide;

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;

    private ImageView iv_head;//头像显示
    private Button btn_takephoto;//拍照
    private Button btn_fromalbum;//相册
    private Uri imageUri;

    private List<Map<String, Object>> lists;
    private SimpleAdapter adapter;
    private ListView listView;

    private String[] theme = {"姓名", "ID", "积分", "手机号"};
    private String[] content = {"name", "001", "20", "139***0434"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        lists = new ArrayList<>();
        for (int i = 0; i < theme.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("theme", theme[i]);
            map.put("content", content[i]);
            lists.add(map);
        }

        adapter = new SimpleAdapter(MineActivity.this, lists, R.layout.item_group_layout, new String[]{"theme", "content"}, new int[]{R.id.title_tv, R.id.content_edt});
        listView = (ListView) findViewById(R.id.person_list);
        listView.setAdapter(adapter);

        RB_home = (RadioButton) findViewById(R.id.RB_home);
        RB_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_history = (RadioButton) findViewById(R.id.RB_history);
        RB_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this, HistoryActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_now = (RadioButton) findViewById(R.id.RB_now);
        RB_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this, NowActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_shezhi = (RadioButton) findViewById(R.id.RB_shezhi);
        RB_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this, ShezhiActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_takephoto = (Button) findViewById(R.id.btn_takephoto);
        btn_fromalbum = (Button) findViewById(R.id.btn_fromalbum);
        iv_head = (ImageView) findViewById(R.id.iv_head);

        btn_takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(Environment.getExternalStorageDirectory(), "tempImage.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.media.action. IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

        btn_fromalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(Environment.getExternalStorageDirectory(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                intent.putExtra("crop",true);
                intent.putExtra("scale",true);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent,CROP_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream
                                (getContentResolver()
                                        .openInputStream(imageUri));
                        iv_head.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
