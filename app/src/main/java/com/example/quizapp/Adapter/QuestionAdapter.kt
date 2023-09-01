package com.example.quizapp.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.models.Question

class QuestionAdapter(val context: Context, val question: Question):
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    private var optionList: List<String> = listOf(question.option1, question.option2, question.option3, question.option4)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item, parent, false)
        return QuestionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val currentOption = optionList[position]
        holder.option.setText(currentOption)
        holder.itemView.setOnClickListener {
            question.userAnswer = optionList[position]
            notifyDataSetChanged()
        }
        if(question.userAnswer == optionList[position]){
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }

    }

    inner class QuestionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val option: TextView = itemView.findViewById(R.id.options)
    }
}