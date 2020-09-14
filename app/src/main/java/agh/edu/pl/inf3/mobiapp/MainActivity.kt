package agh.edu.pl.inf3.mobiapp

import agh.edu.pl.inf3.mobiapp.ui.main.SectionsPagerAdapter
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DownloadMemeTask.Interface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        setSupportActionBar(toolbar2)
        toolbar2.setTitle(R.string.app_name)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, R.string.saved_confirmation, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val task = DownloadMemeTask(this)

        // pobieranie z receivera
        //task.execute(TomorrowImage.H11)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                Snackbar.make(
                    findViewById(R.id.constraintLayout),
                    R.string.saved_confirmation,
                    Snackbar.LENGTH_LONG
                )
                    .setAction("Action", null).show()
            }
            R.id.item2 -> {
                val intent = Intent(this, ScrollActivity::class.java)
                startActivity(intent)
            }
            R.id.item3 -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
            R.id.item4 -> {
                finishAndRemoveTask()
            }
        }

        return true
    }

    override fun onStart() {
        super.onStart()
        overridePendingTransition(0, 0)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun onImageRetrieved(image: Image) {
        // update GUI !

        TODO("Not yet implemented")
    }
}