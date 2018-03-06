package com.example.easysoft.kaiwaapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity {

    private static Socket socket = null;
    private static final String TAG = "Client";

    myModel model;
    Button btnLogin;
    List<myModel> list;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login_button);
        list = new ArrayList<>();

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            username = ((EditText) findViewById(R.id.username)).getText().toString();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            new Thread(new LoginThread()).start();
            model = new myModel();
            model.setNama(username);
            list.add(model);

            i.putExtra(MainActivity.KEY_ITEM, model);
            startActivity(i);
            }
        });
    }

    public void click_button_login(View view){

    }

    public static void setSocket(Socket _socket) {
        LoginActivity.socket = _socket;
    }

    public static Socket getSocket() {
        return LoginActivity.socket;
    }

    private class LoginThread implements Runnable {

        @Override
        public void run() {
            String username = ((EditText) findViewById(R.id.username)).getText().toString();

            try {
                String destAddress = "192.168.0.47";
                int destPort = 2003;

                socket = new Socket(destAddress, destPort);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(username);

                JSONObject jsObject = new JSONObject();
                jsObject.put("socket", socket);
                jsObject.put("username", username);

                LoginActivity.setSocket(socket);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private Context getActivity() {
            return null;
        }

    }
}
