package com.achaud16.monty.Monty

interface GameInterface {

    fun onGameResults(amount: Int, wins: Boolean): Int{
        if (wins == true){
            return 50
        }
        else{
            return -50
        }
    }

}