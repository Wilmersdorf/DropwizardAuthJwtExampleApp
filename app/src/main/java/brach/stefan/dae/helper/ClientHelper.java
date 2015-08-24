package brach.stefan.dae.helper;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class ClientHelper {
    public static OkHttpClient createClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        return client;
    }
}
