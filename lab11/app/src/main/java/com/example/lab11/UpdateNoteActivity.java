package com.example.lab11;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class UpdateNoteActivity extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText courseNameEdt, courseDescriptionEdt;
    TextView dateTextViewEdt;
    Button idBtnUptCourse, datebutton, btnCancle;
    private DBHandler dbHandler;
    String courseName, courseDesc, selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        // initializing all our variables.
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        dateTextViewEdt = findViewById(R.id.dateTextView);
        datebutton = findViewById(R.id.date_button);
        btnCancle = findViewById(R.id.cancleBtn);
        idBtnUptCourse = findViewById(R.id.idBtnUptCourse);

        // on below line we are initializing our dbhandler class.
        dbHandler = new DBHandler(UpdateNoteActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        courseName = getIntent().getStringExtra("name");
        courseDesc = getIntent().getStringExtra("description");
        selectedDate = getIntent().getStringExtra("ngay");


        // setting data to edit text
        // of our update activity.
        courseNameEdt.setText(courseName);
        courseDescriptionEdt.setText(courseDesc);
        dateTextViewEdt.setText(selectedDate);

        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        idBtnUptCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update course
                // method and passing all our edit text values.
                dbHandler.updateCourse(courseName, courseNameEdt.getText().toString(), courseDescriptionEdt.getText().toString(), dateTextViewEdt.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(UpdateNoteActivity.this, "Đã cập nhật..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateNoteActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        dateTextViewEdt = findViewById(R.id.dateTextView);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // Lưu ngày được chọn vào biến
                        selectedDate = year + "-" + (month + 1) + "-" + day;

                        // Cập nhật text view với ngày được chọn
                        dateTextViewEdt.setText(selectedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }
}