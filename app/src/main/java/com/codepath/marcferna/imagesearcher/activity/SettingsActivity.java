package com.codepath.marcferna.imagesearcher.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.codepath.marcferna.imagesearcher.R;
import com.codepath.marcferna.imagesearcher.model.Setting;


public class SettingsActivity extends Activity {

  private Setting defaultSetting = new Setting();

  private Spinner spImageSize;
  private Spinner spImageType;
  private Spinner spColorFilter;
  private EditText etSiteFilter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    defaultSetting = getIntent().getParcelableExtra("settings");
    setupViews();
  }

  public void setupViews() {
    spImageSize = (Spinner) findViewById(R.id.spImageSize);
    spImageType = (Spinner) findViewById(R.id.spImageType);
    spColorFilter = (Spinner) findViewById(R.id.spColorFilter);
    etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);

    if (!defaultSetting.size.isEmpty()) {
      setSpinnerToValue(spImageSize, defaultSetting.size);
    }

    if (!defaultSetting.type.isEmpty()) {
      setSpinnerToValue(spImageType, defaultSetting.type);
    }

    if (!defaultSetting.color.isEmpty()) {
      setSpinnerToValue(spColorFilter, defaultSetting.color);
    }

    if (!defaultSetting.site.isEmpty()) {
      etSiteFilter.setText(defaultSetting.site);
    }
  }

  public void setSpinnerToValue(Spinner spinner, String value) {
    int index = 0;
    SpinnerAdapter adapter = spinner.getAdapter();
    for (int i = 0; i < adapter.getCount(); i++) {
      if (adapter.getItem(i).equals(value)) {
        index = i;
        break; // terminate loop
      }
    }
    spinner.setSelection(index);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_settings, menu);
    return true;
  }

  public void onClickSave(MenuItem mi) {
    Setting setting = new Setting();
    spImageSize = (Spinner) findViewById(R.id.spImageSize);
    spImageType = (Spinner) findViewById(R.id.spImageType);
    spColorFilter = (Spinner) findViewById(R.id.spColorFilter);
    etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);

    String size = spImageSize.getSelectedItem().toString();
    String type = spImageType.getSelectedItem().toString();
    String color = spColorFilter.getSelectedItem().toString();
    String site = etSiteFilter.getText().toString();
    if (!size.equals("any")) {
      setting.size = size;
    }

    if (!type.equals("any")) {
      setting.type = type;
    }

    if (!color.equals("any")) {
      setting.color = color;
    }

    if (!site.isEmpty()) {
      setting.site = site;
    }
    Intent intent = new Intent();
    intent.putExtra("settings", setting);
    setResult(RESULT_OK, intent);
    finish();
  }
}
