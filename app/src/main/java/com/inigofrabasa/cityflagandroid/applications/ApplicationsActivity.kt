package com.inigofrabasa.cityflagandroid.applications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.inigofrabasa.cityflagandroid.utils.ApiConstants.STATUS_OK
import com.inigofrabasa.cityflagandroid.R
import com.inigofrabasa.cityflagandroid.adapters.EntryAdapter
import com.inigofrabasa.cityflagandroid.applicationDetail.DetailActivity
import com.inigofrabasa.cityflagandroid.data.model.ResponseHelper
import com.inigofrabasa.cityflagandroid.databinding.ActivityMainBinding

const val TAG = "Application Name"

class ApplicationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var responseHelperObserver : Observer<ResponseHelper>

    private val viewModel: ApplicationsViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(ApplicationsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel.let {
            binding.viewModel = it
        }

        val adapter = EntryAdapter()
        adapter.setOnClickApplicationListener(object : EntryAdapter.ListenerApplicationSelected{
            override fun onClickFoundation(name: String) {
                Log.v(TAG, name)
                val intent = Intent(this@ApplicationsActivity, DetailActivity::class.java)
                startActivity(intent)
            }
        })
        binding.applicationsRecycler.adapter = adapter

        responseHelperObserver= Observer {response ->
            if(response.code != STATUS_OK ){
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.responseHelperValue.observe(this, responseHelperObserver)
        viewModel.getApplications()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.removeResponseHelperObserver(responseHelperObserver)
    }
}