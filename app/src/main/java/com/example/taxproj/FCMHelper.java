package com.example.taxproj;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FCMHelper {

    private static final String TAG = "FCMHelper";
    private static final String FCM_API_URL = "https://fcm.googleapis.com/fcm/send";
    private static final MediaType JSON = MediaType.get("application/json");

    // Replace with your real FCM Server Key (keep secure!)
    private static final String SERVER_KEY = "YOUR_FCM_SERVER_KEY";

    public static void sendNotification(String token, String title, String body) {
        OkHttpClient client = new OkHttpClient();

        try {
            JSONObject notification = new JSONObject();
            notification.put("title", title);
            notification.put("body", body);

            JSONObject payload = new JSONObject();
            payload.put("to", token);
            payload.put("notification", notification);

            RequestBody requestBody = RequestBody.create(payload.toString(), JSON);

            Request request = new Request.Builder()
                    .url(FCM_API_URL)
                    .post(requestBody)
                    .addHeader("Authorization", "key=" + SERVER_KEY)
                    .addHeader("Content-Type", "application/json")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(TAG, "FCM Failed: " + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "FCM Success: " + response.body().string());
                    } else {
                        Log.e(TAG, "FCM Error: " + response.code() + " - " + response.body().string());
                    }
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "Exception in sendNotification: " + e.getMessage());
        }
    }
}
