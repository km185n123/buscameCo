package com.buscame.oncor.buscame.Util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Base64
import java.io.ByteArrayOutputStream
import android.app.ActivityManager
import android.content.Context
import android.R.attr.bitmap
import android.graphics.drawable.BitmapDrawable
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.view.View


class Util {

    companion object {

        fun decodeBase64(input: String): Bitmap {
            val decodedBytes = Base64.decode(input, 0)
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        }


        @Throws(IllegalArgumentException::class)
        fun base64ToBitmap(base64Str: String): Bitmap {
            val decodedBytes = Base64.decode(
                    base64Str.substring(base64Str.indexOf(",") + 1),
                    Base64.DEFAULT
            )

            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        }

        fun bitmapToBase64(bitmap: Bitmap): String {
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)

            return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
        }

        fun convertToBitmap(drawable: Drawable, widthPixels: Int, heightPixels: Int): Bitmap {
            val mutableBitmap = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(mutableBitmap)
            drawable.setBounds(0, 0, widthPixels, heightPixels)
            drawable.draw(canvas)

            return mutableBitmap
        }

        fun isAppRunning(context: Context, packageName: String): Boolean {
            val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val procInfos = activityManager.runningAppProcesses
            if (procInfos != null) {
                for (processInfo in procInfos) {
                    if (processInfo.processName == packageName) {
                        return true
                    }
                }
            }
            return false
        }

        fun isEmptyOrNull(d:String?): Boolean {

            return d.isNullOrEmpty()

        }

        fun BitmatToDrawable(bitmap: Bitmap , context: Context):Drawable{

            val d = BitmapDrawable(context.getResources(), bitmap)
            return d
        }

        fun viewToBitmap(view: View,width:Int,height:Int): Bitmap {
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            return bitmap
        }
    }





}