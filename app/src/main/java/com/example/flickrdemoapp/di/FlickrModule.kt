package com.example.flickrdemoapp.di

import com.example.data.network.FlickrApiHelper
import com.example.data.network.FlickrApiHelperImpl
import com.example.data.network.FlickrService
import com.example.data.repositories.FlickrRepositoryImpl
import com.example.domain.interfaces.FlickrRepository
import com.example.domain.usecases.FlickrUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FlickrModule {

    @Provides
    @Singleton
    fun provideFlickrService(retrofit: Retrofit): FlickrService {
        return retrofit.create(FlickrService::class.java)
    }

    @Provides
    @Singleton
    fun provideFlickrApiHelper(
        flickrService: FlickrService
    ): FlickrApiHelper {
        return FlickrApiHelperImpl(flickrService)
    }

    @Provides
    fun provideFlickrRepository(
        flickrApiHelper: FlickrApiHelper
    ): FlickrRepository {
        return FlickrRepositoryImpl(flickrApiHelper)
    }

    @Provides
    fun provideFlickrUsesCases(
        flickrRepository: FlickrRepository
    ): FlickrUseCases {
        return FlickrUseCases(flickrRepository)
    }
}