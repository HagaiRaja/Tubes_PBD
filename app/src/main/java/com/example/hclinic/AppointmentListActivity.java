package com.example.hclinic;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AppointmentListActivity extends AppCompatActivity {

    ListView listViewAppointment;
    List<Appointment> appointmentList;
    DatabaseReference databaseAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);

        listViewAppointment = (ListView) findViewById(R.id.appointmentList);
        appointmentList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseAppointments = FirebaseDatabase.getInstance().getReference("appointments");
        databaseAppointments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                appointmentList.clear();
                for (DataSnapshot appointmentSnapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = appointmentSnapshot.getValue(Appointment.class);

                    appointmentList.add(appointment);
                }

                AppointmentList adapter = new AppointmentList(AppointmentListActivity.this, appointmentList);
                listViewAppointment.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
