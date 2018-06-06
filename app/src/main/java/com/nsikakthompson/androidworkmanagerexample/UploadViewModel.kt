package com.nsikakthompson.androidworkmanagerexample

import android.arch.lifecycle.ViewModel
import android.net.Uri
import android.text.TextUtils
import androidx.work.Data

/**
 * Created by Nsikak on 6/6/18.
 */
class UploadViewModel : ViewModel() {


    var mImageUri: Uri? = null

    init {

    }


    private fun uriOrNull(uriString: String): Uri? {
        return if (!TextUtils.isEmpty(uriString)) {
            Uri.parse(uriString)
        } else null
    }

    /**
     * Setters
     */
    fun setImageUri(uri: String) {
        mImageUri = uriOrNull(uri)
    }

    /**
     * Getters
     */
    fun getImageUri(): Uri {
        return mImageUri!!
    }

    /**
     * Creates the input data bundle which includes the Uri to operate on
     * @return Data which contains the Image Uri as a String
     */
    private fun createInputDataForUri(): Data {
        val builder = Data.Builder()
        if (mImageUri != null) {
            builder.putString(MainActivity.KEY_IMAGE_URI, mImageUri.toString())
        }
        return builder.build()
    }
}