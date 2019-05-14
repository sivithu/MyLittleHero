package com.maruani.games.mylittleheroesgi.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.maruani.games.mylittleheroesgi.R;
import com.maruani.games.mylittleheroesgi.data.model.Weapon;
import com.maruani.games.mylittleheroesgi.data.service.NetworkProvider;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseWeaponActivity extends AppCompatActivity {

  @BindView(R.id.activity_choose_weapon_rcv)
  RecyclerView weaponChoiceRcv;
  @BindView(R.id.activity_select_image)
  ImageView imageChoosen;
  @BindView(R.id.activity_next_btn)
  Button nextButton;


  private WeaponAdapter weaponAdapter;

  public String name = "NaN";
  public String sexe = "NaN";
  public String birthDay = "NaN";
  public String image = "NaN";
  public String image_name;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_weapon);
    ButterKnife.bind(this);

    initRecyclerView();
    loadData();
    Intent intent = getIntent();
    name = intent.getStringExtra("name").toString();
    sexe = intent.getStringExtra("sexe").toString();
    birthDay = intent.getStringExtra("birthday").toString();

    System.out.println(birthDay);
  }

    private void initRecyclerView() {
      weaponChoiceRcv.setLayoutManager(new LinearLayoutManager(this));
      weaponAdapter = new WeaponAdapter();
      weaponChoiceRcv.setAdapter(weaponAdapter);

      weaponAdapter.setItemClickListener(new WeaponAdapter.ItemClickListener() {
        @Override
        public void onClick(Weapon weapon) {
          Toast.makeText(ChooseWeaponActivity.this, weapon.getName(), Toast.LENGTH_SHORT).show();
          Glide.with(ChooseWeaponActivity.this).load(weapon.getPictureUrl()).into(imageChoosen);
          image = weapon.getPictureUrl();
          image_name = weapon.getName();
        }
      });
    }


    private void loadData() {
      NetworkProvider.getInstance().getWeapons(new NetworkProvider.Listener<List<Weapon>>() {
        @Override public void onSuccess(List<Weapon> data) {
          Log.d("ChooseWeaponActivity", data.toString());
          weaponAdapter.setWeaponList(data);
        }

        @Override public void onError(Throwable t) {

        }
      });
    }

    private void saveDataTest() {
      //SAVE
      SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
      sharedPreferences.edit().putBoolean("myKey", true).apply();
    }

    private void readDataTest() {
      SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
      sharedPreferences.getBoolean("myKey", false);
    }

  @OnClick(R.id.activity_next_btn) void onNextButtonClick() {

    Intent intent = new Intent(ChooseWeaponActivity.this, RecapActivity.class);
    intent.putExtra("name", name);
    intent.putExtra("gender", sexe);
    intent.putExtra("birthday", birthDay);
    intent.putExtra("image", image);
    intent.putExtra("image_name", image_name);
    startActivity(intent);
    onPause();
  }

}
