package com.learningapp.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.learningapp.R
import com.learningapp.data.api.ApiHelper
import com.learningapp.data.api.ApiServiceImpl
import com.learningapp.data.model.User
import com.learningapp.ui.base.ViewModelFactory
import com.learningapp.ui.main.adapter.ListAdapter
import com.learningapp.ui.main.viewmodel.MainViewModel
import com.learningapp.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var listAdapter: ListAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        setUpViewModel()
        setUpObserver()
    }

    private fun initUI() {
        rv_list.layoutManager = LinearLayoutManager(this)
        listAdapter = ListAdapter(arrayListOf())
        rv_list.addItemDecoration(
            DividerItemDecoration(
                rv_list.context,
                (rv_list.layoutManager as LinearLayoutManager).orientation
            )
        )
        rv_list.adapter = listAdapter
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProviders.of(this, ViewModelFactory(ApiHelper(ApiServiceImpl())))
            .get(MainViewModel::class.java)
    }

    private fun setUpObserver() {
        mainViewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progress_list.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    rv_list.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progress_list.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    progress_list.visibility = View.VISIBLE
                    rv_list.visibility = View.GONE
                }
            }
        })

    }

    private fun renderList(users: List<User>) {
        listAdapter.addAll(users)
        listAdapter.notifyDataSetChanged()
    }

}