package com.example.niramaya_health;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

public class User_full_profile extends AppCompatActivity {

    ImageButton image_button;
    TextView name,email,contact,age,address;

    Button button;

    Uri ImageUri;

    StorageReference storageReference;

    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_full_profile);

        name=findViewById(R.id.edit_text_name);
        email=findViewById(R.id.edit_text_email);
        contact=findViewById(R.id.edit_text_contact);
        age=findViewById(R.id.edit_text_age);
        address=findViewById(R.id.edit_text_address);

        image_button=findViewById(R.id.image_button);
        button=findViewById(R.id.save_update_profile);

        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog=new ProgressDialog(User_full_profile.this);
                progressDialog.setTitle("Saving Data...");
                progressDialog.show();

                uploadImage();
            }
        });





    }

    public void uploadImage()
    {
        String username=name.getText().toString();
        String useremail=email.getText().toString();
        String usercontact=contact.getText().toString();
        String useraddress=contact.getText().toString();
        String userage=age.getText().toString();

        storageReference= FirebaseStorage.getInstance().getReference().child("users/"+ UUID.randomUUID().toString());

        UploadTask uploadTask=storageReference.putFile(ImageUri);

        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                if(task.isSuccessful())
                {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

                            if(user != null)
                            {
                                String uid= user.getUid();
                                FirebaseFirestore db=FirebaseFirestore.getInstance();

                                Map<String,Object> image=new HashMap<>();
                                image.put("downloadUrl",uri.toString());
                                image.put("userId",uid);
                                image.put("name",username);
                                image.put("email",useremail);
                                image.put("contact",usercontact);
                                image.put("address",useraddress);
                                image.put("age",userage);

                                db.collection("users").add(image);

                            }

                            progressDialog.dismiss();
                            Toast.makeText(User_full_profile.this,"Thanks for updating your information",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(User_full_profile.this,User_Activity.class);
                            startActivity(intent);

                        }
                    });
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }

    public void selectImage()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100 && data!=null && data.getData()!=null)
        {
            ImageUri=data.getData();
            image_button.setImageURI(ImageUri);
        }

    }
}