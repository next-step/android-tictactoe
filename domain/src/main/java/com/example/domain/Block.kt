package com.example.domain

sealed class Block

class EmptyBlock : Block() {
    fun assign(player: Player): AssignedBlock {
        return when (player) {
            Player.X -> XBlock()
            Player.O -> OBlock()
        }
    }
}

sealed class AssignedBlock : Block()

class XBlock : AssignedBlock() {}

class OBlock : AssignedBlock() {}
