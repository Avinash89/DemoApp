package com.learningapp.data.api

import com.learningapp.data.model.User
import io.reactivex.Single

interface ApiService {

    fun getUsers(): Single<List<User>>

}