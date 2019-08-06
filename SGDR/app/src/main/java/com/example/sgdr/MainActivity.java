package com.example.sgdr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sgdr.modelo.Pessoa;
import com.google.firebase.FirebaseApp;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    EditText edtNome, edtEmail;
    ListView listV_dados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtEmail = (EditText)findViewById(R.id.editEmail);
        edtNome = (EditText)findViewById(R.id.editNome);
        listV_dados = (EditText)findViewById(R.id.listV_dados);

        inicializarFirebase();

    }

    private void inicializarFirebase (){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_novo){
            Pessoa p = new Pessoa();
            p.setUid(UUID.randomUUID().toString());
            p.setNome(edtNome.getText().toString());
            p.setEmail(edtEmail.getText().toString());
            databaseReference.child("Pessoa").child(p.getUid()).setValue(p);
            limparCampos();
        }
        return true;
    }

    private void limparCampos(){
        edtEmail.setText("");
        edtNome.setText("");
    }
}

