package net.in.forever.megaapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.UUID;

import static com.squareup.okhttp.MultipartBuilder.FORM;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String megaDeviceID;
    public String megaUserID;
    TextView textViewDeviceId;
    TextView textViewUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        textViewDeviceId =(TextView) findViewById(R.id.textViewUserId);
        textViewUserId =(TextView) findViewById(R.id.textViewUserId);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        megaDeviceID=getUserId();
        fetchUserIdFromSite();
        textViewDeviceId.setText(megaDeviceID);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getUserId(){
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
//        System.out.println("Log started");
        tmDevice = "" + tm.getDeviceId();
//        System.out.println(tmDevice);
        tmSerial = "" + tm.getSimSerialNumber();
//        System.out.println(tmSerial);
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
//        System.out.println(androidId);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
//        System.out.println(deviceUuid);
        String deviceId = deviceUuid.toString();
        System.out.println("DeviceID - "+deviceId);
        return deviceId;

    }

    public boolean fetchUserIdFromSite(){
        try {
            megaUserID = "";
            String imei = megaDeviceID;
            OkHttpClient client = new OkHttpClient();
            MultipartBuilder multipartBuilder = new MultipartBuilder();
            multipartBuilder.type(FORM);
            System.out.println("Start message");
            multipartBuilder.addPart(
                    Headers.of("Content-Disposition", "form-data; name=\"imei\""),
                    RequestBody.create(null, imei));
            System.out.println("Start message");
            RequestBody requestBody = multipartBuilder.build();
            System.out.println("Start message");
            Request request = new Request.Builder()
                    .url(SiteApi.serverURLuser)
                    .post(requestBody)
                    .build();
            System.out.println("Start message");
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Request request, IOException e) {
                    // Handle the error
                    System.out.println("error request user id from site");
                    System.out.println(e);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        // Handle the error
                        System.out.println("error response during fetching userid");
                    }
                    // Upload successful

                    System.out.println("REPLYMAX");
                    System.out.println(response.body().string());
                    megaUserID = response.body().toString();
                    textViewUserId.setText(megaUserID);
                }

            });

            return true;
        } catch (Exception ex) {
            System.out.println("error during fetching userid");
            System.out.println(ex);
        }
        return false;

    }


}
