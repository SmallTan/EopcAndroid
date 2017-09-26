package app.ifox.com.eopcandroid.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import app.ifox.com.eopcandroid.R;
import app.ifox.com.eopcandroid.util.MapUtil;


public class MapActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigation;
    private MapView mMapView;
    AMap aMap;
    MapUtil mapUtil;

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
}
