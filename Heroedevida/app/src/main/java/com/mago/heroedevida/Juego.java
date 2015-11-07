package com.mago.heroedevida;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Juego extends AppCompatActivity {
    private Button btnAdivina;
    private EditText txtIntento;
    private ImageView imgOrgano;
    private TextView txtInfo;
    private int numeroaleatorio;
    private Map<Integer, String> informacion;
    private String[] orgaccent={"corazón", "pulmón", "hueso", "hígado", "riñón", "ojo", "intestino"};
    private String[] organos={"corazon","pulmon", "hueso", "higado",
        "rinon", "ojo", "intestino"};
    private  String[] sombraOrganos={"scorazon", "spulmon", "shueso", "shigado",
            "srinon", "sojo", "sintestino"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        llenaMapa();
        btnAdivina=(Button)findViewById(R.id.btnAdivina);
        txtIntento=(EditText)findViewById(R.id.txtIntento);
        numeroaleatorio=aleatorio();
        imgOrgano=(ImageView)findViewById(R.id.imgOrgano);
        txtInfo=(TextView)findViewById(R.id.txtInfo);

        setSombra(numeroaleatorio);
        btnAdivina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=txtIntento.getText().toString().toLowerCase();
                if(nombre.equals(organos[numeroaleatorio]) || nombre.equals(orgaccent[numeroaleatorio])){
                    esconderTeclado(Juego.this);
                    setOrgano(numeroaleatorio);
                    txtInfo.setText(informacion.get(numeroaleatorio));
                    espera();
                }else{
                    Toast.makeText(getApplicationContext(),"Estás equivocado", Toast.LENGTH_SHORT).show();
                    Vibrator vibrator =(Vibrator)getSystemService(getApplicationContext().VIBRATOR_SERVICE);
                    vibrator.vibrate(1000);
                }
            }
        });
    }

    private int aleatorio(){
        return (int)(Math.random()*organos.length);
    }

    private void setSombra(int aleatorio){
        int resId = getResources().getIdentifier(sombraOrganos[aleatorio], "drawable", getPackageName());
        imgOrgano.setImageResource(resId);
    }

    private void setOrgano(int aleatorio){
        int resId = getResources().getIdentifier(organos[aleatorio], "drawable", getPackageName());
        imgOrgano.setImageResource(resId);
    }

    private void espera(){
        new CountDownTimer(2000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                numeroaleatorio=aleatorio();
                setSombra(numeroaleatorio);
                txtIntento.setText("");

            }
        }.start();
    }

    public void esconderTeclado(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    private void llenaMapa(){
        informacion=new HashMap<Integer, String>();
        informacion.put(0, "Órgano central de la circulación de la sangre,  " +
                "el ser humano es un músculo hueco y piramidal situado en la " +
                "cavidad torácica. Funciona como una bomba aspirante e impelente, " +
                "impulsando la sangre a todo el cuerpo.");
        informacion.put(1, "Órgano de la respiración en el que se realiza el " +
                "intercambio gaseoso entre el aire y la sangre; en el hombre " +
                "son dos, situados en la cavidad torácica uno al lado del otro, " +
                "blandos y esponjosos, y durante la respiración se contraen y se dilatan.");
        informacion.put(2, "Pieza dura y resistente del esqueleto, de color blanco " +
                "amarillento; está formada por sustancia orgánica y sales minerales, " +
                "y envuelta por una membrana fibrosa.");
        informacion.put(3, "Órgano glandular del hombre, de forma aplanada, tamaño grande " +
                "y color rojo oscuro, que interviene en la función digestiva, segrega la " +
                "bilis, almacena sustancias nutrientes, elimina sustancias tóxicas y " +
                "sintetiza enzimas, proteínas y glucosa; en el ser humano, se encuentra " +
                "en la parte superior derecha del abdomen.");
        informacion.put(4, "Órgano glandular situado en la región lumbar que tiene la función " +
                "de segregar la orina.");
        informacion.put(5, "Membrana transparente en forma de disco abombado, que constituye " +
                "la parte anterior del globo ocular y se halla delante del iris.");
        informacion.put(6, "Es la parte visceral tubular del aparato digestivo que se extiende " +
                "desde el estómago hasta el ano, situada en la cavidad abdominal.  En el " +
                "intestino se extraen los nutrientes de los alimentos.");
    }

    private String info(int indice){
        return informacion.get(indice);
    }
}
