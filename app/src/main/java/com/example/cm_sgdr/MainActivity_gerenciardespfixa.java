package com.example.cm_sgdr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cm_sgdr.modelo.Fatura;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class MainActivity_gerenciardespfixa extends AppCompatActivity {

    private Intent intent;
    private String codigo;

    private DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gerenciardespfixa);

        Intent intent = getIntent();
        codigo = intent.getStringExtra("c_conta");

        final EditText mes = findViewById(R.id.editText_gerenciardespfixa_mes);
        final EditText multa = findViewById(R.id.editText_gerenciardespfixa_multa);
        final EditText total = findViewById(R.id.editText_gerenciardespfixa_total);
        final EditText data = findViewById(R.id.editText_gerenciardespfixa_data);
        Button confirmar = findViewById(R.id.button_gerenciardespfixa_confirmar);

        confirmar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(mes.getText().toString().isEmpty() || multa.getText().toString().isEmpty() || total.getText().toString().isEmpty() || data.getText().toString().isEmpty()){
                    makeText(MainActivity_gerenciardespfixa.this, "Preencha todos os campos!", LENGTH_LONG).show();
                }

                else {

                    // Fatura

                    Fatura fatura = new Fatura();
                    fatura.setC_conta(codigo);
                    fatura.setData(data.getText().toString());
                    fatura.setMes(mes.getText().toString());
                    fatura.setMulta(multa.getText().toString());
                    fatura.setTotal(total.getText().toString());

                    raiz.child("Fatura").push().setValue(fatura);

                    Intent it = new Intent(MainActivity_gerenciardespfixa.this, MainActivity_principal.class);
                    startActivity(it);
                }
            }
        });

    }
}
