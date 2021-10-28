package com.example.spacexlaunches.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.spacexlaunches.R
import com.example.spacexlaunches.utils.extentions.getParentActivity

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(
            parentActivity,
            Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: LiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

@BindingAdapter("background")
fun setBackgroundColor(view: LinearLayout, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(
            parentActivity,
            Observer { value -> view.setBackgroundColor(Color.parseColor(value ?: "")) })
    }
}

@BindingAdapter("textColor")
fun setTextColor(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(
            parentActivity,
            Observer { value -> view.setTextColor(Color.parseColor(value)) })
    }
}

@BindingAdapter("onImeOption")
fun setOnImeOption(view: EditText, func: Runnable) {
    view.setOnEditorActionListener { _, actionId, _ ->
        return@setOnEditorActionListener when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> {
                func.run()
                true
            }
            else -> false
        }
    }
}


/**
 * Binding function: Loading image url with glide library into image view
 *
 * @param view  ImageView to apply image
 * @param text  Image URL for loading src
 */
@BindingAdapter("glideSrc")
fun setGlideSrc(view: ImageView, text: String?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        Glide.with(view.context).load(text).diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(R.drawable.ic_launcher_background).into(view)
    }
}

@BindingAdapter("bitmapSrc")
fun setImageViewSrc(imageView: ImageView, bitmap: Bitmap){
    imageView.setImageBitmap(bitmap)
}