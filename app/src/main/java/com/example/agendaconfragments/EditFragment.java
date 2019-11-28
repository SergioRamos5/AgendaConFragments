package com.example.agendaconfragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EditFragment extends Fragment {

    private FloatingActionButton fab;
    private Datos datos;
    EditText nombre, apellido, telefono, correo;
    onSelectedItemListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.editar_contacto, container, false);


        fab = v.findViewById(R.id.fab2);
        nombre = v.findViewById(R.id.nombre_edit);
        apellido = v.findViewById(R.id.apellido_edit);
        telefono = v.findViewById(R.id.telefono_edit);
        correo = v.findViewById(R.id.email_edit);


        datos = getArguments().getParcelable("Datos");

        nombre.setText(datos.getNombre());
        apellido.setText(datos.getApellidos());
        correo.setText(datos.getCorreo());
        telefono.setText(datos.getTelefono());


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.setNombre(nombre.getText().toString());
                datos.setApellidos(apellido.getText().toString());
                datos.setCorreo(correo.getText().toString());
                datos.setTelefono(telefono.getText().toString());

                Fragment fragmentPrincipal = new FragmentPrincipal();

                Bundle args = new Bundle();

                fragmentPrincipal.setArguments(args);

                args.putParcelable("Datos", datos);
                FragmentManager FM = getActivity().getSupportFragmentManager();
                FragmentTransaction FT = FM.beginTransaction();
                FT.replace(R.id.fragment_container, fragmentPrincipal);
                FT.addToBackStack(null);
                FT.commit();
            }
        });



       return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (onSelectedItemListener) context;
    }

}
