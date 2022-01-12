package com.example.androidmvvmarchitecturedemo.Controller;

import android.app.Application;
import android.os.AsyncTask;

import com.example.androidmvvmarchitecturedemo.Database.EmployeeDAO;
import com.example.androidmvvmarchitecturedemo.Database.EmployeeDatabase;
import com.example.androidmvvmarchitecturedemo.Models.Employee;

import java.util.List;

import androidx.lifecycle.LiveData;

public class EmployeeController {
    private EmployeeDAO employeeDAO;
    private LiveData<List<Employee>> empList;

    public EmployeeController(Application application){
        EmployeeDatabase employeeDatabase = EmployeeDatabase.getInstance(application);
        employeeDAO = employeeDatabase.employeeDAO();
        empList = employeeDAO.getAllEmployees();
    }

    public void insert(Employee employee){
        new InsertNoteAsynctask(employeeDAO).execute(employee);
    }

    public void update(Employee employee){
        new UpdateNoteAsynctask(employeeDAO).execute(employee);
    }

    public void delete(Employee employee){
        new DeleteNoteAsynctask(employeeDAO).execute(employee);
    }

    public void deleteAll(){
        new DeleteAllNoteAsynctask(employeeDAO).execute();
    }

    public LiveData<List<Employee>> getemployeeList(){
        return empList;
    }

    private static class InsertNoteAsynctask extends AsyncTask<Employee,Void,Void>{

        private EmployeeDAO employeeDAO;

        private InsertNoteAsynctask(EmployeeDAO employeeDAO)
        {
            this.employeeDAO = employeeDAO;
        }
        @Override
        protected Void doInBackground(Employee... employees) {
            employeeDAO.insert(employees[0]);
            return  null;
        }
    }

    private static class UpdateNoteAsynctask extends AsyncTask<Employee,Void,Void>{

        private EmployeeDAO employeeDAO;

        private UpdateNoteAsynctask(EmployeeDAO employeeDAO)
        {
            this.employeeDAO = employeeDAO;
        }
        @Override
        protected Void doInBackground(Employee... employees) {
            employeeDAO.update(employees[0]);
            return  null;
        }
    }

    private static class DeleteNoteAsynctask extends AsyncTask<Employee,Void,Void>{

        private EmployeeDAO employeeDAO;

        private DeleteNoteAsynctask(EmployeeDAO employeeDAO)
        {
            this.employeeDAO = employeeDAO;
        }
        @Override
        protected Void doInBackground(Employee... employees) {
            employeeDAO.delete(employees[0]);
            return  null;
        }
    }

    private static class DeleteAllNoteAsynctask extends AsyncTask<Void,Void,Void>{

        private EmployeeDAO employeeDAO;

        private DeleteAllNoteAsynctask(EmployeeDAO employeeDAO)
        {
            this.employeeDAO = employeeDAO;
        }
        @Override
        protected Void doInBackground(Void...Voids) {
            employeeDAO.deleteAllEmployees();
            return  null;
        }
    }
}
