package com.example.githubusers

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.githubusers.databinding.SingleUserBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.StringBuilder

class SingleUserFragment: Fragment()  {
    private lateinit var binding: SingleUserBinding
    private val viewModel: UsersListViewModel by viewModels()

    companion object{
        const val LOGIN = "LOGIN"
        fun newIntent(login: String): SingleUserFragment{
            val fragment = SingleUserFragment()
            val arguments = Bundle()
            arguments.putString(LOGIN, login)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SingleUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val login = this.arguments?.getString(LOGIN)
        println("login=$login")
        binding.title.text = getString(R.string.name, getString(R.string.loading))
        binding.subtitle.text = getString(R.string.email, getString(R.string.loading))
        if(login!=null) {
            val getSingleUserDate = viewModel.getSingleUserDate(login)
            getSingleUserDate
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {result ->
                        Glide
                            .with(this)
                            .load(result.avatarUrl)
                            .into(binding.avatar)
                        binding.title.text = getString(R.string.name, result.name)
                        binding.subtitle.text = getString(R.string.email, result.email)
                        val textBuilder = StringBuilder()
                        textBuilder
                            .appendLine(getString(R.string.company, result.company))
                            .appendLine(getString(R.string.followingCount, result.following.toString()))
                            .appendLine(getString(R.string.followersCount, result.followers.toString()))
                            .appendLine(getString(R.string.createdAt, result.createdAt))
                        binding.text.text = textBuilder.toString()
                    },
                    { error ->
                        Log.e("UserListViewModel", error.toString())
                    }
                )
        }
    }

}