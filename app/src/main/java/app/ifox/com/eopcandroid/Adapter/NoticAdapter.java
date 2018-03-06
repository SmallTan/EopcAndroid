package app.ifox.com.eopcandroid.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.ifox.com.eopcandroid.R;
import app.ifox.com.eopcandroid.customView.RoundImageView;

/**
 * Created by 13118467271 on 2018/3/3.
 */

public class NoticAdapter extends RecyclerView.Adapter<NoticAdapter.MyViewHolder>{
    private Context context;

    public NoticAdapter(Context context){
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_recyc_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout relativeLayout;
        private RoundImageView headerIamge;
        private TextView feedbackTitle;
        private TextView feedbackTime;
        private TextView feedbackContent;
        public MyViewHolder(View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.notice_relative);
            headerIamge = (RoundImageView) itemView.findViewById(R.id.notice_image);
            feedbackTitle = (TextView) itemView.findViewById(R.id.notice_title);
            feedbackTime = (TextView) itemView.findViewById(R.id.notice_time);
            feedbackContent = (TextView) itemView.findViewById(R.id.notice_content);
        }
    }
}
