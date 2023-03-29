package com.example.niramaya_health;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niramaya_health.models.Question_Answer;
import com.example.niramaya_health.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DisplayQuestion extends AppCompatActivity {

    private String type="",question="";
    private ArrayList<String> question_choice;

    private String optionselected;

    RadioButton r1,r2,r3,r4;
    RadioGroup group;

    EditText edt_answer;
    TextView edt_question;

    Button clear,save,next;
    FrameLayout frameLayout;

    DatabaseReference firebaseDatabase;
    FirebaseAuth auth;

    int w=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_question);

        question_choice=new ArrayList<>();
        auth=FirebaseAuth.getInstance();





        clear=findViewById(R.id.clear_response);
        save=findViewById(R.id.save_response);
        next=findViewById(R.id.next_response);
        optionselected="";


        group=findViewById(R.id.radio_group_question);

        question=getIntent().getStringExtra("question");
        type=getIntent().getStringExtra("type");

        edt_answer=findViewById(R.id.answer);
        edt_question=findViewById(R.id.question_answer);

        edt_question.setText(question);
        r1=findViewById(R.id.option1);
        r2=findViewById(R.id.option2);
        r3=findViewById(R.id.option3);
        r4=findViewById(R.id.option4);



        if(type.equals("integer"))
        {
            w=0;
            edt_answer.setVisibility(View.VISIBLE);


        }
        else if(type.equals("categorical"))
        {
            w=1;
            question_choice=getIntent().getStringArrayListExtra("List");
            Log.d("Choices",question_choice.toString());
            group.setVisibility(View.VISIBLE);
            for(int j=0;j<question_choice.size();j++)
            {
                if(j==0) {
                    r1.setText(question_choice.get(j));
                    r1.setVisibility(View.VISIBLE);
                }
                if(j==1) {
                    r2.setText(question_choice.get(j));
                    r2.setVisibility(View.VISIBLE);
                }
                if(j==2) {
                    r3.setText(question_choice.get(j));
                    r3.setVisibility(View.VISIBLE);
                }
                if(j==3) {
                    r4.setText(question_choice.get(j));
                    r4.setVisibility(View.VISIBLE);
                }

            }






        }



        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                group.clearCheck();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (type.equals("integer")) {
                    optionselected=edt_answer.getText().toString();

                    Date currentDate = new Date();

// Create a calendar instance and set it to the current date and time
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(currentDate);

// Get the year, month, and day from the calendar instance
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1; // Note: month is 0-indexed
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

// Print the date in YYYY-MM-DD format
                    String formattedDate = String.format("%d-%02d-%02d", year, month, day);

                    updatedatabase(question,optionselected,formattedDate);




                } else {



                    int checkedRadioButtonId = group.getCheckedRadioButtonId();
                    if (checkedRadioButtonId != -1) {
                        RadioButton checkedRadioButton = findViewById(checkedRadioButtonId);

                        optionselected = checkedRadioButton.getText().toString();
                        Date currentDate = new Date();


                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(currentDate);

                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH) + 1; // Note: month is 0-indexed
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        String formattedDate = String.format("%d-%02d-%02d", year, month, day);

                        updatedatabase(question,optionselected,formattedDate);



                    }
                }

            }


        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (optionselected.length()==0) {

                    new AlertDialog.Builder(DisplayQuestion.this)
                            .setTitle("Confirmation")
                            .setMessage("Move to Next Question?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(DisplayQuestion.this,AddQuestion.class);
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else if(optionselected.length()>0){
                    Intent intent=new Intent(DisplayQuestion.this,AddQuestion.class);
                    startActivity(intent);

                }
            }
        });








    }

   public void updatedatabase(String question,String answer,String s1)
    {


        firebaseDatabase=FirebaseDatabase.getInstance().getReference();
        firebaseDatabase.child("User").child(auth.getCurrentUser().getUid()).child("Symptoms")
                .child(s1).push().setValue(new Question_Answer(question,answer,s1));

        Toast.makeText(DisplayQuestion.this,"Saved",Toast.LENGTH_SHORT).show();
    }
}