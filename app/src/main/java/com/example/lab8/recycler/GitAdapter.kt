package com.example.lab8.recycler

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.lab8.R
import com.example.lab8.api.RepositoryDataBase
import kotlinx.android.synthetic.main.search_results.view.*

class GitAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var item: List<RepositoryDataBase> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AnswerViewHolder(
            LayoutInflater.from(parent.context!!).inflate(R.layout.search_results, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val actual = item.get(position)

        when(holder){
            is AnswerViewHolder -> {
                holder.bind(item.get(position))

                holder.itemView.setOnClickListener{
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${actual.repos_url}"))
                    holder.itemView.context.startActivity(intent)
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun submitList(repositoryList: List<RepositoryDataBase>){
        item = repositoryList
    }

    class AnswerViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val repository_name = itemView.repositoryName

        fun bind(rep: RepositoryDataBase){
            repository_name.setText(rep.name)
        }
    }


}