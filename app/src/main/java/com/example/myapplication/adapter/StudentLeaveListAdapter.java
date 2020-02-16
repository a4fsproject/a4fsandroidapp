package com.example.myapplication.adapter;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.StudentLeaveResultVo;
import com.example.myapplication.model.Student_result_Vo;
import com.example.myapplication.model.StudentleaveInfoVo;

import java.util.List;


public class StudentLeaveListAdapter extends RecyclerView.Adapter<StudentLeaveListAdapter.ViewHolder>{
    private List<StudentLeaveResultVo> listdata;

   // RecyclerView recyclerView;
    public StudentLeaveListAdapter(List<StudentLeaveResultVo> listdata) {
        this.listdata = listdata;  
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.layout_leave_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }  
  
    @Override  
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final StudentLeaveResultVo myListData = listdata.get(position);

        holder.txt_reason.setText(myListData.getReason());
        holder.txt_from_date.setText(myListData.getFromDate());
        holder.txt_to_date.setText(myListData.getToDate());
        holder.txt_student_info.setText(myListData.getSName() + "(" + myListData.getSEnrollNumber() + ")");



    }

    public List<StudentLeaveResultVo> getListdata() {
        return listdata;
    }
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {  

        public ImageView img_leave;
        public TextView txt_from_date;
        public TextView txt_to_date;
        public TextView txt_student_info;
        public TextView txt_reason;
        public Button btn_accept;
        public Button btn_reject;


        public ViewHolder(View itemView) {  
            super(itemView);  

            this.img_leave = (ImageView) itemView.findViewById(R.id.img_leave);
            this.txt_from_date= (TextView) itemView.findViewById(R.id.txt_from_date);
            this.txt_to_date = (TextView) itemView.findViewById(R.id.txt_to_date);
            this.txt_reason = (TextView) itemView.findViewById(R.id.txt_reason);
            this.txt_student_info = (TextView) itemView.findViewById(R.id.txt_student_info);
            this.btn_accept = (Button) itemView.findViewById(R.id.btn_accept);
            this.btn_reject = (Button) itemView.findViewById(R.id.btn_reject);
        }  
    }  
}  