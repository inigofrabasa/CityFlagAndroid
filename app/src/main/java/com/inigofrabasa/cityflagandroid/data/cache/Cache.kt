package com.inigofrabasa.cityflagandroid.data.cache

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.inigofrabasa.cityflagandroid.data.model.Model

class Cache {
    var applications : MutableLiveData<Model.Applications>? = MutableLiveData()
    var entry : Model.Entry? = null

    fun removeObserver(observer : Observer<Model.Applications>){
        applications?.removeObserver(observer)
    }

    companion object {
        @Volatile private var instance: Cache? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Cache().also { instance = it }
            }
    }
}