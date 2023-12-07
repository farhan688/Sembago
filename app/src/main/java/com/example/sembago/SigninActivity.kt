package com.example.sembago

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.TextView

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val btnLogin: Button = findViewById(R.id.btnLogin)
        val tvBelumpunyakun: TextView = findViewById(R.id.tvBelumpunyakun)

        btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val fullText = "belum punya akun? sign up"

        val spannableString = SpannableString(fullText)

        val startIndex = fullText.indexOf("sign up")
        val endIndex = startIndex + "sign up".length

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(this@SigninActivity, SignupActivity::class.java)
                startActivity(intent)
            }
        }

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        tvBelumpunyakun.text = spannableString
        tvBelumpunyakun.movementMethod = LinkMovementMethod.getInstance()
    }
}