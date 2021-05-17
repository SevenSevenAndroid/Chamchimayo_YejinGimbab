package com.example.se1_hw.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.se1_hw.api.ServiceCreator
import com.example.se1_hw.data.request.RequestLoginData
import com.example.se1_hw.data.response.ResponseLoginData
import com.example.se1_hw.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.d("onCreate", "onCreate(run)")
        setContentView(binding.root)
        initButtonClickEvent()
        initTextClickEvent()
    }
    private fun initButtonClickEvent() {
        binding.button.setOnClickListener{
            val requestLoginData = RequestLoginData(
                    id = binding.idedit.text.toString(),
                    password = binding.passedit.text.toString()
            )
            val call: Call<ResponseLoginData> = ServiceCreator.soptService.postLogin(requestLoginData)
            call.enqueue(object : Callback<ResponseLoginData> {
                override fun onResponse(
                        call: Call<ResponseLoginData>,
                        response: Response<ResponseLoginData>
                ){
                    if(response.isSuccessful){
                        val data = response.body()?.data
                        Toast.makeText(this@SignInActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                        startHomeActivity()
                    }else {
                        Toast.makeText(
                                this@SignInActivity,
                                "아이디/비밀번호를 확인해주세요!",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                override fun onFailure(call: Call<ResponseLoginData>, t:Throwable){
                    Log.d("NetworkTest", "error:$t")
                }
            })

        }
    }
    private fun startHomeActivity(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
    private fun initTextClickEvent(){
        binding.signup.setOnClickListener{
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                binding.idedit.setText(data!!.getStringExtra("id"))
                binding.passedit.setText(data!!.getStringExtra("password"))
            }
        }
    }

    companion object{
        private const val REQUEST_CODE = 100
    }


}