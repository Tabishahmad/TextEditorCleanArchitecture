package com.example.scraobook.data.remote

import com.example.scraobook.util.Resource
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.Flow
import org.json.JSONArray
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class FirebaseTextFetchApi {
    suspend fun loadTextFromFirebase(catName: String): ArrayList<String> {
        return suspendCoroutine<ArrayList<String>> {c->
            val localFile = File.createTempFile("quotes", "txt")
            val storage = Firebase.storage
            val listRef = storage.reference.child("Quotes")
            listRef.listAll()
                .addOnSuccessListener { listResult->
                println("value from firebase go to firebase 2.2 " + listRef.path )
                val list = ArrayList<String>()
                listResult.items[0].getFile(localFile).addOnSuccessListener {
                val text = localFile.readText()
                localFile.delete()
                val array = JSONArray(text)
                for (i in 0 until array.length()) {
                    list.add(array[i].toString())
                }
                println("value from firebase go to firebase 2.1 " + list.size)
                c.resume(list)
            }}
            listRef.listAll().addOnCanceledListener {
                println("")
            }
        }
    }
}