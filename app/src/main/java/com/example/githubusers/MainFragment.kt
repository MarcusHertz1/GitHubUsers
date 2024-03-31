package com.example.githubusers

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.databinding.MainFragmentLayoutBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentLayoutBinding

    private val viewModel: UsersListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.usersList.layoutManager = LinearLayoutManager(requireContext())

        val getUsersDate = viewModel.getUsersDate()
        getUsersDate
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println("getData=$it")
                binding.usersList.adapter = Adapter(it, requireContext()){
                    (activity as? MainActivity)?.addFragment(SingleUserFragment.newIntent(it.login))
                }
            }, { error ->
                Log.e("UserListViewModel", error.toString())
            })
    }

}