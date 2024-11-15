package com.example.monumentos_app;

import java.io.Serializable;

public class Arquitecto implements Serializable {

    private final String nombre;
    private final char sexo;
    private final String pais;

    public Arquitecto(String nombre, char sexo, String pais) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public char getSexo() {
        return sexo;
    }

    public String getPais() {
        return pais;
    }
}
