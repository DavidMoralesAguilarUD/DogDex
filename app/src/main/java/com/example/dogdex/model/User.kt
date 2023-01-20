package com.example.dogdex.model

import android.app.Activity
import android.content.Context
import com.example.dogdex.model.User.Companion.AUTH_TOKEN_KEY
import com.example.dogdex.model.User.Companion.EMAIl_KEY

class User (
    val id: Long,
    val email: String,
    val autheticationToken: String
    ){
    companion object{

        private const val AUTH_PREFS = "auth_prefs"
        private const val ID_KEY = "id"
        private const val EMAIl_KEY = "email"
        private const val AUTH_TOKEN_KEY = "auth_token"

        fun setLoggedInUser(activity: Activity, user:User){
            activity.getSharedPreferences(AUTH_PREFS,
            Context.MODE_PRIVATE).also {
                it.edit()
                    .putLong(ID_KEY, user.id)
                    .putString(EMAIl_KEY, user.email)
                    .putString(AUTH_TOKEN_KEY, user.autheticationToken)
                    .apply()

            }
        }

        fun getLoggedInUser(activity: Activity): User? {
            val prefs = activity.getSharedPreferences(
                AUTH_PREFS,
                Context.MODE_PRIVATE
            ) ?: return null

            val userId = prefs.getLong(ID_KEY, 0)
            if (userId == 0L) {
                return null
            }
            return User(
                userId,
                prefs.getString(EMAIl_KEY, "") ?: "",
                prefs.getString(AUTH_TOKEN_KEY, "") ?: "",
            )

        }
    }

}