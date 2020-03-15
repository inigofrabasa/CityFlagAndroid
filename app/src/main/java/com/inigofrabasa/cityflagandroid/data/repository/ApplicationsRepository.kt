package com.inigofrabasa.cityflagandroid.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.inigofrabasa.cityflagandroid.ApiConstants.STATUS_OK
import com.inigofrabasa.cityflagandroid.data.cache.Cache
import com.inigofrabasa.cityflagandroid.data.model.Model
import com.inigofrabasa.cityflagandroid.data.model.ResponseHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ApplicationsRepository {
    private var viewModelJob = Job()
    private var cScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    var responseHelper : MutableLiveData<ResponseHelper> = MutableLiveData()

    fun getApplications() : LiveData<Model.Applications> {

        if(Cache.getInstance().applications?.value == null){
            cScope.launch {
                try {
                    val response = Service.retrofitService.getRemoteAllApplications(20)

                    val responseHelperValue = ResponseHelper(
                        code = response.code(),
                        message = response.message(),
                        headers = response.headers(),
                        isSuccessful = response.isSuccessful,
                        responseBody = response.errorBody())

                    when(responseHelperValue.code){
                        STATUS_OK -> {
                            Cache.getInstance().applications?.postValue(response.body())
                        }
                    }

                    responseHelper.postValue(responseHelperValue)

                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            }
        }

        return Cache.getInstance().applications!!
    }

    fun removerResponseHelperObserver(observer : Observer<ResponseHelper>){
        responseHelper.removeObserver(observer)
    }

    companion object {
        @Volatile private var instance: ApplicationsRepository? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: ApplicationsRepository().also { instance = it }
            }
    }
}