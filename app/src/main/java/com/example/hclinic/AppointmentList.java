package com.example.hclinic;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AppointmentList extends ArrayAdapter<Appointment> {
    private Activity context;
    private List<Appointment> appointmentList;

    public AppointmentList(Activity context, List<Appointment> appointmentList) {
        super(context, R.layout.activity_appointment_list2, appointmentList);
        this.context = context;
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_appointment_list2, null, true);

        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.appointmentListDate);
        TextView textViewStatus = (TextView) listViewItem.findViewById(R.id.appointmentListStatus);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.appointmentListID);

        Appointment appointment = appointmentList.get(position);

        textViewDate.setText(appointment.getAppointmentDate());
        textViewStatus.setText(appointment.getAppointmentStatus());
        textViewID.setText(appointment.getAppointmentID());

        return listViewItem;
    }
}
