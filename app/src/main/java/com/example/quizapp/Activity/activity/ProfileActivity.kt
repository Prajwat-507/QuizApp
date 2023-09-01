package com.example.quizapp.Activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth


class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    private lateinit var mAUth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        logOutUser()
    }

    private fun logOutUser() {
        mAUth = FirebaseAuth.getInstance()
        binding.emailText.setText(mAUth.currentUser!!.email.toString())
        binding.logoutBtn.setOnClickListener {
            if (mAUth.currentUser != null) {
                mAUth.signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show()
            }
        }
    }
}