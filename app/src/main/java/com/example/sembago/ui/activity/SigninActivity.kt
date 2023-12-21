package com.example.sembago.ui.activity

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sembago.R
import com.example.sembago.databinding.ActivitySigninBinding
import com.example.sembago.viewmodel.LoginViewModel

class SigninActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[LoginViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvBelumpunyakun: TextView = findViewById(R.id.tvBelumpunyakun)

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

        binding.etEmail.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val email = editable.toString()
                if (viewModel.isEmailValid(email)) {
                    binding.tvEmailAlert.text = ""
                } else {
                    binding.tvEmailAlert.text = resources.getString(R.string.email_invalid_string)
                }
            }

        })

        binding.etPasswordSU.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val alertCode = binding.etPasswordSU.tag as? Int // Mendapatkan alertCode dari tag
                val alertMsg = binding.etPasswordSU.text.toString() // Mendapatkan alertMsg dari text

                if (alertCode == 1) {
                    binding.tvPasswordAlert.text = alertMsg
                    binding.tvPasswordAlert.visibility = View.VISIBLE
                } else {
                    binding.tvPasswordAlert.visibility = View.INVISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnLogin.setOnClickListener{
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPasswordSU.text.toString().trim()

            viewModel.loginUser(email, password)
            viewModel.isLoading.observe(this) {isLoading -> showLoading(isLoading)}
            viewModel.isSuccess.observe(this) {isSuccess -> showLoginResponse(isSuccess)}
            viewModel.token.observe(this) {token -> saveSession(token)}
        }

        val sharedPreferences = getSharedPreferences("LoginSession", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            toMainActivity()
        }

    }

    private fun saveSession(token: String?) {
        val sharedPreferences = getSharedPreferences("LoginSession", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.putString("token", token)
        editor.apply()
    }

    private fun toMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            ObjectAnimator.ofFloat(binding.lyLoginInfo, View.ALPHA, 0.3f).start()
            binding.loadLogin.visibility = View.VISIBLE
        } else {
            ObjectAnimator.ofFloat(binding.lyLoginInfo, View.ALPHA, 1f).start()
            binding.loadLogin.visibility = View.GONE
        }
    }
    private fun showLoginResponse(isSuccess: Boolean) {
        if (isSuccess) {
            Toast.makeText(this@SigninActivity, "Login successful", Toast.LENGTH_SHORT).show()
            binding.tvPasswordAlert.visibility = View.INVISIBLE
            toMainActivity()
        } else {
            binding.tvPasswordAlert.visibility = View.VISIBLE
            binding.tvPasswordAlert.text = resources.getString(R.string.log_fail_string)
        }
    }
}