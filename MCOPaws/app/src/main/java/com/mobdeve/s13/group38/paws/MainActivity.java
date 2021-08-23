package com.mobdeve.s13.group38.paws;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    boolean female = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//
//        EditText mDisplayDate = (EditText) findViewById(R.id.et_birthday_register);
//        EditText mDisplayGender = (EditText) findViewById(R.id.et_gender_register);
//
//        mDisplayDate.setOnClickListener(view -> {
//            Calendar cal = Calendar.getInstance();
//            int year = cal.get(Calendar.YEAR);
//            int month = cal.get(Calendar.MONTH);
//            int day = cal.get(Calendar.DAY_OF_MONTH);
//
//            DatePickerDialog dialog = new DatePickerDialog(
//                    MainActivity.this,
//                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                    mDateSetListener,
//                    year,month,day);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog.show();
//        });
//
//        mDisplayDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(
//                        MainActivity.this,
//                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                        mDateSetListener,
//                        year,month,day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//            }
//        });
//
//        mDateSetListener = (view, year, month, dayOfMonth) -> {
//            month = month + 1;
//            Log.d("Main", "onDateSet: mm/dd/yyy: " + month + "/" + dayOfMonth + "/" + year);
//
//            String date = month + "/" + dayOfMonth + "/" + year;
//            mDisplayDate.setText(date);
//        };
//
//        mDisplayGender.setOnClickListener(view -> {
//            if (female){
//                mDisplayGender.setText("Male");
//                female = false;
//            }
//            else{
//                mDisplayGender.setText("Female");
//                female = true;
//            }
//        });
//
//        mDisplayGender.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (female){
//                    mDisplayGender.setText("Male");
//                    female = false;
//                }
//                else{
//                    mDisplayGender.setText("Female");
//                    female = true;
//                }
//            }
//        });
//
    }
}