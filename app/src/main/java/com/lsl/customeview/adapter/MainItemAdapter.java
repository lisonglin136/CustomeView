package com.lsl.customeview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lsl.customeview.R;
import com.lsl.customeview.bean.MainItemBean;

import java.util.List;

/**
 * 创建者： lsl
 * 创建时间： 2017/9/9 13:16
 * 描述：
 * 修改人：
 * 修改时间：
 * 备注：
 */
public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.MyViewHolder> {

    private Context mContext;
    private List<MainItemBean> mList;

    private OnItemClickListener mOnItemClickLitener;

    public MainItemAdapter(Context context, List<MainItemBean> list) {
        mContext = context;
        mList = list;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickLitener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_main, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mTv.setText(mList.get(position).getName());
        Glide.with(mContext).load(mList.get(position).getImageId()).into(holder.mIv);

        if (mOnItemClickLitener != null) {
            holder.mBgLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(view,layoutPosition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout mBgLl;
        ImageView mIv;
        TextView mTv;

        public MyViewHolder(View view)
        {
            super(view);
            mBgLl = (LinearLayout) view.findViewById(R.id.bg_ll);
            mIv = (ImageView) view.findViewById(R.id.iv);
            mTv = (TextView) view.findViewById(R.id.tv);
        }
    }
}
