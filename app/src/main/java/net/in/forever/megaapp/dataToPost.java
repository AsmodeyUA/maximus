package net.in.forever.megaapp;

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
    private final String tempdirectory;
    private File logo;

    dataToPost(String temporarypath){

        description="Пропонуємо чудовий вибір б/у колясок 2 в 1, 3 в 1, трансформерів від 0 до 3 років. Всі коляски в хорошому та ідеальному стані. Великий вибір для дівчаток та хлопців різних кольорів та моделей: Androx, Adamex, Roan Marita, Tako, Babyactive, Navington, Geoby , Сhicco, Coneco, Ok Stile, Adbor, Anmar, Avalon, Bebetto, Donatan, Jupiter, Victoria та інші.\n" +
                "\n" +
                "Працюємо тільки з асортиментом!\n" +
                "\n" +
                "Ціни від 1900 до 5500 грн.\n" +
                "\n" +
                "Всі коляски на сайті (актуальні ціни та наявність):\n" +
                "\n" +
                "dkolaska.com.ua\n" +
                "\n" +
                "Телефонуйте сьогодні! Пн - Нд з 08:00 до 22:00\n" +
                "Підїхати можна Пн - Нд з 09:00 до 21:00\n" +
                "Є вибір колясок! Підїхати (за 1 год попередньо зателефонуйте), подивитись можна на вул. Мідна 2 (гаражний кооператив \"Чайка\" - біля ринку \"Перехрестя\" (вул. Б.Хмельницького))\n" +
                "\n" +
                "Відправляємо безкоштовно кур'єрською службою Інтайм в усі міста і селища України, де розміщені відділення пошти.\n" +
                "Доставка 1-2 дні, у найвіддаленіші міста, селища - 3-4 дні.\n" +
                "Можлива доставка іншими кур'єрськими службами: Новою Поштою, Делівері, Автолюксом. Відправляємо за передоплатою або після отримання завдатку (рівному сумі двом доставках), вартість такої доставки оплачує одержувач.\n" +
                "\n" +
                "Більше колясок у \"Інші оголошення автора\" (знаходиться справа біля першого фото).";
        latitude="33.1123";
        longitude="33.1123";
        userId="5570b31c-3aa2-3824-b980-aa25e7ce9ce1";
        userNick="sampleuser123";
        daysToLive="7";
        title="\n" +
                "Великий вибір б/у колясок 2 в 1, 3 в 1, трансформерів (якість Європи) ";
        logo = null;
        tempdirectory = temporarypath;
    }

    dataToPost loadLogoImage(Bitmap bitmapFile){
        try {
            File photo=new File(tempdirectory,"/logo.jpg");
            if (photo.exists()) {
                final boolean deleteResult = photo.delete();
            }
            System.out.println(photo.getPath());
            FileOutputStream fos=new FileOutputStream(photo.getPath());

            bitmapFile.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            this.logo = photo;
        }
        catch (java.io.IOException e) {
            System.out.println("logo error");
            System.out.println(e);
            this.logo = null;
        }

    return this;
    }


    public Boolean uploadPost() {
        try {
            OkHttpClient client = new OkHttpClient();
            final MediaType MEDIA_TYPE_JPG = MediaType.parse("media/type");
            MultipartBuilder multipartBuilder = new MultipartBuilder();
            multipartBuilder.type(FORM);
            System.out.println("Start message");
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"description\""),
                    RequestBody.create(null, description));
            System.out.println("Start message");
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"latitude\""),
                    RequestBody.create(null, latitude));
            System.out.println("Start message");
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"longitude\""),
                    RequestBody.create(null, longitude));
            System.out.println("Start message");
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"userId\""),
                    RequestBody.create(null, userId));
            System.out.println("Start message");
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"userNick\""),
                    RequestBody.create(null, userNick));
            System.out.println("Start message");
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"daysToLive\""),
                    RequestBody.create(null, daysToLive));
            System.out.println("Start message");
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"title\""),
                    RequestBody.create(null, title));
            System.out.println("Logo message");
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"logo\";filename=\"logo.jpg\"", "Content-Transfer-Encoding", "binary"),
                    RequestBody.create(MEDIA_TYPE_JPG, logo));
            System.out.println("Start message");
            RequestBody requestBody = multipartBuilder.build();
            System.out.println("Stop message");
            Request request = new Request.Builder()
                    .url(SiteApi.serverURL)
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Request request, IOException e) {
                    // Handle the error
                    System.out.println("errorsendingzzzz");
                    System.out.println(e);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        // Handle the error
                        System.out.println("error response");
                    }
                    // Upload successful
                    System.out.println(response);
                    System.out.println("REPLYMAX");
                    System.out.println(response.body().string());
                }

            });

            return true;
        } catch (Exception ex) {
            System.out.println("error");
            System.out.println(ex);
        }
        return false;
    }

}
