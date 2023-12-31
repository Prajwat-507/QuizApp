package com.example.quizapp.Activity.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityResultBinding
import com.example.quizapp.models.Quiz
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    lateinit var quiz: Quiz
    lateinit var  binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        seUpViews()
        backToQuiz()
    }

    private fun backToQuiz() {
        binding.taketoQuizBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun seUpViews() {
        val quizdata = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizdata, Quiz::class.java)
        calculateScore()
        setAnswerView()
    }

    private fun calculateScore() {
        var score=0
        for(entries in quiz.questions.entries){
            val user = entries.value
            if(user.userAnswer == user.answer){
                score+=10
            }
        }
        binding.score.setText(score.toString())
    }

    private fun setAnswerView(){
        val builder = StringBuilder("")
        for(entry in quiz.questions.entries){
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</b></font><br/><br/>")
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            binding.txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        }else{
            binding.txtAnswer.text = Html.fromHtml(builder.toString())
        }
    }
}