package com.revature.foreground.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.revature.foreground.R
import java.lang.Exception

const val NOTIFICATION_CHANNEL_GENERAL ="Checking"
const val INTENT_COMMAND = "Command"
const val INTENT_COMMAND_REPLY = "Reply"

//foreground service
class MyService:Service(){

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val command = intent?.getStringExtra(INTENT_COMMAND)

        if (command == "stop"){
            stopService()
            return  START_NOT_STICKY
        }

        showNotification()

        if(command == "Reply"){

            Toast.makeText(this,"Clicked in the Notification", Toast.LENGTH_LONG).show()
        }

        return START_NOT_STICKY
        //return super.onStartCommand(intent, flags, startId)
    }

    override fun stopService(name: Intent?): Boolean {
        return super.stopService(name)
    }

    private fun stopService(){
        stopForeground(true)
        stopSelf()
    }

    private fun showNotification(){

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        var replyIntent = Intent(this, MyService::class.java).apply{
            putExtra(INTENT_COMMAND, INTENT_COMMAND_REPLY)
        }
        val replyPendingIntent = PendingIntent.getService(this,2,replyIntent,0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            try {

                with(
                    NotificationChannel(
                        NOTIFICATION_CHANNEL_GENERAL,
                        "hello world",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                ){
                    enableLights(true)
                    setShowBadge(true)
                    enableVibration(true)
                    setSound(null,null)
                    description = "hello Description"
                    lockscreenVisibility = Notification.VISIBILITY_PRIVATE
                    manager.createNotificationChannel(this)

                }

            }catch (e:Exception){

                Log.d("Error displaying","show Notification ${e.localizedMessage}")

            }
            with(
                NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_GENERAL)
            ){
                setSmallIcon(R.drawable.ic_launcher_foreground)
                setContentTitle("First")
                setContentIntent(replyPendingIntent)
                setContentText("Notification Text")
                addAction(0,"Replay",replyPendingIntent)
                addAction(0,"Replay",replyPendingIntent)

                startForeground(1,build())
            }
        }
    }
}