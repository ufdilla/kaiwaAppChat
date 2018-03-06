package com.example.easysoft.kaiwaapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import org.json.JSONObject;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;
import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {

    public static final String KEY_ITEM = "item";
    Socket socket = null;
    myModel myModel;
    String username;
    String message = "";
    boolean connected = true;

    ListView lvDetail;
    Context context = MainActivity.this;
    ArrayList myList = new ArrayList();
    String[] desc = new String[]{
            "Desc 1", "Desc 2", "Desc 3", "Desc 4",
            "Desc 5", "Desc 6", "Desc 7", "Desc 8"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new MainActivity.ClientThread()).start();

        myModel = (myModel) getIntent().getSerializableExtra(KEY_ITEM);

        Log.d("nama dari model ", myModel.getNama());

        lvDetail = (ListView) findViewById(R.id.lvCustomList);
        // insert data into the list before setting the adapter
        // otherwise it will generate NullPointerException  - Obviously
        getDataInList();
        lvDetail.setAdapter(new ContactAdapter(context, myList));
    }

    private void getDataInList() {
        for (int i = 0; i < desc.length; i++) {
            // Create a new object for each list item
            ContactModel ld = new ContactModel();
//            ld.setTitle(title[i]);
            ld.setDescription(desc[i]);
//            ld.setImgResId(img[i]);
            // Add this object into the ArrayList myList
            myList.add(ld);
        }
    }

//    public void onClick(View view) {
//        try {
//            Socket socket = LoginActivity.getSocket();
////            String textRequest = ((EditText) findViewById(R.id.textRequest)).getText().toString();
////            String textDestination =((EditText) findViewById(R.id.textDestination)).getText().toString();
//            TextView textViewResponse = findViewById(R.id.textResponse1);
////            String username = myModel.getNama();
//            ConnectorActivity connector = new ConnectorActivity(socket, textRequest, textResponse1);
//            connector.execute();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private Context getActivity() {
        return null;
    }

    class ClientThread extends Thread {

        @Override
        public void run() {
            try {
                while (connected){
                    TextView textViewResponse = (TextView) findViewById(R.id.textResponse1);
                    Socket socket = LoginActivity.getSocket();
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                    String input = dataInputStream.readUTF();
                    JSONObject mesObj = new JSONObject(input);
                    message = mesObj.getString("message");
                    username = mesObj.getString("username");

                    String existingMessage = textViewResponse.getText().toString();
                    message = existingMessage + username + " : " + message + "\n";
                    textViewResponse.setText(username);
                    Log.d(TAG, "username: " + username);
                }
//                super.onPostExecute(result);
            }
            catch (Exception e)
            {
                Log.e(TAG, "run: ", e );
            }

        }

    }
}