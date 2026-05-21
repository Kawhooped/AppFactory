package com.appfactory.utilities

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    /**
     * Save a string value
     */
    fun saveString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    /**
     * Get a string value
     */
    fun getString(key: String, defaultValue: String = ""): String {
        return preferences.getString(key, defaultValue) ?: defaultValue
    }

    /**
     * Save an integer value
     */
    fun saveInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    /**
     * Get an integer value
     */
    fun getInt(key: String, defaultValue: Int = 0): Int {
        return preferences.getInt(key, defaultValue)
    }

    /**
     * Save a boolean value
     */
    fun saveBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    /**
     * Get a boolean value
     */
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    /**
     * Save a float value
     */
    fun saveFloat(key: String, value: Float) {
        preferences.edit().putFloat(key, value).apply()
    }

    /**
     * Get a float value
     */
    fun getFloat(key: String, defaultValue: Float = 0f): Float {
        return preferences.getFloat(key, defaultValue)
    }

    /**
     * Save a long value
     */
    fun saveLong(key: String, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

    /**
     * Get a long value
     */
    fun getLong(key: String, defaultValue: Long = 0L): Long {
        return preferences.getLong(key, defaultValue)
    }

    /**
     * Remove a specific key
     */
    fun remove(key: String) {
        preferences.edit().remove(key).apply()
    }

    /**
     * Clear all preferences
     */
    fun clear() {
        preferences.edit().clear().apply()
    }

    /**
     * Check if a key exists
     */
    fun containsKey(key: String): Boolean {
        return preferences.contains(key)
    }

    companion object {
        private const val PREFERENCES_NAME = "app_preferences"
    }
}
