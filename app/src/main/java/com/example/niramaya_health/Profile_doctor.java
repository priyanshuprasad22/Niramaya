package com.example.niramaya_health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Profile_doctor extends AppCompatActivity {

    ImageButton doctor_image;
    TextView name, Specialization, Experience, qualification, location, about, registration,email,number;
    Button button;
    Uri ImageUri;

    ProgressDialog progressDialog;

    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_doctor);

        doctor_image = findViewById(R.id.upload_doctor_image);
        name = findViewById(R.id.name_edittext);
        Specialization = findViewById(R.id.specialization_edittext);
        qualification = findViewById(R.id.qualification_edittext);
        about = findViewById(R.id.about_edittext);
        registration = findViewById(R.id.registartion_edittext);
        location = findViewById(R.id.location_edittext);
        Experience = findViewById(R.id.experience_edittext);
        number=findViewById(R.id.contact_edittext);
        email=findViewById(R.id.email_editext);

        button = findViewById(R.id.save);

        doctor_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });


    }

    public void uploadImage()
    {

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploading Details...");
        progressDialog.show();

        String doctorname=name.getText().toString();
        String doctoremail=email.getText().toString().trim();
        String doctorabout=about.getText().toString();
        String doctorexperience=Experience.getText().toString();
        String doctorcontact=number.getText().toString().trim();
        String doctorlocation=location.getText().toString();
        String doctorqualification=qualification.getText().toString();
        String doctorregistration=registration.getText().toString().trim();
        String doctorspecialization=Specialization.getText().toString();






        storageReference= FirebaseStorage.getInstance().getReference().child("images/" + UUID.randomUUID().toString());;

       UploadTask uploadTask= storageReference.putFile(ImageUri);

       uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
           @Override
           public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

               if(task.isSuccessful())
               {
                   storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                       @Override
                       public void onSuccess(Uri uri) {

                           FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

                           if (user != null) {
                               String uid = user.getUid();
                               FirebaseFirestore db = FirebaseFirestore.getInstance();
                               Map<String, Object> image = new HashMap<>();
                               image.put("downloadUrl", uri.toString());
                               image.put("userId", uid);
                               image.put("name",doctorname);
                               image.put("contact",doctorcontact);
                               image.put("experience",doctorexperience);
                               image.put("location",doctorlocation);
                               image.put("qualification",doctorqualification);
                               image.put("email",doctoremail);
                               image.put("registration",doctorregistration);
                               image.put("about",doctorabout);
                               image.put("specialization",doctorspecialization);
                               db.collection("images").add(image);
                           }
                           progressDialog.dismiss();
                           Toast.makeText(Profile_doctor.this,"Thanks for Completing your Profile",Toast.LENGTH_LONG).show();
                           Intent intent=new Intent(Profile_doctor.this,Activity_professional.class);
                           startActivity(intent);



                       }
                   });
               }



           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {

               if(progressDialog.isShowing())
               {
                   progressDialog.dismiss();
               }
               Toast.makeText(Profile_doctor.this,"Failed to Upload",Toast.LENGTH_LONG).show();

           }
       });




    }

    public void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && data !=null && data.getData()!=null)
        {
            ImageUri=data.getData();
            doctor_image.setImageURI(ImageUri);
        }


    }
}