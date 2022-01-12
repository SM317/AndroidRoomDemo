package com.example.androidmvvmarchitecturedemo.ViewModel;

import android.app.Application;

import com.example.androidmvvmarchitecturedemo.Controller.EmployeeController;
import com.example.androidmvvmarchitecturedemo.Models.Employee;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EmployeeViewModel extends AndroidViewModel {
    private EmployeeController employeeController;
    private LiveData<List<Employee>> allEmployees;
    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        employeeController = new EmployeeController(application);
        allEmployees = employeeController.getemployeeList();
    }

    public void insertEmployee(Employee employee){
        employeeController.insert(employee);
    }

    public void updateEmployee(Employee employee){
        employeeController.update(employee);
    }

    public void deleteEmployee(Employee employee){
        employeeController.delete(employee);
    }

    public void deleteAllEmployee(){
        employeeController.deleteAll();
    }

    public LiveData<List<Employee>> getAllEmployees()
    {
        return allEmployees;
    }
}
