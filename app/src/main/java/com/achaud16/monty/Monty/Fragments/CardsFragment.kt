package com.achaud16.monty.Monty.Fragments

import android.animation.*
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import com.achaud16.monty.Monty.Model.Account
import com.achaud16.monty.Monty.Model.MontyModel
import com.achaud16.monty.Monty.Model.Outcome
import com.achaud16.monty.R

interface GameInterface{
    fun onGameResults(amount: Int);
}
class CardsFragment : Fragment() {

    private lateinit var bottomCard: ImageView
    private lateinit var middleCard: ImageView
    private lateinit var topCard: ImageView


    private lateinit var callback: GameInterface
    private lateinit var account: Account

    private lateinit var startBtn: Button
    private lateinit var resetBtn: Button

    private var animationCount = 3
    private var screenHeight = 0f;
    private var screenWidth = 0f

    private lateinit var winnerImg : ImageView
    private lateinit var loserImg : ImageView

    private val model = MontyModel();
    private lateinit var matchResult: Outcome;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewVar = inflater.inflate(R.layout.fragment_cards, container, false)
        return viewVar

    }
        //setContentView(R.layout.fragment_cards)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomCard = requireView().findViewById(R.id.Card1)
        middleCard = requireView().findViewById(R.id.card2)
        topCard = requireView().findViewById(R.id.card3)

        winnerImg = requireView().findViewById(R.id.winI)
        loserImg = requireView().findViewById(R.id.lossI)

        winnerImg.alpha = 0.0f
        loserImg.alpha = 0.0f



        startBtn = requireView().findViewById(R.id.button2)
        startBtn.isEnabled = true

        matchResult = Outcome.Blank


        startBtn.setOnClickListener{
            model.play()
            animationCount = 3
            startBtn.isEnabled = false
            resetBtn.isEnabled = true
            animateCards()

            var randArray = model.randomCardAssignment()

            bottomCard.setOnClickListener{
                topCard.setOnClickListener(null)
                bottomCard.setOnClickListener(null)
                middleCard.setOnClickListener(null)

                val animatorL1 = ObjectAnimator.ofFloat(bottomCard, "scaleY", 1f, 0f).setDuration(500)



                val animatorL2 = ObjectAnimator.ofFloat(bottomCard, "scaleY", 0f, -1f).setDuration(500)

                animatorL1.interpolator = AccelerateInterpolator()
                animatorL2.interpolator = AccelerateInterpolator()

                animatorL1.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)

                        if (randArray[0] == Outcome.Blank){//model.wins(0) == false
                            callback.onGameResults(-50)
                            bottomCard.setImageResource(R.drawable.blank)
                            loserImg.alpha = 1.0f
                        }
                        else{
                            callback.onGameResults(50)
                            bottomCard.setImageResource(R.drawable.ace)
                            winnerImg.alpha = 1.0f
                        }

                        animatorL2.start()
                    }
                })

                val set = AnimatorSet()
                set.play(animatorL1).before(animatorL2)

                set.start()
            }

            topCard.setOnClickListener{
                topCard.setOnClickListener(null)
                bottomCard.setOnClickListener(null)
                middleCard.setOnClickListener(null)

                val animatorL3 = ObjectAnimator.ofFloat(topCard, "scaleY", 1f, 0f).setDuration(500)



                val animatorL4 = ObjectAnimator.ofFloat(topCard, "scaleY", 0f, -1f).setDuration(500)

                animatorL3.interpolator = AccelerateInterpolator()
                animatorL4.interpolator = AccelerateInterpolator()

                animatorL3.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)

                        if (randArray[1] == Outcome.Blank){
                            callback.onGameResults(-50)
                            loserImg.alpha = 1.0f
                            topCard.setImageResource(R.drawable.blank)
                        }
                        else{
                            callback.onGameResults(50)
                            winnerImg.alpha = 0.0f
                            topCard.setImageResource(R.drawable.ace)
                        }

                        animatorL4.start()
                    }
                })

                val set = AnimatorSet()
                set.play(animatorL3).before(animatorL4)

                set.start()
            }

            middleCard.setOnClickListener{
                topCard.setOnClickListener(null)
                bottomCard.setOnClickListener(null)
                middleCard.setOnClickListener(null)
                val animatorL5 = ObjectAnimator.ofFloat(middleCard, "scaleY", 1f, 0f).setDuration(500)



                val animatorL6 = ObjectAnimator.ofFloat(middleCard, "scaleY", 0f, -1f).setDuration(500)

                animatorL5.interpolator = AccelerateInterpolator()
                animatorL6.interpolator = AccelerateInterpolator()

                animatorL5.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)

                        if (randArray[2] == Outcome.Blank){
                            callback.onGameResults(-50)
                            loserImg.alpha = 1.0f
                            middleCard.setImageResource(R.drawable.blank)
                        }
                        else{
                            callback.onGameResults(50)
                            winnerImg.alpha = 1.0f
                            middleCard.setImageResource(R.drawable.ace)
                        }

                        //middleCard.setImageResource(R.drawable.ace)
                        animatorL6.start()
                    }
                })

                val set = AnimatorSet()
                set.play(animatorL5).before(animatorL6)

                set.start()
            }



        }

        //resetBtn.isEnabled = false
        resetBtn = requireView().findViewById(R.id.button)
        resetBtn.isEnabled = false
        resetBtn.setOnClickListener{
            winnerImg.alpha = 0.0f
            loserImg.alpha = 0.0f
            animationCount = 3
            startBtn.isEnabled = true
            resetBtn.isEnabled = false
            resetCards()
        }

        //return viewVar
    }

    companion object {
        @JvmStatic
        fun newInstance() = CardsFragment()

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as GameInterface
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//
//    }

    private fun animateCards(){
        val cardWidth = middleCard.drawable.intrinsicWidth.toFloat()
        val animator = ValueAnimator.ofFloat(middleCard.getLeft().toFloat(), middleCard.getLeft() + cardWidth + 70)
        val animator2 = ValueAnimator.ofFloat(middleCard.getLeft().toFloat(), middleCard.getLeft() - cardWidth - 70)


        animator.addUpdateListener {
            bottomCard.x = it.animatedValue as Float

        }
        animator2.addUpdateListener {
            topCard.x = it.animatedValue as Float
        }
        animator.interpolator = LinearInterpolator()
        animator.duration = 500L
        animator.start()

        animator2.interpolator = LinearInterpolator()
        animator2.duration = 500L

        animator2.start()

    }


    private fun resetCards(){


        model.reset()
        topCard.setImageResource(R.drawable.cardback)
        middleCard.setImageResource(R.drawable.cardback)
        bottomCard.setImageResource(R.drawable.cardback)
        val cardWidth = middleCard.drawable.intrinsicWidth.toFloat()
        val animator = ValueAnimator.ofFloat(middleCard.getLeft() + cardWidth + 70, middleCard.getLeft().toFloat())
        val animator2 = ValueAnimator.ofFloat(middleCard.getLeft() - cardWidth - 70, middleCard.getLeft().toFloat())

        animator.addUpdateListener {
            bottomCard.x = it.animatedValue as Float

        }
        animator2.addUpdateListener {
            topCard.x = it.animatedValue as Float
        }
        animator.interpolator = LinearInterpolator()
        animator.duration = 500L
        animator.start()

        animator2.interpolator = LinearInterpolator()
        animator2.duration = 500L

        animator2.start()

    }




    override fun onResume(){
        super.onResume()
        resources.displayMetrics.let{displayMetrics ->
            screenHeight = displayMetrics.heightPixels.toFloat()
            screenWidth = displayMetrics.widthPixels.toFloat()
        }
    }

}