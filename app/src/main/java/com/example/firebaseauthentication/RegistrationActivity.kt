package com.example.firebaseauthentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseauthentication.databinding.ActivityRegistrationBinding
import com.example.firebaseauthentication.viewModel.AuthViewModel

class RegistrationActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityRegistrationBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
            setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.registerBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val confirmPass = binding.conPassEt.text.toString()
            if(email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()){
                Toast.makeText(this@RegistrationActivity,"Please fill up all the fields!!!", Toast.LENGTH_SHORT).show()
            }else if(!password.equals(confirmPass)){
                Toast.makeText(this@RegistrationActivity,"Password doesn't matched!!!", Toast.LENGTH_SHORT).show()
            }
            else {
                viewModel.signup(email, confirmPass).observe(this) { result ->
                    Toast.makeText(this@RegistrationActivity, result, Toast.LENGTH_SHORT).show()
                }


            }
        }
        binding.loginTxt.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}