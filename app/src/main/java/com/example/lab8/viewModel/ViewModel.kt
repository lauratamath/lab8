package com.example.lab8.viewModel

import androidx.lifecycle.ViewModel
import com.example.lab8.api.RepositoryDataBase

class ViewModelGit :  ViewModel(){
    var user_name = ""
    var repositories: List<RepositoryDataBase> = ArrayList()

    fun setUserName(user: String){
        this.user_name = user
    }

    fun getUserName():String{
        return user_name
    }

    fun setReps(reps: List<RepositoryDataBase>){
        repositories = reps
    }

    fun getReps(): List<RepositoryDataBase>{
        return repositories
    }
}