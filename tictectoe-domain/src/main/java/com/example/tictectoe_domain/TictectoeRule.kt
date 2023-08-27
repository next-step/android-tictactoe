package com.example.tictectoe_domain


class TictectoeRule {
    fun isWin(list: MutableList<Int>): Boolean {
        if(list[1] == list[2] && list[2] == list[3] ||
            list[4] == list[5] && list[5] == list[6] ||
            list[7] == list[8] && list[8] == list[9]) {
            // 가로 승리 조건
            return true
        } else if(list[1] == list[4] && list[4] == list[7] ||
            list[2] == list[5] && list[5] == list[8] ||
            list[3] == list[6] && list[6] == list[7]) {
            // 세로 승리 조건
            return true
        } else if(list[1] == list[5] && list[5] == list[9] ||
            list[3] == list[5] && list[5] == list[7]) {
            // 대각선 승리 조건
            return true
        }
        return false
    }
}
