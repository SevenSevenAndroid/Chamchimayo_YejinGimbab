package com.example.se1_hw.data

import android.content.Context
import com.example.se1_hw.data.local.SoptUserInfo

class SoptUserAuthStorage private constructor(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(
        "${context.packageName}.$STORAGE_KEY",
        Context.MODE_PRIVATE
    )

    private val editor = sharedPreferences.edit()

    companion object {
        private const val STORAGE_KEY = "user_auth"
        private const val USER_ID_KEY = "user_id"
        private const val USER_PW_KEY = "user_pw"

        @Volatile
        private var instance: SoptUserAuthStorage? = null

        @JvmStatic
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: SoptUserAuthStorage(context).apply {
                instance = this
            }
        }
    }

    fun getUserData(): SoptUserInfo = SoptUserInfo(
        id = sharedPreferences.getString(USER_ID_KEY, "") ?: "",
        password = sharedPreferences.getString(USER_PW_KEY, "") ?: ""
    )

    fun saveUserData(userData: SoptUserInfo) {
        editor.putString(USER_ID_KEY, userData.id)
            .putString(USER_PW_KEY, userData.password)
            .apply()
    }

    fun hasUserData(): Boolean {
        with(getUserData()) {
            return id.isNotEmpty() && password.isNotEmpty()
        }
    }

    fun clearAuthStorage() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }
}