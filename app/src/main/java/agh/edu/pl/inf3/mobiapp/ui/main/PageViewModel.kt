package agh.edu.pl.inf3.mobiapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModel : ViewModel() {

    private val _image = MutableLiveData<Int>()
//    val text: LiveData<String> = Transformations.map(_image) {
//        "Hello world from section: $it"
//    }

    fun setImage(image: Int) {
        _image.value = image
    }

    fun getImage() : MutableLiveData<Int>{
        return _image
    }
}