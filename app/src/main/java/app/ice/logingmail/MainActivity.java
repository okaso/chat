package app.ice.logingmail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private    EditText et1,et2;
    private Button btn1,btn2;

    TextView tv1;
    RecyclerView recyclerView;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    FirebaseRecyclerAdapter<Notes,NotesAdapter.ViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth= FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        et1= findViewById(R.id.editText);
        btn1= findViewById(R.id.button);
        recyclerView= findViewById(R.id.recicle);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter= new FirebaseRecyclerAdapter<Notes, NotesAdapter.ViewHolder>(
                Notes.class,
                R.layout.user,
                NotesAdapter.ViewHolder.class,
                databaseReference.child("Items")
                ) {
            @Override
            protected void populateViewHolder(NotesAdapter.ViewHolder viewHolder, Notes model, int position) {
                viewHolder.count.setText(model.getCount());
                viewHolder.name.setText(model.getName());

            }
        };

        final SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        final Date date = new Date();


        recyclerView.setAdapter(adapter);
        final int i=4;
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendario = new GregorianCalendar();
                final int hora, minutos, segundos;
                hora =calendario.get(Calendar.HOUR_OF_DAY);
                minutos = calendario.get(Calendar.MINUTE);
                segundos = calendario.get(Calendar.SECOND);

                final String fecha = simpleDateFormat.format(date)+hora+minutos+segundos;

                String mensaje=et1.getText().toString();
                databaseReference.child("Items").child(fecha).child("name").setValue(mensaje);
            }
        });


        /*et1= findViewById(R.id.editText);
        et2= findViewById(R.id.editText2);
        btn1=findViewById(R.id.button);
        btn2=findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User= et1.getText().toString();
                String Pass= et2.getText().toString();
                if(TextUtils.isEmpty(User)){
                    Toast.makeText(MainActivity.this,"Coloca Un Correo",Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(Pass)){
                    Toast.makeText(MainActivity.this,"Coloca Un password",Toast.LENGTH_LONG).show();

                }
                auth.signInWithEmailAndPassword(User,Pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Correo o Password incorrecto",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });*/
        /*
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });*/

    }
}
