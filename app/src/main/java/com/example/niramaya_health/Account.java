package com.example.niramaya_health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Account extends AppCompatActivity {

    TextView name,contact,email,address,age;
    ImageView profile_pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        name=findViewById(R.id.name_value);
        contact=findViewById(R.id.phone_value);
        email=findViewById(R.id.email_value);
        address=findViewById(R.id.address_value);
        age=findViewById(R.id.age_value);

        profile_pic=findViewById(R.id.profile_id);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .whereEqualTo("userId", FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                            Object data = documentSnapshot.getData();
                            name.setText(documentSnapshot.get("name").toString());
                            age.setText(documentSnapshot.get("age").toString());
                            contact.setText(documentSnapshot.get("contact").toString());
                            address.setText(documentSnapshot.get("address").toString());
                            email.setText(documentSnapshot.get("email").toString());

                            Glide.with(Account.this).load(documentSnapshot.get("downloadUrl")).into(profile_pic);
                            // retrieve document data here
                        } else {
                            // No document matches the query
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle unsuccessful document read
                    }
                });




    }
}