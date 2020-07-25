package com.example.databasesinandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add_new;
    ViewGroup root;
    int i;
    View view;
    TextView name,roll,marks,gender;
    Button edit,delete;
    List<Student> allStudents;
    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_new = findViewById(R.id.add_new_student_fab);
        root  = findViewById(R.id.student_list);
        databaseHandler = new DatabaseHandler(this);

        loadData();

        add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,AddNewStudent.class),100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100)
            loadData();
    }

    private void loadData() {
        root.removeAllViewsInLayout();
        allStudents = databaseHandler.getAllData();
        for(i=0;i<allStudents.size();i++){
            view = LayoutInflater.from(this).inflate(R.layout.student_view,null);
            name = view.findViewById(R.id.name_out);
            roll = view.findViewById(R.id.roll_out);
            marks = view.findViewById(R.id.marks_out);
            gender = view.findViewById(R.id.gender_out);
            delete = view.findViewById(R.id.delete_out);
            edit = view.findViewById(R.id.edit_out);

            name.setText("Name: "+allStudents.get(i).getName());
            roll.setText("Roll: "+allStudents.get(i).getRoll());
            marks.setText("Marks: "+allStudents.get(i).getMarks());
            gender.setText("Gender: "+allStudents.get(i).getGender());

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Delete Clicked", Toast.LENGTH_SHORT).show();
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Edit Clicked", Toast.LENGTH_SHORT).show();
                }
            });

            root.addView(view);
        }
    }
}