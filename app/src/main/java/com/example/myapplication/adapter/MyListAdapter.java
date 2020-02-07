package com.example.myapplication.adapter;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;  
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;  
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Student_result_Vo;

import java.util.List;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private List<Student_result_Vo> listdata;
  
   // RecyclerView recyclerView;  
    public MyListAdapter(List<Student_result_Vo> listdata) {
        this.listdata = listdata;  
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.add_student_mark_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }  
  
    @Override  
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Student_result_Vo myListData = listdata.get(position);
        holder.edt_enroll_number.setText(myListData.getEnrollNumber());

        holder.theory_marks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                myListData.setTheoryMark(editable.toString());
                listdata.set(position,myListData);

            }
        });

                holder.practical_marks.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                myListData.setPracticleMark(editable.toString());
                listdata.set(position,myListData);

            }
        });

    }

    public List<Student_result_Vo> getListdata() {
        return listdata;
    }
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {  

        public TextView edt_enroll_number;
        public EditText theory_marks;
        public EditText practical_marks;

        public ViewHolder(View itemView) {  
            super(itemView);  

            this.edt_enroll_number = (TextView) itemView.findViewById(R.id.edt_enroll_number);
            this.theory_marks = (EditText) itemView.findViewById(R.id.theory_marks);
            this.practical_marks = (EditText) itemView.findViewById(R.id.practical_marks);

        }  
    }  
}  