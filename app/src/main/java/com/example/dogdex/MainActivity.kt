package com.example.dogdex
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.dogdex.api.ApiServiceInterceptor
import com.example.dogdex.auth.LoginActivity
import com.example.dogdex.databinding.ActivityMainBinding
import com.example.dogdex.doglist.DogListActivity
import com.example.dogdex.model.User
import com.example.dogdex.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                Log.d("TAG", "requestPermissionLauncher: Permiso Aceptado")
            } else {
                Toast.makeText(this,"You need to accept camera permission to use camera",
                    Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = User.getLoggedInUser(this)
        if(user == null){
            openLoginActivity()
            return
        } else{
            ApiServiceInterceptor.setSessionToken(user.autheticationToken)
        }
        binding.settingsFab.setOnClickListener{
            openSettingsActivity()
        }
        binding.dogListFab.setOnClickListener{
            openDogListActivity()
        }
        requestCameraPermission()
    }

    private fun requestCameraPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA

                ) == PackageManager.PERMISSION_GRANTED -> {
                    Log.d("TAG", "requestCameraPermission: Permiso Aceptado")

                }
                shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    AlertDialog.Builder(this)
                        .setTitle("Debe aceptar la solicitud")
                        .setMessage("Acepta la camara porfavor")
                        .setPositiveButton(android.R.string.ok){_, _ ->
                            requestPermissionLauncher.launch(
                                Manifest.permission.CAMERA
                            )
                        }
                        .setNegativeButton(android.R.string.cancel){_,_ ->

                        }.show()

            }
                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestPermissionLauncher.launch(
                        Manifest.permission.CAMERA

                    )
                }
            }
        } else {

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