package com.nextstep.edu.tictactoe.domain

class Tictactoe {
    private var isXTurn: Boolean? = null

    fun isXTurn(): Boolean? {
        isXTurn = if (isXTurn == null) {
            true
        } else {
            isXTurn != true
        }
        return isXTurn
    }

    fun restart() {
        isXTurn = null
    }
}