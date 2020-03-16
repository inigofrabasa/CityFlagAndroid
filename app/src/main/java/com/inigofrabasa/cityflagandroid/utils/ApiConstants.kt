package com.inigofrabasa.cityflagandroid.utils

import com.inigofrabasa.cityflagandroid.BuildConfig

object ApiConstants {
    const val PATH_TOP_FREE_APPLICATIONS: String = "topfreeapplications/limit=${BuildConfig.LIMIT}/json"
    const val READ_TIME_OUT : Long = 20
    const val CONNECTION_TIME_OUT : Long = 20
    const val QUERY_LIMIT = "limit"

    const val STATUS_OK : Int = 200
}