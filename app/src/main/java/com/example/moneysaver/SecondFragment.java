package com.example.moneysaver;

import static com.example.moneysaver.FirstFragment.NEWS;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    private Button notifButton;

    private static String NOTIFICATION_CHANNEL_ID = "My notification channel";

    public SecondFragment() {
        super(R.layout.fragment_second);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createNotificationChannel();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            NewsModel model = bundle.getParcelable(NEWS);

            ((TextView) view.findViewById(R.id.title)).setText(model.getTitle());
        }

        notifButton = view.findViewById(R.id.btn);

        notifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotification();
            }
        });

    }

    private void addNotification() {

        int id = 1;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity().getApplicationContext())
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notificare")
                .setContentText("Descriere notificare")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity().getApplicationContext());
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        notificationManagerCompat.notify(id, builder.build());

        }

        private void createNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "channel name", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("channel description");

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity().getApplicationContext());
            notificationManagerCompat.createNotificationChannel(channel);
        }
    }

}


