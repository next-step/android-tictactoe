package com.example.tictectoe_domain


class TictectoeRule {
    fun getWinningPlayer(board: List<Cell>): Cell {
        if (board[1] == board[2] && board[2] == board[3]) {
            if(board[1] != Cell.NONE) return board[1]
        }
        if (board[4] == board[5] && board[5] == board[6]) {
            if(board[4] != Cell.NONE) return board[4]
        }
        if (board[7] == board[8] && board[8] == board[9]) {
            if(board[7] != Cell.NONE) return board[7]
        }
        if (board[1] == board[4] && board[4] == board[7]) {
            if(board[1] != Cell.NONE) return board[1]
        }
        if (board[2] == board[5] && board[5] == board[8]) {
            if(board[2] != Cell.NONE) return board[2]
        }
        if (board[3] == board[6] && board[6] == board[9]) {
            if(board[3] != Cell.NONE) return board[3]
        }
        if (board[1] == board[5] && board[5] == board[9]) {
            if(board[1] != Cell.NONE) return board[1]
        }
        if (board[3] == board[5] && board[5] == board[7]) {
            if(board[3] != Cell.NONE) return board[3]
        }

        return Cell.NONE
    }

    fun isDraw(list: List<Cell>): Boolean {
        return list.count{it == Cell.NONE} == 1
    }
}
