package com.sammy.android_ui_settings

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.preference.MultiSelectListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat


class AccountSettingsFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
//        setPreferencesFromResource(R.xml.account_settings, rootKey)

        val publicInfoPref = MultiSelectListPreference(requireContext())
        publicInfoPref.entries = resources.getStringArray(R.array.entries_public_info)
        publicInfoPref.entryValues = resources.getStringArray(R.array.values_public_info)
        publicInfoPref.key = getString(R.string.key_public_info)
        publicInfoPref.title = getString(R.string.title_public_info)
        publicInfoPref.isIconSpaceReserved = false

        val logOutPref = Preference(requireContext())
        logOutPref.key = getString(R.string.key_log_out)
        logOutPref.title = getString(R.string.title_log_out)
        logOutPref.isIconSpaceReserved = false

        val deleteAccPref = Preference(requireContext())
        deleteAccPref.key = getString(R.string.key_delete_account)
        deleteAccPref.summary = getString(R.string.summary_delete_account)
        deleteAccPref.title = getString(R.string.title_delete_account)
        deleteAccPref.icon =
            ResourcesCompat.getDrawable(resources, android.R.drawable.ic_menu_delete, null)

        val privacyCategory = PreferenceCategory(requireContext())
        privacyCategory.title = getString(R.string.title_privacy)
        privacyCategory.isIconSpaceReserved = false

        val securityCategory = PreferenceCategory(requireContext())
        securityCategory.title = getString(R.string.title_security)
        securityCategory.isIconSpaceReserved = false

        val prefScreen = preferenceManager.createPreferenceScreen(requireContext())

        // Step 2: Add all the Preference objects in hierarchy
        prefScreen.addPreference(privacyCategory)
        prefScreen.addPreference(securityCategory)

        privacyCategory.addPreference(publicInfoPref)

        securityCategory.addPreference(logOutPref)
        securityCategory.addPreference(deleteAccPref)

        // Step 3: Set the preference screen
        preferenceScreen = prefScreen

    }
}
