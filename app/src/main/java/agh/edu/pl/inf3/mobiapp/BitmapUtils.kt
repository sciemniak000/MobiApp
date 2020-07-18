package agh.edu.pl.inf3.mobiapp.utils

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore

class BitmapUtils {

    companion object {

        private const val APP_DIRECTORY = "MobiApp"
        private const val MAX_MEME_WIDTH = 1000
        private const val MAX_MEME_HEIGHT = 1000

        fun saveImage(context: Context, bitmap: Bitmap): Boolean {
            val resolver: ContentResolver = context.contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "meme")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            contentValues.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                "DCIM/$APP_DIRECTORY"
            )
            val imageUri =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            val fos = imageUri?.let { resolver.openOutputStream(it) }

            val outputBitmap: Bitmap = scaleBitmap(bitmap)
            outputBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)

            val isSaved = outputBitmap.compress(Bitmap.CompressFormat.PNG, 10, fos)
            fos?.flush()
            fos?.close()
            return isSaved
        }

        private fun scaleBitmap(
            bitmap: Bitmap
        ): Bitmap {
            val ratio =
                (bitmap.width.toDouble() / MAX_MEME_WIDTH).coerceAtLeast(bitmap.height.toDouble() / MAX_MEME_HEIGHT)
            val scaledWidth = (bitmap.width / ratio).toInt()
            val scaledHeight = (bitmap.height / ratio).toInt()

            return Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, false)
        }

    }

}
