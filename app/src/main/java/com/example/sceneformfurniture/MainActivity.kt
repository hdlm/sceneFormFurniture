package com.example.sceneformfurniture

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.ar.core.Anchor
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var fragment: ArFragment
    private lateinit var selectedObject: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment = supportFragmentManager.findFragmentById(R.id.sceneform_fragment) as ArFragment

        InitializeGallery()
        selectedObject = Uri.parse("couch.sfb")

        fragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->

            if (plane.type != Plane.Type.HORIZONTAL_UPWARD_FACING)  {
               //do nothing
            }
            else {
                val anchor  = hitResult.createAnchor()
                placeObject(fragment, anchor, selectedObject)
            }

        }

    }

    private fun InitializeGallery():Unit {
        var chair:ImageView = ImageView(this)
        chair.setImageResource(R.drawable.chair_thumb)
        chair.contentDescription = "chair"
        chair.setOnClickListener { view ->
            selectedObject = Uri.parse("chair.sfb")
        }
        gallery_layout.addView(chair)

        var coach:ImageView = ImageView(this)
        coach.setImageResource(R.drawable.couch_thumb)
        coach.contentDescription = "coach"
        coach.setOnClickListener { view ->
            selectedObject = Uri.parse("couch.sfb")
        }
        gallery_layout.addView(coach)

        var lamp:ImageView = ImageView(this)
        lamp.setImageResource(R.drawable.lamp_thumb)
        lamp.contentDescription = "lamp"
        lamp.setOnClickListener { view ->
            selectedObject = Uri.parse("lamp.sfb")
        }
        gallery_layout.addView(lamp)

        var table:ImageView = ImageView(this)
        table.setImageResource(R.drawable.lamp_thumb)
        table.contentDescription = "table"
        table.setOnClickListener { view ->
            selectedObject = Uri.parse("table.sfb")
        }
        gallery_layout.addView(table)

    }

    /**
     * Function to handle the renderable object and place object in scene
     */
    private fun placeObject (fragment: ArFragment, anchor : Anchor, model: Uri ): Unit {

        val modelRenderable = ModelRenderable.builder()
            .setSource(fragment.requireContext(), model)
            .build()
            .thenAccept { renderable -> addNoteToScene(fragment, anchor, renderable) }
            .exceptionally {
                val toast = Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT)
                toast.show()
                null
            }

    }


    private fun addNoteToScene(fragment: ArFragment, anchor: Anchor, renderable: Renderable) {
        var anchorNode : AnchorNode = AnchorNode(anchor)
        var node :TransformableNode = TransformableNode(fragment.transformationSystem)
        node.renderable = renderable
        node.setParent(anchorNode)
        fragment.arSceneView.scene.addChild(anchorNode)
        node.select()

    }

}
