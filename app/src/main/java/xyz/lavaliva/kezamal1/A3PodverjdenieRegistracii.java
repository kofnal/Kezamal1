package xyz.lavaliva.kezamal1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class A3PodverjdenieRegistracii extends AppCompatActivity {
    EditText etKodObiektaA3;
    Button btVxodVobiektA3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3_podverjdenie_registracii);
        etKodObiektaA3=findViewById(R.id.etKodObiektaA3);
        btVxodVobiektA3=findViewById(R.id.btVxodVobiektA3);
        proverkaOdobreniaDostupa();
        btVxodVobiektA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readeFireCode(etKodObiektaA3.getText().toString());
            }
        });



        //showNotification(this, "a", "sd");


    }

    private void proverkaOdobreniaDostupa() {
        String mUserID="";
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            mUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        } else {
            //login or register screen
        }
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(mUserID);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                   User usss = dataSnapshot.getValue(User.class);
                System.out.println("Razrehili "+usss.isDostup()+dataSnapshot+" ");

if(usss.isDostup()) {
    System.out.println("if dostup");
    Intent intent = new Intent(A3PodverjdenieRegistracii.this, A5OsnovnoeActivity.class);
    startActivity(intent);
    finish();
    // ...
}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message


                System.out.println("NOT Saved user");
                // ...
            }
        };
        mDatabase.addValueEventListener(postListener);







    }

    public static void showNotification(Context context, String title, String messageBody) {

        boolean isLoggedIn = true;

        Intent intent = null;
        if (isLoggedIn) {
            //goto notification screen
            intent = new Intent(context, A4AdminPriglahaet.class);
           // intent.putExtra(Extras.EXTRA_JUMP_TO, DrawerItems.ITEM_NOTIFICATION);
        } else {
            //goto login screen
            intent = new Intent(context, A4AdminPriglahaet.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);

        //Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_app_notification_icon);

        String channel_id = createNotificationChannel(context);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channel_id)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
                /*.setLargeIcon(largeIcon)*/
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark) //needs white icon with transparent BG (For all platforms)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                .setVibrate(new long[]{1000, 1000})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify((int) ((new Date(System.currentTimeMillis()).getTime() / 1000L) % Integer.MAX_VALUE) /* ID of notification */, notificationBuilder.build());
    }

    public static String createNotificationChannel(Context context) {

        // NotificationChannels are required for Notifications on O (API 26) and above.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // The id of the channel.
            String channelId = "Channel_id";

            // The user-visible name of the channel.
            CharSequence channelName = "Application_name";
            // The user-visible description of the channel.
            String channelDescription = "Application_name Alert";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
            boolean channelEnableVibrate = true;
//            int channelLockscreenVisibility = Notification.;

            // Initializes NotificationChannel.
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, channelImportance);
            notificationChannel.setDescription(channelDescription);
            notificationChannel.enableVibration(channelEnableVibrate);
//            notificationChannel.setLockscreenVisibility(channelLockscreenVisibility);

            // Adds NotificationChannel to system. Attempting to create an existing notification
            // channel with its original values performs no operation, so it safe to perform the
            // below sequence.
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);

            return channelId;
        } else {
            // Returns null for pre-O (26) devices.
            return null;
        }
    }
    private void readeFireCode(String s) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("obiekti");
        Query query=mDatabase.orderByChild("id").equalTo(s);
        System.out.print(" ssssssssss"+s);



        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
System.out.println(dataSnapshot.toString()+" Datasnapshot");


//                for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                    //Здесь будет вложенный цикл для сравнения
//                    String comp = ",";
//                    String[] selected = selectedItems.split(comp);
//                    for (int i = 0; i < selected.length; i++) {
//                        if (selected[i].contains(ds.child("Ingridients").getValue(String.class))) {
//                            result_list.add(ds.getValue(String.class));
//                        }
//                    }
//                }









            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        mDatabase.child("obiekti").orderByChild(s);
    }


}
