package com.saloni.doctoroncall;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saloni.doctoroncall.Doctor.Epidemic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateSelectorForEpidemicMap extends AppCompatActivity {

    private Calendar calendar;
    private TextView dateFrom;
    private TextView dateTo;
    private int year, month, day;

    DatabaseReference myRef;
    MyValueEventListener listener;
    public static long from_mills;
    public static long to_mills;
    public static ArrayList<Epidemic> epidemicArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selector_for_epidemic_map);

        dateFrom = (TextView) findViewById(R.id.datefrom);
        dateTo = (TextView) findViewById(R.id.dateto);
        calendar = Calendar.getInstance();

        epidemicArrayList = new ArrayList<>();
        listener = new MyValueEventListener();
        myRef = FirebaseDatabase.getInstance().getReference("epidemics");
        myRef.addValueEventListener(listener);

    }

    public void setDateFrom(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        dateFrom.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        from_mills = new Date(year, monthOfYear, dayOfMonth).getTime();
//                        Toast.makeText(DateSelectorForEpidemicMap.this,String.valueOf(from_mills),Toast.LENGTH_SHORT).show();

                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    public void setDateTo(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        dateFrom.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        to_mills = new Date(year, monthOfYear, dayOfMonth).getTime();
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    public void search(View view) {
        if (epidemicArrayList.size() == 0)
            Toast.makeText(this, "No records present between these dates.", Toast.LENGTH_SHORT).show();
        else {
            Intent i = new Intent(this, EpidemicSpreadMap.class);
            startActivity(i);
        }
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.d("Data Snapshot:", dataSnapshot.toString());

            epidemicArrayList.clear();

            for (DataSnapshot epidemic : dataSnapshot.getChildren()) {

                Log.e(epidemic.getKey(), epidemic.toString());
                Epidemic e = epidemic.getValue(Epidemic.class);
                assert e != null;
                epidemicArrayList.add(e);
            }

            myRef.removeEventListener(listener);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }
}
