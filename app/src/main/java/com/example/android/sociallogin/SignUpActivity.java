package com.example.android.sociallogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private TextView loginHere;
    private Button mRegisterBtn;
    private EditText mName,mPassword,mEmail,mAge;
    private FirebaseAuth mAuth;
    private static final String TAG="SIGN_UP_ACTIVITY";
    private String name;
    private String email;
    private String age;




  /*  @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void updateUI(FirebaseUser User) {
        if(User!=null){
            finish();
            startActivity(new Intent(SignUpActivity.this,WelcomeActivity.class));
        }
    }
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setupUIViews();

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){

                    mAuth.createUserWithEmailAndPassword(mEmail.getText().toString().trim(), mPassword.getText().toString().trim())
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        Toast.makeText(SignUpActivity.this, "Registeraton Successfull", Toast.LENGTH_SHORT).show();
                                        //sendUserdata();
                                        startActivity(new Intent(SignUpActivity.this,UserDetails.class));
                                        //FirebaseUser user = mAuth.getCurrentUser();
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignUpActivity.this, "Registeraton Failed", Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });

                }
            }
        });

        loginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
            }

        });
    }

    private boolean validate() {
        boolean result=false;
        name=mName.getText().toString();
        String password=mPassword.getText().toString();
        email=mEmail.getText().toString();
        age=mAge.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
        }
        else {
            result= true;
        }
        return result;

    }

    private void setupUIViews() {
        mName=(EditText) findViewById(R.id.fullname);
        mPassword=(EditText) findViewById(R.id.password);
        mEmail=(EditText) findViewById(R.id.hobby);
        mAge=(EditText) findViewById(R.id.age);
        loginHere=(TextView)findViewById(R.id.loginHere);
        mRegisterBtn=(Button)findViewById(R.id.registerButton);
        mAuth=FirebaseAuth.getInstance();

    }

    private void sendUserdata(){
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref= mDatabase.getReference(mAuth.getUid());
        UserInfo userinfo=new UserInfo(name,age,email);
        ref.setValue(userinfo);

    }





}

