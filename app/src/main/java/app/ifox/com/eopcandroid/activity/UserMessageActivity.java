package app.ifox.com.eopcandroid.activity;

import android.Manifest;
import android.app.Activity;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
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

import app.ifox.com.eopcandroid.R;
import app.ifox.com.eopcandroid.customView.RoundImageView;
import app.ifox.com.eopcandroid.util.PermissionsActivity;
import app.ifox.com.eopcandroid.util.PermissionsChecker;
import app.ifox.com.eopcandroid.util.TakePhoto;


/**
 * Created by 13118467271 on 2017/10/8.
 */

public class UserMessageActivity extends Activity implements View.OnClickListener {
    LinearLayout myscrollLinearlayout;
    LinearLayout mainheadview; //顶部个人资料视图
    RelativeLayout mainactionbar; //顶部菜单栏
    ImageButton userMessageIntentMap;
    RoundImageView userMesaageHeader;
    TakePhoto takePhoto = new TakePhoto(this,UserMessageActivity.this,userMesaageHeader);

    private PermissionsChecker mPermissionsChecker = new PermissionsChecker(UserMessageActivity.this);; // 权限检测器
    static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};


    int Y;
    int position = 0; //拖动Linearlayout的距离Y轴的距离
    int scrollviewdistancetotop = 0; //headView的高
    int menubarHeight = 0;
    int chufaHeight = 0; //需要触发动画的高
    float scale; //像素密度
    int headViewPosition = 0;
    ImageView userinfo_topbar;
    static boolean flag = true;
    static boolean topmenuflag = true;

    private TextView nikeName;
    private TextView email;
    private TextView school;
    private TextView number;
    private TextView personalizedSignature;
    private TextView makeOnecel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_interface);
        initView();         //实现界面的可滚动性
        initListener();
    }

    private void initListener() {
        userMessageIntentMap = (ImageButton) findViewById(R.id.userinfo_returnbtn);
        userMesaageHeader = (RoundImageView) findViewById(R.id.user_message_header);
        nikeName = (TextView) findViewById(R.id.nikename_user_message);
        email = (TextView) findViewById(R.id.e_mail_user_message);
        school = (TextView) findViewById(R.id.school_user_message);
        number = (TextView) findViewById(R.id.number_user_message);
        personalizedSignature = (TextView) findViewById(R.id.personalized_signature_user_message);
        makeOnecel = (TextView) findViewById(R.id.make_onecel_user_message);

        userMessageIntentMap.setOnClickListener(this);
        userMesaageHeader.setOnClickListener(this);
        nikeName.setOnClickListener(this);
        email.setOnClickListener(this);
        school.setOnClickListener(this);
        number.setOnClickListener(this);
        personalizedSignature.setOnClickListener(this);
        makeOnecel.setOnClickListener(this);

    }

    private void initView() {
        userinfo_topbar = (ImageView) findViewById(R.id.userinfo_topbar);
        //获得像素密度
        scale = this.getResources().getDisplayMetrics().density;
        mainheadview = (LinearLayout) findViewById(R.id.mainheadview);
        mainactionbar = (RelativeLayout) findViewById(R.id.mainactionbar);
        menubarHeight = (int) (55 * scale);
        chufaHeight = (int) (110 * scale);
        scrollviewdistancetotop = (int) ((260 )*scale);
        position = scrollviewdistancetotop;
        myscrollLinearlayout = (LinearLayout) findViewById(R.id.myscrollLinearlayout);
        myscrollLinearlayout.setY( scrollviewdistancetotop); //要减去Absolote布局距离顶部的高度
        myscrollLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        //这里设置滑动效果
        myscrollLinearlayout.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下的Y的位置
                        Y = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int nowY = (int) myscrollLinearlayout.getY(); //拖动界面的Y轴位置
                        int tempY = (int) (event.getRawY() - Y); //手移动的偏移量
                        Y = (int) event.getRawY();
                        if ((nowY + tempY >= 0) && (nowY + tempY <= scrollviewdistancetotop)) {
                            if ((nowY + tempY <= menubarHeight)&& (topmenuflag == true) ){
                                userinfo_topbar.setVisibility(View.VISIBLE);
                                topmenuflag = false;
                            } else if ((nowY + tempY > menubarHeight) && (topmenuflag == flag)) {
                                userinfo_topbar.setVisibility(View.INVISIBLE);
                                topmenuflag = true;
                            }
                            int temp = position += tempY;
                            myscrollLinearlayout.setY(temp);
                            int headviewtemp = headViewPosition += (tempY/5);
                            mainheadview.setY(headviewtemp);
                        }
                        //顶部的动画效果
                        if ((myscrollLinearlayout.getY() <= chufaHeight) && (flag == true)) {
                            ObjectAnimator anim = ObjectAnimator.ofFloat(mainheadview, "alpha", 1, 0.0f);
                            anim.setDuration(500);
                            anim.start();
                            flag = false;
                        } else if ((myscrollLinearlayout.getY() > chufaHeight + 40) && (flag == false)) {
                            ObjectAnimator anim = ObjectAnimator.ofFloat(mainheadview, "alpha", 0.0f, 1f);
                            anim.setDuration(500);
                            anim.start();
                            flag = true;
                        }
                        break;
                }
                return false;
            }
        });
    }


    @Override
    public void onClick(View v) {
        View dialogView = View.inflate(UserMessageActivity.this,R.layout.user_message_input_dialog,null);
         EditText dialogEditText = (EditText) dialogView.findViewById(R.id.edittext_dialog);
         TextView dialogTextView = (TextView) dialogView.findViewById(R.id.header_dialog);
        switch (v.getId()){
            case R.id.userinfo_returnbtn:
                Intent intent = new Intent(UserMessageActivity.this,MapActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.user_message_header:
                popupChose();
                break;
            case R.id.nikename_user_message:
                AlertDialog.Builder nikenameBuilder = new AlertDialog.Builder(UserMessageActivity.this);
                nikenameBuilder.setView(dialogView);
                nikenameBuilder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                     public void onClick(DialogInterface dialog,int id){
                         String text = dialogEditText.getText().toString().trim();
                         nikeName.setText(text);
                         dialogTextView.setText("昵称");
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
            case R.id.e_mail_user_message:
//                AlertDialog.Builder emailBuilder = new AlertDialog.Builder(UserMessageActivity.this);
//                emailBuilder.setView(dialogView);
//                emailBuilder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
//                    public void onClick(DialogInterface dialog,int id){
//                        String text = dialogEditText.getText().toString().trim();
//                        email.setText(text);
//                        dialogTextView.setText("邮箱");
//                        dialog.dismiss();
//                    }
//                });
//                emailBuilder .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                AlertDialog dialogEmail = emailBuilder.create();
//                dialogEmail.show();
                break;
            case R.id.school_user_message:
//                AlertDialog.Builder schoolBuilder = new AlertDialog.Builder(UserMessageActivity.this);
//                schoolBuilder.setView(dialogView);
//                schoolBuilder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
//                    public void onClick(DialogInterface dialog,int id){
//                        String text = dialogEditText.getText().toString().trim();
//                        school.setText(text);
//                        dialogTextView.setText("学校");
//                        dialog.dismiss();
//                    }
//                });
//                schoolBuilder .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                AlertDialog dialogSchool = schoolBuilder.create();
//                dialogSchool.show();
                break;
            case R.id.number_user_message:
                break;
            case R.id.personalized_signature_user_message:
                AlertDialog.Builder personalizedBuilder = new AlertDialog.Builder(UserMessageActivity.this);
                personalizedBuilder.setView(dialogView);
                personalizedBuilder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int id){
                        String text = dialogEditText.getText().toString().trim();
                        personalizedSignature.setText(text);
                        dialogTextView.setText("个性签名");
                        dialog.dismiss();
                    }
                });
                personalizedBuilder .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogPersonal = personalizedBuilder.create();
                dialogPersonal.show();
                break;
            case R.id.make_onecel_user_message:
                AlertDialog.Builder makeOnecleBuilder = new AlertDialog.Builder(UserMessageActivity.this);
                makeOnecleBuilder.setView(dialogView);
                makeOnecleBuilder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int id){
                        String text = dialogEditText.getText().toString().trim();
                        makeOnecel.setText(text);
                        dialogTextView.setText("简介");
                        dialog.dismiss();
                    }
                });
                makeOnecleBuilder .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogMakeOnecel = makeOnecleBuilder.create();
                dialogMakeOnecel.show();
                break;
            default:
                break;
        }
    }
    public void popupChose(){
        final String[] items = new String[]{"从本地选择","拍照"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Log.e("=======================",PERMISSIONS.toString() );
        Log.e("--------------",mPermissionsChecker.toString());
        builder.setTitle("选择照片").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
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
                    userMesaageHeader.setImageBitmap(bitmap);
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
}
