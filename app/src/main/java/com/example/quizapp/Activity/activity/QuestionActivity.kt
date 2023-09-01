package com.example.quizapp.Activity.activity

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.Adapter.QuestionAdapter
import com.example.quizapp.Adapter.QuizAdapter
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityLoginBinding
import com.example.quizapp.databinding.ActivityQuestionBinding
import com.example.quizapp.models.Question
import com.example.quizapp.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson


class QuestionActivity : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
    lateinit var date: String
    lateinit var adapter: QuizAdapter
    lateinit var binding: ActivityQuestionBinding
    var quizzes = mutableListOf<Quiz>()
    var question = (mutableMapOf <String, Question>())
    var index = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFirestore()
        setUpEventListner()
    }

    private fun setUpEventListner() {

        binding.nxtBtn.setOnClickListener {
            index+=1
            bindView()
        }
        binding.prevButton.setOnClickListener {
            index-=1
            bindView()
        }
        binding.submitBtn.setOnClickListener {
            Log.d("userAns", question.toString())
            val intent = Intent(this, ResultActivity::class.java)
            val json = Gson().toJson(quizzes[0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
            finish()
        }
    }

    private fun setFirestore() {
        date = intent.getStringExtra("DATE").toString()
        Log.d("DATE", date)
        firestore = FirebaseFirestore.getInstance()
        val docRef = firestore.collection("quizzes").whereEqualTo("title", date)
            .get()
            .addOnSuccessListener {
                Log.d("DATA", it.toObjects(Quiz::class.java).toString())
                quizzes = it.toObjects(Quiz::class.java)
                Log.d("QUIZset", quizzes.toString())
                question = quizzes[0].questions
                Log.d("quesPos",question["question$index"].toString())
                bindView()
            }
        }

    private fun bindView() {

        binding.nxtBtn.visibility = View.GONE
        binding.prevButton.visibility = View.GONE
        binding.submitBtn.visibility = View.GONE
        if(index == 1){
            binding.nxtBtn.visibility = View.VISIBLE
        }
        else if(index>1 && index<question.size){
            binding.prevButton.visibility = View.VISIBLE
            binding.nxtBtn.visibility = View.VISIBLE
        }
        else{
            binding.prevButton.visibility = View.VISIBLE
            binding.nxtBtn.visibility = View.GONE
            binding.submitBtn.visibility = View.VISIBLE
        }

        val question1: Question? = question.get("question$index")
        question1.let {
            binding.description.setText(it!!.description)
            val optionAdapter = QuestionAdapter(this, it)
            binding.questionRecycler.layoutManager = LinearLayoutManager(this)
            binding.questionRecycler.adapter = optionAdapter
            binding.questionRecycler.setHasFixedSize(true)
        }
    }
}