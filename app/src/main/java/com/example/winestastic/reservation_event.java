package com.example.winestastic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class reservation_event extends AppCompatActivity {
    private TextView titleText, addressText, textDescription, horarioTextView;
    private FirebaseFirestore mFirestore;
    private String idEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_event);

        // Inicializamos Firestore
        mFirestore = FirebaseFirestore.getInstance();

        //Aquí encontramos las referencias a los elementos de la interfaz de usuario
        titleText = findViewById(R.id.titleText);
        textDescription = findViewById(R.id.textDescription);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textDescription.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }
        addressText = findViewById(R.id.addressText);
        ImageView vinedoImg = findViewById(R.id.vinedoImg);

        horarioTextView = findViewById(R.id.horarioTextView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textDescription.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }

        // Obtenemos el ID del viñedo actual
        idEvento = obteneridEvento();

        // Obtenemos el ID del usuario ya autenticado
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = (user != null) ? user.getUid() : null;

        configSwipe();
        // Obtenemos el nombre del viñedo
        obtenerInformacionEvento();





        //Mostrar el botón para regresar y eliminar title
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void configSwipe() {
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Simulamos una actualización de 2 segundos
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        // Refrescar la actividad actual
                        recreate();
                    }
                }, 600);
            }
        });
        // Configurar el menú de favoritos
        invalidateOptionsMenu();
    }

    // Método para obtener la información de los eventos ?
    private void obtenerInformacionEvento() {
        // Obtenemos el nombre de los viñedos desde la intención
        Intent intent = getIntent();
        String name = (intent != null) ? intent.getStringExtra("titleTxt") : null;
        idEvento = obteneridEvento(); // Asegúrate de obtener el idEvento correctamente

        Log.d("DetailEventosActivity", "Nombre del evento recibido: " + name);
        Log.d("DetailEventosActivity", "ID de evento obtenido: " + idEvento);

        // Verificamos la existencia del nombre y que idEvento no sea vacío
        if (name != null && !idEvento.isEmpty()) {
            // Consultamos en Firestore para obtener información de los viñedos
            mFirestore.collection("eventos").document(idEvento).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            // Extraemos los campos del documento
                            String nombre = document.getString("nombre_evento");
                            String info = document.getString("descripcion");
                            String ubicacion = document.getString("ubicacion_evento");
                            Timestamp horario = document.getTimestamp("fecha_eventoo");
                            String imageUrl = document.getString("url");

                            // Convertimos el Timestamp a un String con un formato de fecha específico para los Eventos
                            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy 'a las' HH:mm:ss a");
                            String horarioStr = sdf.format(horario.toDate());
                            horarioTextView.setText(horarioStr);
                            // Configuramos los elementos de la interfaz de usuario con la información obtenida
                            Log.d("MyExceptionHandler -> nombre", nombre);
                            Log.d("MyExceptionHandler -> name", name);
                            titleText.setText(nombre);
                            textDescription.setText(info);
                            addressText.setText(ubicacion);
                            //horarioTextView.setText(horario);

                            // Cargar la imagen usando Glide o cualquier otra biblioteca de carga de imágenes
                            if (imageUrl != null && !imageUrl.isEmpty()) {
                                Log.d("DetailEventosActivity", "URL de la imagen: " + imageUrl);
                                ImageView vinedoImg = findViewById(R.id.vinedoImg);
                                Glide.with(reservation_event.this)
                                        .load(imageUrl)
                                        .into(vinedoImg);
                            } else {
                                // Manejo de caso donde no hay URL de imagen
                                Log.e("DetailEventosActivity", "La URL de la imagen es nula o vacía.");
                            }
                        } else {
                            // Manejo de errores
                            Log.e("DetailEventosActivity", "Documento no encontrado en Firestore para el ID: " + idEvento);
                        }
                    } else {
                        // Manejo de errores
                        Log.e("DetailEventosActivity", "Error al obtener la información de los eventos desde Firestore", task.getException());
                    }
                }
            });
        } else {
            // Manejo de errores
            if (name == null) {
                Log.e("DetailEventosActivity", "El nombre del evento es nulo en la intención.");
            }
            if (idEvento.isEmpty()) {
                Log.e("DetailEventosActivity", "El ID de evento es inválido o vacío.");
            }
        }
    }




    // Método para obtener el ID del evento actual
    private String obteneridEvento() {
        Intent intent = getIntent();
        if (intent != null) {
            // Obtenemos el ID del evento desde la intención
            String idEvento = intent.getStringExtra("idEvento");
            Log.e("DetailEventosActivity", "Intent recibida correctamente.");
            Log.e("DetailEventosActivity", "ID de evento obtenido: " + idEvento);
            if (idEvento != null && !idEvento.isEmpty()) {
                return idEvento;
            } else {
                Toast.makeText(this, "Error: ID de evento no válida", Toast.LENGTH_SHORT).show();
                Log.e("DetailEventosActivity", "ID de evento no válido: " + idEvento);
            }
        } else {
            Log.e("DetailEventosActivity", "Intent es nulo.");
        }
        return "";
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}