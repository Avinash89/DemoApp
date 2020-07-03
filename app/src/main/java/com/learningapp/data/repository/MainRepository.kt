package com.learningapp.data.repository

import com.learningapp.data.api.ApiHelper
import com.learningapp.data.model.User
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers()
    }

}