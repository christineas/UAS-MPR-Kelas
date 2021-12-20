package com.example.submission2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private var title: String = "Github User Detail"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setActionBarTitle(title)
        setData()
        viewPagerConfig()
    }

    private fun setData() {
        val gitUsers = intent.getParcelableExtra<Parcelable>(EXTRA_DATA) as GitUser
        detail_name.text = gitUsers.name.toString()
        detail_username.text = gitUsers.username.toString()
        detail_company.text = gitUsers.company.toString()
        detail_location.text = gitUsers.location.toString()
        detail_repository.text = gitUsers.repository.toString()
        detail_follower.text = gitUsers.followers.toString()
        detail_following.text = gitUsers.following.toString()
        Glide.with(this)
                .load(gitUsers.photo.toString())
                .into(detail_photo)
    }

    private fun viewPagerConfig() {
        val viewPagerDetailAdapter = ViewPagerDetailAdapter(this, supportFragmentManager)
        view_pager.adapter = viewPagerDetailAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar!!.title = title
        }
    }
}