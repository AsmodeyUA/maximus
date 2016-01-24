package forever.in.net.samplehttp;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpMainActivity extends AppCompatActivity {

    private Button getRequest;
    private TextView textResView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //check commit from Android studio
        //commit from second PC
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getRequest=(Button) findViewById(R.id.button);
        textResView=(TextView) findViewById(R.id.textView);
        getRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String replyFromServer ="Reply";
                try {
                    replyFromServer = SiteApi.getRequestFromSite("http://95.215.156.221:8899/web2/find/extended?key=river&latitude=48.1851006&longitude=23.177981&radius=10000");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textResView.setText(replyFromServer);
            }
        });
    }
    public static void execute11() {
        Map<String, String> comment = new HashMap<String, String>();
        comment.put("subject", "Using the GSON library");
        comment.put("message", "Using libraries is convenient.");
        String json = new GsonBuilder().create().toJson(comment, Map.class);
        makeRequest("http://192.168.0.1:3000/post/77/comments", json);
    }

    public static int makeRequest(String uri, String json) {
//        try {
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return 0;
    }
    public class PostExample {
        public final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String post(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        String postToJson(String player1, String player2) {
            return "{'winCondition':'HIGH_SCORE',"
                    + "'name':'Bowling',"
                    + "'round':4,"
                    + "'lastSaved':1367702411696,"
                    + "'dateStarted':1367702378785,"
                    + "'players':["
                    + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                    + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                    + "]}";
        }

        public void execcute11() throws IOException {
            PostExample example = new PostExample();
            String json = example.postToJson("Jesse", "Jake");
            String response = example.post("http://www.roundsapp.com/post", json);
            System.out.println(response);
        }
    }


}
