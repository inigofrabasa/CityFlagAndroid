package com.inigofrabasa.cityflagandroid.data.repository

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.inigofrabasa.cityflagandroid.BuildConfig
import com.inigofrabasa.cityflagandroid.CityFlagApplication
import com.inigofrabasa.cityflagandroid.utils.ApiConstants.STATUS_OK
import com.inigofrabasa.cityflagandroid.data.cache.Cache
import com.inigofrabasa.cityflagandroid.data.database.AppDatabase
import com.inigofrabasa.cityflagandroid.data.model.Model
import com.inigofrabasa.cityflagandroid.data.model.ResponseHelper
import kotlinx.coroutines.*

class ApplicationsRepository{
    private var viewModelJob = Job()
    private var cScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    var responseHelper : MutableLiveData<ResponseHelper> = MutableLiveData()

    fun getApplications() : LiveData<Model.Applications> {

        if(Cache.getInstance().applications?.value == null){
            if(verifyAvailableNetwork()){
                cScope.launch {
                    try {
                        val response = Service.retrofitService.getRemoteAllApplications(BuildConfig.LIMIT.toInt())

                        val responseHelperValue = ResponseHelper(
                            code = response.code(),
                            message = response.message(),
                            headers = response.headers(),
                            isSuccessful = response.isSuccessful,
                            responseBody = response.errorBody())

                        when(responseHelperValue.code){
                            STATUS_OK -> {
                                Cache.getInstance().applications?.postValue(response.body())

                                val entriesList = mutableListOf<Model.RoomEntry>()
                                response.body()?.feed!!.entry.map {
                                    entriesList.add(Model.RoomEntry(it.id.label, it.name.label, it.summary.label, it.image[0].label))
                                }

                                RoomRepository.getInstance(AppDatabase.getInstance(
                                    CityFlagApplication.instance.appContext)
                                    .entryDao()).insertEntries(entriesList)
                            }
                        }

                        responseHelper.postValue(responseHelperValue)

                    } catch (t: Throwable) {
                        t.printStackTrace()
                    }
                }
            } else {
                RoomRepository.getInstance(AppDatabase.getInstance(
                    CityFlagApplication.instance.appContext)
                    .entryDao()).getEntriesDataBase().observeForever{list ->
                        cScope.launch {
                            if(list.isNotEmpty()){
                                val entries = mutableListOf<Model.Entry>()
                                val feed = Model.Feed(entries)
                                list.map {
                                    entries.add(Model.Entry(Model.Id(it.id), Model.Name(it.name), listOf(Model.Image(it.image)), Model.Summary(it.summary)))
                                }
                                Cache.getInstance().applications?.postValue(Model.Applications(feed))
                            }
                        }
                }
            }
        }

        return Cache.getInstance().applications!!
    }

    fun removerResponseHelperObserver(observer : Observer<ResponseHelper>){
        responseHelper.removeObserver(observer)
    }

    fun getEntry() : Model.Entry?{
        return Cache.getInstance().entry
    }

    fun setEntry(entry : Model.Entry){
        Cache.getInstance().entry = entry
    }

    private fun verifyAvailableNetwork():Boolean{
        val connectivityManager = CityFlagApplication.instance.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }

    companion object {
        @Volatile private var instance: ApplicationsRepository? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: ApplicationsRepository().also { instance = it }
            }
    }
}