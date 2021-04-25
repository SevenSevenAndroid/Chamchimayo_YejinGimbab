package com.example.se1_hw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.se1_hw.databinding.FragmentFollowingListBinding
import com.example.se1_hw.databinding.FragmentFollowingRepoBinding

class FollowingRepoFragment : Fragment() {
    private var _binding:FragmentFollowingRepoBinding?=null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private lateinit var followingRepoAdapter: FollowingRepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingRepoBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followingRepoAdapter = FollowingRepoAdapter()

        binding.repoList.adapter = followingRepoAdapter

        followingRepoAdapter.repoList.addAll(
            listOf<FollowingRepo>(
                FollowingRepo(
                    repotext1 = "레포지터리 이름",
                    repotext2 = "레포지터리 설명",
                    repotext3 = "언어"
                ),
                FollowingRepo(
                    repotext1 = "레포지터리 이름",
                    repotext2 = "레포지터리 설명",
                    repotext3 = "언어"
                ),
                FollowingRepo(
                    repotext1 = "지금은 빈칸~!",
                    repotext2 = "레포지터리 설명1",
                    repotext3 = "언어2"
                ),
                FollowingRepo(
                    repotext1 = "지금은 빈칸!",
                    repotext2 = "레포지터리 설명2",
                    repotext3 = "언어2"
                )
            )
        )
        followingRepoAdapter.notifyDataSetChanged()
    }
}