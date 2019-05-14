package com.maruani.games.mylittleheroesgi.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maruani.games.mylittleheroesgi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecapActivity extends AppCompatActivity {


    @BindView(R.id.activity_recap_image)
    ImageView recapImage;
    @BindView(R.id.pseudo_name_value)
    TextView pseudoText;
    @BindView(R.id.gender_name_value)
    TextView genderText;
    @BindView(R.id.birthday_name_value)
    TextView birthdayText;

    public String name = "NaN";
    public String sexe = "NaN";
    public String birthDay = "NaN";
    public String image = "NaN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        sexe = intent.getStringExtra("gender");
        birthDay = intent.getStringExtra("birthday");
        image = intent.getStringExtra("image");

        Glide.with(RecapActivity.this).load(image).into(recapImage);
        pseudoText.setText(name);
        genderText.setText(sexe);
        birthdayText.setText(birthDay);

    }
}
