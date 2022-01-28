package scylla.task.scyllatask.Network

import okhttp3.Interceptor
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object {
        val BASE_URL = "https://api.faat.link/"
        var tok ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImNmNWVmMmU3LWI3ZGEtNDFmOS1hZjU0LTZlYmMxNGE0ZTAwMSIsInNhbHQiOiJFYkpyZXAiLCJ0eXBlIjoidXNlciIsImlhdCI6MTY0MzIwNzQ4Mn0.ivRC63gFnXofRhgsdMfLp0FCcKWLZulSVcGIi9EazUM"

        private val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .addInterceptor(OAuthInterceptor("Bearer", tok))
            .build()




        var retrofit: Retrofit? = null

        fun getClient(): Retrofit {
            when (retrofit) {
                null -> retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit as Retrofit
        }
    }

}

class OAuthInterceptor(private val tokenType: String, private val accessToken: String) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "$tokenType $accessToken").build()

        return chain.proceed(request)
    }
}