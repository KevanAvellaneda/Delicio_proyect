package com.example.winestastic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterEventos,adapterVinedos;
    private RecyclerView recyclerViewEventos, recyclerViewVinedos;


    private MeowBottomNavigation bottomNavigation;
    TextView txt_Nombre,txt_correo,txt_telefono,txt_Nombre2,txt_correo2;
    Button cerrar;
    RelativeLayout  menu, home, calendar, map;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseFirestore mFirestore;


    LinearLayout cardviewchatbot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        cerrar = findViewById(R.id.cerrar_sesion);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home);
        calendar = findViewById(R.id.calendar);
        map = findViewById(R.id.map);
        txt_Nombre = findViewById(R.id.Mostrarnombre);
        txt_Nombre2 = findViewById(R.id.nombre2);
        txt_correo2 = findViewById(R.id.correo2);
        txt_correo = findViewById(R.id.Mostrarcorreo);
        txt_telefono = findViewById(R.id.Mostrartelefono);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mFirestore = FirebaseFirestore.getInstance();
        cardviewchatbot = findViewById(R.id.cardviewchat);
        bottomNavigation.show(2,true);




        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.menuanvorgesa));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_calendar_month_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.baseline_public_24));

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()){


                    case 1:

                        menu.setVisibility(View.VISIBLE);
                        home.setVisibility(View.GONE);
                        calendar.setVisibility(View.GONE);
                        map.setVisibility(View.GONE);

                        break;

                    case 2:

                        menu.setVisibility(View.GONE);
                        home.setVisibility(View.VISIBLE);
                        calendar.setVisibility(View.GONE);
                        map.setVisibility(View.GONE);

                        break;

                    case 3:

                        menu.setVisibility(View.GONE);
                        home.setVisibility(View.GONE);
                        calendar.setVisibility(View.VISIBLE);
                        map.setVisibility(View.GONE);

                        break;

                    case 4:

                        menu.setVisibility(View.GONE);
                        home.setVisibility(View.GONE);
                        calendar.setVisibility(View.GONE);
                        map.setVisibility(View.VISIBLE);

                        break;

                }
                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()){

                    case 1:

                        menu.setVisibility(View.VISIBLE);
                        home.setVisibility(View.GONE);
                        calendar.setVisibility(View.GONE);
                        map.setVisibility(View.GONE);

                        break;
                }

                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()){

                    case 2:

                        menu.setVisibility(View.GONE);
                        home.setVisibility(View.VISIBLE);
                        calendar.setVisibility(View.GONE);
                        map.setVisibility(View.GONE);

                        break;
                }

                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()){

                    case 3:

                        menu.setVisibility(View.GONE);
                        home.setVisibility(View.GONE);
                        calendar.setVisibility(View.VISIBLE);
                        map.setVisibility(View.GONE);

                        break;
                }

                return null;
            }
        });


        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()){

                    case 4:

                        menu.setVisibility(View.GONE);
                        home.setVisibility(View.GONE);
                        calendar.setVisibility(View.GONE);
                        map.setVisibility(View.VISIBLE);

                        break;
                }

                return null;
            }
        });



        Date today = new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 30);

        CalendarPickerView datePicker = findViewById(R.id.calendarView);
        datePicker.init(today, nextYear.getTime()).withSelectedDate(today);

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                Toast.makeText(MainActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });


        Fragment fragment = new Map_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();






        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });


        cardviewchatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, chatbot.class);
                startActivity(intent);
                finish();
            }
        });

        initRecyclerView();

    }

    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            irLogin();
        }else{
            mostrarMensaje("Bienvenido");
            cargardatos();
        }
    }

    private void cargardatos(){
        mFirestore.collection("usuarios").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        String nombre = document.getString("nombre");
                        String correo = document.getString("correo");
                        String telefono = document.getString("telefono");

                        txt_Nombre.setText(nombre);
                        txt_correo.setText(correo);
                        txt_telefono.setText(telefono);
                        txt_Nombre2.setText(nombre);
                        txt_correo2.setText(correo);
                    }
                }

            }
        });

    }

    private void logout(){
        mAuth.signOut();
        irLogin();
    }

    private void irLogin(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void mostrarMensaje(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }


    private void initRecyclerView() {
        // ArrayList para los viñedos
        ArrayList<ItemsDomain> vinedosArrayList = new ArrayList<>();
        vinedosArrayList.add(new ItemsDomain("La Redonda", "Carr. San Juan Del Rio a\n" + "Ezequiel Montes Km 33.5\n", "laredonda"));
        vinedosArrayList.add(new ItemsDomain("Freixenet", "Carr. San Juan del Río a\n" + "Cadereyta Km 40.5\n", "freixenet"));
        vinedosArrayList.add(new ItemsDomain("Puerta del lobo", "Carr. La Griega\n" + "Santiago de Qro Km 4.5\n", "puertadellobo2"));
        vinedosArrayList.add(new ItemsDomain("Vinícola de Cote", "Libramiento Norponiente\n" + "Santiago de Qro Km 5.9\n", "vinicoladecote2"));

        // ArrayList para los eventos
        ArrayList<ItemsDomain> eventosArrayList = new ArrayList<>();
        eventosArrayList.add(new ItemsDomain("Cata de vinos", "La Redonda\n", "eventoredonda2"));
        eventosArrayList.add(new ItemsDomain("Cena de navidad", "Freixenet\n", "eventofreixenet"));
        eventosArrayList.add(new ItemsDomain("Cata de queso", "Puerta del Lobo\n", "eventolobo"));
        eventosArrayList.add(new ItemsDomain("Pisada de uvas", "Vinícola de Cote\n", "eventocote"));

        recyclerViewEventos = findViewById(R.id.viewEventos);
        recyclerViewVinedos = findViewById(R.id.viewViñedos);

        recyclerViewEventos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewVinedos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterVinedos = new ItemsAdapter(vinedosArrayList);
        adapterEventos = new ItemsAdapter(eventosArrayList);

        recyclerViewVinedos.setAdapter(adapterVinedos);
        recyclerViewEventos.setAdapter(adapterEventos);
    }

}