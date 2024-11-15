package com.example.monumentos_app;

import java.io.Serializable;

public class Monumento implements Serializable {
    private final int idDimagen;
    private final String nombre;
    private final String ubicacion;
    private final int anioConstruccion;
    private final Arquitecto arquitecto;
    private final String descripcion;
    private final String patrimonioSiNo;
    private final String estilo;
    private final int altura;

    public Monumento(int idDimagen, String nombre, String ubicacion, int anioConstruccion, Arquitecto arquitecto, String descripcion, String patrimonioSiNo, String estilo, int altura) {
        this.idDimagen = idDimagen;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.anioConstruccion = anioConstruccion;
        this.arquitecto = arquitecto;
        this.descripcion = descripcion;
        this.patrimonioSiNo = patrimonioSiNo;
        this.estilo = estilo;
        this.altura = altura;
    }

    public int getIdDimagen() {
        return idDimagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getAnioConstruccion() {
        return anioConstruccion;
    }

    public Arquitecto getArquitecto() {
        return arquitecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPatrimonioSiNo() {
        return patrimonioSiNo;
    }

    public String getEstilo() {
        return estilo;
    }

    public double getAltura() {
        return altura;
    }
}
