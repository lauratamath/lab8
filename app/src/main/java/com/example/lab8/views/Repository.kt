package com.example.lab8.views


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab8.R
import com.example.lab8.api.RepositoryDataBase
import com.example.lab8.databinding.FragmentRepositoryBinding
import com.example.lab8.recycler.GitAdapter
import com.example.lab8.recycler.TopSpacingItem
import com.example.lab8.viewModel.ViewModelGit



class Repository : Fragment() {
    private lateinit var binding: FragmentRepositoryBinding
    private lateinit var gitAdapter: GitAdapter
    private lateinit var repositories: List<RepositoryDataBase>
    private lateinit var viewModel: ViewModelGit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_repository, container, false
        )

        //ViewModel
        viewModel = ViewModelProvider(activity!!).get(ViewModelGit::class.java)
        repositories = viewModel.getReps()

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


}
