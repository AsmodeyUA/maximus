package forever.in.net.samplehttp;

import android.graphics.Bitmap;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.squareup.okhttp.MultipartBuilder.FORM;

public class dataToPost {
    private final String description;
    private final String latitude;
    private final String longitude;
    private final String userId;
    private final String userNick;
    private final String daysToLive;
    private final String title;
    private File logo;

    dataToPost(){

        description="NewDescription";
        latitude="42.780601501464844";
        longitude="35.0875000953674316";
        userId="7f49b84d-0bbc-38e9-a493-718013baace6";
        userNick="Polishman";
        daysToLive="7";
        title="new Title";
        logo = null;
    }

    dataToPost loadLogoImage(Bitmap bitmapFile){
        try {
            File photo=new File("logo.jpg");
            if (photo.exists()) {
                final boolean deleteResult = photo.delete();
            }
            FileOutputStream fos=new FileOutputStream(photo.getPath());
            bitmapFile.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            this.logo = photo;
        }
        catch (java.io.IOException e) {
            this.logo = null;
        }

    return this;
    }


    public Boolean uploadPost() {
        try {
            OkHttpClient client = new OkHttpClient();
            final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpeg");
            MultipartBuilder multipartBuilder = new MultipartBuilder();
            multipartBuilder.type(FORM);
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"description\""),
                    RequestBody.create(null, description));
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"latitude\""),
                    RequestBody.create(null, latitude));
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"longitude\""),
                    RequestBody.create(null, longitude));
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"userId\""),
                    RequestBody.create(null, userId));
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"daysToLive\""),
                    RequestBody.create(null, daysToLive));
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"title\""),
                    RequestBody.create(null, title));
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"logo\""),
                    RequestBody.create(MEDIA_TYPE_JPG, logo));
            RequestBody requestBody = multipartBuilder.build();

            Request request = new Request.Builder()
                    .url(SiteApi.serverURL)
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
