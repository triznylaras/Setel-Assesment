package mobile.setel.com.restaurantlist.networking

import mobile.setel.com.restaurantlist.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class HttpClient {
    private var client: Retrofit? = null
    private var endpoint: Endpoint? = null

    companion object {
        private val mInstance: HttpClient = HttpClient()

        @Synchronized
        fun getInstance(): HttpClient {
            return mInstance
        }
    }

    init {
        buildRetrofitClient()
    }

    fun getApi(): Endpoint? {
        if (endpoint == null) {
            endpoint = client!!.create(Endpoint::class.java)
        }
        return endpoint
    }

    fun buildRetrofitClient() {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(1, TimeUnit.MINUTES)
        builder.readTimeout(1, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }

        val okHttpClient = builder.build()
        client = Retrofit.Builder()
            .baseUrl("http://setel.axzae.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        endpoint = null // clear retrofit endpoint
    }

    /**
     * get [Interceptor] with single header
     * @param headerName  [String] of header name
     *
     * @param headerValue [String] of header value
     *
     * @return Interceptor with header
     */
    private fun getInterceptorWithHeader(headerName: String, headerValue: String): Interceptor {
        val header = HashMap<String, String>()
        header.put(headerName, headerValue)
        return getInterceptorWithHeader(header)
    }

    /**
     * get [Interceptor] with multiple header

     * @param headers set header name to key and header value to value
     * *
     * @return Interceptor with headers
     */
    private fun getInterceptorWithHeader(headers: Map<String, String>): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
            for ((key, value) in headers) {
                builder.addHeader(key, value)
            }
            builder.method(original.method(), original.body())
            chain.proceed(builder.build())
        }
    }
}