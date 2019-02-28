package com.example.hclinic;

public class Appointment {
    String appointmentID;
    String patientName;
    String appointmentDate;
    String appointmentStatus;
    String appointmentToken;

    public Appointment(){

    }

    public Appointment(String appointmentID, String patientName, String appointmentDate, String appointmentStatus, String appointmentToken) {
        this.appointmentID = appointmentID;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentStatus = appointmentStatus;
        this.appointmentToken = appointmentToken;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }
}
