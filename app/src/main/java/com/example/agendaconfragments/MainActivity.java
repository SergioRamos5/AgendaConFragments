package com.example.agendaconfragments;

import android.os.Bundle;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity implements onSelectedItemListener, onSelectedItemEditar, onSelectedItemAdd {

    RecyclerView recyclerView;
    public Adaptador adaptador;
    ArrayList<Datos> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        añadirDatos();

        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT  = FM.beginTransaction();
        Fragment fragment = new FragmentPrincipal(datos);
        FT.replace(R.id.fragment_container, fragment);
        FT.commit();

    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);

    }

    @Override
    public void onItemSelected(Datos datos) {
        Fragment fragmentEdit = new EditFragment();

        Bundle args = new Bundle();

        fragmentEdit.setArguments(args);

        args.putParcelable("Datos", datos);
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FT.replace(R.id.fragment_container, fragmentEdit);
        FT.addToBackStack(null);

        FT.commit();
    }

    @Override
    public void onItemEditSelected(Datos datos) {

        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT  = FM.beginTransaction();
        Fragment fragment = new FragmentPrincipal(this.datos);
        FT.replace(R.id.fragment_container, fragment);
        FT.commit();
    }

    public void añadirDatos()
    {
        datos = new ArrayList<>();
        datos.add(new Datos("Sergio", "Ramos Santonja", "676813768","sergio@gmail.com"));
        datos.add(new Datos("Carlos", "Clemente Bellido", "659478945","carlos@gmail.com"));
        datos.add(new Datos("Aìtor", "Soto Jimenez", "675221581","aitor@gmail.com"));
        datos.add(new Datos("Paula", "Valero Ferrandez", "678912528","paula@gmail.com"));
        datos.add(new Datos("Andrea", "Ramos Santonja", "7548475254","andrea@gmail.com"));
    }

    @Override
    public void onItemAddSelected(Datos datos) {
        this.datos.add(datos);
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT  = FM.beginTransaction();
        Fragment fragment = new FragmentPrincipal(this.datos);
        FT.replace(R.id.fragment_container, fragment);
        FT.commit();
    }
}
