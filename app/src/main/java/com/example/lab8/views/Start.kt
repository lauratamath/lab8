package com.example.lab8.views


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.lab8.R
import com.example.lab8.api.GitsApi
import com.example.lab8.api.RepositoryDataBase
import com.example.lab8.api.UserDataBase
import com.example.lab8.databinding.FragmentStartBinding
import com.example.lab8.viewModel.ViewModelGit
import kotlinx.android.synthetic.main.fragment_start.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class Start : Fragment() {
    private lateinit var binding: FragmentStartBinding
    private lateinit var viewModel: ViewModelGit
    private lateinit var repositories: List<RepositoryDataBase>
    private var lastUser = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_start, container, false)
        //
        binding.imageViewAvatar.setVisibility(View.GONE)
        binding.userName.setVisibility(View.GONE)
        binding.seeRepositories.setVisibility(View.GONE)
        //ViewModel
        viewModel = ViewModelProvider(activity!!).get(ViewModelGit::class.java)



        binding.searchUser.setOnClickListener{
            searchUser()
            showUser()
            getReps()
        }

        binding.seeRepositories.setOnClickListener{
            view!!.findNavController().navigate(R.id.action_start2_to_repository)
        }

        return binding.root
    }

    private fun searchUser(){
        lastUser = githubUser.getText().toString()

        GitsApi.retrofitService.getUser(lastUser).enqueue(
            object : Callback<UserDataBase> {

            override fun onFailure(call: Call<UserDataBase>, t: Throwable) {
                Log.d("Error", "Not found")
            }

            override fun onResponse(call: Call<UserDataBase>, response: Response<UserDataBase>) {
                if (response.body()?.login != null) {
                    lastUser = response.body()!!.login
                    viewModel.setUserName(lastUser)
                    binding.userName.text = response.body()?.login

                    Glide.with(context!!)
                        .load(response.body()?.avatar_url)
                        .into(binding.imageViewAvatar)
                } else {
                    Toast.makeText(activity, "$lastUser was not found", Toast.LENGTH_SHORT).show()
                    lastUser = ""
                }

            }

        })



    }

    fun showUser(){
        //If user exists
        if (lastUser != ""){
            //Shows avatar and button
            binding.imageViewAvatar.setVisibility(View.VISIBLE)
            binding.seeRepositories.setVisibility(View.VISIBLE)
            binding.userName.setVisibility(View.VISIBLE)
        } else {
            //Shows toast with error, set visibility of avatar and button to gone
            binding.imageViewAvatar.setVisibility(View.GONE)
            binding.userName.setVisibility(View.GONE)
            binding.seeRepositories.setVisibility(View.GONE)
        }
    }
    fun getReps() {
        GitsApi.retrofitService.getRepositories(lastUser).enqueue(object : retrofit2.Callback<List<RepositoryDataBase>> {
            override fun onFailure(call: Call<List<RepositoryDataBase>>, t: Throwable) {
                Log.d("Error", "Not found")
            }

            override fun onResponse(call: Call<List<RepositoryDataBase>>, response: Response<List<RepositoryDataBase>>) {
                if (response.body() != null){
                    viewModel.setReps(response.body()!!)
                    Log.d("tamano rep", response.body()?.size.toString())
                    Log.d("tamano rep", viewModel.repositories.size.toString())
                }
            }

        })
    }

}
