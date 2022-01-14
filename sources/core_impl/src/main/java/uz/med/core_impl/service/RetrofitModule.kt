package uz.med.core_impl.service

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uz.med.core_api.service.MoviesService
import javax.inject.Singleton

@Module
object RetrofitModule {
    val BASE_URL = "https://api.themoviedb.org/3/"
    val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original/"


    @Singleton
    @Provides
    fun provideRetrofitClient(
        context: Context
    ): OkHttpClient {
//        val token = BuildConfig.API_TOKEN
        val client = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .addInterceptor { chain ->
                chain.run {
                    proceed(
                        request()
                            .newBuilder()
//                            .addHeader("Authorization", "Bearer $token")
                            .build()
                    )
                }
            }
        return client.build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        converter: MoshiConverterFactory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converter)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }
}