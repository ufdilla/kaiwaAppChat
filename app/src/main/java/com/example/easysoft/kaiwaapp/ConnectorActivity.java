package com.example.easysoft.kaiwaapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectorActivity extends AsyncTask<Void, Void, Void> {

    TextView textResponse1;
    String textDestination;
    String username;
    String textRequest;
    String message = "";
    Socket socket;
    myModel model;

    public ConnectorActivity(Socket socket, String textRequest, TextView textResponse1) {
        this.socket = socket;
        this.textRequest = textRequest;
        this.textResponse1= textResponse1;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            JSONObject jObj = new JSONObject();
            jObj.put("message", textRequest);
//            jObj.put("username", username);
//            jObj.put("destination", textDestination);
            dataOutputStream.writeUTF(String.valueOf(jObj));
            dataOutputStream.flush();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            message = "UnknownHostException: " + e.toString() + "\r\n";
        } catch (IOException e) {
            e.printStackTrace();
            message = "IOException: " + e.toString() + "\r\n";
            e.getCause();
            e.getMessage();
        } catch (JSONException e) {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}