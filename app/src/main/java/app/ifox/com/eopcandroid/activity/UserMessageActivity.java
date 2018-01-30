package app.ifox.com.eopcandroid.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.ifox.com.eopcandroid.R;
import app.ifox.com.eopcandroid.customView.RoundImageView;
import app.ifox.com.eopcandroid.util.NetUtil;
import app.ifox.com.eopcandroid.util.PermissionsActivity;
import app.ifox.com.eopcandroid.util.PermissionsChecker;
import app.ifox.com.eopcandroid.util.TakePhoto;


/**
 * Created by 13118467271 on 2017/10/8.
 */

public class UserMessageActivity extends Activity implements View.OnClickListener {

    private RelativeLayout information_relative_username;//设置名字
    private RelativeLayout information_relative_user_email;//设置邮箱
    private RelativeLayout information_relative_user_school;//设置学校
    private RelativeLayout information_relative_user_introduce;//设置简介
    private RelativeLayout information_back;//返回
    private TextView information_user_name;//显示的名字
    private TextView information_user_email;//显示邮箱
    private RoundImageView information_user_headimage;//显示的头像
    private TextView information_user_setup_name;//设置的名字
    private TextView information_user_setup_email;//设置的邮箱
    private TextView information_user_setup_school;//设置学校
    private TextView information_user_setup_introduce;//设置个人简介
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor perEditor;
    private Integer userId;//用户的唯一id
    private String resultName,resultAvatar,resultSchool,resultResume;
    private String getDialogName,getDialogSchool,getDialogResume;
    NetUtil netUtil;
    Message msg ;
    private PermissionsChecker mPermissionsChecker = new PermissionsChecker(UserMessageActivity.this);; // 权限检测器
    static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    TakePhoto takePhoto = new TakePhoto(this,UserMessageActivity.this,information_user_headimage);

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0://修改名字
                    if (resultName == null) {
                        Toast.makeText(UserMessageActivity.this, "修改失败，请检查网络或者联系管理员", Toast.LENGTH_SHORT).show();
                    }else {
                        information_user_name.setText(getDialogName);
                        information_user_setup_name.setText(getDialogName);
                        perEditor.putString("name",getDialogName);
                        perEditor.commit();
                    }
                    break;
                case 1:
                    if (resultSchool == null) {
                        Toast.makeText(UserMessageActivity.this, "修改失败，请检查网络或者联系管理员", Toast.LENGTH_SHORT).show();
                    }else {
                        information_user_setup_school.setText(getDialogSchool);
                        perEditor.putString("school",getDialogSchool);
                        perEditor.commit();
                    }
                    break;
                case 2:
                    if (resultResume == null) {
                        Toast.makeText(UserMessageActivity.this, "修改失败，请检查网络或者联系管理员", Toast.LENGTH_SHORT).show();
                    }else {
                        information_user_setup_introduce.setText(getDialogResume);
                        perEditor.putString("introduce",getDialogResume);
                        perEditor.commit();
                    }
            }
        }
    };
    public UserMessageActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information);
        init();
    }

    private void init() {
        information_back = (RelativeLayout) findViewById(R.id.information_back);
        information_relative_username = (RelativeLayout) findViewById(R.id.information_relative_username);
        information_relative_user_email = (RelativeLayout) findViewById(R.id.information_relative_user_email);
        information_relative_user_school = (RelativeLayout) findViewById(R.id.information_relative_user_school);
        information_relative_user_introduce = (RelativeLayout) findViewById(R.id.information_relative_user_introduce);
        information_user_name = (TextView) findViewById(R.id.information_user_name);
        information_user_email = (TextView) findViewById(R.id.information_user_email);
        information_user_headimage = (RoundImageView) findViewById(R.id.information_user_headimage);
        information_user_setup_name = (TextView) findViewById(R.id.information_user_setup_name);
        information_user_setup_email = (TextView) findViewById(R.id.information_user_setup_email);
        information_user_setup_school = (TextView) findViewById(R.id.information_user_setup_school);
        information_user_setup_introduce = (TextView) findViewById(R.id.information_user_setup_introduce);
        sharedPreferences = getSharedPreferences("per_information",MODE_PRIVATE);
        perEditor = sharedPreferences.edit();
        userId = sharedPreferences.getInt("userId",0);
        netUtil = new NetUtil();
        msg = new Message();
        initMessage();
        information_back.setOnClickListener(this);
        information_relative_username.setOnClickListener(this);
        information_relative_user_email.setOnClickListener(this);
        information_relative_user_school.setOnClickListener(this);
        information_relative_user_introduce.setOnClickListener(this);
        information_user_headimage.setOnClickListener(this);

    }

    private void initMessage() {
        information_user_name.setText(sharedPreferences.getString("userName",""));
        information_user_setup_name.setText(sharedPreferences.getString("userName",""));
        information_user_setup_email.setText(sharedPreferences.getString("email",""));
        information_user_setup_school.setText(sharedPreferences.getString("school",""));
        information_user_setup_introduce.setText(sharedPreferences.getString("introduce",""));
        information_user_email.setText(sharedPreferences.getString("email",""));
    }

    @Override
    public void onClick(View v) {
        View dialogView = View.inflate(UserMessageActivity.this,R.layout.user_message_input_dialog,null);
        EditText dialogEditText = (EditText) dialogView.findViewById(R.id.edittext_dialog);
        TextView dialogTextView = (TextView) dialogView.findViewById(R.id.header_dialog);
        switch (v.getId()){
            case R.id.information_back_button:
            case R.id.information_back_text:
            case R.id.information_back:
                Intent intentInformation = new Intent(UserMessageActivity.this,MapActivity.class);
                startActivity(intentInformation);
                finish();
                break;
            case R.id.information_user_headimage:
                popupChose();
                break;
            case R.id.information_relative_username:
                AlertDialog.Builder nikenameBuilder = new AlertDialog.Builder(UserMessageActivity.this);
                nikenameBuilder.setView(dialogView);
                dialogTextView.setText("昵称");
                nikenameBuilder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int id){
                        getDialogName = dialogEditText.getText().toString().trim();
                        if (!getDialogName.equals("")) {
                            changeUserName(getDialogName);
                        }
                        dialog.dismiss();
                    }
                });
                nikenameBuilder .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogNikemane = nikenameBuilder.create();
                dialogNikemane.show();
                break;
            case R.id.information_relative_user_email:
                Toast.makeText(this, "暂不支持邮箱的修改", Toast.LENGTH_SHORT).show();
                break;
            case R.id.information_relative_user_school:
                AlertDialog.Builder schoolBuilder = new AlertDialog.Builder(UserMessageActivity.this);
                schoolBuilder.setView(dialogView);
                dialogTextView.setText("学校");
                schoolBuilder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int id){
                        getDialogSchool = dialogEditText.getText().toString().trim();
                        if (!getDialogSchool.equals("")) {
                            changeSchool(getDialogSchool);
                        }
                        dialog.dismiss();
                    }
                });
                schoolBuilder .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogSchool = schoolBuilder.create();
                dialogSchool.show();
                break;
            case R.id.information_relative_user_introduce:
                AlertDialog.Builder introduceBuilder = new AlertDialog.Builder(UserMessageActivity.this);
                introduceBuilder.setView(dialogView);
                dialogTextView.setText("个人简介");
                introduceBuilder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int id){
                        getDialogResume = dialogEditText.getText().toString().trim();
                        if (!getDialogResume.equals("")) {
                            changeIntroduce(getDialogResume);
                        }
                        dialog.dismiss();
                    }
                });
                introduceBuilder .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogIntroduce = introduceBuilder.create();
                dialogIntroduce.show();
                break;
        }
    }

    private void changeIntroduce(String getDialogResume) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "";
                String request = "userId=" + userId + "&resume=" + getDialogResume;
                resultSchool = netUtil.upInfo(url,"",request,"utf-8");
                msg.what = 2;
                handler.sendMessage(msg);
            }
        }).start();
    }

    private void changeSchool(String getDialogSchool) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "";
                String request = "userId=" + userId + "&school=" + getDialogSchool;
                resultSchool = netUtil.upInfo(url,"",request,"utf-8");
                msg.what = 1;
                handler.sendMessage(msg);
            }
        }).start();
    }

    private void changeUserName(String text) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "";
                String request = "userId=" + userId + "&userName=" + text;
                resultName = netUtil.upInfo(url,"",request,"utf-8");
                msg.what = 0;
                handler.sendMessage(msg);
            }
        }).start();
    }

    public void popupChose() {
        final String[] items = new String[]{"从本地选择", "拍照"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Log.e("=======================", PERMISSIONS.toString());
        Log.e("--------------", mPermissionsChecker.toString());
        builder.setTitle("选择照片").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:

                        //检查权限(6.0以上做权限判断)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                                takePhoto.startPermissionsActivity();
                            } else {
                                takePhoto.openCamera();
                            }
                        } else {
                            TakePhoto.openCamera();
                        }
                        TakePhoto.isClickCamera = true;

                        break;
                    case 1:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                                takePhoto.startPermissionsActivity();
                            } else {
                                TakePhoto.selectFromAlbum();
                            }
                        } else {
                            TakePhoto.selectFromAlbum();
                        }
                        TakePhoto.isClickCamera = false;

                        break;
                    default:
                        break;
                }
            }
        });
        builder.create().show();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TakePhoto.REQUEST_PICK_IMAGE://从相册选择
                if (Build.VERSION.SDK_INT >= 19) {
                    TakePhoto.handleImageOnKitKat(data);
                } else {
                    TakePhoto.handleImageBeforeKitKat(data);
                }
                break;
            case TakePhoto.REQUEST_CAPTURE://拍照
                if (resultCode == RESULT_OK) {
                    TakePhoto.cropPhoto();
                }
                break;
            case TakePhoto.REQUEST_PICTURE_CUT://裁剪完成
                Bitmap bitmap = null;
                try {
                    if (TakePhoto.isClickCamera) {

                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(TakePhoto.imageUri));
                        TakePhoto.saveBitmapToSharedPreferences(bitmap);
                    } else {
                        bitmap = BitmapFactory.decodeFile(TakePhoto.imagePath);
                    }
                    information_user_headimage.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case TakePhoto.REQUEST_PERMISSION://权限请求
                if (resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
                    finish();
                } else {
                    if (TakePhoto.isClickCamera) {
                        TakePhoto.openCamera();
                    } else {
                        TakePhoto.selectFromAlbum();
                    }
                }
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

