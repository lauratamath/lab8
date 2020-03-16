package com.example.lab8.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab8.R
import com.example.lab8.api.RepositoryDataBase
import com.example.lab8.databinding.FragmentRepositoryBinding
import com.example.lab8.recycler.GitAdapter
import com.example.lab8.recycler.TopSpacingItem

/**
 * A simple [Fragment] subclass.
 */
class Repository : Fragment() {
    private lateinit var binding: FragmentRepositoryBinding
    private lateinit var gitAdapter: GitAdapter
    private lateinit var repositories: List<RepositoryDataBase>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_repository, container, false)

        //Recycler View
        val recycler = binding.RecyclerViewSeeResults

        recycler.apply {
            layoutManager = LinearLayoutManager(this.context!!)
            val topSpacingDecorator = TopSpacingItem(30)
            addItemDecoration(topSpacingDecorator)
            gitAdapter = GitAdapter()
            adapter = gitAdapter
            gitAdapter.submitList(repositories)

        }

        return binding.root
    }
    /*
    fun getRepositories() {
        GitsApi.retrofitService.getRepositories()
            .enqueue(object : Callback<List<RepositoryDataBase>> {
                override fun onFailure(call: Call<List<RepositoryDataBase>>, t: Throwable) {
                    Log.d("Error", "Not found")
                }

                override fun onResponse(
                    call: Call<List<RepositoryDataBase>>,
                    response: Response<List<RepositoryDataBase>>
                ) {
                    repositories = response.body()

                    TODO("not implemented")
                }
            })
    }
    */
}
