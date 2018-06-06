package com.nsikakthompson.androidworkmanagerexample

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_upload.*

class UploadActivity : AppCompatActivity() {

    lateinit var mViewModel: UploadViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        //Get the viewmodel instance
        mViewModel = ViewModelProviders.of(this).get(UploadViewModel::class.java)

        val imageUri = intent.getStringExtra(MainActivity.KEY_IMAGE_URI)
        mViewModel.setImageUri(imageUri)

        Glide.with(this).load(mViewModel.getImageUri()).into(imageView2)
        textView.text = mViewModel.getImageUri().toString()
    }
}
