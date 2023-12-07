package com.example.sembago

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val btnMasuk: TextView = findViewById(R.id.btnMasuk)
        val tvSudahpunyaakun: TextView = findViewById(R.id.tvSudahpunyakun)

        btnMasuk.setOnClickListener {
            val intent = Intent(this, VerificationActivity::class.java)
            startActivity(intent)
        }

        val fullText = "sudah punya akun? sign in"

        val spannableString = SpannableString(fullText)

        val startIndex = fullText.indexOf("sign in")
        val endIndex = startIndex + "sign in".length

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(this@SignupActivity, SigninActivity::class.java)
                startActivity(intent)
            }
        }

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        tvSudahpunyaakun.text = spannableString
        tvSudahpunyaakun.movementMethod = LinkMovementMethod.getInstance()
    }
}