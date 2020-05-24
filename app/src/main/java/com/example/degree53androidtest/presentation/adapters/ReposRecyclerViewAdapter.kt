package com.example.degree53androidtest.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.degree53androidtest.R
import com.example.degree53androidtest.data.models.GitHubRepo
import com.example.degree53androidtest.data.models.RepoDetails
import com.example.degree53androidtest.data.models.SearchResponse
import com.example.degree53androidtest.utils.dateConvert
import kotlinx.android.synthetic.main.repos_card_layout.view.*

class ReposRecyclerViewAdapter(private val searchResponse: SearchResponse,
                               private val context: Context?,
                               private val onRepoListener: OnRepoListener)
    : RecyclerView.Adapter<ReposRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View,
                     private val onRepoListener: OnRepoListener,
                     private val context: Context?
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private lateinit var repoDetails : RepoDetails

        init{
            itemView.setOnClickListener(this)
        }

        fun bindItem(gitHubRepo : GitHubRepo) {

            // bind data to view
            if (gitHubRepo.description == null){
                itemView.repo_description_tv.visibility = View.GONE
            } else {
                itemView.repo_description_tv.text = gitHubRepo.description
            }
            itemView.repo_full_name_tv.text = gitHubRepo.full_name
            itemView.repo_language_tv.text = gitHubRepo.language
            itemView.repo_updated_at_tv.text = context!!
                .getString(R.string.last_updated, gitHubRepo.updated_at.dateConvert())

            // keep a reference of the rest of the details for future processing
            repoDetails =
                RepoDetails(
                    gitHubRepo.owner, gitHubRepo.name, gitHubRepo.forks,
                    gitHubRepo.stargazers_count, gitHubRepo.watchers, gitHubRepo.open_issues
                )
        }

        override fun onClick(v: View?) {
            onRepoListener.onRepoClick(adapterPosition, repoDetails)
        }
    }

    interface OnRepoListener {
        fun onRepoClick(position : Int, repoData : RepoDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repos_card_layout, parent, false)

        return ViewHolder(view, onRepoListener, context)
    }

    override fun getItemCount(): Int {
        return searchResponse.items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(searchResponse.items[position])
    }
}