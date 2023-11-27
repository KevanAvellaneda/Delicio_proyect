package com.example.winestastic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
//import domain.Item;

public class DetailActivity extends AppCompatActivity {
    private TextView titleText, addressText, textDescription;
    // Es una clase del proyecto que manda a llamar con más elementos
    // private ItemsDomain item;
    private ImageView vinedoImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        setVariable();
    }

    private void setVariable(){
//
/*      item = (ItemsDomain) getIntent().getSerializableExtra(name: "object");
        titleText.setText(item.getTitle());
        addressText.setText(item.getAddress());
        textDescription.setText(item.getDescription());

        int drawableResourceId=getResources().getIdentifier(item.getvinedoImg(), "drawable", getPackageName());


  */  }

    private void initView() {
        titleText = findViewById(R.id.titleText);
        addressText = findViewById(R.id.addressText);
        textDescription = findViewById(R.id.textDescription);
        vinedoImg = findViewById(R.id.vinedoImg);
    }
}