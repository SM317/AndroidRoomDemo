package com.example.androidmvvmarchitecturedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.androidmvvmarchitecturedemo.Adaptor.EmployeeAdaptor;
import com.example.androidmvvmarchitecturedemo.Models.Employee;
import com.example.androidmvvmarchitecturedemo.View.AddEmployeeActivity;
import com.example.androidmvvmarchitecturedemo.ViewModel.EmployeeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_EMPLOY_REQUEST = 1;
    private EmployeeViewModel employeeViewModel;

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == ADD_EMPLOY_REQUEST) {
                        // There are no request codes
                        Intent data = result.getData();
                        String title = data.getStringExtra(AddEmployeeActivity.EXTRA_NAME);
                        String description = data.getStringExtra(AddEmployeeActivity.EXTRA_DESCRIPTION);
                        int priority = data.getIntExtra(AddEmployeeActivity.EXTRA_EMP_NO, 1);
                        Employee employee = new Employee(title, description, priority);
                        employeeViewModel.insertEmployee(employee);
                        Toast.makeText(MainActivity.this, "Employee detail saved", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Employee detail not saved", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddNote = findViewById(R.id.btn_add);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNoteActivityForResult();
            }
        });

        RecyclerView rc_view = findViewById(R.id.rv_note);
        rc_view.setLayoutManager(new LinearLayoutManager(this));
        rc_view.setHasFixedSize(true);

        final EmployeeAdaptor adapter = new EmployeeAdaptor();
        rc_view.setAdapter(adapter);

        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        employeeViewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
               adapter.setNotes(employees);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                employeeViewModel.deleteEmployee(adapter.getEmployeeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this,"Employee deleted",Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(rc_view);
    }

    public void openAddNoteActivityForResult() {
        Intent intent = new Intent(this, AddEmployeeActivity.class);
        someActivityResultLauncher.launch(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_delete_all_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_menu:
                employeeViewModel.deleteAllEmployee();
                Toast.makeText(this,"All employee details deleted",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}