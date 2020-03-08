package com.example.myapplication.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.AssignmentResultVo;
import com.example.myapplication.model.ScheduleResultVo;

import java.util.List;


public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder> {
    private List<ScheduleResultVo> listdata;

    // RecyclerView recyclerView;
    public ScheduleListAdapter(List<ScheduleResultVo> listdata) {
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_schedule_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ScheduleResultVo myListData = listdata.get(position);
        holder.txt_schedule_type.setText(myListData.getScheduleType());
        holder.txt_schedule_note.setText("Note : " + myListData.getScheduleNote());
    }

    public List<ScheduleResultVo> getListdata() {
        return listdata;
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_schedule_type;
        public ImageView img_schedule_file;
        public TextView txt_schedule_note;


        public ViewHolder(View itemView) {
            super(itemView);

            this.txt_schedule_type = (TextView) itemView.findViewById(R.id.txt_schedule_type);
            this.img_schedule_file = (ImageView) itemView.findViewById(R.id.img_schedule_file);
            this.txt_schedule_note = (TextView) itemView.findViewById(R.id.txt_schedule_note);

        }
    }
}  