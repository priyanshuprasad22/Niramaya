package com.example.niramaya_health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.niramaya_health.models.Doctor_full_info;
import com.example.niramaya_health.models.Symptom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Doctor_full_view extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    ImageView imageView;
    TextView name,specialization,contact,email,location,about,experience;
    Button bookappointment;
    TextView selectdate,selecttime;
    String recievermail;
    String useremail,usercontact,username;

    String formatuserdate,doctorid;



    String time,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_full_view);

        imageView=findViewById(R.id.doctor_photo);
        name=findViewById(R.id.doctor_name);
        specialization=findViewById(R.id.doctor_specialization);
        contact=findViewById(R.id.doctor_phone);
        email=findViewById(R.id.doctor_email);
        location=findViewById(R.id.doctor_location);
        about=findViewById(R.id.doctor_about);
        experience=findViewById(R.id.doctor_experience);

        bookappointment=findViewById(R.id.doctor_book_appointment);
        selectdate=findViewById(R.id.Select_date);
        selecttime=findViewById(R.id.select_time);

        formatuserdate=getIntent().getStringExtra("formatteddate");

        usercontact="";
        useremail="";
        username="";


        Doctor_full_info doctor_full_info= (Doctor_full_info) getIntent().getSerializableExtra("doctorinfo");

        Glide.with(this).load(doctor_full_info.getDownloadUrl()).into(imageView);

        name.setText(doctor_full_info.getName());
        specialization.setText(doctor_full_info.getSpecialization());
        contact.setText(doctor_full_info.getContact());
        email.setText(doctor_full_info.getEmail());
        about.setText(doctor_full_info.getAbout());
        experience.setText(doctor_full_info.getExperience()+" years of Experience");
        location.setText(doctor_full_info.getLocation());

        doctorid=doctor_full_info.getUserId();




        selectdate.setOnClickListener(view -> {
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
        });

        selecttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });


        bookappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Doctor_full_view.this,"Thank you for booking apppointment",Toast.LENGTH_LONG).show();
                sentmessage();

            }
        });



    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String formattedDate = String.format("%d-%02d-%02d", year, month, dayOfMonth);
        selectdate.setText(formattedDate);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        String formattedTime=String.format("%d:%02d",hourOfDay, minute);
        selecttime.setText(formattedTime);

    }


    public static class DatePickerFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }
    }

    public static class TimePickerFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default time in the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, false);
        }
    }


    public void sentmessage() {

        recievermail = "";


        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference("User/" + auth.getCurrentUser().getUid());

        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                recievermail = snapshot.child("email").getValue(String.class);
                String username=snapshot.child("name").getValue(String.class);

                Log.d("recivedmail", recievermail);

                try {
                    Properties props = new Properties();
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.ssl.enable", "true");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "465");

                    Session session = Session.getInstance(props,
                            new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(Utils.EMAIL, Utils.PASSWORD);
                                }
                            });


                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(Utils.EMAIL));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recievermail));
                    message.setSubject("Appointment Confirmation by Niramaya");
                    message.setText("Hello "+username+","+"\n\nWe are pleased to inform you that your appointment has been scheduled with "+name.getText()+" at "+selecttime.getText()+" on "+selectdate.getText()+"\n\nKindly visit the clinic on scheduled time."+"\n"+"Clinic: "+location.getText()+"\n"+"Contact: "+contact.getText()+
                            "\n\nThankyou for using our App.\n" +
                            "Hope you have a great experience here!\n\n\nRegards,\nTeam Niramaya");

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Transport.send(message);
                            } catch (MessagingException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();

                } catch (AddressException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setdatabaseuser();
        setdatabaseprofessional();






    }

    public void setdatabaseuser()
    {
        Map<String,String> symptoms;

        symptoms=new HashMap<>();

        DatabaseReference database=FirebaseDatabase.getInstance().getReference("User/"+FirebaseAuth.getInstance().getCurrentUser().getUid());

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                usercontact=snapshot.child("number").getValue(String.class);
                username=snapshot.child("name").getValue(String.class);
                useremail=snapshot.child("email").getValue(String.class);

                DatabaseReference data=FirebaseDatabase.getInstance().getReference("User/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Symptoms/"+formatuserdate);


                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {



                        for(DataSnapshot dataSnapshot:snapshot.getChildren())
                        {
                            String question=dataSnapshot.child("question").getValue(String.class);
                            String answer=dataSnapshot.child("answer").getValue(String.class);

                            symptoms.put(question,answer);


                        }

                        FirebaseAuth auth=FirebaseAuth.getInstance();
                        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
                        databaseReference.child("User").child(auth.getCurrentUser().getUid()).child("Appointment").push().setValue(new Symptom(symptoms,FirebaseAuth.getInstance().getCurrentUser().getUid(),doctorid,selectdate.getText().toString(),selecttime.getText().toString(),username,useremail,usercontact,name.getText().toString(),location.getText().toString(),specialization.getText().toString()));

                        DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference();
                        databaseReference1.child("Professional").child(doctorid).child("UpcomingAppointment").push().setValue(new Symptom(symptoms,FirebaseAuth.getInstance().getCurrentUser().getUid(),doctorid,selectdate.getText().toString(),selecttime.getText().toString(),username,useremail,usercontact,name.getText().toString(),location.getText().toString(),specialization.getText().toString()));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
























    }
    public void setdatabaseprofessional()
    {

    }
}