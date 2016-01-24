package forever.in.net.samplehttp;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

/**
 * Created by asmodey on 24.01.16.
 */
public class SiteApi {

    public static String getRequestFromSite(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public static Boolean uploadFile(String serverURL, File file) {
        try {
            OkHttpClient client = new OkHttpClient();
            final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
            MultipartBuilder m=new MultipartBuilder();
            RequestBody requestBody = new MultipartBuilder()
//                    .type(MultipartBuilder.FORM)
//                    .addPart("member_id", "text")
//                    .addFormDataPart("logo", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file))
                    .build();

           Request request = new Request.Builder()
                    .url(serverURL)
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Request request, IOException e) {
                    // Handle the error
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        // Handle the error
                    }
                    // Upload successful
                }
            });

            return true;
        } catch (Exception ex) {
            // Handle the error
        }
        return false;
    }

}
