package app.ifox.com.eopcandroid.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import app.ifox.com.eopcandroid.R;
import app.ifox.com.eopcandroid.util.MapUtil;


public class MapActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE = 1;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigation;
    private MapView mMapView;
    AMap aMap;
    MapUtil mapUtil;

    private Button chatRoom;
    private Button mScannr;
    private Button mSpace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_map);
        init(savedInstanceState);
    }
    protected void onPause(){
        super.onPause();
        mapUtil.getMapView().onPause();
    }
    protected void onResume(){
        super.onResume();
        mapUtil.getMapView().onResume();
    }
    protected void onDestroy(){
        super.onDestroy();
        mapUtil.getMapView().onDestroy();
        if (null != mapUtil.getmLocationClient()){
            mapUtil.getmLocationClient().stopLocation();
            mapUtil.getmLocationClient().onDestroy();
        }
    }
    /**
     * 界面初始化，实例化布局文件
     */
    private void init(Bundle savedInstanceState) {
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.map_drawerlayout);
        mNavigation = (NavigationView) findViewById(R.id.nv_user);
        mMapView = (MapView) findViewById(R.id.map_view);
        aMap = mMapView.getMap();
        mapUtil = new MapUtil(mMapView,MapActivity.this,savedInstanceState);

        chatRoom = (Button) findViewById(R.id.chat_room);
        mScannr = (Button) findViewById(R.id.scan_qr_code);
        mSpace = (Button) findViewById(R.id.go_space);
        chatRoom.setOnClickListener(this);
        mScannr.setOnClickListener(this);
        mSpace.setOnClickListener(this);

        mToolbar.setTitle("园码时代");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.user_message, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chat_room:
                Toast.makeText(MapActivity.this,"聊天室",Toast.LENGTH_SHORT).show();
                break;
            case R.id.scan_qr_code:
                Intent intent = new Intent(MapActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.go_space:
                Toast.makeText(MapActivity.this,"空间",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MapActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }

        }
    }
}
