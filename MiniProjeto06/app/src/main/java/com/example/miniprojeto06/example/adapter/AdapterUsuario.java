package com.example.miniprojeto06.example.adapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojeto06.example.model.Usuario;
import com.example.miniprojeto06.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class AdapterUsuario  extends RecyclerView.Adapter<AdapterUsuario.ViewHolderUsuario>{

    private List<Usuario> usuarios;
    public int img;
    Random aleatorio = new Random();
    int opcaoPc = 0;
    private SQLiteDatabase bancoDados;

    public AdapterUsuario(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolderUsuario onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemLista =
                LayoutInflater.from(parent.getContext()).
                        inflate(
                                R.layout.adapter_layout_usuario2,
                                parent,
                                false
                        );

        return new ViewHolderUsuario(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderUsuario holder, int position) {

        holder.itemNome.setText(usuarios.get(position).getNome());
        holder.itemTelefone.setText(usuarios.get(position).getTelefone());
        switch(usuarios.get(position).getImg()){
            case 0:
                img = R.drawable.um;
                break;
            case 1:
                img = R.drawable.dois;
                break;
            case 2:
                img = R.drawable.tres;
                break;
            case 3:
                img=R.drawable.quatro;
                break;
        }

    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public class ViewHolderUsuario extends RecyclerView.ViewHolder{

        private TextView itemNome;
        private TextView itemTelefone;
        private ImageView imgPc;


        public ViewHolderUsuario(@NonNull @NotNull View itemView) {
            super(itemView);
            itemNome = itemView.findViewById(R.id.itemNome);
            itemTelefone = itemView.findViewById(R.id.itemTelefone);
            imgPc = (ImageView) itemView.findViewById(R.id.imageView);
            imgPc.setImageResource(img);

        }
    }

}

