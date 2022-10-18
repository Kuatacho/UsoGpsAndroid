package com.example.usogpsandroid

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.example.usogpsandroid.databinding.ActivityMainBinding


/*Activar binding e implementar google/services en gradle/module */
/*En manifest activar permisos pertinentes*/
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isGpsEnabled=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fabGps.setOnClickListener{
            enableGPSService()
        }
    }
    //evaluar y gestionar si el gps en el celular esta ACTIVO
    private fun enableGPSService() {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_dialog_title)
            .setMessage(R.string.alert_dialog_description)
            .setPositiveButton(R.string.alert_dialog_button_accept,
            DialogInterface.OnClickListener{
                dialog, which ->  goToEnableGPS()
            })
            .setNegativeButton(R.string.alert_dialog_denny){
                dialog,wich -> isGpsEnabled=false
            }
            .setCancelable(true)
            .show()


    }

    private fun goToEnableGPS() {
        val intent= Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(intent)


    }
}























