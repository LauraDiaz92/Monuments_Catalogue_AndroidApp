package com.example.monumentos_app;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvCatalogo, tvTitulo, tvDescripcion, tvUbicacion;
    ImageView ivImagenMonumento;
    Button btVerDetalles, btVerOtro;
    Monumento monumentoActual;
    public static final String MONUMENTO_GUARDADO = "monumento_guardado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initReferences();
        setMonumentoAleatorio();
        setListenersToButtons();

        if (savedInstanceState != null) {
            monumentoActual = (Monumento) savedInstanceState.getSerializable(MONUMENTO_GUARDADO);

            if (monumentoActual != null) {
                tvTitulo.setText(monumentoActual.getNombre());
                tvDescripcion.setText(monumentoActual.getDescripcion());
                tvUbicacion.setText(monumentoActual.getUbicacion());
                ivImagenMonumento.setImageResource(monumentoActual.getIdDimagen());
            } else {
                Toast.makeText(this, "No se ha recibido el monumento" , Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Inicia las referencias de las vistas
     */
    private void initReferences() {
        tvCatalogo = findViewById(R.id.tvCatalogo);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvUbicacion = findViewById(R.id.tvUbicacion);
        ivImagenMonumento = findViewById(R.id.ivImagenMonumento);
        btVerDetalles = findViewById(R.id.btVerDetalles);
        btVerOtro = findViewById(R.id.btVerOtro);
    }

    /**
     * Asigna los escuchadores a los botones
     */
    private void setListenersToButtons() {
        btVerDetalles.setOnClickListener(this);
        btVerOtro.setOnClickListener(this);
    }

    /**
     * Crea los 5 monumentos y los añade a una lista
     * @return la lista de monumentos
     */
    private List<Monumento> getArrayMonumentos() {

        List<Monumento> listaMonumentos = new ArrayList<>();

        Monumento torreEiffel = new Monumento(R.drawable.torreeiffel, "La torre Eiffel", "- París, Francia -", 1889, new Arquitecto("Gustave Eiffel", 'H',"Francia"), "\n\n Es un emblemático monumento de hierro con elegante diseño. Fue construida para una exposición y se ha convertido en el símbolo más representativo de la ciudad.", "Patrimonio Mundial: No", "Estilo industrial del siglo XIX", 330);
        Monumento alhambra = new Monumento(R.drawable.alhambra, "La Alhambra", "- Granada, España -", 1238, new Arquitecto("Al-Jatib",'H',"Granada"), "\n\n Es un palacio-fortaleza construido por reyes nazaríes. Destaca por su arquitectura islámica, intrincados detalles decorativos y hermosos jardines.", "Patrimonio Mundial: Sí", "Estilo nazarí", 20);
        Monumento coliseo = new Monumento(R.drawable.coliseo, "El coliseo Romano", "- Roma, Italia -", 80, new Arquitecto("Vespasiano", 'H', "Roma"), "\n\n Es un antiguo anfiteatro usado para espectáculos como luchas de gladiadores. Es uno de los monumentos más emblemáticos y visitados del mundo.", "Patrimonio Mundial: Sí", "Estilo clásico romano", 48);
        Monumento cristoRedentor = new Monumento(R.drawable.cristoredentor, "El Cristo Redentor", "- Río de Janeiro, Brasil -", 1931, new Arquitecto("Paul LandowsKi", 'H', "Francia"), "\n\n Es una estatua de Jesucristo icónica de la ciudad y de la fe cristiana, con los brazos extendidos, ofreciendo una vista panorámica de Río.", "Patrimonio Mundial: Sí", "Estilo: Arte monumental religioso", 30);
        Monumento tajMahal = new Monumento(R.drawable.tajmahal, "El Taj Mahal", "- Agra, India -", 1653, new Arquitecto("Ustad Ahmad", 'H', "India"), "\n\n Es un mausoleo de mármol blanco. El emperador Shah Jahan lo construyó en memoria de su esposa fallecida.\n Es famoso por su belleza y simetría. Representa el eterno amor", "Patrimonio Mundial: Sí", "Estilo islámico, persa y mughal", 73);

        listaMonumentos.add(torreEiffel);
        listaMonumentos.add(alhambra);
        listaMonumentos.add(coliseo);
        listaMonumentos.add(cristoRedentor);
        listaMonumentos.add(tajMahal);

        return listaMonumentos;
    }

    /**
     * Toma una posición aleatoria de la lista de monumentos y el monumento que se
     * encuentra en esa posición se asigna a monumentoActual
     * Las vistas contendrán la información de ese monumento
     */
    private void setMonumentoAleatorio() {
        int aleatorio = (int)(Math.random() * getArrayMonumentos().size());
        monumentoActual = getArrayMonumentos().get(aleatorio);

        tvTitulo.setText(monumentoActual.getNombre());
        tvDescripcion.setText(monumentoActual.getDescripcion());
        tvUbicacion.setText(monumentoActual.getUbicacion());
        ivImagenMonumento.setImageResource(monumentoActual.getIdDimagen());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle miBundle) {
        super.onSaveInstanceState(miBundle);
        miBundle.putSerializable(MONUMENTO_GUARDADO, monumentoActual);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btVerDetalles) {
            lanzarSegundaPantalla();
        } else {
            setMonumentoAleatorio();
        }
    }

    private void lanzarSegundaPantalla() {
        Intent iSegundaPantalla = new Intent(getApplicationContext(), SegundaActivity.class);
        iSegundaPantalla.putExtra(MONUMENTO_GUARDADO, monumentoActual);
        startActivity(iSegundaPantalla);
    }

    /**
     * Recibe el Intent con el monumento que se muestra en la segunda pantalla y
     * lo asigna a monumentoActual.
     * Recrea la Aplicación con la información de las vistas de ese monumento
     */
    public void getDatosIntent2Pantalla() {
        monumentoActual = (Monumento)getIntent().getSerializableExtra(SegundaActivity.MONUMENTO_ALMACENADO);
        setMonumentoAleatorio();
    }
}