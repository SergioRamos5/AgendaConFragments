package com.example.agendaconfragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter implements View.OnLongClickListener,View.OnClickListener, View.OnTouchListener {

    Holder holder;
    Context context;
    ArrayList<Datos> datos;
    View.OnClickListener listener;
    View.OnLongClickListener longListener;
    View.OnTouchListener listenerTouch;

    public Adaptador(ArrayList<Datos> datos) {
        this.datos = datos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder, parent,false);
        holder = new Holder(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        view.setOnTouchListener(this);
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
}