package com.example.se1_hw.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.se1_hw.R
import com.example.se1_hw.databinding.ActivityRepoInfoBinding
import com.example.se1_hw.ui.fragment.FollowingRepoFragment

class RepoInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val followingRepoFragment = FollowingRepoFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.repo_info_fragment,followingRepoFragment)
        transaction.commit()
    }
}