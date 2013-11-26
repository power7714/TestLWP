package com.curtrostudios.testlwp;

import rajawali.wallpaper.Wallpaper;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

@SuppressWarnings("deprecation")
public class Settings extends PreferenceActivity implements
                SharedPreferences.OnSharedPreferenceChangeListener {
	
	public static final String PREF_LIGHT_INTENSITY = "pref_light_intensity";
	public static final String PREF_ROTATION_RATE = "pref_rotation_rate";
	Preference text_pref;

        protected void onCreate(Bundle icicle) {
                super.onCreate(icicle);
                getPreferenceManager().setSharedPreferencesName(Wallpaper.SHARED_PREFS_NAME);
                addPreferencesFromResource(R.xml.settings);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
                
                this.text_pref = findPreference("pref_text");
              this.text_pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
          		
          		@Override
          		public boolean onPreferenceChange(Preference preference, Object newValue) {
          			//preference = sharedPreferences;
          			if (preference.getKey().equalsIgnoreCase("pref_text")) {
          				try {
          					String input = newValue.toString();
          					if (input.length()<1) throw new Exception ("Invalid length");
          					return true;
          				} catch (Exception e) {
          					return false;
          				}
          			}
          			return true;
          		}
          	});
        }

        protected void onResume() {
                super.onResume();
        }

        protected void onDestroy() {
                getPreferenceManager().getSharedPreferences()
                                .unregisterOnSharedPreferenceChangeListener(this);
                super.onDestroy();
        }

        public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString) {
        }
}
