package com.example.myapplication.adapter;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.AssignmentResultVo;
import com.example.myapplication.model.StudentResultVo;

import java.util.List;


public class AssignmentListAdapter extends RecyclerView.Adapter<AssignmentListAdapter.ViewHolder> {
    private List<AssignmentResultVo> listdata;

    // RecyclerView recyclerView;
    public AssignmentListAdapter(List<AssignmentResultVo> listdata) {
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_assignment_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final AssignmentResultVo myListData = listdata.get(position);
        holder.sub_name.setText(myListData.getSubName());
        holder.txt_issue_date.setText(myListData.getIssueDate());
        holder.txt_last_date.setText(myListData.getLastDate());
        holder.txt_assignment_details.setText("Note : " + myListData.getAssignDetails());
    }

    public List<AssignmentResultVo> getListdata() {
        return listdata;
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView sub_name;
        public TextView txt_issue_date;
        public TextView txt_last_date;
        public ImageView img_assignment_file;
        public TextView txt_assignment_details;


        public ViewHolder(View itemView) {
            super(itemView);

            this.sub_name = (TextView) itemView.findViewById(R.id.sub_name);
            this.txt_issue_date = (TextView) itemView.findViewById(R.id.txt_issue_date);
            this.txt_last_date = (TextView) itemView.findViewById(R.id.txt_last_date);
            this.img_assignment_file = (ImageView) itemView.findViewById(R.id.img_assignment_file);
            this.txt_assignment_details = (TextView) itemView.findViewById(R.id.txt_assignment_details);

        }
    }
}  