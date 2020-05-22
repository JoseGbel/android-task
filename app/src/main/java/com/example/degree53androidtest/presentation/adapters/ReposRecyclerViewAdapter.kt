package com.example.degree53androidtest.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.degree53androidtest.R
import com.example.degree53androidtest.model.GitHubRepo
import com.example.degree53androidtest.model.GitHubRepoData
import com.example.degree53androidtest.model.SearchResponseObject
import kotlinx.android.synthetic.main.repos_card_layout.view.*

class ReposRecyclerViewAdapter(val searchResponse: SearchResponseObject,
                               val context: Context?,
                               val onRepoListener: OnRepoListener)
    : RecyclerView.Adapter<ReposRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, val onRepoListener: OnRepoListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        lateinit var repoData : GitHubRepoData
        init{
            itemView.setOnClickListener(this)
        }

        fun bindItem(gitHubRepo : GitHubRepo) {
            itemView.repo_name_hyperlink_tv.text = gitHubRepo.name
            if (gitHubRepo.description == null){
                itemView.repo_description_tv.visibility = View.GONE
            } else {
                itemView.repo_description_tv.text = gitHubRepo.description
            }
            itemView.repo_language_tv.text = gitHubRepo.language
            itemView.repo_updated_at_tv.text = "Last updated Test"

            repoData = GitHubRepoData(gitHubRepo.owner, gitHubRepo.name, gitHubRepo.forks,
               gitHubRepo.stargazers_count, gitHubRepo.watchers, gitHubRepo.open_issues)
        }

        override fun onClick(v: View?) {
            onRepoListener.onRepoClick(adapterPosition, repoData)
        }
    }

    interface OnRepoListener {
        fun onRepoClick(position : Int, repoData : GitHubRepoData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repos_card_layout, parent, false)

        return ViewHolder(view, onRepoListener)
    }

    override fun getItemCount(): Int {
        return searchResponse.items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(searchResponse.items[position])
    }
}