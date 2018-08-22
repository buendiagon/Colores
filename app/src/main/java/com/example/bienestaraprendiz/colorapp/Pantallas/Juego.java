package com.example.bienestaraprendiz.colorapp.Pantallas;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
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
    TextView palabra,vidas,correctas;
    private Chronometer cronometro;
    private boolean iniciado=false;
    Button boton1,boton2,boton3,boton4;
    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            vidas.setText(String.valueOf((Integer.valueOf(vidas.getText().toString()))-1));
            iniciar();
            if(Integer.valueOf(vidas.getText().toString())==0){
                if(iniciado){
                    cronometro.stop();
                    iniciado=false;
                }
                Intent intent=new Intent(Juego.this,Resultados.class);
                intent.putExtra("tiempo",cronometro.getText().toString());
                intent.putExtra("puntaje",correctas.getText().toString());
                //intent con tiempo
                startActivity(intent);
                handler.removeCallbacks(runnable);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        cronometro=findViewById(R.id.crono);
        if(!iniciado){
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();
            iniciado=true;
        }
        vidas=findViewById(R.id.vidasId);
        correctas=findViewById(R.id.correctasId);
        vidas.setText("3");
        correctas.setText("0");
        iniciar();
    }
    public void iniciar(){
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
        handler.postDelayed(runnable,3000);
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
        while (p==c){
            p=rnd.nextInt(4);
            c=rnd.nextInt(4);
        }
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
        int si=0;
        handler.removeCallbacks(runnable);
        for(int i=0;i<4;i++){
            if(id==ids.get(i)){if(palabra.getTextColors().equals(ColorStateList.valueOf(Color.parseColor(getPalabras.get(i).getColor())))){
                correctas.setText(String.valueOf((Integer.valueOf(correctas.getText().toString()))+1));
                Log.d("verificar","hola");
                si=0;
                iniciar();
                break;
                }
            }
            else si=1;
        }
        if(si==1){
            vidas.setText(String.valueOf((Integer.valueOf(vidas.getText().toString()))-1));
            iniciar();
            if(Integer.valueOf(vidas.getText().toString())==0){
                if(iniciado){
                    cronometro.stop();
                    iniciado=false;
                }
                Intent intent=new Intent(Juego.this,Resultados.class);
                intent.putExtra("puntaje",correctas.getText().toString());
                intent.putExtra("tiempo",cronometro.getText().toString());
                startActivity(intent);
                handler.removeCallbacks(runnable);
                finish();
            }
        }
    }
    public void regresar(View view){
        handler.removeCallbacks(runnable);
        finish();
    }
    public void pausar(View view){
        //reiniciar cronometro
    }
}

