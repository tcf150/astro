package com.zachary.astro.ui.Guide;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zachary.astro.R;
import com.zachary.astro.base.BaseViewHolder;
import com.zachary.astro.model.Events;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by user on 23/5/2017.
 */

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder>{
    private SimpleDateFormat formatter = new SimpleDateFormat("HH:MM");

    private List<Events> eventsList;

    public GuideAdapter(List<Events> eventsList){
        this.eventsList = eventsList;
    }

    public void addList(List<Events> eventsList){
        this.eventsList.addAll(eventsList);
        notifyDataSetChanged();
    }

    public void clearList(){
        eventsList.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guide,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(eventsList.get(position));
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class ViewHolder extends BaseViewHolder<Events>{
        TextView tvChannelName;
        TextView tvProgrammeTitle;
        TextView tvProgrammeTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvChannelName = (TextView) itemView.findViewById(R.id.tvChannelName);
            tvProgrammeTitle = (TextView) itemView.findViewById(R.id.tvProgrammeTitle);
            tvProgrammeTime = (TextView) itemView.findViewById(R.id.tvProgrammeTime);
        }

        @Override
        public void bindData(Events model) {
            super.bindData(model);
            tvChannelName.setText(model.getChannelTitle());
            tvProgrammeTitle.setText(model.getProgrammeTitle());
            tvProgrammeTime.setText(formatter.format(model.getGetDisplayDateTime()));
        }
    }
}
