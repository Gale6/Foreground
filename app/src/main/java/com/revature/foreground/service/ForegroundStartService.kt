package com.revature.foreground.service

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

fun Context.foregroundStartService(command:String){

    val intent = Intent(this,MyService::class.java)

    if(command == "Start"){

        intent.putExtra(INTENT_COMMAND,command)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            this.startForegroundService(intent)
            Log.d("started","service started")
        }else{
            this.startService(intent)
        }

    }else if (command == "Exit"){

        intent.putExtra(INTENT_COMMAND,command)
        this.stopService(intent)
        Log.d("stopped","service stopped")

    }
}