package com.example.sembago

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sembago.databinding.ActivitySignupBinding
import com.example.sembago.viewmodel.RegisterViewModel
import de.hdodenhof.circleimageview.CircleImageView

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[RegisterViewModel::class.java]
    }

    private lateinit var ivProfile: CircleImageView

    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK && result.data != null) {
            val imageUri = result.data?.data
            imageUri?.let {
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                ivProfile.setImageBitmap(bitmap)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etEmailSU.addTextChangedListener(object: TextWatcher {
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

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etPassword.alertCode == 1) {
                    binding.tvPasswordAlert.text = binding.etPassword.alertMsg
                    binding.tvPasswordAlert.visibility = TextView.VISIBLE
                } else {
                    binding.tvPasswordAlert.visibility = TextView.INVISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        binding.etConfirmpass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = s.toString()
                if (binding.etConfirmpass.alertCode == 1) {
                    binding.tvPassword2Alert.text = binding.etConfirmpass.alertMsg
                    binding.tvPassword2Alert.visibility = TextView.VISIBLE
                } else {
                    if (binding.etConfirmpass.text.toString() != password) {
                        binding.tvPassword2Alert.text = resources.getString(R.string.pass_invalid_string)
                        binding.tvPassword2Alert.visibility = View.VISIBLE
                    } else {
                        binding.tvPassword2Alert.text = resources.getString(R.string.pass_valid_string)
                        binding.tvPassword2Alert.visibility = View.INVISIBLE
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        val tvSudahpunyaakun: TextView = findViewById(R.id.tvSudahpunyakun)
        ivProfile = findViewById(R.id.ivProfile)

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

        val btnAddImage: View = findViewById(R.id.btnAddImage)
        btnAddImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImage.launch(gallery)
        }

        binding.btnMasuk.setOnClickListener {
            val name = binding.etNama.text.toString()
            val email = binding.etEmailSU.text.toString()
            val password = binding.etPassword.text.toString()
            val passwordConfirmation = binding.etConfirmpass.text.toString()

            if (password == passwordConfirmation) {
                binding.tvPasswordAlert.visibility = TextView.INVISIBLE
                binding.tvPassword2Alert.visibility = TextView.INVISIBLE
                viewModel.registerUser(name, email, password)
                viewModel.isSuccess.observe(this) { isSuccess ->
                    if (isSuccess){
                        showRegisterResponse(isSuccess)
                    }
                }
            }
        }
    }

    private fun navigateToSignIn() {
        val intent = Intent(this@SignupActivity, SigninActivity::class.java)
        startActivity(intent)
    }
    private fun showRegisterResponse(isSuccess: Boolean) {
        if (isSuccess) {
            binding.tvPassword2Alert.apply {
                visibility = View.VISIBLE
                setTextColor(Color.BLUE)
                text = "" +
                        "Register successful. You can login with your account now"
            }
        } else {
            binding.tvPassword2Alert.apply {
                visibility = View.INVISIBLE
                setTextColor(Color.RED)
                text = "Register failed. Please try again or adjust your email to another email."
            }
        }
    }
}
