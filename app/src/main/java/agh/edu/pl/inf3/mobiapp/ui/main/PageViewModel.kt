package agh.edu.pl.inf3.mobiapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel() {

    private val _image = MutableLiveData<Int>()

    fun setImage(image: Int) {
        _image.value = image
    }

    fun getImage() : MutableLiveData<Int>{
        return _image
    }
}