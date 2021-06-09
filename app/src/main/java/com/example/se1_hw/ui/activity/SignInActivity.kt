package com.example.se1_hw.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.se1_hw.api.ServiceCreator
import com.example.se1_hw.data.SoptUserAuthStorage
import com.example.se1_hw.data.local.SoptUserInfo
import com.example.se1_hw.data.request.RequestLoginData
import com.example.se1_hw.data.response.ResponseLoginData
import com.example.se1_hw.databinding.ActivityMainBinding
import com.example.se1_hw.utils.enqueueUtil
import com.example.se1_hw.utils.showToast
import retrofit2.Call

class SignInActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        binding.idedit.setText(it.data?.getStringExtra("ID"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchUserAuthStorage()
        loginButtonEvent()
        signUpResult()
    }
    private fun searchUserAuthStorage() {
        with(SoptUserAuthStorage.getInstance(this)) {
            if (hasUserData()) {
                requestLogin(getUserData().let { RequestLoginData(it.id, it.password) })
            }
        }
    }

    private fun loginButtonEvent() {
        binding.button.setOnClickListener{
            val requestLoginData = RequestLoginData(
                    id = binding.idedit.text.toString(),
                    password = binding.passedit.text.toString()
            )
            requestLogin(requestLoginData)
        }
    }
    private fun signUpResult() {
        binding.signup.setOnClickListener {
            signUpActivityLauncher.launch(
                Intent(this, SignUpActivity::class.java)
            )
        }
    }
    private fun startHomeActivity(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun requestLogin(requestLoginData: RequestLoginData) {
        val call: Call<ResponseLoginData> = ServiceCreator.soptService
            .postLogin(requestLoginData)
        call.enqueueUtil(
            onSuccess = { response ->
                val data = response.data
                showToast(data?.user_nickname.toString())
                with(SoptUserAuthStorage.getInstance(this)) {
                    saveUserData(requestLoginData.let { SoptUserInfo(it.id, it.password) })
                }
                startHomeActivity()
            }
        )
    }
}