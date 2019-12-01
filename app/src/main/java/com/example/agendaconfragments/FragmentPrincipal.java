package com.example.agendaconfragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentPrincipal extends Fragment {

    private RecyclerView recyclerView;
    public ArrayList<Datos> datos;
    private Adaptador adaptador;
    private onSelectedItemListener listener;
    private onSelectedItemAdd listenerAdd;
    int pos;
    private SwipeDetector swipeDetector;
    private FloatingActionButton fab;


    public FragmentPrincipal(ArrayList<Datos> datos) {
        this.datos = datos;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recycler, container, false);

        adaptador = new Adaptador(datos);
        adaptador.setLongListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pos = recyclerView.getChildAdapterPosition(v);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("¿Eliminar contacto " + datos.get(pos).getNombre() + " ?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                datos.remove(pos);
                                recyclerView.setAdapter(adaptador);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });

        swipeDetector = new SwipeDetector();
        adaptador.setTouchListener(swipeDetector);
        adaptador.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swipeDetector.swipeDetected())
                {
                    if (swipeDetector.getAction() == SwipeDetector.Action.LR){
                        pos = recyclerView.getChildAdapterPosition(view);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("¿Llamar a " + datos.get(pos).getNombre() + " ?")
                                .setPositiveButton("Llamar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intento = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+datos.get(pos).getTelefono()));
                                        startActivity(intento);

                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else if (swipeDetector.getAction() == SwipeDetector.Action.RL)
                    {
                        pos = recyclerView.getChildAdapterPosition(view);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("¿Enviar mensaje a " + datos.get(pos).getNombre() + " ?")
                                .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intento = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",datos.get(pos).getCorreo(), null));
                                        startActivity(intento);
                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else
                        Toast.makeText(getContext(), "tt", Toast.LENGTH_SHORT).show();
                }else
                {
                    pos = recyclerView.getChildAdapterPosition(view);
                    listener.onItemSelected(datos.get(pos));

                }
            }
        });
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        recyclerView.setAdapter(adaptador);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));


        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager FM = getActivity().getSupportFragmentManager();
                FragmentTransaction FT  = FM.beginTransaction();
                Fragment addfragment = new AddFragment();
                FT.replace(R.id.fragment_container, addfragment);
                FT.commit();
            }
        });

        return v;
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (onSelectedItemListener) context;
        listenerAdd = (onSelectedItemAdd)context;
    }



}
