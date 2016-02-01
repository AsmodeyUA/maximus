package forever.in.net.samplehttp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class HttpMainActivity extends AppCompatActivity {

    private TextView textResView;

    private Uri mImageCaptureUri;
    private ImageView mImageView;

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_FILE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //check commit from Android studio
        //commit from second PC
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Button getRequest = (Button) findViewById(R.id.button);
        textResView=(TextView) findViewById(R.id.textView);
        getRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String replyFromServer = "Reply";
                try {
                    replyFromServer = SiteApi.getRequestFromSite("http://95.215.156.221:8899/web2/find/extended?key=river&latitude=48.1851006&longitude=23.177981&radius=10000");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textResView.setText(replyFromServer);
            }
        });

        Button showBubbleMenu = (Button) findViewById(R.id.ShowMenu);
        showBubbleMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HttpMainActivity.this, BubbleMenuActivity.class);
                startActivity(intent);

            }
        });

        final String [] items           = new String [] {"From Camera", "From SD Card"};
        ArrayAdapter<String> adapter  = new ArrayAdapter<> (this, android.R.layout.select_dialog_item,items);
        AlertDialog.Builder builder     = new AlertDialog.Builder(this);

        builder.setTitle("Select Image");
        builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
            public void onClick( DialogInterface dialog, int item ) {
                if (item == 0) {
                    Intent intent    = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file        = new File(Environment.getExternalStorageDirectory(),
                            "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
                    mImageCaptureUri = Uri.fromFile(file);

                    try {
                        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                        intent.putExtra("return-data", true);

                        startActivityForResult(intent, PICK_FROM_CAMERA);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    dialog.cancel();
                } else {
                    Intent intent = new Intent();

                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);

                    startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);
                }
            }
        } );

        final AlertDialog dialog = builder.create();

        mImageView = (ImageView) findViewById(R.id.imageView);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.print(resultCode);
        if (resultCode != RESULT_OK) return;

        Bitmap bitmap   = null;
        String path ;

        if (requestCode == PICK_FROM_FILE) {
            mImageCaptureUri = data.getData();
            path = getRealPathFromURI(mImageCaptureUri); //from Gallery

            if (path == null)
                path = mImageCaptureUri.getPath(); //from File Manager

            if (path != null)
                bitmap  = BitmapFactory.decodeFile(path);
        } else {
            path    = mImageCaptureUri.getPath();
            bitmap  = BitmapFactory.decodeFile(path);

        }
        dataToPost post1 = new dataToPost();
        post1.loadLogoImage(bitmap);
        post1.uploadPost();
        mImageView.setImageBitmap(bitmap);
    }

    private String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] project = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, project, null, null, null);
        if (cursor != null) {
            if(cursor.moveToFirst()){
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close();
        }
        return res;
    }

}
