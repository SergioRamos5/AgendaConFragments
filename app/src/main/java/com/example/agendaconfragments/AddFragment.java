package com.example.agendaconfragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddFragment extends Fragment {

    private Datos datos;
    private FloatingActionButton fab;
    EditText nombre, apellido, correo, telefono;
    onSelectedItemAdd listenerAdd;
    RadioButton amigos, trabajo, familia;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add, container, false);

        datos = new Datos();

        nombre = v.findViewById(R.id.eT_Nombre);
        apellido = v.findViewById(R.id.eT_Apellido);
        correo = v.findViewById(R.id.eT_email);
        telefono = v.findViewById(R.id.eT_Telefono);
        familia = v.findViewById(R.id.rB_Familia);
        amigos = v.findViewById(R.id.rB_Amigos);
        trabajo = v.findViewById(R.id.rB_Trabajo);

        fab = v.findViewById(R.id.fab1);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.setNombre(nombre.getText().toString());
                datos.setApellidos(apellido.getText().toString());
                datos.setTelefono(telefono.getText().toString());
                datos.setCorreo(correo.getText().toString());
                datos.setAmigos(amigos.isChecked());
                datos.setFamilia(familia.isChecked());
                datos.setTrabajo(trabajo.isChecked());

                listenerAdd.onItemAddSelected(datos);
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listenerAdd = (onSelectedItemAdd) context;
    }
}
