package com.pandf.bde.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.android.colorpicker.ColorPickerDialog;
import com.android.colorpicker.ColorPickerSwatch;
import com.onesignal.OneSignal;
import com.pandf.bde.R;

import java.io.File;

import static com.pandf.bde.activity.Utility.PREFS_COLOR;
import static com.pandf.bde.activity.Utility.THEME;

public class SettingsActivity extends AppCompatPreferenceActivity {

    private static final String TAG = SettingsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Utility.themer(SettingsActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // load settings fragment
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new MainPreferenceFragment()).commit();
    }

    public class MainPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_main);

            // feedback preference click listener
            final Preference mycache = findPreference(getString(R.string.key_cache));
            long size = 0;
            File[] files = getCacheDir().listFiles();
            for (File f:files) {
                size = size+f.length();
                size = size/100;
            }
            mycache.setSummary("Taille du cache: " + size + " ko");
            mycache.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    return true;
                }
            });
            Preference theme = findPreference(getString(R.string.key_theme));
            theme.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {

                    int[] mColorChoices = null;
                    String[] color_array = getResources().getStringArray(R.array.default_color_choice_values);

                    if (color_array != null && color_array.length > 0) {
                        mColorChoices = new int[color_array.length];
                        for (int i = 0; i < color_array.length; i++) {
                            mColorChoices[i] = Color.parseColor(color_array[i]);
                        }
                    }


                    ColorPickerDialog colorcalendar = ColorPickerDialog.newInstance(R.string.color_picker_default_title, mColorChoices, 3, 3, 1);

                    //Implement listener to get selected color value
                    colorcalendar.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {

                        @Override
                        public void onColorSelected(int color) {
                            Log.d("COLOR", String.valueOf(color));
                            SharedPreferences settings;
                            SharedPreferences.Editor editor;

                            settings = getSharedPreferences(PREFS_COLOR,
                                    Context.MODE_PRIVATE);
                            editor = settings.edit();

                            if (color == -14575885) {
                                String jsonFavorites = "default";
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().putString(THEME,
                                        jsonFavorites).commit();
                                Log.d("Choose", jsonFavorites);


                            } else if (color == -16121) {
                                String jsonFavorites2 = "lime";

                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().putString(THEME,
                                        jsonFavorites2).commit();
                                Log.d("Choose", jsonFavorites2);

                            } else if (color == -769226) {

                                String jsonFavorites3 = "red";

                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().putString(THEME,
                                        jsonFavorites3).commit();
                                Log.d("Choose", jsonFavorites3);

                            } else if (color == -11751600) {
                                String jsonFavorites4 = "green";

                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().putString(THEME,
                                        jsonFavorites4).commit();
                                Log.d("Choose", jsonFavorites4);


                            } else if (color == -1499549) {
                                String jsonFavorites5 = "pink";

                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().putString(THEME,
                                        jsonFavorites5).commit();
                                Log.d("Choose", jsonFavorites5);

                            } else if (color == -6543440) {
                                String jsonFavorites6 = "purple";

                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().putString(THEME,
                                        jsonFavorites6).commit();
                                Log.d("Choose", jsonFavorites6);

                            } else if (color == -8825528) {

                                String jsonFavorites7 = "brown";

                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().putString(THEME,
                                        jsonFavorites7).commit();
                                Log.d("Choose", jsonFavorites7);


                            } else if (color == -12627531) {
                                String jsonFavorites8 = "indigo";

                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().putString(THEME,
                                        jsonFavorites8).commit();
                                Log.d("Choose", jsonFavorites8);

                            } else if (color == -16728876) {
                                String jsonFavorites9 = "cyan";

                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().putString(THEME,
                                        jsonFavorites9).commit();
                                Log.d("Choose", jsonFavorites9);

                            }
                            Intent intent = getIntent();
                            Intent i = new Intent(SettingsActivity.this, MainActivity.class);
                            startActivity(i);
                        }

                    });

                    colorcalendar.show(getFragmentManager(), "cal");
                    return true;

                }
            });

            Preference licenses = findPreference(getString(R.string.key_cache));
            licenses.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {

                    return true;
                }
            });

            final SwitchPreference notification = (SwitchPreference) findPreference("key_notif");
            notification.setOnPreferenceChangeListener(new SwitchPreference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {

                   boolean checked =  notification.isChecked();
                    if (checked) {
                        SharedPreferences.Editor editor = getSharedPreferences("NOTIFY", MODE_PRIVATE).edit();
                        editor.putBoolean("checked", false);
                        editor.apply();
                        OneSignal.setSubscription(false);
                        Log.d("CHecked", "dechecked");
                    } else {
                        SharedPreferences.Editor editor = getSharedPreferences("NOTIFY", MODE_PRIVATE).edit();
                        editor.putBoolean("checked", true);
                        editor.apply();
                        OneSignal.startInit(SettingsActivity.this)
                                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                                .unsubscribeWhenNotificationsAreDisabled(true)
                                .init();
                        Log.d("CHecked", "checked");
                    }

                    return true;
                }
            });

            Preference contact = findPreference(getString(R.string.key_cache));
            contact.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {



                    return true;
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
