package com.example.winestastic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {



    private MeowBottomNavigation bottomNavigation;
    RelativeLayout  menu, home, calendar, map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home);
        calendar = findViewById(R.id.calendar);
        map = findViewById(R.id.map);




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

        ArrayList<SlideModel> imageListCat = new ArrayList<>();
        ArrayList<SlideModel> imageListEve = new ArrayList<>();


// imageList.add(new SlideModel("String Url" or R.drawable)
// imageList.add(new SlideModel("String Url" or R.drawable, "title") You can add title

        imageListCat.add(new SlideModel(R.drawable.vino, "Cata de vinos", ScaleTypes.CENTER_INSIDE));
        imageListCat.add(new SlideModel(R.drawable.barril, "Vinos", ScaleTypes.CENTER_INSIDE));
        imageListCat.add(new SlideModel(R.drawable.vinoqueso, "Para acompañar", ScaleTypes.CENTER_INSIDE));
        imageListCat.add(new SlideModel(R.drawable.vinedos, "Viñedos", ScaleTypes.CENTER_INSIDE));

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageListCat);



        imageListEve.add(new SlideModel(R.drawable.winead2, ScaleTypes.FIT));
        imageListEve.add(new SlideModel(R.drawable.winead4, ScaleTypes.FIT));
        imageListEve.add(new SlideModel(R.drawable.winead5, ScaleTypes.FIT));

        ImageSlider imageSlider2 = findViewById(R.id.image_slider2);
        imageSlider2.setImageList(imageListEve);


    }
}