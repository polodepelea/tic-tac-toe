package com.example.tic_tac_toe

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tic_tac_toe.databinding.JugarIaTableroBinding
import com.example.tic_tac_toe.databinding.JugarTableroBinding
import kotlin.random.Random

class TableroIAJugar : AppCompatActivity() {

    private lateinit var binding: JugarIaTableroBinding

    private var simboloInicial: Boolean = false
    private var simbolo: Boolean = false
    val simbolos = Array(3) { CharArray(3) { ' ' } }

    val imageViews: List<ImageView> by lazy {
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
        binding = JugarIaTableroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        simboloInicial = intent.getBooleanExtra("simbolo_elegido", false)
        simbolo = simboloInicial

        binding.btReset.setOnClickListener {
            reset()
        }
        binding.btHome.setOnClickListener {
            goHome()
        }

        setListener()
    }

    private fun setListener() {
        for (imageView in imageViews) {
            imageView.setOnClickListener {
                val carita = it as ImageView
                val skull = it

                if (!isCellOccupied(imageView) && !gameOver()) { // Solo permite jugar si no hay ganador ni empate
                    if (simbolo) {
                        simbolos[getCellIndex(imageView).first][getCellIndex(imageView).second] = 'O'
                        carita.setImageResource(R.drawable.caritafachera)
                    } else {
                        simbolos[getCellIndex(imageView).first][getCellIndex(imageView).second] = 'X'
                        skull.setImageResource(R.drawable.skull)
                    }

                    imageView.setOnClickListener(null)

                    if (playerWon(if (simbolo) 'O' else 'X')) {
                        Toast.makeText(this, if (simbolo) "¡Carita fachera gano!" else "¡Rip Bozo gano!", Toast.LENGTH_SHORT).show()
                        desactivarListeners()
                    } else if (!gameOver()) {
                        botJugar(simbolo)
                    } else {
                        Toast.makeText(this, "¡Partida terminada en empate!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun desactivarListeners() {
        for (imageView in imageViews) {
            imageView.setOnClickListener(null)
        }
    }

    private fun botJugar(simbolo: Boolean) {
        val availableImageViews = imageViews.filter { !isCellOccupied(it) }

        if (availableImageViews.isNotEmpty()) {
            val numeroAleatorio = Random.nextInt(0, availableImageViews.size)
            val imagenTocar = availableImageViews[numeroAleatorio]

            if (simbolo) {
                simbolos[getCellIndex(imagenTocar).first][getCellIndex(imagenTocar).second] = 'X'
                imagenTocar.setImageResource(R.drawable.skull)
            } else {
                simbolos[getCellIndex(imagenTocar).first][getCellIndex(imagenTocar).second] = 'O'
                imagenTocar.setImageResource(R.drawable.caritafachera)
            }

            imagenTocar.performClick()
            imagenTocar.setOnClickListener(null)

            if (playerWon(if (simbolo) 'X' else 'O')) {
                Toast.makeText(this, if (simbolo) "¡Rip Bozo gano!" else "¡Carita fachera gano!", Toast.LENGTH_SHORT).show()
                desactivarListeners()
            } else if (gameOver()) {
                Toast.makeText(this, "¡Partida terminada en empate!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun reset() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                simbolos[i][j] = ' '
            }
        }

        for (imageView in imageViews) {
            imageView.setImageResource(android.R.color.transparent)
            imageView.setOnClickListener(null)
        }

        setListener()
    }

    private fun goHome() {
        val intent = Intent(this, ElegirSimbolo::class.java)
        startActivity(intent)
    }

    private val tableroHelper = TableroOpciones()

    private fun gameOver(): Boolean {
        return tableroHelper.gameOver(simbolos)
    }

    private fun isCellOccupied(imageView: ImageView): Boolean {
        return tableroHelper.isCellOccupied(simbolos, imageView)
    }

    private fun getCellIndex(imageView: ImageView): Pair<Int, Int> {
        return tableroHelper.getCellIndex(imageView)
    }

    private fun playerWon(player: Char): Boolean {
        return tableroHelper.playerWon(simbolos, player)
    }
}
