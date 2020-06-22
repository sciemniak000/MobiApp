package agh.edu.pl.inf3.mobiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ScrollActivity : AppCompatActivity() {

    private val IMAGES = arrayOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)
        var index: Int = 0
        val imageView: ImageView = findViewById(R.id.scrolledImage)
        imageView.setImageResource(IMAGES[index])

        val del: FloatingActionButton = findViewById(R.id.deleteSaved)

        del.setOnClickListener { view ->
            Snackbar.make(view, R.string.deleted_confirmation, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val left: FloatingActionButton = findViewById(R.id.scrollLeft)

        left.setOnClickListener {
            index = decrementIndex(index)
            imageView.setImageResource(IMAGES[index])
        }

        val right: FloatingActionButton = findViewById(R.id.scrollRight)

        right.setOnClickListener {
            index = incrementIndex(index)
            imageView.setImageResource(IMAGES[index])
        }
    }

    private fun incrementIndex(index: Int) : Int {
        var i = index + 1
        if(i >= 3){
            i = 0
        }
        return i
    }

    private fun decrementIndex(index: Int) : Int {
        var i = index - 1
        if(i <= -1){
            i = 2
        }
        return i
    }
}
