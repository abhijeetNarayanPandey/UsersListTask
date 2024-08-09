package com.cheezycode.daggermvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import com.cheezycode.daggermvvm.viewmodels.UserViewModel


import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheezycode.daggermvvm.R
import com.cheezycode.daggermvvm.adapters.UsersAdapter
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UserViewModel
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView
        userRecyclerView = findViewById(R.id.usersList)
        userRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize Adapter
        usersAdapter = UsersAdapter(emptyList())
        userRecyclerView.adapter = usersAdapter


        // Initialize vieModel
        usersViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // Observe LiveData from ViewModel
        usersViewModel.users.observe(this, Observer { users ->
            // Update the adapter with the new list of users
            usersAdapter = UsersAdapter(users)
            userRecyclerView.adapter = usersAdapter
        })
    }
}


















