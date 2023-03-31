package com.example.niramaya_health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.niramaya_health.models.Symptom;
import com.example.niramaya_health.models.User;
import com.example.niramaya_health.models.patient_medical_data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class Prescription extends AppCompatActivity {

    EditText symptoms, doctoraddedsymptom, height, weigth, Bloodpressure, o2level, advices, medication, allergies, treatmentplan, followup, diagnosis;
    TextView name, patient_id, contact, email;

    Button download;

    patient_medical_data symptom;

    ScrollView scrollView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        symptoms = findViewById(R.id.symptoms_edttext);
        doctoraddedsymptom = findViewById(R.id.doctor_symptoms_edttext);
        height = findViewById(R.id.patient_height);
        weigth = findViewById(R.id.patient_weight);
        Bloodpressure = findViewById(R.id.patient_blood_pressure);
        o2level = findViewById(R.id.patient_O2_level);
        advices = findViewById(R.id.medical_advices);
        medication = findViewById(R.id.patient_medication);
        diagnosis = findViewById(R.id.diagnosis_appoint);
        allergies = findViewById(R.id.allegies);
        treatmentplan = findViewById(R.id.treatment_plan);
        followup = findViewById(R.id.follow_up_date_time);

        name = findViewById(R.id.name_patient);
        contact = findViewById(R.id.contact_patient);
        patient_id = findViewById(R.id.patient_id);
        email = findViewById(R.id.email_patient);
        scrollView=findViewById(R.id.prescription_scrollview);


        symptom = (patient_medical_data) getIntent().getSerializableExtra("Symptom");

        download=findViewById(R.id.download_btn);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateAndSharePdf();
            }
            });


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.child("name").getValue(String.class));
                contact.setText(snapshot.child("number").getValue(String.class));
                patient_id.setText(FirebaseAuth.getInstance().getUid());
                email.setText(snapshot.child("email").getValue(String.class));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        symptoms.setText(symptom.getSymptoms().toString());
        doctoraddedsymptom.setText(symptom.getDoctoraddedsymptoms());
        height.setText(symptom.getHeight());
        weigth.setText(symptom.getWeight());
        medication.setText(symptom.getMedication());
        o2level.setText(symptom.getO2level());
        followup.setText(symptom.getFollowup());
        allergies.setText(symptom.getAllergies());
        advices.setText(symptom.getAdvices());
        Bloodpressure.setText(symptom.getBloodpressure());
        diagnosis.setText(symptom.getDiagonosis());
        treatmentplan.setText(symptom.getTreatmentplan());


    }

    private void generateAndSharePdf() {

        Log.d("generateAndSharePdf", "Method started.");


        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        scrollView.scrollTo(0, 0);

        // Set a global layout listener to calculate the height of the root view
        rootView.post(new Runnable() {
            @Override
            public void run() {

                // Get the height of the root view
                int height = scrollView.getHeight();
                // Get the height of the display
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int screenHeight = displayMetrics.heightPixels;
                // Initialize the PDF document
                PdfDocument document = new PdfDocument();
                // Create a page with the desired dimensions
                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(scrollView.getWidth(), height, 2).create();
                PdfDocument.Page page = document.startPage(pageInfo);
                // Get the canvas to draw on
                Canvas canvas = page.getCanvas();

                int totalHeight = 0;
                for (int i = 0; i < scrollView.getChildCount(); i++) {
                    totalHeight += scrollView.getChildAt(i).getHeight();
                }

                // Draw the root view on the canvas
                scrollView.draw(canvas);
                // If the view is taller than the screen height, capture additional pages
                while (totalHeight > height) {
                    document.finishPage(page); // Finish the current page before starting a new one
                    pageInfo = new PdfDocument.PageInfo.Builder(scrollView.getWidth(), height, 1).create();
                    page = document.startPage(pageInfo);
                    canvas = page.getCanvas();
                    canvas.translate(0, -height);
                    scrollView.draw(canvas);
                    totalHeight -= height;
                }

                document.finishPage(page);

                // Create the PDF file
                Log.d("Before", "Before Generation" + page.toString());
                File file = new File(getExternalFilesDir(null), "my_file.pdf");
                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    document.writeTo(outputStream);
                    document.close();
                    outputStream.close();
                    Log.d("After", "After Generation" + file.getAbsolutePath());


                    // Create the share intent inside the try block
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("application/pdf");

                    // Check if the file exists before creating the URI
                    if (file.exists()) {
                        Uri fileUri = FileProvider.getUriForFile(Prescription.this, getPackageName() + ".fileprovider", file);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
                        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(Intent.createChooser(shareIntent, "Share PDF"));
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}