package com.example.domain

interface Game {
    val state: GameState

    fun assignBlock(blockIndex: Int)
    fun changeMode(mode: SelectMode)
    fun reset()

}
