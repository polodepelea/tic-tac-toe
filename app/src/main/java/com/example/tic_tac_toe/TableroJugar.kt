package com.example.tic_tac_toe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

import com.example.tic_tac_toe.databinding.JugarTableroBinding

class TableroJugar : AppCompatActivity() {

    private lateinit var binding: JugarTableroBinding

    private var simbolo: Boolean = false
    private var simboloInicial: Boolean = false

    private val simbolos = Array(3) { CharArray(3) { ' ' } }

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
        binding = JugarTableroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        simboloInicial = intent.getBooleanExtra("simbolo_elegido", false)
        simbolo = simboloInicial

        setListener()
        binding.btReset.setOnClickListener {
            resetGame()
        }
        binding.btHome.setOnClickListener {
            goHome()
        }
    }

    private fun goHome() {
        val intent = Intent(this, ElegirSimbolo::class.java)
        startActivity(intent)
    }

    private fun setListener() {
        for (imageView in imageViews) {
            imageView.setOnClickListener {
                val carita = it as ImageView
                val skull = it

                if (!isCellOccupied(imageView)) {
                    if (simbolo) {
                        simbolos[getCellIndex(imageView).first][getCellIndex(imageView).second] = 'O'
                        carita.setImageResource(R.drawable.caritafachera)
                    } else {
                        simbolos[getCellIndex(imageView).first][getCellIndex(imageView).second] = 'X'
                        skull.setImageResource(R.drawable.skull)
                    }

                    imageView.setOnClickListener(null)

                    if (playerWon('O')) {
                        Toast.makeText(this, "¡Carita fachera gano!", Toast.LENGTH_SHORT).show()
                        for (imageView in imageViews) {
                            imageView.setOnClickListener(null)
                        }

                    }else if (playerWon('X')) {
                        Toast.makeText(this, "¡Rip Bozo gano!", Toast.LENGTH_SHORT).show()
                        for (imageView in imageViews) {
                            imageView.setOnClickListener(null)
                        }
                    }else if(gaveOver()){
                        Toast.makeText(this, "¡Partida terminada en empate!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        simbolo = !simbolo
                    }

                }
            }
        }
    }

    private fun gaveOver(): Boolean {
        for (row in 0 until 3) {
            for (col in 0 until 3) {
                if (simbolos[row][col] == ' ') {
                    return false
                }
            }
        }
        return true
    }


    private fun isCellOccupied(imageView: ImageView): Boolean {
        val cellIndex = getCellIndex(imageView)
        return simbolos[cellIndex.first][cellIndex.second] != ' '
    }

    private fun getCellIndex(imageView: ImageView): Pair<Int, Int> {
        val id = imageView.id
        val row = (id - R.id.imageView1) / 3
        val col = (id - R.id.imageView1) % 3
        return Pair(row, col)
    }

    private fun playerWon(player: Char): Boolean {
        for (row in 0 until 3) {
            if (simbolos[row][0] == player && simbolos[row][1] == player && simbolos[row][2] == player) {
                return true
            }
        }

        for (col in 0 until 3) {
            if (simbolos[0][col] == player && simbolos[1][col] == player && simbolos[2][col] == player) {
                return true
            }
        }

        if (simbolos[0][0] == player && simbolos[1][1] == player && simbolos[2][2] == player) {
            return true
        }
        if (simbolos[0][2] == player && simbolos[1][1] == player && simbolos[2][0] == player) {
            return true
        }

        return false
    }


    private fun resetGame() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                simbolos[i][j] = ' '
            }
        }

        for (imageView in imageViews) {
            imageView.setImageResource(android.R.color.transparent)
            imageView.setOnClickListener(null)
        }

        simbolo = simboloInicial

        setListener()
    }
}



