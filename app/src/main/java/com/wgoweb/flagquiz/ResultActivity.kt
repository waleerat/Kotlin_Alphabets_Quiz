package com.wgoweb.flagquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val username = intent.getStringExtra(Constants.USER_NAME)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val totalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTION, 0)

        tvName.text = username
        tvScore.text = "Your Score is ${correctAnswers.toString()} out of ${totalQuestion.toString()}"

        bntFinish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}

