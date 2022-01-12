package com.example.androidmvvmarchitecturedemo.Database;

import com.example.androidmvvmarchitecturedemo.Models.Employee;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface EmployeeDAO {

    @Insert
    void insert(Employee employee);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);

    @Query("DELETE FROM employee")
    void deleteAllEmployees();

    @Query("Select * FROM employee ORDER BY empNo DESC")
    LiveData<List<Employee>> getAllEmployees();

}
