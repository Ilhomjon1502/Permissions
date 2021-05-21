package com.ilhomjon.permissions

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    var requestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chekAudioPermission()
    }

    private fun chekAudioPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Audio permission granted", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Audio permission denied", Toast.LENGTH_SHORT).show()
            requestAudioPermissions()
        }
    }

    private fun requestAudioPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)){

            val builder = AlertDialog.Builder(this)
            builder.setMessage("Ovoz yozib olish uchun ruxsat berishingiz kerak aks holda ilova irofonni ishlata olmaydi")
            builder.setTitle("Permissions")

            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO),requestCode)
            })

            builder.create().show()

        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO),requestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == this.requestCode){
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "permissions granted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "permissions denit", Toast.LENGTH_SHORT).show()
            }
        }
    }
}