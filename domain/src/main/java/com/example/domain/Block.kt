package com.example.domain

sealed class Block

class EmptyBlock : Block() {
    fun assign(player: Player): AssignedBlock {
        return when (player) {
            Player.X -> XBlock
            Player.O -> OBlock
        }
    }

    override fun toString(): String {
        return "EmptyBlock()"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

sealed class AssignedBlock : Block()

object XBlock : AssignedBlock() {
    override fun toString(): String {
        return "XBlock()"
    }
}

object OBlock : AssignedBlock() {
    override fun toString(): String {
        return "OBlock()"
    }
}
