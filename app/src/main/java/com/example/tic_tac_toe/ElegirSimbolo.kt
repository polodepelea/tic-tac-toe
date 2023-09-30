package com.example.tic_tac_toe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tic_tac_toe.databinding.SimboloElegirBinding

class ElegirSimbolo : AppCompatActivity() {

    private lateinit var binding: SimboloElegirBinding
    private var simbolo: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SimboloElegirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener() {
        binding.equipoCara.setOnClickListener {
            simbolo = true
            startGame()
        }
        binding.equipoCruz.setOnClickListener {
            simbolo = false
            startGame()
        }
        binding.equipoCaraRobot.setOnClickListener {
            simbolo = true
            startGameRobot()
        }
        binding.equipoCruzRobot.setOnClickListener {
            simbolo = false
            startGameRobot()
        }
    }

    private fun startGame() {
        val intent = Intent(this, TableroJugar::class.java)
        intent.putExtra("simbolo_elegido", simbolo)
        startActivity(intent)
    }

    private fun startGameRobot() {
        val intent = Intent(this, TableroIAJugar::class.java)
        intent.putExtra("simbolo_elegido", simbolo)
        startActivity(intent)
    }



}