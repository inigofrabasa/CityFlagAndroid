package com.inigofrabasa.cityflagandroid.applicationDetail

import androidx.lifecycle.ViewModel
import com.inigofrabasa.cityflagandroid.data.model.Model
import com.inigofrabasa.cityflagandroid.data.repository.ApplicationsRepository

class DetailViewModel : ViewModel() {
    var entry : Model.Entry? = ApplicationsRepository.getInstance().getEntry()
}