package com.example.lab11;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lab11.DBHandler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText courseNameEdt, courseDescriptionEdt;
    private Button addCourseBtn, huyBtn, dateButton, idBtnReadCourse;
    private DBHandler dbHandler;
    private TextView dateTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        dateTextView = findViewById(R.id.dateTextView);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);
        huyBtn = findViewById(R.id.huyBtn);
        dateButton = findViewById(R.id.date_button);
        idBtnReadCourse = findViewById(R.id.idBtnReadCourse);


        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        // below line is to add on click listener for our add course button.
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is to get data from all edit text fields.
                String courseName = courseNameEdt.getText().toString();
                String selectedDate = dateTextView.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();
                // validating if the text fields are empty or not.
                if (courseName.isEmpty()  || courseDescription.isEmpty() || selectedDate.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nhập đủ đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewCourse(courseName, courseDescription, selectedDate);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
                dateTextView.setText("");
                courseDescriptionEdt.setText("");
                courseNameEdt.requestFocus();

            }
        });
        huyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseNameEdt.setText(null);
                courseDescriptionEdt.setText(null);
                dateTextView.setText(null);
            }
        });

        idBtnReadCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity.this, ViewCourses.class);
                startActivity(i);
            }
        });
    }
    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        dateTextView = findViewById(R.id.dateTextView);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // Lưu ngày được chọn vào biến
                        String selectedDate = year + "-" + (month + 1) + "-" + day;

                        // Cập nhật text view với ngày được chọn
                        dateTextView.setText(selectedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

}
