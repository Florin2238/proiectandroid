package com.example.moneysaver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Main_Activity extends AppCompatActivity {

    EditText Name, Psd;
    Button Btn;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    GoogleSignInOptions gso;

    GoogleSignInClient gsc;

    ImageView googleBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = preferences.edit();

        if (preferences.contains("saved_Name")) {
            Intent intent = new Intent(Main_Activity.this, Second_Activity.class);
            startActivity(intent);
        } else {

            Name = findViewById(R.id.Name);
            Psd = findViewById(R.id.Psd);
            Btn = findViewById(R.id.btn);
            Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String my_name = Name.getText().toString();
                    String my_password = Psd.getText().toString();
                    editor.putString("saved_Name", my_name);
                    editor.putString("saved_password", my_password);
                    editor.commit();
                    Intent intent = new Intent(Main_Activity.this, Second_Activity.class);
                    startActivity(intent);

                }
            });
        }

        googleBtn = findViewById(R.id.google);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            navigateToSecondActivity();
        }

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }

    void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        }
    }

      void navigateToSecondActivity() {

        finish();
       Intent intent = new Intent(Main_Activity.this, Second_Activity.class);
       startActivity(intent);

     }

}


