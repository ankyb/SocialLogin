package com.example.android.sociallogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfleActivity extends AppCompatActivity {

    private TextView mName;
    private TextView mhobby;
    private TextView mAge;
    private TextView mColour;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profle);
        mName=findViewById(R.id.name);
        mhobby =findViewById(R.id.hobby);
        mColour=findViewById(R.id.colour);
        mAge = findViewById(R.id.age);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        DatabaseReference myRef = mDatabase.getReference(mAuth.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Database value = dataSnapshot.getValue(Database.class);
                if(value!=null) {
                    mName.setText(value.getName());
                    mColour.setText(value.getColour());
                    mhobby.setText(value.getHobby());
                    mAge.setText(value.getAge());
                }
               // Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
               // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }

}