package com.example.cm_sgdr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity_cadastro1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro1);

        Button proximo = (Button) findViewById(R.id.button_cadastro1_proximo);

        proximo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MainActivity_cadastro1.this, MainActivity_cadastro2.class);
                startActivity(it);
            }
        });

        Spinner spinner_republica = (Spinner)
        findViewById(R.id.spinner_cadastro1_republica);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.menu, android.R.layout.simple_spinner_item);
        spinner_republica.setAdapter(adapter);

    }
}
