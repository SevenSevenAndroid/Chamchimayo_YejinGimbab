package com.example.se1_hw

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.se1_hw.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        Log.d("onCreate", "onCreate(run)")
        setContentView(binding.root)

        initputResult()

    }
    private fun initputResult(){
        binding.btn.setOnClickListener{
            val userNAME = binding.nameEdit.text
            val userID = binding.idEdit.text
            val userPWD = binding.pwdEdit.text

            if (userNAME.isNullOrBlank() || userID.isNullOrBlank() || userPWD.isNullOrBlank()) {
                Toast.makeText(
                    this@SignUpActivity,
                    "빈 칸이 있는지 확인해주세요!",
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                intent.putExtra("id", userID.toString())
                intent.putExtra("pwd", userPWD.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
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