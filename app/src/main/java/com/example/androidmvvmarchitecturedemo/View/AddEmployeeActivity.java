package com.example.androidmvvmarchitecturedemo.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidmvvmarchitecturedemo.R;

public class AddEmployeeActivity extends AppCompatActivity {

    private EditText et_name,et_desgn,et_empNo;
    public static final String EXTRA_NAME =
            "com.codinginflow.architectureexample.EXTRA_NAME";
    public static final String EXTRA_DESCRIPTION =
            "com.codinginflow.architectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_EMP_NO =
            "com.codinginflow.architectureexample.EXTRA_EMP_NO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        et_name = findViewById(R.id.edit_text_emp_name);
        et_desgn  = findViewById(R.id.edit_text_desgn);
        et_empNo = findViewById(R.id.edit_text_empno);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Employee detail");
    }

    private void saveEmployeeDetails() {
        String title = et_name.getText().toString();
        String description = et_desgn.getText().toString();
        String empNo = et_empNo.getText().toString();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a name and designation", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_EMP_NO, empNo);
        setResult(1, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveEmployeeDetails();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}