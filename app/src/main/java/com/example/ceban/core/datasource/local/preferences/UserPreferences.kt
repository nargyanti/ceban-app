package com.example.ceban.core.datasource.local.preferences

import android.content.Context
import androidx.core.content.edit
import com.example.ceban.core.datasource.local.entity.UserEntity

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

    fun setUser(userEntity: UserEntity) {
        preferences.edit {
            putString(NAME, userEntity.name)
            putInt(ID, userEntity.id)
            putString(USERNAME, userEntity.username)
            putString(PASSWORD, userEntity.password)
            putString(TELP, userEntity.telp)
            putString(LEVEL, userEntity.level)
            putString(ENTRY_YEAR, userEntity.entryYear)
        }
    }

    fun getUser(): UserEntity {
        val user = UserEntity()
        user.name = preferences.getString(NAME, null)
        user.id = preferences.getInt(ID, 0)
        user.username = preferences.getString(USERNAME, null)
        user.password = preferences.getString(PASSWORD, null)
        user.telp = preferences.getString(TELP, null)
        user.level = preferences.getString(LEVEL, null)?: ""
        user.entryYear = preferences.getString(ENTRY_YEAR, null)
        return user
    }

}