package com.sammy.android_ui_settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.*


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val dataStore = DataStore()
//        preferenceManager.preferenceDataStore = dataStore

        val accSettingsPref = findPreference<Preference>(getString(R.string.key_account_settings))

        accSettingsPref?.setOnPreferenceClickListener {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController = navHostFragment.navController
            val action = SettingsFragmentDirections.actionSettingsToAccSettings()
            navController.navigate(action)

            true
        }

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        val autoReplyTime = sharedPreferences.getString(getString(R.string.key_auto_reply_time), "")
        Log.d("TEST", "onCreatePreferences: $autoReplyTime")

        val autoDownload = sharedPreferences.getBoolean(getString(R.string.key_auto_download), false)
        Log.d("TEST", "onCreatePreferences: $autoDownload")


        val statusPref = findPreference<EditTextPreference>(getString(R.string.key_status))
        statusPref?.setOnPreferenceChangeListener { preference, newValue ->
            Log.i("TEST", "onPreferenceChange: $newValue")

            val newStatus = newValue as String

            if(newStatus.contains("bad")) {
                Toast.makeText(requireContext(), "Inappropriate status. Please maintain community guidelines", Toast.LENGTH_SHORT).show()
                false
            } else {
                true
            }
        }

        val notificationPref = findPreference<SwitchPreferenceCompat>(getString(R.string.key_new_msg_notif))
        notificationPref?.summaryProvider = Preference.SummaryProvider<SwitchPreferenceCompat> { switchPref ->
            if(switchPref.isChecked) {
                "Status: ON"
            } else {
                "Status: OFF"
            }
        }
        notificationPref?.preferenceDataStore = dataStore

        val isNotificationEnabled = dataStore.getBoolean("key_new_msg_notif", false)

    }

    class DataStore: PreferenceDataStore() {

        override fun getBoolean(key: String?, defValue: Boolean): Boolean {
            if(key == "key_new_msg_notif") {
                // save value to cloud or local db
                Log.i("TEST", "$key")
            }

            return defValue
        }

        override fun putBoolean(key: String?, value: Boolean) {
            if(key == "key_new_msg_notif") {
                // save value to cloud or local db
                Log.i("TEST", "$key: $value")
            }
        }

    }


}