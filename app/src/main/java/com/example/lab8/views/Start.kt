package com.example.lab8.views


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.lab8.R
import com.example.lab8.api.GitsApi
import com.example.lab8.api.UserDataBase
import com.example.lab8.databinding.FragmentStartBinding
import kotlinx.android.synthetic.main.fragment_start.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class Start : Fragment() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_start, container, false)
        //ViewModel
        //viewModelResults = ViewModelProvider(activity!!).get(ResultsViewModel::class.java)
        binding.searchUser.setOnClickListener{
            searchUser()
        }

        return binding.root
    }

    private fun searchUser(){
        //Get answer from edit text
        var lastUser = githubUser.getText().toString()

        GitsApi.retrofitService.getUser(lastUser).enqueue(
            object : Callback<UserDataBase> {

            override fun onFailure(call: Call<UserDataBase>, t: Throwable) {
                Log.d("Error", "Not found")
            }

            override fun onResponse(call: Call<UserDataBase>, response: Response<UserDataBase>) {
                lastUser = response.body()!!.login
                binding.userName.text = response.body()?.login

                Glide.with(context!!)
                    .load(response.body()?.avatar_url)
                    .into(binding.imageViewAvatar)
            }

        })
        //If user exists
        if (lastUser != ""){
            //Shows avatar and button
            binding.imageViewAvatar.setVisibility(View.VISIBLE)
            binding.seeRepositories.setVisibility(View.VISIBLE)
        } else {
            //Shows toast with error, set visibility of avatar and button to gone
            Toast.makeText(activity, "$lastUser was not found", Toast.LENGTH_SHORT).show()
            binding.imageViewAvatar.setVisibility(View.GONE)
            binding.seeRepositories.setVisibility(View.GONE)
        }
    }
}
