package com.yourcompany.skaI.settings

import android.content.Context
import android.content.SharedPreferences

class SettingsManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("SKAISettings", Context.MODE_PRIVATE)
}