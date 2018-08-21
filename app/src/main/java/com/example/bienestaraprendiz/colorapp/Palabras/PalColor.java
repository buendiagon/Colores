package com.example.bienestaraprendiz.colorapp.Palabras;

public class PalColor {
    String palabra,color;

    public PalColor(String palabra, String color) {
        this.palabra = palabra;
        this.color = color;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
