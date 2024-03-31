package com.example.githubusers

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefreshLayout.isRefreshing = true
        binding.usersList.layoutManager = LinearLayoutManager(requireContext())

        setUsersDate()
        binding.swipeRefreshLayout.setOnRefreshListener{
            setUsersDate()
        }
    }

    @SuppressLint("CheckResult")
    private fun setUsersDate() {
        val getUsersDate = viewModel.getUsersDate()
        getUsersDate
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println("getData=$it")
                binding.usersList.adapter = Adapter(it, requireContext()) {
                    (activity as? MainActivity)?.addFragment(SingleUserFragment.newIntent(it.login))
                }
                binding.swipeRefreshLayout.isRefreshing = false
            }, { error ->
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
                Log.e("UsersListViewModel", error.toString())
                binding.swipeRefreshLayout.isRefreshing = false
            })
    }
}