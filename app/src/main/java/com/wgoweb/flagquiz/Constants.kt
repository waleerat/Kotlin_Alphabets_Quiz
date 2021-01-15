package com.wgoweb.flagquiz

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTION: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions() : ArrayList<Question> {
        val quizBrain = QuizBrain()
        val questionList = ArrayList<Question>()
        var question: Question
        for (i in 1..10) {
             question = quizBrain.generateChoices(quizId = i)
            questionList.add(question)
        }
        return questionList
    }
}
