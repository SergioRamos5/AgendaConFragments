package com.example.agendaconfragments;

import android.net.Uri;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    TextView nombre, apellidos, correo, telefono;
    Datos datos;
    ImageView imagen;

    public Holder(@NonNull View itemView) {
        super(itemView);


        nombre = itemView.findViewById(R.id.Nombre);
        apellidos = itemView.findViewById(R.id.Apellido);
        telefono = itemView.findViewById(R.id.Telefono);
        correo = itemView.findViewById(R.id.Correo);
        imagen = itemView.findViewById(R.id.Imagen);
        imagen.setOnCreateContextMenuListener(this);

    }

    public void bind(Datos datos, int pos) {

        nombre.setText(datos.getNombre());
        apellidos.setText(datos.getApellidos());
        telefono.setText(datos.getTelefono());
        correo.setText(datos.getCorreo());
        imagen.setImageBitmap(datos.getImagen());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = new MenuInflater(v.getContext());
        menuInflater.inflate(R.menu.menu_contextual,menu);
    }

}
