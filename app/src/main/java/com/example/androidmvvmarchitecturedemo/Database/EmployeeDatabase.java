package com.example.androidmvvmarchitecturedemo.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.androidmvvmarchitecturedemo.Models.Employee;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Employee.class},version = 1, exportSchema = false)
public abstract class EmployeeDatabase extends RoomDatabase {
    private static EmployeeDatabase instance;
    public abstract EmployeeDAO employeeDAO();
    public synchronized static EmployeeDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), EmployeeDatabase.class,"employee_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private EmployeeDAO employeeDao;

        private PopulateDbAsyncTask(EmployeeDatabase db) {
            employeeDao = db.employeeDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            employeeDao.insert(new Employee("Sourabh", "Senior Developer", 42284));
            employeeDao.insert(new Employee("Mehta", "Executive 3", 42323));
            employeeDao.insert(new Employee("Sourabh Mehta", "Manager 1", 40828));
            return null;
        }
    }
}
