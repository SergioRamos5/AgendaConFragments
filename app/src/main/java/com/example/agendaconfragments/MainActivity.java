package com.example.agendaconfragments;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements onSelectedItemListener, onSelectedItemEditar, onSelectedItemAdd {

    ArrayList<Datos> datos;
    DrawerLayout drawerLayout;
    FragmentPrincipal fragmentPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout);

        crearToolbar();

        añadirDatos();

        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT  = FM.beginTransaction();
        Fragment fragment = new FragmentPrincipal(datos);
        FT.replace(R.id.fragment_container, fragment);
        FT.commit();

        fragmentPrincipal = (FragmentPrincipal) fragment;
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager FM = getSupportFragmentManager();
                FragmentTransaction FT  = FM.beginTransaction();
                Fragment fragment = new FragmentPrincipal(datos);
                FT.replace(R.id.fragment_container, fragment);
                switch (menuItem.getItemId())
                {
                    case R.id.Familia:
                        fragmentPrincipal.adaptador.getFilter().filter("Familiar");
                        break;
                    case R.id.Trabajo:
                        fragmentPrincipal.adaptador.getFilter().filter("Trabajo");
                        break;
                    case R.id.Amigos:
                        fragmentPrincipal.adaptador.getFilter().filter("Amigos");
                        break;
                    case R.id.Todos:
                        fragmentPrincipal.adaptador.getFilter().filter(null);
                        break;
                }
                FT.commitNow();
                return true;
            }
        });
    }

    public void crearToolbar()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void crearFragmentPrincipal()
    {
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT  = FM.beginTransaction();
        Fragment fragment = new FragmentPrincipal(datos);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
            drawerLayout.openDrawer(GravityCompat.START);
            break;
            case R.id.vista_lista:
                Utilidades.visualizacion = Utilidades.LISTA;
                break;
            case R.id.vista_parrilla:
                Utilidades.visualizacion = Utilidades.PARRILLA;
                break;
        }
        crearFragmentPrincipal();
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onItemAddSelected(Datos datos) {
        this.datos.add(datos);
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT  = FM.beginTransaction();
        Fragment fragment = new FragmentPrincipal(this.datos);
        FT.replace(R.id.fragment_container, fragment);
        FT.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vista, menu);
        return true;
    }

}
