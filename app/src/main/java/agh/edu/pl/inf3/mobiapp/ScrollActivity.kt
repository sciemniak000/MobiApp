package agh.edu.pl.inf3.mobiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ScrollActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)
        val imageView: ImageView = findViewById(R.id.scrolledImage)
        imageView.setImageResource(R.drawable.image3)
    }
}
