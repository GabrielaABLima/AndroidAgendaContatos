package com.example.miniprojeto06;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.miniprojeto06.example.adapter.AdapterUsuario;
import com.example.miniprojeto06.example.adapter.RecyclerClickListener;
import com.example.miniprojeto06.example.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Usuario> usuarios;
    private FloatingActionButton botaoFlutuante;
    private SQLiteDatabase bancoDados;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarios = new ArrayList<>();
        configurarLista();
        System.out.println("oi");

        try {
            String aU = "auto_increment";

            String comando = "create table if not exists usuario (id INTEGER PRIMARY KEY, nome VARCHAR, telefone VARCHAR, img INTEGER);";
            bancoDados = openOrCreateDatabase("app6", MODE_PRIVATE, null);
            bancoDados.execSQL(comando);

        } catch (Exception e) {
            e.printStackTrace();
        }


        botaoFlutuante = findViewById(R.id.floatButton);
        botaoFlutuante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                if (usuarios.size() == 0) {
                    usuarios.add(new Usuario(1, "nome", "telefone", 1));
                } else {
                    id = pegarid();
                }

                intent.putExtra("id", id);
                startActivity(intent);
            }
        });


    }

    private void configurarLista() {
        recyclerView = findViewById(R.id.recyclerView);

        criarListaUsuario();

        AdapterUsuario adapterUsuario = new AdapterUsuario(usuarios);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterUsuario);

        recyclerView.addOnItemTouchListener(new RecyclerClickListener(
                getApplicationContext(),
                recyclerView,
                new RecyclerClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        Usuario u = usuarios.get(position);

                        Intent intent = new Intent(getApplicationContext(), Activity3.class);
                        intent.putExtra("nome", u.getNome());
                        intent.putExtra("telefone", u.getTelefone());
                        intent.putExtra("id", u.getId());
                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        try {
                            Usuario u2 = usuarios.get(position);
                            bancoDados = openOrCreateDatabase("app6", MODE_PRIVATE, null);
                            String comandoSQL = "DELETE FROM usuario WHERE  id = " + u2.getId() + ";";
                            bancoDados.execSQL(comandoSQL);
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Login deletado: " + u2.getNome() +
                                            " Id: " + u2.getId(),

                                    Toast.LENGTH_SHORT
                            ).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                        } catch (Exception e) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Delete cancelado",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                }
        ));
    }

    private void criarListaUsuario() {

        try {
            bancoDados = openOrCreateDatabase("app6", MODE_PRIVATE, null);
            Cursor cursor = bancoDados.rawQuery("select * from usuario", null);
            if (cursor != null) {
                while (cursor.moveToNext())

                    usuarios.add(new Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
                System.out.println("====================================================================");

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!ERRO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }


    }



    private int pegarid() {
        int i = 0;
        try {
            bancoDados = openOrCreateDatabase("app6", MODE_PRIVATE, null);
            Cursor cursor = bancoDados.rawQuery("Select Max(id) from usuario", null);
            if (cursor != null) {
                while (cursor.moveToNext()){
                    System.out.println(cursor.getInt(0));
                    i = cursor.getInt(0);
                }
                System.out.println("====================================================================");

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!ERRO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        return i;
    }

}