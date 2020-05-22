package com.inube.demonav;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.ViewHolder> {
    private List<NavDrawerModel> mList;
    private Context mContext;
    private OnItemSelectListener mListener;

    public NavDrawerAdapter(Context mContext) {
        this.mList = new ArrayList<>();
        this.mContext = mContext;
        this.mList = new ArrayList<>();
//        this.mListener = mListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemSelected(mList.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        NavDrawerModel model = mList.get(position);
        holder.title.setText(model.getTitle());
        holder.icon.setImageResource(model.getIcon());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                mListener.onItemSelected(mList.get(position));
////            }
////        });
    }

    @Override
    public int getItemCount() {
        return   mList != null && mList.size() > 0 ? mList.size() : 0;
    }
    public void addData(List<NavDrawerModel> notificationData) {
        mList.clear();
        mList.addAll(notificationData);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView headerText;
        ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.icon);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mListener.onItemSelected(mList.get(getAdapterPosition()));
//
//                }
//            });
        }
    }
    public void setOnItemClickLister(OnItemSelectListener mListener) {
        this.mListener = mListener;
    }
    public interface OnItemSelectListener{
        public void onItemSelected(NavDrawerModel navDrawerModel);
    }
}
