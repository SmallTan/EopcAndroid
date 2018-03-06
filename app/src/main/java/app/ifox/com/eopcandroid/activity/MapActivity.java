package app.ifox.com.eopcandroid.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import app.ifox.com.eopcandroid.R;
import app.ifox.com.eopcandroid.util.MapUtil;


public class MapActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
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

//    private MenuItem itemUser;
//    private MenuItem itemFriends;
//    private MenuItem itemNotice;
//    private MenuItem itemFeedback;
//    private MenuItem itemSetup;

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
        mDrawerToggle.syncState();
        mNavigation = (NavigationView)findViewById(R.id.nv_user);
        mNavigation.setNavigationItemSelectedListener(this);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chat_room:
                Toast.makeText(MapActivity.this,"聊天室",Toast.LENGTH_SHORT).show();
                Intent mapIntentChatRoom = new Intent(MapActivity.this,ChatRoomActivity.class);
                startActivity(mapIntentChatRoom);

                break;
            case R.id.scan_qr_code:
                Intent mapIntentToCapActivity = new Intent(MapActivity.this, CaptureActivity.class);
                startActivityForResult(mapIntentToCapActivity, REQUEST_CODE);
                break;
            case R.id.go_space:
                Intent spaceIntent = new Intent(MapActivity.this,SpaceActivity.class);
                startActivity(spaceIntent);
                finish();

                Toast.makeText(MapActivity.this,"空间",Toast.LENGTH_SHORT).show();
                break;

            default:
                break;

        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_user) {
            Toast.makeText(getApplicationContext(), "我", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.item_feedback){

        }else if (id == R.id.item_notice){

        }else if (id == R.id.item_setup){

        }
        mDrawerLayout = (DrawerLayout)findViewById(R.id.map_drawerlayout);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.item_user) {
            Intent intent = new Intent(MapActivity.this,UserMessageActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(getApplicationContext(), "我", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.item_feedback){
            Intent feedbackIntent = new Intent(MapActivity.this,FeedBackActivity.class);
        }else if (id == R.id.item_notice){
            Intent noticeIntent = new Intent(MapActivity.this,NoticeActivity.class);
            startActivity(noticeIntent);
            finish();
        }else if (id == R.id.item_setup){
            Intent setupIntent = new Intent(MapActivity.this,SetUpActivity.class);
            startActivity(setupIntent);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.map_drawerlayout);

        return true;
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
