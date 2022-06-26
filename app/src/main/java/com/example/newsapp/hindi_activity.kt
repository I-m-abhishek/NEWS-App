package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class hindi_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hindi)

    }

    fun hindinewstogo(view: View) {


        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    fun englsihnewstogo(view: View) {
        val intent =Intent(this , english_activity::class.java)
        startActivity(intent)
    }


}