package com.example.tic_tac_toe

import android.widget.ImageView

class TableroOpciones {

    fun gameOver(simbolos: Array<CharArray>): Boolean {
        for (row in 0 until 3) {
            for (col in 0 until 3) {
                if (simbolos[row][col] == ' ') {
                    return false
                }
            }
        }
        return true
    }

    fun isCellOccupied(simbolos: Array<CharArray>, imageView: ImageView): Boolean {
        val cellIndex = getCellIndex(imageView)
        return simbolos[cellIndex.first][cellIndex.second] != ' '
    }

    fun getCellIndex(imageView: ImageView): Pair<Int, Int> {
        val id = imageView.id
        val row = (id - R.id.imageView1) / 3
        val col = (id - R.id.imageView1) % 3
        return Pair(row, col)
    }

    fun playerWon(simbolos: Array<CharArray>, player: Char): Boolean {
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
}

