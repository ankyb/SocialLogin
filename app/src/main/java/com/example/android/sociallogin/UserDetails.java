package com.example.android.sociallogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDetails extends AppCompatActivity {

    private EditText mName,mColour,mHobby,mAge;
    private Button saveBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        mAuth=FirebaseAuth.getInstance();
        mName=(EditText)findViewById(R.id.editname);
        mColour=(EditText)findViewById(R.id.editcolour);
        mHobby=(EditText)findViewById(R.id.edithobby);
        mAge=(EditText)findViewById(R.id.editage);
        saveBtn=findViewById(R.id.btnsave);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    sendUserdata();
                    Toast.makeText(UserDetails.this, "User data saved", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }
            }
        });


    }

    private void updateUI(FirebaseUser User) {
        if(User!=null){
            finish();
            startActivity(new Intent(this,WelcomeActivity.class));
        }
    }

    private void sendUserdata(){
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref= mDatabase.getReference(mAuth.getUid());
        Database data2= new Database(mName.getText().toString(),mColour.getText().toString(),mHobby.getText().toString(),mAge.getText().toString());
        // UserInfo userinfo=new UserInfo(name,age,email);
        ref.setValue(data2);
    }

    private boolean validate() {
        boolean result=false;

        String name,colour,age,hobby;

        name=mName.getText().toString();
        colour=mColour.getText().toString();
        age=mAge.getText().toString();
        hobby=mHobby.getText().toString();

        if(name.isEmpty() || colour.isEmpty() || hobby.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
        }
        else {
            result= true;
        }
        return result;

    }
}


