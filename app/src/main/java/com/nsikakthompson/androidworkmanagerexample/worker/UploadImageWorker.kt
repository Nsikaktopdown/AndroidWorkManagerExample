package com.nsikakthompson.androidworkmanagerexample.worker

import android.net.Uri
import android.text.TextUtils
import android.util.Log
import androidx.work.Worker
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.storage.FirebaseStorage
import com.nsikakthompson.androidworkmanagerexample.MainActivity

/**
 * Created by Nsikak on 6/6/18.
 */
class UploadImageWorker : Worker() {


    val TAG = UploadImageWorker::class.java.simpleName
    var STATUS: WorkerResult? = null


    override fun doWork(): WorkerResult {

        var mStorage = FirebaseStorage.getInstance().reference

        val resourceUri = inputData.getString(MainActivity.KEY_IMAGE_URI, null)


        if (TextUtils.isEmpty(resourceUri)) {
            Log.e(TAG, "Invalid input uri")
            throw IllegalArgumentException("Invalid input uri")
        }

        val photoRef = mStorage.child("photo").child(Uri.parse(resourceUri).lastPathSegment)


        photoRef.putFile(Uri.parse(resourceUri)).addOnCompleteListener(OnCompleteListener { task ->

            if (!task.isSuccessful) {
                task.exception
            }

            STATUS = WorkerResult.SUCCESS


        }).addOnFailureListener({ e ->
            STATUS = WorkerResult.FAILURE
            Log.e(TAG, e.toString())
        })


        return STATUS!!


    }
}