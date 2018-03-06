package app.ifox.com.eopcandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import app.ifox.com.eopcandroid.Adapter.SpaceAdapter;
import app.ifox.com.eopcandroid.R;
import app.ifox.com.eopcandroid.model.NineGridTestModel;

/**
 * Created by 13118467271 on 2018/3/5.
 */

public class SpaceActivity extends AppCompatActivity implements View.OnClickListener {
    private List<NineGridTestModel> mList = new ArrayList<>();
    private RefreshLayout postRefreshLayout;
    private RecyclerView postRecycle;
    private FloatingActionButton fAButton;
    public ImageLoader imageLoader;
    private SpaceAdapter mAdapter;
    private Button back;
    private TextView backText;
    private String[] mUrls = new String[]{
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://img3.fengniao.com/forum/attachpics/537/165/21472986.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=ea218b2c5566d01661199928a729d498/a08b87d6277f9e2fd4f215e91830e924b999f308.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2644422079,4250545639&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1444023808,3753293381&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=882039601,2636712663&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=4119861953,350096499&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2437456944,1135705439&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3251359643,4211266111&fm=21&gp=0.jpg",
            "http://img4.duitang.com/uploads/item/201506/11/20150611000809_yFe5Z.jpeg",
            "http://img5.imgtn.bdimg.com/it/u=1717647885,4193212272&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2024625579,507531332&fm=21&gp=0.jpg"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.space);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(SpaceActivity.this));
        initListData();
        initView();

    }
    private void initView() {
        postRefreshLayout = (RefreshLayout) findViewById(R.id.post_refreshLayout);
        postRecycle = (RecyclerView) findViewById(R.id.post_rcycle);
        fAButton = (FloatingActionButton) findViewById(R.id.post_add);
        back = (Button) findViewById(R.id.space_back_button);
        backText = (TextView) findViewById(R.id.space_back_text);
        back.setOnClickListener(this);
        backText.setOnClickListener(this);
        fAButton.setOnClickListener(this);
        postRecycle.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SpaceAdapter(this);
        mAdapter.setList(mList);
//        setLislen(mAdapter);
        postRecycle.setAdapter(mAdapter);
        postRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //这里实现加载最新数据
                refreshlayout.finishRefresh(2000);
            }
        });
        postRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                //实现加载更多数据
                refreshlayout.finishLoadmore(2000);
            }
        });
    }
    public void initListData() {
        NineGridTestModel model1 = new NineGridTestModel();
        model1.urlList.add(mUrls[0]);
        mList.add(model1);

        NineGridTestModel model2 = new NineGridTestModel();
        model2.urlList.add(mUrls[4]);
        mList.add(model2);
//
//        NineGridTestModel model3 = new NineGridTestModel();
//        model3.urlList.add(mUrls[2]);
//        mList.add(model3);

        NineGridTestModel model4 = new NineGridTestModel();
        for (int i = 0; i < mUrls.length; i++) {
            model4.urlList.add(mUrls[i]);
        }
        model4.isShowAll = false;
        mList.add(model4);

        NineGridTestModel model5 = new NineGridTestModel();
        for (int i = 0; i < mUrls.length; i++) {
            model5.urlList.add(mUrls[i]);
        }
        model5.isShowAll = true;//显示全部图片
        mList.add(model5);

        NineGridTestModel model6 = new NineGridTestModel();
        for (int i = 0; i < 9; i++) {
            model6.urlList.add(mUrls[i]);
        }
        mList.add(model6);

        NineGridTestModel model7 = new NineGridTestModel();
        for (int i = 3; i < 7; i++) {
            model7.urlList.add(mUrls[i]);
        }
        mList.add(model7);

        NineGridTestModel model8 = new NineGridTestModel();
        for (int i = 3; i < 6; i++) {
            model8.urlList.add(mUrls[i]);
        }
        mList.add(model8);
    }
//    public void setLislen(SpaceAdapter mAdapter) {
//        mAdapter.setOnItemClickListener(new SpaceAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.post_add:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                try {
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                break;
            case R.id.space_back_button:
            case R.id.space_back_text:
                Intent backIntent = new Intent(SpaceActivity.this,MapActivity.class);
                startActivity(backIntent);
                finish();
                break;
            default:
                    break;
        }
    }
}


