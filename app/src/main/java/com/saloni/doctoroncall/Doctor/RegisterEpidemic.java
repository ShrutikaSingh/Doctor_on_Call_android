package com.saloni.doctoroncall.Doctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saloni.doctoroncall.MainActivity;
import com.saloni.doctoroncall.R;

import java.util.ArrayList;

public class RegisterEpidemic extends AppCompatActivity {

    EditText Name;
    EditText Phone;
    Spinner Epidemic;
    EditText Symptoms;

    String docKey;
    String docName;
    String docPhone;

    ScrollView scrollView;
    ProgressBar pb;
    DatabaseReference myRef;
    MyValueEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_epidemic);

        Name = findViewById(R.id.name);
        Phone = findViewById(R.id.phone);
        Epidemic = findViewById(R.id.select_epid);
        Symptoms = findViewById(R.id.symptoms);

        pb = findViewById(R.id.loadDbProgress);
        pb.setVisibility(View.INVISIBLE);
        scrollView = findViewById(R.id.scrollView_Reg);


        String[] quals = new String[]{"Malaria", "Cholera",
                "Small Pox", "Swine Flu", "Typhoid", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, quals);
        Epidemic.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        docKey = extras.getString("docKey");
        assert docKey != null;
        docName = extras.getString("docName");
        assert docName != null;
        docPhone = extras.getString("docPhone");
        assert docPhone != null;
    }

    public void registerEpidemic(View view) {
        scrollView.scrollTo(0, 0);
        pb.setVisibility(View.VISIBLE);

        String name = Name.getText().toString();
        String phone = Phone.getText().toString();

        //Testing for data inconsistency
        StringBuilder errorString = new StringBuilder("");
        if (name.isEmpty())
            errorString.append("Name cannot be left empty");
        if (phone.isEmpty())
            errorString.append("\nPhone No. cannot be left empty");

        if (!errorString.toString().isEmpty()) {
            Toast.makeText(this, errorString.toString().trim(), Toast.LENGTH_SHORT).show();
            pb.setVisibility(View.INVISIBLE);
            finish();
            return;
        }

        //Inserting in Database!
        myRef = FirebaseDatabase.getInstance().getReference("epidemics");
        listener = new MyValueEventListener();
        myRef.addValueEventListener(listener);
    }

    class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String name = Name.getText().toString();
            String phone = Phone.getText().toString();
            String epidemic = Epidemic.getSelectedItem().toString();
            ArrayList<String> symptoms = new ArrayList<>();

            long time = System.currentTimeMillis();
            String lat = String.valueOf(MainActivity.lat);
            String lng = String.valueOf(MainActivity.lng);

            for (String symptom : Symptoms.getText().toString().split(",")) {
                symptoms.add(symptom.trim());
            }

            Epidemic epid = new Epidemic(epidemic, name, phone, symptoms, docKey, docName, docPhone, time, lat, lng);
            myRef.push().setValue(epid);
            Toast.makeText(RegisterEpidemic.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

            myRef.removeEventListener(listener);
            pb.setVisibility(View.INVISIBLE);
            finish();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }
}
