package com.example.se1_hw

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.se1_hw.databinding.ActivityMainBinding

class SignInActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.d("onCreate", "onCreate(run)")
        setContentView(binding.root)
        initButtonClickEvent()
        inittextClickEvent()
    }
    private fun initButtonClickEvent() {
        binding.button.setOnClickListener{
            val userID = binding.idedit.text
            val userPWD = binding.passedit.text
            if (userID.isNullOrBlank() || userPWD.isNullOrBlank()) {
                Toast.makeText(
                        this@SignInActivity,
                        "아이디/비밀번호를 확인해주세요!",
                        Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(
                        this@SignInActivity,
                        "로그인 성공",
                        Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
    private fun inittextClickEvent(){
        binding.signup.setOnClickListener{
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivityForResult(intent,REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                binding.idedit.setText(data!!.getStringExtra("id"))
                binding.passedit.setText(data!!.getStringExtra("password"))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("onStart", "onStart(run)")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("onRestart", "onRestart(run)")
    }
    override fun onResume() {
        super.onResume()
        Log.d("onResume", "onResume(run)")
    }
    override fun onPause() {
        super.onPause()
        Log.d("onPause", "onPause(run)")
    }
    override fun onStop() {
        super.onStop()
        Log.d("onStop", "onStop(run)")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "onDestroy(run)")
    }
}