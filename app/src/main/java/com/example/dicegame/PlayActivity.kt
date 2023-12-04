package com.example.dicegame

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        val tvname=findViewById<TextView>(R.id.id_tvname)
        val tvnum=findViewById<TextView>(R.id.id_tvnum)
        val name:String=intent.getStringExtra("nameplayer").toString()
        val Number:Int=intent.getIntExtra("favnumber",0)
        tvname.text="Welcome $name"
        tvnum.text="Favourite number is $Number"

        val imagecamera=findViewById<ImageButton>(R.id.id_ivcamera)
        imagecamera.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this@PlayActivity,
                    Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this@PlayActivity, arrayOf(Manifest.permission.CAMERA),100)
            }else{
                val i= Intent("android.media.action.IMAGE_CAPTURE")
                startActivityForResult(i,100)
            }
        }


        /////////fragment
        val firstfrag:FragmentTransaction=supportFragmentManager.beginTransaction()
        val f1:Fragment=fragmentone()
        firstfrag.replace(R.id.id_flmain,f1).commit()

        //////////////
        val userNumber = intent.getIntExtra("favnumber", 0)

        val fragment = fragmentone.newInstance(userNumber)
        supportFragmentManager.beginTransaction()
            .replace(R.id.id_flmain, fragment)
            .commit()

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK){
            if(requestCode==100)
            {
                Toast.makeText(this@PlayActivity,"Image updated Sccussfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
}