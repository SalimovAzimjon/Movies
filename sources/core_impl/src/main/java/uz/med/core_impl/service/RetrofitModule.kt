package uz.med.core_impl.service

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import uz.med.core_impl.BuildConfig
import javax.inject.Singleton


@Module
object RetrofitModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("okhttp").i(message)
            }
        }).also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideRetrofitClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val token = BuildConfig.API_KEY
        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                chain.run {
                    proceed(
                        request()
                            .newBuilder()
                            .addHeader("Authorization", "Bearer $token")
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


}
