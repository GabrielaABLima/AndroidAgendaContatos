package com.example.miniprojeto06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    private TextView editNome;
    private TextView editTelefone;
    private Button botaoEditar;
    private SQLiteDatabase bancoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);


        editNome = findViewById(R.id.editNome);
        editTelefone = findViewById(R.id.editTelefone);

        Bundle dados = getIntent().getExtras();
        editNome.setText(dados.getString("nome"));
        editTelefone.setText(dados.getString("telefone"));


        botaoEditar = findViewById(R.id.botaoEditar);
        botaoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    bancoDados = openOrCreateDatabase("app6", MODE_PRIVATE, null);
                    String comandoSQL = "UPDATE usuario SET nome = '" +  editNome.getText() + "', telefone = '" + editTelefone.getText() + "' WHERE id = " + dados.getInt("id") + ";";
                    bancoDados.execSQL(comandoSQL);
                    Toast.makeText(
                            getApplicationContext(),
                            "Login atualizado: "+editNome.getText(),
                            Toast.LENGTH_SHORT
                    ).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);



                } catch (Exception e) {
                    Toast.makeText(
                            getApplicationContext(),
                            "DEU ERRO, EIN MARCELO",
                            Toast.LENGTH_SHORT
                    ).show();
                }


            }
        });

    }
}