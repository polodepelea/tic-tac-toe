package com.example.tic_tac_toe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.tic_tac_toe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var simbolo: Boolean = false // Inicialmente, el símbolo es "cruz"

    private val imageViews: List<ImageView> by lazy {
        listOf(
            binding.imageView1,
            binding.imageView2,
            binding.imageView3,
            binding.imageView4,
            binding.imageView5,
            binding.imageView6,
            binding.imageView7,
            binding.imageView8,
            binding.imageView9
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener() {

        for (imageView in imageViews) {
            imageView.setOnClickListener {
                val carita = it as ImageView
                val skull = it


                if (!simbolo) {
                    simbolo = true

                    carita.setImageResource(R.drawable.caritafachera)
                    imageView.setOnClickListener(null)

                } else {
                    simbolo = false

                    skull.setImageResource(R.drawable.skull)
                    imageView.setOnClickListener(null)
                }
            }
        }
    }

    private fun onClick(){
        for (imageView in imageViews) {

            imageView.setImageResource(android.R.color.white)
        }

    }
}



