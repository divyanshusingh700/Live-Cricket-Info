package com.truecodes.worldcuptv;



import android.content.Context;
import android.util.Log;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    private void sendRegistrationToServer(String str) {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            scheduleJob();
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    @Override
    public void onNewToken(String str) {
        Log.d(TAG, "Refreshed token: " + str);
        sendRegistrationToServer(str);
    }

    private void scheduleJob() {
        WorkManager.getInstance(this).beginWith(new OneTimeWorkRequest.Builder(MyWorker.class).build()).enqueue();
    }

    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    public static class MyWorker extends Worker {
        public MyWorker(Context context, WorkerParameters workerParameters) {
            super(context, workerParameters);
        }

        @Override
        public ListenableWorker.Result doWork() {
            return ListenableWorker.Result.success();
        }
    }
}