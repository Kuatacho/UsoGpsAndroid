package com.example.usogpsandroid

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import com.example.usogpsandroid.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient


/*Activar binding e implementar google/services en gradle/module */
/*En manifest activar permisos pertinentes*/
class MainActivity : AppCompatActivity() {

    //OjO: esto no es extremamente necesario para esta variable
    //solo se esta enseñando es a companion object
    //companion object sirve para definir constantes que sean a un nivel global en su
    //clase constantes que ustedes no requieran iniciaizar en cada instancia de su clase
    companion object{
        val REQUIRED_PERMISSION_GPS= arrayOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_NETWORK_STATE
        )
    }

    private lateinit var binding: ActivityMainBinding
    private var isGpsEnabled=false
    private val PERMISSION_ID=42
    //Variable que vamos a usar para gestionar el gps con google play services
    //FUSEDLOCATION: Fusiona los datos respectivos a gps en un objeto
    private lateinit var fusedLocation:FusedLocationProviderClient

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
        if (!hasGPSEnabled()){
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
        Toast.makeText(this,"GPS alredy enabled",
            Toast.LENGTH_SHORT).show()


    }
    private fun hasGPSEnabled():Boolean{
        //castear el tipo de dato
        //Manager en ANDROID = DIRECTOR DE LA ORQUESTA
        //LocationManager: orquesta o gestiona o controla todo lo referido a localizacion
        //desde el ambito del uso de las librerias de Android para localizacion
        val locationManager:LocationManager= getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun goToEnableGPS() {
        val intent= Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(intent)


    }

    //Seccion: Tratamiento de permisos para el uso de GPS
    private fun allPermissionsGrantedGPS():Boolean{
        //checkself revisa en tu app que valor tienen los permisos que estas
        //consultando--- ejemplo para GPS ACCESS_FINE_LOCATION que valor tienen en tu app
        //packagemanager.PermissionGranted = contiene el valor que android considera
        // como permiso otorgado ... 0
        //si tu permiso en la app tiene el valor de lo que android considera permiso otorgaod
        //tu si podras usar el gps en esta app:==)
        return REQUIRED_PERMISSION_GPS.all {
            ContextCompat.checkSelfPermission(baseContext,it) == PackageManager.PERMISSION_GRANTED

        }

    }
    //Este metodo hace lo mismo que el anterior, pero es mas explicito su forma de desarrollo
    private fun checkPermissionsGPS():Boolean{
        return ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED
    }

    //la aplicacion solicita permisos para que el usuario decida aceptar o denegar
    private fun requestPermissionLocation(){
        ActivityCompat.requestPermissions(this, REQUIRED_PERMISSION_GPS,PERMISSION_ID)

    }








}























