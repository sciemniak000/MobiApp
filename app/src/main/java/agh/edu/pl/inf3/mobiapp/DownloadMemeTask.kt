package agh.edu.pl.inf3.mobiapp

import android.app.Activity
import android.media.Image
import android.os.AsyncTask

class DownloadMemeTask constructor(private var listener : Interface) : AsyncTask<TomorrowImage, Int, Image>() {
    override fun doInBackground(vararg params: TomorrowImage): Image {
        // pobieraj z bazy
        publishProgress(0)

        TODO("Not yet implemented")
    }

    override fun onPostExecute(result: Image?) {
        if (result != null) {
            listener.onImageRetrieved(result)
        }
    }

    interface Interface {
        fun onImageRetrieved(image: Image)
    }
}