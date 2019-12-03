package com.example.agendaconfragments;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;



import androidx.recyclerview.widget.RecyclerView;

public class Datos implements Parcelable {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correo;
    private Bitmap imagen;
    private boolean amigos;
    private boolean familia;
    private boolean trabajo;

    public Datos() {
    }


    public Datos(String nombre, String apellidos, String telefono, String correo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        amigos = false;
        trabajo = false;
        familia = false;
    }

    protected Datos(Parcel in) {
        nombre = in.readString();
        apellidos = in.readString();
        telefono = in.readString();
        correo = in.readString();
    }

    public static final Creator<Datos> CREATOR = new Creator<Datos>() {
        @Override
        public Datos createFromParcel(Parcel in) {
            return new Datos(in);
        }

        @Override
        public Datos[] newArray(int size) {
            return new Datos[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public boolean isAmigos() {
        return amigos;
    }

    public void setAmigos(boolean amigos) {
        this.amigos = amigos;
    }

    public boolean isFamilia() {
        return familia;
    }

    public void setFamilia(boolean familia) {
        this.familia = familia;
    }

    public boolean isTrabajo() {
        return trabajo;
    }

    public void setTrabajo(boolean trabajo) {
        this.trabajo = trabajo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeString(telefono);
        dest.writeString(correo);
    }
}

