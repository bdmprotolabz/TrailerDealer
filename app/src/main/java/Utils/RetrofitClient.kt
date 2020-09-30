package Utils

import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

//import constants.ApiConstants;
//import okhttp3.logging.HttpLoggingInterceptor;

class RetrofitClient () {
    private var retrofit: Retrofit? = null
    var Base_URL = "https://phpstack-102119-1169738.cloudwaysapps.com/API/"
    //live base url
    // public static String Base_URL = "https://admin.theinfrazone.com/api/";


    /*  Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(1);
            dispatcher.cancelAll();*///     .dispatcher(dispatcher)
    /*     Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();*/ val client: Retrofit?
        get() {
            if (retrofit == null) {

                val client = OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.MINUTES)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl(Base_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

    //get retrofit instance
    /*
    public static Retrofit getRestServiceGoogle() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.GOOGLE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
        return retrofit;
    }
*/


    //get OkHttp instance
    /*  public static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(httpLoggingInterceptor);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        return builder.build();
    }*/
    internal class OkHttpClientExt : OkHttpClient() {

        override fun newCall(request: Request): Call {
            val requestBuilder = request.newBuilder()
            requestBuilder.tag(TAG_CALL)
            return super.newCall(requestBuilder.build())
        }

        companion object {
            val TAG_CALL = Any()
        }
    }


}
