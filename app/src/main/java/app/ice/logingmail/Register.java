package app.ice.logingmail;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {
    private EditText et1,et2;
    private Button btn1;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth= FirebaseAuth.getInstance();
        et1= findViewById(R.id.editText3);
        et2= findViewById(R.id.editText5);
        btn1=findViewById(R.id.button3);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = et1.getText().toString();
                String password = et2.getText().toString();
                if(TextUtils.isEmpty(user)){
                    Toast.makeText(Register.this,"Coloca Un Correo",Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this,"Coloca Un password",Toast.LENGTH_LONG).show();

                }

                mAuth.createUserWithEmailAndPassword(user,password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Register.this,"Se ha Creado El usuario",Toast.LENGTH_SHORT).show();
                        if(!task.isSuccessful()){
                            Toast.makeText(Register.this,"Tenemos Un problema",Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });
    }
}
