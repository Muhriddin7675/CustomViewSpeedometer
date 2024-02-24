package com.example.customviewspeedometer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.SeekBar
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.delay
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val qrCode = findViewById<MyQrCode>(R.id.speedometer)
//
//        findViewById<Button>(R.id.buttonInput).setOnClickListener {
//            findViewById<TextInputEditText>(R.id.length).text.toString().toInt()
//            findViewById<TextInputEditText>(R.id.right).text.toString().toInt()
//            qrCode.setMatrix(
//                findViewById<TextInputEditText>(R.id.length).text.toString().toInt(),
//                findViewById<TextInputEditText>(R.id.right).text.toString().toInt()
//            )
//        }

       val image =  findViewById<ImageView>(R.id.image)
        image.setOnClickListener {
            myAnimation2(image)
        }




//        val seekbar : SeekBar = findViewById<SeekBar>(R.id.seekbar)
//        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                speedometer.minSpeed = (progress * 1.4).toInt()
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//
//            }
//        })
    }
    fun myAnimation(view: View) = lifecycleScope.launchWhenResumed {
        val duration = 2000f
        val refreshRate = 16L
        val numberOfFrames = ceil(duration / refreshRate).toInt()

        repeat(numberOfFrames + 1) {
            delay(refreshRate)
            val angle = (it * (360F / numberOfFrames))
            view.rotation = angle
        }
    }
    fun  myAnimation2(view: View) = lifecycleScope.launchWhenResumed{
        val refreshRate = 16L
        val r = 360/2000
        for (i in 0 until 120){
            delay(refreshRate)
            val agle = i * r
            view.rotation  = agle.toFloat()
        }
    }
}