package com.example.domain

data class GameState(val status: GameStatus, val turn: Turn, val board: BoardState)
