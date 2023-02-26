package com.achaud16.monty.Monty
import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import com.achaud16.monty.Monty.Model.MontyModel
import com.achaud16.monty.Monty.Model.Outcome
import com.achaud16.monty.R

class MainActivity : AppCompatActivity() {

    private lateinit var bottomCard: ImageView
    private lateinit var middleCard: ImageView
    private lateinit var topCard: ImageView

    private lateinit var startBtn: Button
    private lateinit var resetBtn: Button

    private var animationCount = 3
    private var screenHeight = 0f;
    private var screenWidth = 0f

    private lateinit var winnerImg : ImageView
    private lateinit var loserImg : ImageView

    private val model = MontyModel();
    private lateinit var matchResult: Outcome;

    companion object{
        const val CARD_ANIMATION_DURATION = 500L;
        const val NINETY = 90f;
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomCard = findViewById(R.id.Card1)
        middleCard = findViewById(R.id.card2)
        topCard = findViewById(R.id.card3)

        winnerImg = findViewById(R.id.winI)
        loserImg = findViewById(R.id.lossI)

        winnerImg.alpha = 0.0f
        loserImg.alpha = 0.0f



        startBtn = findViewById(R.id.button2)
        startBtn.isEnabled = true

        matchResult = Outcome.Blank


        startBtn.setOnClickListener{
            animationCount = 3
            startBtn.isEnabled = false
            resetBtn.isEnabled = true
            animateCards()

            bottomCard.setOnClickListener{
                val animatorL1 =ObjectAnimator.ofFloat(bottomCard, "scaleY", 1f, 0f).setDuration(500)

//                animatorL1.addListener(object : Animator.AnimatorListener {
//                    override fun onAnimationEnd(animation: Animator) {
//                        if (model.getWinner(b))
//                    }
//                })

                val animatorL2 =ObjectAnimator.ofFloat(bottomCard, "scaleY", 0f, -1f).setDuration(500)

                animatorL1.interpolator = AccelerateInterpolator()
                animatorL2.interpolator = AccelerateInterpolator()

                val set = AnimatorSet()
                set.play(animatorL1).before(animatorL2)

                set.start()
            }

            topCard.setOnClickListener{
                val animatorL3 =ObjectAnimator.ofFloat(topCard, "scaleY", 1f, 0f).setDuration(500)

//                animatorL1.addListener(object : Animator.AnimatorListener {
//                    override fun onAnimationEnd(animation: Animator) {
//                        if (model.getWinner(b))
//                    }
//                })

                val animatorL4 =ObjectAnimator.ofFloat(topCard, "scaleY", 0f, -1f).setDuration(500)

                animatorL3.interpolator = AccelerateInterpolator()
                animatorL4.interpolator = AccelerateInterpolator()

                val set = AnimatorSet()
                set.play(animatorL3).before(animatorL4)

                set.start()
            }

            middleCard.setOnClickListener{
                val animatorL5 =ObjectAnimator.ofFloat(middleCard, "scaleY", 1f, 0f).setDuration(500)

//                animatorL1.addListener(object : Animator.AnimatorListener {
//                    override fun onAnimationEnd(animation: Animator) {
//                        if (model.getWinner(b))
//                    }
//                })

                val animatorL6 =ObjectAnimator.ofFloat(middleCard, "scaleY", 0f, -1f).setDuration(500)

                animatorL5.interpolator = AccelerateInterpolator()
                animatorL6.interpolator = AccelerateInterpolator()

                val set = AnimatorSet()
                set.play(animatorL5).before(animatorL6)

                set.start()
            }



        }

        //resetBtn.isEnabled = false
        resetBtn = findViewById(R.id.button)
        resetBtn.isEnabled = false
        resetBtn.setOnClickListener{
            animationCount = 3
            startBtn.isEnabled = true
            resetBtn.isEnabled = false
            resetCards()
        }
    }



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
        animator.duration = CARD_ANIMATION_DURATION
        animator.start()

        animator2.interpolator = LinearInterpolator()
        animator2.duration = CARD_ANIMATION_DURATION

        animator2.start()

    }


    private fun resetCards(){
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
        animator.duration = CARD_ANIMATION_DURATION
        animator.start()

        animator2.interpolator = LinearInterpolator()
        animator2.duration = CARD_ANIMATION_DURATION

        animator2.start()

    }

    private fun flipCard(){

    }

    private fun winAnimation(){

    }


    override fun onResume(){
        super.onResume()
        resources.displayMetrics.let{displayMetrics ->
            screenHeight = displayMetrics.heightPixels.toFloat()
            screenWidth = displayMetrics.widthPixels.toFloat()
        }
    }
}