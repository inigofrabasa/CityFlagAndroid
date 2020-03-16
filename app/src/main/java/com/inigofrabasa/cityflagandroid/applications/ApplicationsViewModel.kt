package com.inigofrabasa.cityflagandroid.applications

import androidx.lifecycle.*
import com.inigofrabasa.cityflagandroid.data.model.Model
import com.inigofrabasa.cityflagandroid.data.model.ResponseHelper
import com.inigofrabasa.cityflagandroid.data.repository.ApplicationsRepository

class ApplicationsViewModel : ViewModel(){
    var applicationsList = MutableLiveData<List<Model.Entry>>()

    val responseHelperValue: LiveData<ResponseHelper>
        get() = ApplicationsRepository.getInstance().responseHelper

    fun getApplications(){
        ApplicationsRepository.getInstance().getApplications().observeForever { result ->
            applicationsList.value = result.feed.entry
        }
    }

    fun removeResponseHelperObserver(observer : Observer<ResponseHelper>){
        ApplicationsRepository.getInstance().removerResponseHelperObserver(observer)
    }
}