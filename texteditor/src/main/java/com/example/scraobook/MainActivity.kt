package com.example.scraobook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var mainActivity : MainActivity
    }

//    private var quotes = MutableLiveData<List<String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = this
        setContentView(R.layout.main_activity)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.text_edit_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.saveImage -> {
//                Toast.makeText(applicationContext," SAVE IMAGE ",Toast.LENGTH_SHORT).show()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//    private fun getQuotes(catName: String) {
//        val storage = Firebase.storage
//        val listRef = storage.reference.child("Quotes")
//        listRef.listAll()
//            .addOnSuccessListener { listResult ->
//                val localFile = File.createTempFile("quotes", "txt")
//                if (listResult.items.isEmpty()) {
//                    quotes.value = ArrayList()
//                } else {
//                    listResult.items[0].getFile(localFile).addOnSuccessListener {
//                        val text = localFile.readText()
//                        localFile.delete()
//                        val array = JSONArray(text)
//                        val list = ArrayList<String>()
//                        for (i in 0 until array.length()) {
//                            list.add(array[i].toString())
//                        }
//                        quotes.postValue(list)
//                    }
//                    .addOnFailureListener {
//                        quotes.value = ArrayList()
//                    }
//                }
//            }
//            .addOnFailureListener {
//                quotes.value = ArrayList()
//            }
//    }
}