package com.example.dicegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnplay = findViewById<Button>(R.id.id_btnplay)
        val etname = findViewById<EditText>(R.id.id_etname)
        val etnum = findViewById<EditText>(R.id.id_etnum)
        btnplay.setOnClickListener {
            val number = etnum.text.toString()
            val num = number.toIntOrNull()
            val name = etname.text.toString()
            Log.d("WHAT", num.toString())
            if (num in 1..6) {
                val i = Intent(this@MainActivity, PlayActivity::class.java)
                i.putExtra("nameplayer", name)
                i.putExtra("favnumber", num)
                startActivity(i)
                finish()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Please Enter number from 1 To 6 ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}