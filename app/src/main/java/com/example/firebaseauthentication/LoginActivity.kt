package com.example.firebaseauthentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseauthentication.databinding.ActivityLoginBinding
import com.example.firebaseauthentication.viewModel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this@LoginActivity,"Please fill up all the fields!!!", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.signIn(email,password).observe(this) {
                    Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT).show()
                    if (it.equals("Sign in successful")) {
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                }


            }
        }
        binding.registerTxt.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser?.uid!=null){
            startActivity(Intent(this, HomeActivity::class.java))
        }

    }
}