package com.example.miniprojeto06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojeto06.example.model.Usuario;

import java.sql.ResultSet;
import java.util.Random;

public class Activity2 extends AppCompatActivity {

    private TextView cadastroNome;
    private TextView cadastroTelefone;
    private SQLiteDatabase bancoDados;
    private Button botaoSalvar;
    private int auxId = 0;
    Random aleatorio = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);



        cadastroNome = findViewById(R.id.cadastroNome);
        cadastroTelefone = findViewById(R.id.cadastroTelefone);
        botaoSalvar = findViewById(R.id.botaoSalvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Bundle dados = getIntent().getExtras();
                    auxId = dados.getInt("id") + 1;
                    bancoDados = openOrCreateDatabase("app6", MODE_PRIVATE, null);
                    String comandoSQL = "insert into usuario(id, nome, telefone, img) values (" + auxId + ", '" + cadastroNome.getText() + "', '" + cadastroTelefone.getText() + "', " + aleatorio.nextInt(3) + ");";
                    bancoDados.execSQL(comandoSQL);

                    Toast.makeText(
                            getApplicationContext(),
                            "Login efetuado: "+cadastroNome.getText(),
                            Toast.LENGTH_SHORT
                    ).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


    }
}