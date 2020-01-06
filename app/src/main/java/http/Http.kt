package http


import db.User
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import org.json.JSONObject
import org.reactivestreams.Subscriber
import xui.getList
import xui.getObject
import xui.toJson
import java.io.IOException
import java.net.ConnectException


import java.util.concurrent.TimeUnit


object Http {

    private val MEDIA_TYPE = MediaType.parse("application/octet-stream")
    val MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8")
    private val TAG = "zjt"
    private val CODE_OK = 200
    var mClient: OkHttpClient = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
            //                    .addInterceptor(appIntercepter)
            .build()




    inline  fun <reified T: Any > returnPojo(api: String, jsonObject: Any): Single<T> {
        return Single.create<T>({
            val requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonObject.toJson())
            val request = Request.Builder().url(Api.URL_ROOT + api).post(requestBody).build()
            val re = mClient.newCall(request).execute()
            if (re.isSuccessful) {
                val res = re.body()?.string()
                val json = JSONObject(res)
                when (json.getInt("Code")) {
                    0 -> {
                        val jo= json.get("Content").toString()
                        val a= jo.getObject <T>()
                        it.onSuccess(a)
                    }
                    else -> {
                        throw Throwable("${json.getString("Msg")}")
                    }
                }

            } else {
                it.onError(Throwable("网络出错"))
            }

        }).io_main()


    }

    inline fun <reified T : Any> returnList(api: String, jsonObject: Any): Single<List<T>> {

        return Single.create<List<T>>({
            val requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonObject.toJson())
            val request = Request.Builder().url(Api.URL_ROOT + api).post(requestBody).build()

            val re = mClient.newCall(request).execute()
            if (re.isSuccessful) {
                val res = re.body()?.string()
                val json = JSONObject(res)
                when (json.getInt("Code")) {
                    0 -> {
                        val jo= json.get("Content").toString()
                        val data= jo.getList<T>()

                      it.onSuccess(data!! )
                    }
                    else -> {
                        throw Throwable("${json.getString("Msg")}")
                    }
                }

            } else {
                it.onError(Throwable("网络出错"))
            }

        })

                .io_main()


    }


    inline fun   test(api: String, jsonObject: Any): Single<String> {

        return Single.create<String>({
            val requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonObject.toJson())
            val request = Request.Builder().url(Api.URL_ROOT + api).post(requestBody).build()

            val re = mClient.newCall(request).execute()
            if (re.isSuccessful) {
                val res = re.body()!!.string()
                it.onSuccess(res )

            } else {
                it.onError(Throwable("网络出错"))
            }

        })

                .io_main()


    }



//    fun postForm(api: String, parms: String, map: Map<String, Any>, files: List<File>): Observable<Res> {
//        return Observable.create(object : Observable.OnSubscribe<Res>() {
//            fun call(subscribe: Subscriber<in Res>) {
//                try {
//                    /* form的分割线,自己定义 */
//                    val boundary = "xx--------------------------------------------------------------xx"
//
//                    val builder = MultipartBody.Builder(boundary).setType(MultipartBody.FORM)
//                    //                    for (String key : map.keySet()) {
//                    //                        builder.addFormDataPart(key, map.get(key).toString());
//                    //                    }
//
//                    builder.addFormDataPart(parms, JsonUtil.toJson(map))
//
//                    for (i in files.indices) {
//                        builder.addFormDataPart("file" + i, files[i].name, RequestBody.create(MEDIA_TYPE, files[i]))
//                    }
//
//                    val request = Request.Builder().url(Api.getUrlRoot() + api).post(builder.build()).build()
//                    val call = getClient().newCall(request)
//                    val response = call.execute()
//                    if (response.isSuccessful()) {
//                        if (!subscribe.isUnsubscribed()) {
//                            val string = response.body().string()
//                            val res = JsonUtil.getObject(string, Res::class.java)
//                            if (res.getCode() === CODE_OK) {
//                                subscribe.onNext(res)
//                                subscribe.onCompleted()
//                            } else {
//                                subscribe.onError(Throwable("请求出错"))
//
//                            }
//                        }
//
//                    } else {
//                        subscribe.onError(Throwable("请求出错"))
//
//                    }
//
//                } catch (ex: ConnectException) {
//                    if (!subscribe.isUnsubscribed()) {
//                        subscribe.onError(ex)
//
//                    }
//                } catch (e: IOException) {
//                    if (!subscribe.isUnsubscribed()) {
//                        subscribe.onError(e)
//
//                    }
//                }
//
//            }
//
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//
//
//    }

//    fun postFormWithJson(api: String, parms: String, o: Any, files: Map<String, File>): Observable<Res> {
//        return Observable.create(object : Observable.OnSubscribe<Res>() {
//            fun call(subscribe: Subscriber<in Res>) {
//                try {
//                    /* form的分割线,自己定义 */
//                    val boundary = "xx--------------------------------------------------------------xx"
//
//                    val builder = MultipartBody.Builder(boundary).setType(MultipartBody.FORM)
//
//
//                    builder.addFormDataPart(parms, JsonUtil.toJson(o))
//
//
//                    for (key in files.keys) {
//                        builder.addFormDataPart(key, files[key].getName(), RequestBody.create(MEDIA_TYPE, files[key]))
//                    }
//
//                    val request = Request.Builder().url(Api.getUrlRoot() + api).post(builder.build()).build()
//                    val call = getClient().newCall(request)
//                    val response = call.execute()
//                    if (response.isSuccessful()) {
//                        if (!subscribe.isUnsubscribed()) {
//                            val string = response.body().string()
//                            val res = JsonUtil.getObject(string, Res::class.java)
//                            if (res.getCode() === CODE_OK) {
//                                subscribe.onNext(res)
//
//                            } else {
//                                subscribe.onError(Throwable("请求出错"))
//
//                            }
//                        }
//
//                    } else {
//                        subscribe.onError(Throwable("请求出错"))
//
//                    }
//
//                } catch (ex: ConnectException) {
//                    if (!subscribe.isUnsubscribed()) {
//                        subscribe.onError(ex)
//
//                    }
//                } catch (e: IOException) {
//                    if (!subscribe.isUnsubscribed()) {
//                        subscribe.onError(e)
//
//                    }
//                }
//
//            }
//
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//
//
//    }




//    private fun dataObservable(api: String, `object`: Any): Observable<Res> {
//        return Observable.create(object : Observable.OnSubscribe<Res>() {
//            fun call(subscribe: Subscriber<in Res>) {
//
//                val requestBody = RequestBody.create(MEDIA_TYPE_JSON, JsonUtil.toJson(`object`))
//                val request = Request.Builder().url(Api.getUrlRoot() + api).post(requestBody).build()
//                val call = getClient().newCall(request)
//                call.enqueue(object : Callback() {
//                    fun onFailure(call: Call, e: IOException) {
//                        if (!subscribe.isUnsubscribed()) {
//
//                            subscribe.onError(Throwable("请求出错"))
//                        }
//                    }
//
//                    @Throws(IOException::class)
//                    fun onResponse(call: Call, response: Response) {
//                        if (!subscribe.isUnsubscribed()) {
//                            if (response.isSuccessful()) {
//                                val string = response.body().string()
//                                val res = JsonUtil.getObject(string, Res::class.java)
//                                if (res.getCode() === CODE_OK) {
//                                    subscribe.onNext(res)
//                                } else {
//                                    subscribe.onError(Throwable("请求出错"))
//
//                                }
//
//                            } else {
//                                subscribe.onError(Throwable("请求出错"))
//                            }
//
//
//                        }
//
//
//                    }
//                })
//
//
//            }
//        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
//    }


    //    public static Observable<Res> login(@NonNull final Map<String, Object> map) {
    //        return Observable.create(new Observable.OnSubscribe<Res>() {
    //            @Override
    //            public void call(Subscriber<? super Res> subscribe) {
    //
    //
    //                    FormBody.Builder builder = new FormBody.Builder();
    //                    for (String key : map.keySet()) {
    //                        builder.add(key, map.get(key).toString());
    //                    }
    //                    Request request = new Request.Builder()
    //                            .url(Api.getUrlRoot() + "/applogin")
    //                            .post(builder.build())
    //                            .build();
    //
    //                Response response = null;
    //                try {
    //                    response = getClient().newCall(request).  execute();
    //                    if (!subscribe.isUnsubscribed()) {
    //                        if (response.isSuccessful()) {
    //                            String s=response.body().string();
    //                            Log.d(TAG,s);
    //                            Res res =JsonUtil.getObject(s,Res.class);
    //                            if (res!=null&&res.getCode()==0){
    //                                subscribe.onNext(res);
    //                            }else {
    //                                subscribe.onError(new Throwable(""));
    //                            }
    //
    //                        } else {
    //
    //                                subscribe.onError(new Throwable("请求出错"));
    //
    //                        }
    //                    }
    //
    //                } catch (IOException e) {
    //                    e.printStackTrace();
    //                }
    //
    //
    //
    //            }
    //        }).subscribeOn(Schedulers.io())
    //                .observeOn(AndroidSchedulers.mainThread());
    //    }


    //    //应用拦截器：主要用于设置公共参数，头信息，日志拦截等,有点类似Retrofit的Converter
    //    private static Interceptor appIntercepter = new Interceptor() {
    //        @Override
    //        public Response intercept(Chain chain) throws IOException {
    //            Request request = processRequest(chain.request());
    //            Response response = processResponse(chain.proceed(request));
    //            return response;
    //        }
    //    };

    //    //访问网络之前，处理Request(这里统一添加了Cookie)
    //    private static Request processRequest(Request request) {
    //        String session = CacheManager.getSessionId();
    //        if (TextUtils.isEmpty(session))
    //            return request.newBuilder().build();
    //        else return request.newBuilder().addHeader("Cookie", session).build();
    //    }

    //    //访问网络之后，处理Response(这里没有做特别处理)
    //    private static Response processResponse(Response response) {
    //        return response;
    //    }


    //    public static Observable<Boolean> getSessionId() {
    //        return Observable.create(new Observable.OnSubscribe<Boolean>() {
    //            @Override
    //            public void call(Subscriber<? super Boolean> subscriber) {
    //
    //                Request request = new Request.Builder()
    //                        .url(Api.getUrlRoot() + "/appsession")
    //                        .build();
    //
    //                Response response = null;
    //                try {
    //                    response = getClient().newCall(request).execute();
    //                    if (response.isSuccessful()) {
    //                        Headers headers = response.headers();
    //                        List<String> cookies = headers.values("Set-Cookie");
    //                        String session = cookies.get(0);
    //                        String s = session.substring(0, session.indexOf(";"));
    //                        if (!TextUtils.isEmpty(s)) {
    //
    //                            CacheManager.setSessionId(s);
    //                            subscriber.onNext(true);
    //                        } else {
    //                            subscriber.onError(new Throwable("没有session"));
    //                        }
    //                    } else {
    //                        subscriber.onError(new Throwable("请求出错"));
    //                    }
    //                } catch (IOException e) {
    //                    e.printStackTrace();
    //                }
    //
    //
    //            }
    //        }).subscribeOn(Schedulers.io())
    //                .observeOn(AndroidSchedulers.mainThread());
    //    }


}


