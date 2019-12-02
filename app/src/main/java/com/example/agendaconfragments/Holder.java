package com.example.agendaconfragments;

import android.net.Uri;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView nombre, apellidos, correo, telefono;
    Datos datos;
    OnClickImagen listenerImgane;
    ImageView imagen;

    public Holder(@NonNull View itemView) {
        super(itemView);


        nombre = itemView.findViewById(R.id.Nombre);
        apellidos = itemView.findViewById(R.id.Apellido);
        telefono = itemView.findViewById(R.id.Telefono);
        correo = itemView.findViewById(R.id.Correo);
        imagen = itemView.findViewById(R.id.Imagen);
        imagen.setOnClickListener(this);

    }

    public void bind(Datos datos, int pos) {

        nombre.setText(datos.getNombre());
        apellidos.setText(datos.getApellidos());
        telefono.setText(datos.getTelefono());
        correo.setText(datos.getCorreo());
        if (datos.getImagen() != null)
            imagen.setImageBitmap(datos.getImagen());
    }


    public void ClickImagen(OnClickImagen listenerImgane)
    {
        if(listenerImgane != null) this.listenerImgane = listenerImgane;
    }

    @Override
    public void onClick(View v) {
        if (listenerImgane != null)
            listenerImgane.onClickImagen(v);
    }
}
