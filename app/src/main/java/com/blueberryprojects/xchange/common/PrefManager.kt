package com.blueberryprojects.xchange.common

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PrefManager(
    private val application: Application,
) {

    companion object {
        private const val PREF_SHARED = "PREF_SHARED"

        private const val PREF_INITIAL_LAUNCH = "PREF_INITIAL_LAUNCH"
    }

    private fun getSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences(PREF_SHARED, Context.MODE_PRIVATE)
    }

    var isInitialLaunch: Boolean
        get() = getSharedPreferences().getBoolean(PREF_INITIAL_LAUNCH, true)
        set(value) {
            getSharedPreferences().edit {
                putBoolean(PREF_INITIAL_LAUNCH, value)
            }
        }
}