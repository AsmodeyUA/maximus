package net.in.forever.megaapp;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class SiteApi {

    public static String serverURL = "http://95.215.156.221:8899/web2/adverts/add?";
    public static String serverURLuser = "http://95.215.156.221:8899/web2/users/add";

    public static JSONArray getRequestFromSite(String url) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        JSONArray jarray = new JSONArray(response.body().toString());
        return  jarray;
    }
}
