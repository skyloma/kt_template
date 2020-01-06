package http;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

//import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



/**
 * Created by loma on 2017/4/11.
 */

public class Http2 {
    public static String address = "http://192.168.1.112:8080";
    public static String api     = "";

    public static String getApi() {
        return address + api;
    }



//    public static Observable<Res> json(String path, @Nullable Object obj) {
//        return Observable.create(new Observable.OnSubscribe<Res>() {
//            @Override
//            public void call(Subscriber<? super Res> subscriber) {
//                try {
//                    //创建连接
//                    URL url = new URL(getApi() + path);
//
//                    HttpURLConnection connection;
//                    //添加 请求内容
//                    connection = (HttpURLConnection) url.openConnection();
//                    //设置http连接属性
//                    connection.setDoOutput(true);// http正文内，因此需要设为true, 默认情况下是false;
//                    connection.setDoInput(true);// 设置是否从httpUrlConnection读入，默认情况下是true;
//                    connection.setRequestMethod("POST"); // 可以根据需要 提交 GET、POST、DELETE、PUT等http提供的功能
//                    connection.setRequestProperty("Cookie", CacheManager.getSessionId());
//                    // connection.setInstanceFollowRedirects(true);
//                    // connection.setRequestProperty("Host", "*******");  //设置请 求的服务器网址，域名，例如***.**.***.***
//                    connection.setRequestProperty("Content-Type", " application/json");//设定 请求格式 json，也可以设定xml格式的
//                    // connection.setRequestProperty("Accept-Charset", "utf-8");  //设置编码语言
//                    // connection.setRequestProperty("X-Auth-Token", "token");  //设置请求的token
//                    // connection.setRequestProperty("Connection", "keep-alive");  //设置连接的状态
//                    //connection.setRequestProperty("Transfer-Encoding", "chunked");//设置传输编码
//                    //connection.setRequestProperty("Content-Length", obj.toString().getBytes().length + "");  //设置文件请求的长度
//                    connection.setReadTimeout(10000);//设置读取超时时间
//                    connection.setConnectTimeout(10000);//设置连接超时时间
//                    connection.connect();
//                    if (obj!=null){
//                        OutputStream out = connection.getOutputStream();
//                        //向对象输出流写出数据，这些数据将存到内存缓冲区中
//                        out.write(JsonUtil.toJson(obj).getBytes());
//                        //刷新对象输出流，将任何字节都写入潜在的流中
//                        out.flush();
//                        // 关闭流对象,此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中
//                        out.close();
//                    }
//
//                    //读取响应
//                    if (connection.getResponseCode() == 200) {
//                        // 从服务器获得一个输入流
//                        Gson              gson        = new Gson();
//                        InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
//                        //调用HttpURLConnection连接对象的getInputStream()函数, 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
//
//                        Res proBean = gson.fromJson(inputStream, Res.class);
//                        subscriber.onNext(proBean);
//                        subscriber.onCompleted();
//                    } else {
//                        subscriber.onError(new Throwable(connection.getResponseMessage()+ ""));
//                        Log.i("zjt", "请求失败" + connection.getResponseCode());
//                    }
//
//                    //断开连接
//                    connection.disconnect();
//
//                }  catch (Exception e) {
//                    subscriber.onError(e);
//                }
//
//
//            }
//        }).compose(RxSchedulersHelper.io_main())
//               ;
//
//
//    }

    //每个post参数之间的分隔
    static final String BOUNDARY = "----MyFormBoundarySMFEtUYQG6r5B920";

//    public static Observable<Res> form(String path, Map<String, Object> map, List<File> files) {
//        return Observable.create(new Observable.OnSubscribe<Res>() {
//            @Override
//            public void call(Subscriber<? super Res> subscriber) {
//                try {
//                    //创建连接
//                    URL               url = new URL(getApi() + path);
//                    HttpURLConnection connection;
//                    //添加 请求内容
//                    connection = (HttpURLConnection) url.openConnection();
//                    //设置http连接属性
//                    connection.setDoOutput(true);// http正文内，因此需要设为true, 默认情况下是false;
//                    connection.setDoInput(true);// 设置是否从httpUrlConnection读入，默认情况下是true;
//                    connection.setRequestMethod("POST"); // 可以根据需要 提交 GET、POST、DELETE、PUT等http提供的功能
//                    connection.setRequestProperty("Cookie", CacheManager.getSessionId());
//                    // connection.setInstanceFollowRedirects(true);
//                    // connection.setRequestProperty("Host", "*******");  //设置请 求的服务器网址，域名，例如***.**.***.***
//                    //设定 请求格式 json，也可以设定xml格式的
////                    connection.setRequestProperty("Content-Type", " application/json");
//                    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
//                    // connection.setRequestProperty("Accept-Charset", "utf-8");  //设置编码语言
//                    // connection.setRequestProperty("X-Auth-Token", "token");  //设置请求的token
//                    // connection.setRequestProperty("Connection", "keep-alive");  //设置连接的状态
//                    //connection.setRequestProperty("Transfer-Encoding", "chunked");//设置传输编码
//                    //connection.setRequestProperty("Content-Length", obj.toString().getBytes().length + "");  //设置文件请求的长度
//                    connection.setReadTimeout(10000);//设置读取超时时间
//                    connection.setConnectTimeout(10000);//设置连接超时时间
//                    connection.connect();
//                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//                    //向对象输出流写出数据，这些数据将存到内存缓冲区中
//                    // out.write(JsonUtil.toJson(obj).getBytes());
//                    writeStringParams(out, map);
//                    writeFileParams(out, files);
//                    paramsEnd(out);
//                    //刷新对象输出流，将任何字节都写入潜在的流中
//                    out.flush();
//                    // 关闭流对象,此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中
//                    out.close();
//                    //读取响应
//                    if (connection.getResponseCode() == 200) {
//                        // 从服务器获得一个输入流
//                        Gson              gson        = new Gson();
//                        InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());//调用HttpURLConnection连接对象的getInputStream()函数, 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
//                        Res               proBean     = gson.fromJson(inputStream, Res.class);
//                        subscriber.onNext(proBean);
//
//
//                    } else {
//                        subscriber.onError(new Throwable(connection.getResponseCode() + ""));
//                        Log.i("zjt", "请求失败" + connection.getResponseCode());
//                    }
//
//
//                    //断开连接
//                    connection.disconnect();
//
//                } catch (Exception e) {
//                    subscriber.onError(e);
//                }
//
//
//            }
//        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
//    }

    private static void writeFileParams(DataOutputStream ds, List<File> files) throws Exception {

        for (File file : files) {
            ds.writeBytes("--" + BOUNDARY + "\r\n");
            ds.writeBytes("Content-Disposition: form-data; name=file; filename=\"" + encode(file.getName()) + "\"\r\n");
            ds.writeBytes("Content-Type: application/octet-stream" + "\r\n");
            ds.writeBytes("\r\n");
            ds.write(getBytes(file));
            ds.writeBytes("\r\n");

        }

    }

    //普通字符串数据
    private static void writeStringParams(DataOutputStream ds, Map<String, Object> map) throws IOException {


        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            String                    key   = entry.getKey();
            String                    va    = entry.getValue().toString();

            ds.writeBytes("--" + BOUNDARY + "\r\n");
            ds.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"\r\n");
            ds.writeBytes("\r\n");
            ds.writeBytes(va + "\r\n");
        }


    }

    //把文件转换成字节数组
    private static byte[] getBytes(File f) throws Exception {
        FileInputStream       in  = new FileInputStream(f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[]                b   = new byte[1024];
        int                   n;
        while ((n = in.read(b)) != -1) {
            out.write(b, 0, n);
        }
        in.close();
        return out.toByteArray();
    }

    //添加结尾数据
    private static void paramsEnd(DataOutputStream ds) throws Exception {
        ds.writeBytes("--" + BOUNDARY + "--" + "\r\n");
        ds.writeBytes("\r\n");
    }

    // 对包含中文的字符串进行转码，此为UTF-8。服务器那边要进行一次解码
    private static String encode(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, "UTF-8");
    }




}
