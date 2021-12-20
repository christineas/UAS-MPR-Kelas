package com.example.submission2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.*
import kotlin.collections.ArrayList

class ListGitUserAdapter(private var listData: ArrayList<GitUser>) : RecyclerView.Adapter<ListGitUserAdapter.ListDataHolder>() {

    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagePhoto: CircleImageView = itemView.git_photo
        var name: TextView = itemView.git_name
        var username: TextView = itemView.git_username
        var follower: TextView = itemView.git_follower
        var following: TextView = itemView.git_following
    }

    private lateinit var mcontext: Context
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(gitUser: GitUser)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListDataHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false)
        val sch = ListDataHolder(view)
        mcontext = viewGroup.context
        return sch
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        val data = listData[position]
        Glide.with(holder.itemView.context)
            .load(data.photo)
            .apply(RequestOptions().override(250, 250))
            .into(holder.imagePhoto)
        holder.name.text = data.name
        holder.username.text = data.username
        holder.follower.text = data.followers.toString().trim()
        holder.following.text = data.following.toString().trim()
        holder.itemView.setOnClickListener {
            val gitUser = GitUser(
                data.username,
                data.name,
                data.photo,
                data.company,
                data.location,
                data.repository,
                data.followers,
                data.following
            )
            val intentDetail = Intent(mcontext, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.EXTRA_DATA, gitUser)
            mcontext.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listData.size
}