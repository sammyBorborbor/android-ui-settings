package com.sammy.android_ui_settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

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

    }


}