package com.inigofrabasa.cityflagandroid.applications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.inigofrabasa.cityflagandroid.ApiConstants.STATUS_OK
import com.inigofrabasa.cityflagandroid.R
import com.inigofrabasa.cityflagandroid.adapters.EntryAdapter
import com.inigofrabasa.cityflagandroid.data.model.Model
import com.inigofrabasa.cityflagandroid.data.model.ResponseHelper
import com.inigofrabasa.cityflagandroid.databinding.ActivityMainBinding

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
            override fun onClickFoundation(application: Model.Entry) {
                Toast.makeText(this@ApplicationsActivity, application.name.label, Toast.LENGTH_SHORT).show()
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