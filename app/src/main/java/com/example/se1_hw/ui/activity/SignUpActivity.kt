package com.example.se1_hw.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.se1_hw.api.ServiceCreator
import com.example.se1_hw.data.request.RequestLoginData
import com.example.se1_hw.data.request.RequestSignUpData
import com.example.se1_hw.data.response.ResponseLoginData
import com.example.se1_hw.data.response.ResponseSignUpData
import com.example.se1_hw.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            val requestSignUpData = RequestSignUpData(
                    id = binding.idEdit.text.toString(),
                    password = binding.pwdEdit.text.toString(),
                    nickname = binding.nameEdit.text.toString(),
                    sex = "0",
                    phone = "010-7940-2825",
                    birth = "1999-05-29"
            )
            val call: Call<ResponseSignUpData> = ServiceCreator.soptService.postSignUp(requestSignUpData)
            call.enqueue(object : Callback<ResponseSignUpData> {
                override fun onResponse(
                        call: Call<ResponseSignUpData>,
                        response: Response<ResponseSignUpData>
                ){
                    if(response.isSuccessful){
                        Toast.makeText(this@SignUpActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        startLogin()
                    }else {
                        Toast.makeText(
                                this@SignUpActivity,
                                "다시 입력해주세요!",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                override fun onFailure(call: Call<ResponseSignUpData>, t:Throwable){
                    Log.d("NetworkTest", "error:$t")
                }
            })

        }
    }
    private fun startLogin(){
        val id = binding.idEdit.text.toString()
        val password = binding.pwdEdit.text.toString()
        val name = binding.nameEdit.text.toString()
        if(id.isNullOrBlank() || password.isNullOrBlank() || name.isNullOrBlank()){
            Toast.makeText(this@SignUpActivity, "빈 칸이 있는지 확인해주세요!", Toast.LENGTH_SHORT).show()
        }
        else {
            val intent = Intent()
            intent.putExtra("id", id).putExtra("password", password)
            setResult(Activity.RESULT_OK, intent)
            finish()
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