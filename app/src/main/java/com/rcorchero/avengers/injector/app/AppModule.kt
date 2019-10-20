package com.rcorchero.avengers.injector.app

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.rcorchero.avengers.BuildConfig
import com.rcorchero.avengers.R
import com.rcorchero.avengers.data.AvengersRepositoryImpl
import com.rcorchero.avengers.data.source.local.AvengersDatabaseHelper
import com.rcorchero.avengers.data.source.local.LocalDataSource
import com.rcorchero.avengers.data.source.local.LocalDataSourceImpl
import com.rcorchero.avengers.data.source.remote.APIService
import com.rcorchero.avengers.data.source.remote.RemoteDataSource
import com.rcorchero.avengers.data.source.remote.RemoteDataSourceImpl
import com.rcorchero.avengers.domain.repository.AvengersRepository
import com.rcorchero.avengers.platform.Navigator
import com.rcorchero.avengers.platform.widget.SpinnerLoading
import com.rcorchero.avengers.platform.widget.SpinnerLoadingImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context =
        application

    @Provides
    @Singleton
    fun provideNavigator(): Navigator = Navigator()

    @Provides
    fun provideSpinnerLoading(): SpinnerLoading = SpinnerLoadingImpl()

    @Provides
    @Singleton
    internal fun provideSQLiteOpenHelper(context: Context): SQLiteOpenHelper =
        AvengersDatabaseHelper(context)

    @Provides
    @Singleton
    @Named("socketTimeout")
    fun provideSocketTimeout(context: Application): Int = context.resources.getInteger(R.integer.socketTimeout)

    @Provides
    @Singleton
    @Named("connectionTimeout")
    fun provideConnectionTimeout(context: Application): Int = context.resources.getInteger(R.integer.connectionTimeout)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = BuildConfig.LEVEL_LOGS
        return interceptor
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor,
                                     @Named("socketTimeout") socketTimeout: Int,
                                     @Named("connectionTimeout") connectionTimeout: Int): OkHttpClient {
        val clientBuilder = OkHttpClient().newBuilder()
        clientBuilder.readTimeout(socketTimeout.toLong(), TimeUnit.SECONDS)
        clientBuilder.connectTimeout(connectionTimeout.toLong(), TimeUnit.SECONDS)
        clientBuilder.addInterceptor(loggingInterceptor)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofitClient(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

    @Provides
    @Singleton
    internal fun provideLocalDataSource(sqLiteOpenHelper: SQLiteOpenHelper): LocalDataSource =
        LocalDataSourceImpl(sqLiteOpenHelper)

    @Provides
    @Singleton
    internal fun provideRemoteDataSource(apiService: APIService): RemoteDataSource =
        RemoteDataSourceImpl(apiService)

    @Provides
    @Singleton
    internal fun provideRepository(localDataSource: LocalDataSource,
                                   remoteDataSource: RemoteDataSource): AvengersRepository =
        AvengersRepositoryImpl(localDataSource, remoteDataSource)
}