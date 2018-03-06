package app.ifox.com.eopcandroid.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.ifox.com.eopcandroid.R;
import app.ifox.com.eopcandroid.customView.NineGridTestLayout;
import app.ifox.com.eopcandroid.model.NineGridTestModel;


/**
 * Created by 13118467271 on 2017/12/3.
 */

public class SpaceAdapter extends RecyclerView.Adapter<SpaceAdapter.ViewHolder>{

    private Context mContext;
    private List<NineGridTestModel> mList;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    public SpaceAdapter(Context context){
        mContext = context;
        inflater = LayoutInflater.from(context);
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

//        void onItemLongClick(View view, int position);

    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    public void setList(List<NineGridTestModel> mList) {
        this.mList = mList;
        Log.d("TAG","" + mList.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(R.layout.space_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.layout.setIsShowAll(mList.get(position).isShowAll);
        holder.layout.setUrlList(mList.get(position).urlList);

        holder.spaceLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点了第" + position + "条的赞", Toast.LENGTH_SHORT).show();
                holder.likeNum.setText("" + 1);
                holder.likeImage.setImageResource(R.mipmap.like_blue);
            }
        });
        holder.spaceLike.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "取消点了第" + position + "条的赞", Toast.LENGTH_SHORT).show();
                holder.likeNum.setText("" + 0);
                holder.likeImage.setImageResource(R.mipmap.like_gray);
                return true;
            }
        });
        if (mOnItemClickListener != null){
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.layout, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        NineGridTestLayout layout;
        ImageView headImage;
        TextView userName;
        TextView contentText;
        TextView time;
        TextView likeNum;
        TextView commentNum;
        ImageView likeImage;
        RelativeLayout spaceLike;
        public ViewHolder(View itemView) {
            super(itemView);
            layout = (NineGridTestLayout) itemView.findViewById(R.id.layout_nine_grid);
            headImage = (ImageView) itemView.findViewById(R.id.space_item_user_head_image);
            userName = (TextView) itemView.findViewById(R.id.space_item_user_name);
            contentText = (TextView) itemView.findViewById(R.id.space_item_content_text);
            commentNum = (TextView) itemView.findViewById(R.id.space_comment_num);
            time = (TextView) itemView.findViewById(R.id.space_item_time);
            likeNum = (TextView) itemView.findViewById(R.id.space_like_num);
            spaceLike = (RelativeLayout) itemView.findViewById(R.id.space_like);
            likeImage = (ImageView) itemView.findViewById(R.id.space_like_image);
        }
    }
}