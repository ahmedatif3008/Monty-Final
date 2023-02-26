package com.achaud16.monty.Monty.Model

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
//import com.achaud16.monty.Monty.Model.Symbol.Companion.randomSymbol
import kotlin.random.Random.Default.nextInt

enum class Outcome {
    Blank,
    Ace
}

class Monty {
    var isPlaying = false
    var flippedCard: Int? = null
    var assignment: Array<Outcome>? = null//array of card type

    fun play() {
        isPlaying = true
    }

    fun reset() {
        isPlaying = false
        flippedCard = null
        assignment = null
    }

    fun randomCardAssignment(): Array<Outcome> {
        assignment = Array<Outcome>(3) {
            Outcome.Blank
        }

        assignment!![(0..2).random()] = Outcome.Ace

        return assignment!!
    }

    fun wins(cardIndex: Int): Boolean? {
        if (assignment == null) {
            return null
        }

        return assignment!![cardIndex] == Outcome.Ace;
    }
}