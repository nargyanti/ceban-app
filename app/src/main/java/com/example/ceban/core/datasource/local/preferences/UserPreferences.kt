package com.example.ceban.core.datasource.local.preferences

import android.content.Context
import androidx.core.content.edit
import com.example.ceban.core.datasource.remote.responses.UserResponse

class UserPreferences(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val NAME = "name"
        private const val ID = "id"
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
        private const val TELP = "telp"
        private const val LEVEL = "level"
        private const val ENTRY_YEAR = "entry_year"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(userResponse: UserResponse) {
        preferences.edit {
            putString(NAME, userResponse.name)
            putInt(ID, userResponse.id ?: 0)
            putString(USERNAME, userResponse.username)
            putString(PASSWORD, userResponse.password)
            putString(TELP, userResponse.telp)
            putString(LEVEL, userResponse.level)
            putString(ENTRY_YEAR, userResponse.entryYear)
        }
    }

    fun getUser(): UserResponse {
        val user = UserResponse()
        user.name = preferences.getString(NAME, null)
        user.id = preferences.getInt(ID, 0)
        user.username = preferences.getString(USERNAME, null)
        user.password = preferences.getString(PASSWORD, null)
        user.telp = preferences.getString(TELP, null)
        user.level = preferences.getString(LEVEL, null)
        user.entryYear = preferences.getString(ENTRY_YEAR, null)
        return user
    }

}