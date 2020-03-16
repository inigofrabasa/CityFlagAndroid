package com.inigofrabasa.cityflagandroid

import com.inigofrabasa.cityflagandroid.applications.ApplicationsViewModel
import com.inigofrabasa.cityflagandroid.data.model.Model
import com.inigofrabasa.cityflagandroid.data.repository.ServiceApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.initMocks

class ApiTest{
    @Mock
    lateinit var serviceApi: ServiceApi
    lateinit var applicationsViewModel: ApplicationsViewModel

    @Before
    fun setUp() {
        initMocks(this)
        applicationsViewModel = ApplicationsViewModel()
    }

    @Test
    fun testApi(){
        GlobalScope.launch {
            Mockito.`when`(serviceApi.getRemoteAllApplicationsTest(10)).thenReturn(Model.Applications(Model.Feed(mutableListOf())))
        }
    }
}