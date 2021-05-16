package com.example.se1_hw.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.se1_hw.R
import com.example.se1_hw.databinding.ActivityUserInfoBinding
import com.example.se1_hw.ui.fragment.FollowingListFragment

class UserInfoActivity:AppCompatActivity() {
    private lateinit var binding: ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val followingListFragment = FollowingListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.user_info_fragment,followingListFragment)
        transaction.commit()
    }
}