package demo.yangzikang.handlertest;

import android.os.Handler;
import android.os.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.os.Looper.getMainLooper;

public class NetUtils {

    private Handler handler;
    private static IResponse resp;

    public void doGetRequest(final String path, final IResponse response) {
        handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                super.handleMessage(message);
                resp.onResponse((String) message.obj);
            }
        };
        resp = response;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj = getJsonByInternet(path);
                handler.sendMessage(message);
                handler.dispatchMessage(message);
            }
        }).start();
    }

    public interface IResponse {
        void onResponse(String jsonString);
    }

    /**
     * 从网络获取json数据,(String byte[})
     *
     * @param path
     * @return
     */
    private static String getJsonByInternet(String path) {
        try {
            URL url = new URL(path.trim());
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if (200 == urlConnection.getResponseCode()) {
                //得到输入流
                InputStream is = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}

