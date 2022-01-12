package com.example.androidmvvmarchitecturedemo.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "employee")
public class Employee {
    private String empName;
    @ColumnInfo(name = "deg")
    private String designation;
    @PrimaryKey()
    private int empNo;

    public Employee(String empName, String designation, int empNo) {
        this.empName = empName;
        this.designation = designation;
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public String getDesignation() {
        return designation;
    }

    public int getEmpNo() {
        return empNo;
    }
}
