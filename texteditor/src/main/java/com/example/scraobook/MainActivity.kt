package com.example.scraobook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.scraobook.presentation.text_list.QuoteListFragment
import com.example.scraobook.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import java.io.File
import kotlin.coroutines.resume

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var quotes = MutableLiveData<List<String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, QuoteListFragment.newInstance())
//                .commitNow()
//        }
        getQuotes("")
    }
    private fun getQuotes(catName: String) {
        val storage = Firebase.storage
        val listRef = storage.reference.child("Quotes")
        listRef.listAll()
            .addOnSuccessListener { listResult ->
                val localFile = File.createTempFile("quotes", "txt")
                if (listResult.items.isEmpty()) {
                    quotes.value = ArrayList()
                } else {
                    listResult.items[0].getFile(localFile).addOnSuccessListener {
                        val text = localFile.readText()
                        localFile.delete()
                        val array = JSONArray(text)
                        val list = ArrayList<String>()
                        for (i in 0 until array.length()) {
                            list.add(array[i].toString())
                        }
                        quotes.postValue(list)
                    }
                    .addOnFailureListener {
                        quotes.value = ArrayList()
                    }
                }
            }
            .addOnFailureListener {
                quotes.value = ArrayList()
            }
    }
}