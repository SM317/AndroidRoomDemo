package com.example.androidmvvmarchitecturedemo.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidmvvmarchitecturedemo.Models.Employee;
import com.example.androidmvvmarchitecturedemo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeAdaptor extends RecyclerView.Adapter<EmployeeAdaptor.EmployeeViewHolder> {

    private List<Employee> employees = new ArrayList<>();
    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_item,parent,false);
        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee current = employees.get(position);
        holder.tv_Name.setText(current.getEmpName());
        holder.tv_Desgn.setText(current.getDesignation());
        holder.tv_Emp.setText(String.valueOf(current.getEmpNo()));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void setNotes(List<Employee> employees){
        this.employees = employees;
        this.notifyDataSetChanged();
    }

    public Employee getEmployeeAt(int position){
        return employees.get(position);
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_Name,tv_Desgn,tv_Emp;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.text_view_name);
            tv_Desgn = itemView.findViewById(R.id.text_view_desg);
            tv_Emp = itemView.findViewById(R.id.text_view_emp_no);
        }
    }
}
