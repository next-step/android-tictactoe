package com.example.domain

data class GameState(
    val status: GameStatus,
    val board: BoardState
)
