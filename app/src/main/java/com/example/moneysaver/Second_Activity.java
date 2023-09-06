package com.example.moneysaver;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import com.google.android.gms.auth.api.signin.GoogleSignIn;
        import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
        import com.google.android.gms.auth.api.signin.GoogleSignInClient;
        import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;

public class Second_Activity extends AppCompatActivity {
    Button btn;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    GoogleSignInOptions gso;

    GoogleSignInClient gsc;

    TextView name, email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = preferences.edit();
        btn = findViewById(R.id.btn_logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                Intent intent = new Intent(Second_Activity.this, Main_Activity.class);
                startActivity(intent);
            }
        });

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);

        }  else if(savedInstanceState == null) {
            FirstFragment fragment = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout);
            if (fragment == null) {
                // Dacă nu există, îl adăugăm
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.frame_layout, new FirstFragment(), "FirstFragment")
                        .commit();

                findViewById(R.id.name).setVisibility(View.INVISIBLE);
                findViewById(R.id.email).setVisibility(View.INVISIBLE);
            }
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

    }
    void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                editor.clear(); // Ștergeți datele din SharedPreferences
                editor.commit();
                finish();
                startActivity(new Intent(Second_Activity.this, Main_Activity.class));
            }
        });
    }

}