package com.achaud16.monty.Monty.Fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.achaud16.monty.Monty.Model.Account
import com.achaud16.monty.R
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), GameInterface{
    private lateinit var winsValue: TextView
    private lateinit var lossValue: TextView

    private lateinit var amountValue: TextView

    private lateinit var account: Account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        amountValue = findViewById(R.id.CurrentValue    )
        winsValue = findViewById(R.id.winsVal)
        lossValue = findViewById(R.id.lossesVal)

        winsValue.text = 0.toString()
        lossValue.text = 0.toString()

        account = Account()
        onGameResults(0)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.cards_frame, CardsFragment.newInstance())
                .commitNow()
        }



    }

    override fun onGameResults(amount: Int) {
        if(amount > 0) {
            account.wins++
            account.total += amount
        }
        else if(amount < 0) {
            account.losses++
            account.total += amount
        }

        winsValue.text = account.wins.toString()
        lossValue.text = account.losses.toString()
        amountValue.text = account.total.toString()
    }
}

