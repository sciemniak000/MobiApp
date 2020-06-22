package agh.edu.pl.inf3.mobiapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import agh.edu.pl.inf3.mobiapp.R
import android.widget.ImageView

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setImage(arguments?.getInt(ARG_IMAGE_IDENTIFIER) ?: R.drawable.image1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val imageView: ImageView = root.findViewById(R.id.imageView)
        imageView.setImageResource(pageViewModel.getImage().value!!)
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_IMAGE_IDENTIFIER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    when (sectionNumber) {
                        1 -> putInt(ARG_IMAGE_IDENTIFIER, R.drawable.image1)
                        2 -> putInt(ARG_IMAGE_IDENTIFIER, R.drawable.image2)
                        3 -> putInt(ARG_IMAGE_IDENTIFIER, R.drawable.image3)
                        else -> putInt(ARG_IMAGE_IDENTIFIER, R.drawable.image2)
                    }
                }
            }
        }
    }
}