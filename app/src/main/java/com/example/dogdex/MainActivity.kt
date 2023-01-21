package com.example.dogdex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import com.example.dogdex.auth.LoginActivity
import com.example.dogdex.databinding.ActivityMainBinding
import com.example.dogdex.doglist.DogListActivity
import com.example.dogdex.model.User
import com.example.dogdex.settings.SettingsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = User.getLoggedInUser(this)
        if(user == null){
            openLoginActivity()
            return
        }
        binding.settingsFab.setOnClickListener{
            openSettingsActivity()
        }
        binding.dogListFab.setOnClickListener{
            openDogListActivity()
        }
    }

    private fun openDogListActivity() {
        startActivity(Intent(this, DogListActivity::class.java))
    }

    private fun openSettingsActivity() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun openLoginActivity(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}