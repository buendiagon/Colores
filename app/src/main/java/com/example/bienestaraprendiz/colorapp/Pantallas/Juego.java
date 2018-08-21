package com.example.bienestaraprendiz.colorapp.Pantallas;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bienestaraprendiz.colorapp.Palabras.PalColor;
import com.example.bienestaraprendiz.colorapp.R;

import java.util.ArrayList;
import java.util.Random;

public class Juego extends AppCompatActivity {
    ArrayList<String> colores;
    ArrayList<String> palabras;
    ArrayList<PalColor> getPalabras;
    ArrayList<Integer> ids;
    TextView palabra;
    Button boton1,boton2,boton3,boton4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        colores=new ArrayList<>();
        palabras=new ArrayList<>();
        ids=new ArrayList<>();
        getPalabras=new ArrayList<>();
        palabra=findViewById(R.id.palabraId);
        ids.add((boton1=findViewById(R.id.boton1)).getId());
        ids.add((boton2=findViewById(R.id.boton2)).getId());
        ids.add((boton3=findViewById(R.id.boton3)).getId());
        ids.add((boton4=findViewById(R.id.boton4)).getId());
        for(int i=0;i<4;i++){
            getPalabras.add(new PalColor("n","n"));
        }
        palabras.add("amarillo");palabras.add("rojo");palabras.add("verde");palabras.add("azul");
        colores.add("#ffff00");colores.add("#ff0000");colores.add("#00ff00");;colores.add("#0000ff");
        CrearPalabra();
    }

    private void CrearPalabra() {
        Random rnd =new Random(System.currentTimeMillis());
        int p=rnd.nextInt(4);
        int c=rnd.nextInt(4);
        for (int i=0;i<4;i++) {
            while (p==i){
                p=rnd.nextInt(4);
            }
            while (!getPalabras.get(c).getColor().equals("n")) {
                c=rnd.nextInt(4);
            }
            getPalabras.get(c).setColor(colores.get(i));
            getPalabras.get(c).setPalabra(palabras.get(i));
        }
        llenarBotones();
        llenarPalabra();
    }

    private void llenarPalabra() {
        Random rnd =new Random(System.currentTimeMillis());
        int p=rnd.nextInt(4);
        int c=rnd.nextInt(4);
        palabra.setText(palabras.get(p));
        palabra.setTextColor(Color.parseColor(colores.get(c)));
    }

    private void llenarBotones() {
        Log.d("verif",getPalabras.get(0).getColor());
        boton1.setBackgroundColor(Color.parseColor(getPalabras.get(0).getColor()));
        boton2.setBackgroundColor(Color.parseColor(getPalabras.get(1).getColor()));
        boton3.setBackgroundColor(Color.parseColor(getPalabras.get(2).getColor()));
        boton4.setBackgroundColor(Color.parseColor(getPalabras.get(3).getColor()));
        boton1.setText(getPalabras.get(0).getPalabra());
        boton2.setText(getPalabras.get(1).getPalabra());
        boton3.setText(getPalabras.get(2).getPalabra());
        boton4.setText(getPalabras.get(3).getPalabra());
    }
    public void verificar(View view){
        int id=view.getId();
        for(int i=0;i<4;i++){
            if(id==ids.get(i)){if(palabra.getTextColors().equals(ColorStateList.valueOf(Color.parseColor(getPalabras.get(i).getColor())))){
                    Toast.makeText(getApplicationContext(),"Has Ganado",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

