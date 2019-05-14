package com.maruani.games.mylittleheroesgi.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.maruani.games.mylittleheroesgi.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreatePlayerActivity extends AppCompatActivity {

  EditText pseudoEdt;
  @BindView(R.id.activity_create_player_birthdate_edt) EditText birthdateEdt;
  @BindView(R.id.activity_create_player_gender_gr) RadioGroup genderGr;

  DatePickerDialog datePickerDialog;

  public String sexe;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_player);
    ButterKnife.bind(this);

    pseudoEdt = findViewById(R.id.activity_create_player_pseudo_edt);
    initDatePicker();



    genderGr.setOnCheckedChangeListener((group, checkedId) -> {
      if (checkedId == R.id.activity_create_player_radio_button_male) {
        sexe = "male";
      } else {
        sexe = "female";
      }
    });
  }

  private void initDatePicker() {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
      String format = "dd/MM/yy";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);

      calendar.set(Calendar.YEAR, year1);
      calendar.set(Calendar.MONTH, month1);
      calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

      birthdateEdt.setText(simpleDateFormat.format(calendar.getTime()));
    }, year, month, day);
  }

  @OnClick(R.id.activity_create_player_birthdate_edt) void onBirthdateClick() {
    datePickerDialog.show();
  }

  @OnClick(R.id.activity_create_player_next_btn) void onNextButtonClick() {
    //validate data
    //if OK navigate
    //if KO show error
    Intent intent = new Intent(CreatePlayerActivity.this, ChooseWeaponActivity.class);
    intent.putExtra("name", ""+pseudoEdt.getText());
    intent.putExtra("sexe", ""+sexe);
    intent.putExtra("birthday", ""+birthdateEdt.getText());
    startActivity(intent);
    onPause();
  }
}
