package uz.med.core_api.service

import retrofit2.Retrofit

interface NetworkServiceProvider {

    fun provideMoviesService(): Retrofit
}
