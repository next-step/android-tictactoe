package com.example.domain

sealed class GameMode

object TwoPlayerMode : GameMode()

class RandomMode : GameMode() {

}
