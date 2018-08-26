package io.collaborapp.collaborapp.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;

import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.ui.chat.ChatActivity;

import static io.collaborapp.collaborapp.data.model.MessageEntity.MESSAGE_TYPE_IM;

public class NotificationManager {
    private Context context;

    public static final String CHANNEL_ID = "28924";

    public NotificationManager(Context context) {
        this.context = context;
    }

    public void showChatNotification(ChatEntity chat) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (chat == null || chat.getLastMessage().getFrom().equals(userId))
            return;
        Intent i = new Intent(context, ChatActivity.class);
        i.putExtra("chatId", chat.getChatId());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = null;
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setContentText(chat.getLastMessage().getType().equals(MESSAGE_TYPE_IM) ? chat.getLastMessage().getText() : "Multimedia Message")
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle(chat.getTitle().equals("") ? chat.getMembers().get(0) : chat.getTitle())
                .setContentIntent(pendingIntent);
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            builder.setSmallIcon(R.drawable.mercurio_logo_white);
//        } else {
//            builder.setSmallIcon(R.drawable.ic_mercurio_notification);
//        }

        android.app.NotificationManager manager = (android.app.NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        manager.notify(0, builder.build());
    }

}
