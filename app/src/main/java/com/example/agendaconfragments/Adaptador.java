package com.example.agendaconfragments;


import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter implements View.OnLongClickListener,View.OnClickListener, View.OnTouchListener, Filterable {

    Holder holder;
    ArrayList<Datos> datos, datosFull;
    View.OnClickListener listener;
    View.OnLongClickListener longListener;
    View.OnTouchListener listenerTouch;
    OnClickImagen listenerImagen;



    public Adaptador(ArrayList<Datos> datos) {
        this.datos = datos;
        datosFull = new ArrayList<>(datos);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder, parent,false);
        final View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_parrilla, parent,false);

        if (Utilidades.visualizacion == Utilidades.PARRILLA)
            holder = new Holder(view2);
        else
            holder = new Holder(view);

        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        view.setOnTouchListener(this);
        holder.ClickImagen(new OnClickImagen() {
            @Override
            public void onClickImagen(View v) {
                if (Utilidades.visualizacion == Utilidades.LISTA)
                    listenerImagen.onClickImagen(view);
                else
                    listenerImagen.onClickImagen(view2);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).bind(datos.get(position), position);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setClickListener(View.OnClickListener listener)
    {
        if(listener != null)
            this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) listener.onClick(v);
    }

    public void setLongListener(View.OnLongClickListener longListener)
    {
        if (longListener != null)
            this.longListener = longListener;
    }

    @Override
    public boolean onLongClick(View v) {
        if (longListener != null)
            longListener.onLongClick(v);
        return true;
    }

    public void setTouchListener(View.OnTouchListener listenerTouch)
    {
        if (listenerTouch != null)
            this.listenerTouch = listenerTouch;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        if (listenerTouch != null)
            listenerTouch.onTouch(view, motionEvent);
        return false;
    }

    public void ClickImagen(OnClickImagen listenerImagen)
    {
        if (listenerImagen != null)
            this.listenerImagen = listenerImagen;
    }


    @Override
    public Filter getFilter() {
        return  ContactosFilter;
    }

    private Filter ContactosFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence seleccion) {


            List<Datos> datosFiltrados = new ArrayList<>();

            if (seleccion == null ||seleccion.length() == 0) datosFiltrados = datosFull;
            else if (seleccion.equals("Todo")) datosFiltrados = datosFull;
            else {
                for (Datos d : datosFull)
                {
                    if (seleccion.equals("Amigos") && d.isAmigos()) datosFiltrados.add(d);
                    else if (seleccion.equals("Familiar") && d.isFamilia()) datosFiltrados.add(d);
                    else if (seleccion.equals("Trabajo") && d.isTrabajo())datosFiltrados.add(d);
                }
            }

            FilterResults results = new FilterResults();
            results.values = datosFiltrados;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            datos.clear();
            datos.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

}