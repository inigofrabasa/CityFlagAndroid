package com.inigofrabasa.cityflagandroid.data.repository

import com.inigofrabasa.cityflagandroid.ApiConstants
import com.inigofrabasa.cityflagandroid.data.model.Model
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET(ApiConstants.PATH_TOP_FREE_APPLICATIONS)
    suspend fun getRemoteAllApplications(@Query(ApiConstants.QUERY_LIMIT) limit: Int): retrofit2.Response<Model.Applications>
}