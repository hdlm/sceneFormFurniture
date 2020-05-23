package com.example.sceneformfurniture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.google.ar.sceneform.ux.ArFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var fragment: ArFragment
    private lateinit var selectedObject: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment = supportFragmentManager.findFragmentById(R.id.sceneform_fragment) as ArFragment

        InitializeGallery()

        fragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            Log.d("model", selectedObject)
        }
    }

    private fun InitializeGallery():Unit {
        var chair:ImageView = ImageView(this)
        chair.setImageResource(R.drawable.chair_thumb)
        chair.contentDescription = "chair"
        chair.setOnClickListener { view ->
            selectedObject = "chair"
        }
        gallery_layout.addView(chair)

        var coach:ImageView = ImageView(this)
        coach.setImageResource(R.drawable.couch_thumb)
        coach.contentDescription = "coach"
        coach.setOnClickListener { view ->
            selectedObject = "coach"
        }
        gallery_layout.addView(coach)

        var lamp:ImageView = ImageView(this)
        lamp.setImageResource(R.drawable.lamp_thumb)
        lamp.contentDescription = "lamp"
        lamp.setOnClickListener { view ->
            selectedObject = "lamp"
        }
        gallery_layout.addView(lamp)

        var table:ImageView = ImageView(this)
        table.setImageResource(R.drawable.lamp_thumb)
        table.contentDescription = "table"
        table.setOnClickListener { view ->
            selectedObject = "table"
        }
        gallery_layout.addView(table)
        

    }
}
