package com.zachary.astro.ui.Main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zachary.astro.R;
import com.zachary.astro.base.BaseViewHolder;
import com.zachary.astro.model.ChannelList;

import java.util.List;

/**
 * Created by user on 22/5/2017.
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder>{
    private List<ChannelList> channelList;
    private OnFavouriteClickListener onFavouriteClickListener;

    public ChannelAdapter(@NonNull List<ChannelList> channelList){
        this.channelList = channelList;
    }

    public void replaceData(@NonNull List<ChannelList> channelList){
        this.channelList = channelList;
        notifyDataSetChanged();
    }

    public void setOnFavouriteClickListener(OnFavouriteClickListener onFavouriteClickListener){
        this.onFavouriteClickListener = onFavouriteClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(channelList.get(position));
    }

    @Override
    public int getItemCount() {
        return channelList.size();
    }

    public class ViewHolder extends BaseViewHolder<ChannelList> implements View.OnClickListener{
        TextView tvChannelNumber;
        TextView tvChannelName;
        ImageView ivFavourite;

        public ViewHolder(View itemView) {
            super(itemView);
            tvChannelNumber = (TextView) itemView.findViewById(R.id.tvChannelNumber);
            tvChannelName = (TextView) itemView.findViewById(R.id.tvChannelName);
            ivFavourite = (ImageView) itemView.findViewById(R.id.ivFavourite);
            itemView.setOnClickListener(this);
        }

        @Override
        public void bindData(ChannelList model) {
            super.bindData(model);
            tvChannelNumber.setText(String.valueOf(model.getChannelStbNumber()));
            tvChannelName.setText(model.getChannelTitle());
            ivFavourite.setSelected(model.isFavourite());
        }

        @Override
        public void onClick(View v) {
            if (onFavouriteClickListener != null) onFavouriteClickListener.onFavouriteClick(model);
        }
    }

    public interface OnFavouriteClickListener{
        void onFavouriteClick(ChannelList channel);
    }
}
