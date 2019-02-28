package com.example.hclinic;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {
    EditText patientName;
    TextView selectedDate;
    Button selectDateButton, addAppointmentDateButton, seeAppointmentButton;

    DatabaseReference databaseAppointments;

    String token = "gg";

    void changeToken(String token) {
        this.token = token;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientName = (EditText) findViewById(R.id.patientName);
        selectedDate = (TextView) findViewById(R.id.dateSelected);
        selectDateButton = (Button) findViewById(R.id.selectDate);
        addAppointmentDateButton = (Button) findViewById(R.id.addAppointmentDate);
        seeAppointmentButton = (Button) findViewById(R.id.seeAppointment);

        databaseAppointments = FirebaseDatabase.getInstance().getReference("appointments");

        Log.d("newToken", this.getPreferences(Context.MODE_PRIVATE).getString("fb", "empty :("));

        String date = getIntent().getStringExtra("date");
        if(date != null) {
            selectedDate.setText(date);
        }

        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        addAppointmentDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseInstanceId.getInstance().getInstanceId()
                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                            @Override
                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                if (!task.isSuccessful()) {
                                }
                                // Get new Instance ID token
                                changeToken(task.getResult().getToken());
                                addAppointment();
                            }
                        });
            }
        });

        seeAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AppointmentListActivity.class);
                startActivity(intent);
            }
        });
    }


    private void addAppointment() {
        String pName = patientName.getText().toString();
        String aDate = selectedDate.getText().toString();
        String aStatus = "Unfinished";

        if(aDate != "Pick Your Date") {
            String id = databaseAppointments.push().getKey();

            Appointment appointment = new Appointment(id, pName, aDate, aStatus, token);

            databaseAppointments.child(id).setValue(appointment);

            Toast.makeText(this, "Appointment Added", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Appointment Insertion Failed", Toast.LENGTH_LONG).show();
        }
    }


}
