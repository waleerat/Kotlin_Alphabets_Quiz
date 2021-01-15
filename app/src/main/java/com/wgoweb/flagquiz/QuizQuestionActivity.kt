package com.wgoweb.flagquiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_quiestion.*
import kotlin.math.roundToInt


class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mUserName: String? = null

    private var mCurrentPostition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz_quiestion)

        // get value from previous screen
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        // get Questions
        mQuestionList = Constants.getQuestions()
        setQuestion()

        textViewOptionOne.setOnClickListener(this)
        textViewOptionTwo.setOnClickListener(this)
        textViewOptionThree.setOnClickListener(this)
        textViewOptionFour.setOnClickListener(this)
        bntSummit.setOnClickListener(this)

    }

    fun setQuestion(){
        //val currentPosition = 1
        val question = mQuestionList!![mCurrentPostition - 1]//Question? = questionList[currentPosition -1]
        // set to default
        defaultOptionView()

        progressBar.progress = mCurrentPostition

        progressBar.max = mQuestionList!!.size
        textViewProgressBar.text = "$mCurrentPostition" + "/" + progressBar.max

        if (mCurrentPostition == mQuestionList!!.size)  {
            bntSummit.text = "FINISH"
        } else {
            bntSummit.text = "SUBMIT"
        }

        // get dynamic image
        TextViewQuestion.text = question.question
        val imageResource = resources.getIdentifier(question.drawable, null, packageName)
        val res = resources.getDrawable(imageResource)

        imageViewQuiz.setImageDrawable(res)


        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenHeight = displayMetrics.heightPixels
        val screenWidth = displayMetrics.widthPixels

        val imgHeight = (screenHeight * 0.30)
        //val imgWidth = (screenWidth * 0.25) // 25% of screen.
        imageViewQuiz.getLayoutParams().height = imgHeight.roundToInt()
        //imageViewQuiz.getLayoutParams().width = imgWidth.roundToInt()

        textViewOptionOne.text = question.optionOne
        textViewOptionTwo.text = question.optionTwo
        textViewOptionThree.text = question.optionThree
        textViewOptionFour.text = question.optionFour
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.textViewOptionOne -> {
                selectOptionView(textViewOptionOne, 1)
            }
            R.id.textViewOptionTwo -> {
                selectOptionView(textViewOptionTwo, 2)
            }
            R.id.textViewOptionThree -> {
                selectOptionView(textViewOptionThree, 3)
            }
            R.id.textViewOptionFour -> {
                selectOptionView(textViewOptionFour, 4)
            }


            R.id.bntSummit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPostition++
                    when {
                        mCurrentPostition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTION, mQuestionList!!.size)
                            startActivity(intent)
                        }
                    }
                } else {
                    val question = mQuestionList?.get(mCurrentPostition - 1)

                    if (question!!.correctAnswer + 1 != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.option_border_wrong_bg)
                    } else {
                        mCorrectAnswer++
                    }

                    answerView(question.correctAnswer + 1, R.drawable.option_border_correct_bg)

                    if (mCurrentPostition == mQuestionList!!.size) {
                        bntSummit.text = "FINISH"
                    } else {
                        bntSummit.text = "Go to the next question"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when(answer) {
            1 -> {
                textViewOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                textViewOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                textViewOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                textViewOptionFour.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun selectOptionView(textView: TextView, selectedOptionNum: Int) {
        // Set to default
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNum

        textView.setTextColor(Color.parseColor("#7A8089"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(this, R.drawable.option_border_selected_bg)
    }

    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        options.add(0, textViewOptionOne)
        options.add(1, textViewOptionTwo)
        options.add(2, textViewOptionThree)
        options.add(3, textViewOptionFour)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.option_border_default_bg)
        }
    }



}
