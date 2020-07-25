package com.example.databasesinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddNewStudent extends AppCompatActivity {
    EditText name,roll,gender,marks;
    Button save;
    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);
        name = findViewById(R.id.name);
        roll = findViewById(R.id.roll);
        gender = findViewById(R.id.gender);
        marks = findViewById(R.id.marks);
        save = findViewById(R.id.submit_data);
        databaseHandler = new DatabaseHandler(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                int r = Integer.parseInt(roll.getText().toString());
                String g = gender.getText().toString();
                float m = Float.parseFloat(marks.getText().toString());
                if(databaseHandler.insertData(r,n,g,m)){
                    Toast.makeText(AddNewStudent.this, "Insert Success", Toast.LENGTH_SHORT).show();
                    setResult(100);
                    finish();
                }
                else{
                    Toast.makeText(AddNewStudent.this, "Error Inserting Values", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}