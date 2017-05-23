package com.zachary.astro.ui.Guide;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zachary.astro.R;
import com.zachary.astro.base.BaseViewHolder;
import com.zachary.astro.model.Events;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by user on 23/5/2017.
 */

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder>{
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:sss");
    private SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
    private final static int TYPE_LOADING = 1;
    private final static int TYPE_ITEM = 0;

    private List<Events> eventsList;

    public GuideAdapter(List<Events> eventsList){
        this.eventsList = eventsList;
        dateFormatter.setTimeZone(TimeZone.getDefault());
        formatter.setTimeZone(TimeZone.getDefault());
    }

    public void addList(List<Events> eventsList){
        this.eventsList.addAll(eventsList);
        notifyDataSetChanged();
    }

    public void clearList(){
        eventsList.clear();
        notifyDataSetChanged();
    }

    public void showProgress(boolean show){
        if (show){
            if (!eventsList.contains(null)){
                eventsList.add(null);
            }
        }else {
            eventsList.remove(null);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (eventsList.get(position) == null) return TYPE_LOADING;
        return TYPE_ITEM;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            default:
            case TYPE_ITEM:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guide,parent,false);
                return new ViewHolderEvent(view);
            }
            case TYPE_LOADING:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false);
                return new ViewHolder(view);
            }
        }
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

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolderEvent extends ViewHolder{
        TextView tvChannelName;
        TextView tvProgrammeTitle;
        TextView tvProgrammeTime;

        public ViewHolderEvent(View itemView) {
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
            try{
                Date date = dateFormatter.parse(model.getDisplayDateTime());
                tvProgrammeTime.setText(formatter.format(date));
            }catch (Exception e){
                e.printStackTrace();
                tvProgrammeTime.setText(model.getDisplayDateTime());
            }

        }
    }
}
