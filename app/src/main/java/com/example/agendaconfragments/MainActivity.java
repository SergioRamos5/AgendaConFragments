package com.example.agendaconfragments;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;



public class MainActivity extends FragmentActivity implements onSelectedItemListener {

    RecyclerView recyclerView;
    public Adaptador adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT  = FM.beginTransaction();
        Fragment fragment = new FragmentPrincipal();
        FT.replace(R.id.fragment_container, fragment);
        FT.commit();

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
}
