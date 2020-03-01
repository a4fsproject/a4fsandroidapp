package com.example.myapplication.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.StudentMarkResultVo;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.util.List;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;


public class StudentMarkListAdapter extends RecyclerView.Adapter<StudentMarkListAdapter.ViewHolder>{
    private List<StudentMarkResultVo> listdata;

   // RecyclerView recyclerView;
    public StudentMarkListAdapter(List<StudentMarkResultVo> listdata) {
        this.listdata = listdata;  
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.layout_show_marks_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }  
  
    @Override  
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final StudentMarkResultVo myListData = listdata.get(position);

    }

    public List<StudentMarkResultVo> getListdata() {
        return listdata;
    }
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_cplus;
        CircularProgressIndicator cplus_theory,cplus_practical,cplus_total;

        public ViewHolder(View itemView) {  
            super(itemView);

            txt_cplus=(TextView)itemView.findViewById(R.id.txt_cplus);

            cplus_theory=(CircularProgressIndicator)itemView.findViewById(R.id.cplus_theory);
            cplus_practical=(CircularProgressIndicator)itemView.findViewById(R.id.cplus_practical);
            cplus_total=(CircularProgressIndicator)itemView.findViewById(R.id.cplus_total);
        }  
    }  
}  