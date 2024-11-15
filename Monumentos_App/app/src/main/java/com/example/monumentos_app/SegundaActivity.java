package com.example.monumentos_app;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SegundaActivity extends AppCompatActivity {

    TextView tvTitulo2, tvUbicacion2, tvDescripcion2, tvArquitecto2, tvEstilo2,
            tvAnioConstr2, tvAltura2, tvPatMundial2;
    ImageView ivImagenMonumento2;
    Button btIrPrincipal;
    Monumento monumentoRecibido;
    public static final String MONUMENTO_ALMACENADO = "monumento_almacenado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initreferences();
        setListenersToButtons();
        getDatosIntent();

        if (savedInstanceState != null) {
            monumentoRecibido = (Monumento)savedInstanceState.getSerializable(MONUMENTO_ALMACENADO);
            if (monumentoRecibido != null) {
                setMonumentoRecibido();
            } else {
                Toast.makeText(this, "No se ha recibido el monumento" , Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Inicia las referencias de las vistas
     */
    private void initreferences() {
        tvTitulo2 = findViewById(R.id.tvTitulo2);
        tvDescripcion2 = findViewById(R.id.tvDescripcion2);
        tvUbicacion2 = findViewById(R.id.tvUbicacion2);
        tvArquitecto2 = findViewById(R.id.tvArquitecto2);
        tvEstilo2 = findViewById(R.id.tvEstilo2);
        tvAnioConstr2 = findViewById(R.id.tvAnioConstr2);
        tvAltura2 = findViewById(R.id.tvAltura2);
        tvPatMundial2 = findViewById(R.id.tvPatMundial2);
        ivImagenMonumento2 = findViewById(R.id.ivImagenMonumento2);
        btIrPrincipal = findViewById(R.id.btIrPrincipal);
    }

    /**
     * Asigna los escuchadores a los botones
     */
    private void setListenersToButtons() {
        btIrPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iPantallaPrincipal = new Intent(getApplicationContext(), MainActivity.class);
                iPantallaPrincipal.putExtra(MONUMENTO_ALMACENADO, monumentoRecibido);
                startActivity(iPantallaPrincipal);
            }
        });
    }

    /**
     * Recibe el Intent con el monumento que se muestra en la primera pantalla y
     * lo asigna a monumentoRecibido.
     * Recrea la Aplicación con la información de las vistas de ese monumento
     */
    private void getDatosIntent() {
        monumentoRecibido = (Monumento)getIntent().getSerializableExtra(MainActivity.MONUMENTO_GUARDADO);
        setMonumentoRecibido();
        }

    /**
     * Comprueba si el monumento recibido de la MainActivity es nulo. Si no lo es,
     * asigna a monumentoRecibido los valores de las vistas de ese monumento que recibió
     * Si el null salta un mensaje de error diciendo que no se ha recibido el monumento
     */
    @SuppressLint("SetTextI18n")
    public void setMonumentoRecibido() {

        if (monumentoRecibido != null) {
            tvTitulo2.setText(monumentoRecibido.getNombre());
            tvUbicacion2.setText(monumentoRecibido.getUbicacion());
            tvDescripcion2.setText(monumentoRecibido.getDescripcion());
            String ArquitectoSexo;
            if (monumentoRecibido.getArquitecto().getSexo() == 'H') {
                ArquitectoSexo = "Hombre";
            } else if (monumentoRecibido.getArquitecto().getSexo() == 'M'){
                ArquitectoSexo = "Mujer";
            } else {
                ArquitectoSexo = "Sexo sin especificar";
            }
            tvArquitecto2.setText("Arquitecto: " + monumentoRecibido.getArquitecto().getNombre() + ", " + ArquitectoSexo + ", " + monumentoRecibido.getArquitecto().getPais());
            tvEstilo2.setText(monumentoRecibido.getEstilo());
            tvAnioConstr2.setText("Se construyó en el año " + String.valueOf(monumentoRecibido.getAnioConstruccion()));
            tvAltura2.setText("Altura: " + String.valueOf(monumentoRecibido.getAltura()) + " metros");
            tvPatMundial2.setText(monumentoRecibido.getPatrimonioSiNo());
            ivImagenMonumento2.setImageResource(monumentoRecibido.getIdDimagen());
        } else {
            Toast.makeText(this, "No se ha recibido el monumento" , Toast.LENGTH_SHORT).show();
        }
        }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle miBundle) {
        super.onSaveInstanceState(miBundle);
        miBundle.putSerializable(MONUMENTO_ALMACENADO, monumentoRecibido);
    }
}
