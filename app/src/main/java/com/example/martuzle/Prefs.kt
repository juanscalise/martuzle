package com.example.martuzle

import android.content.Context

class Prefs(context: Context) {

    private val DB = "My dataBase"
    private val IMAGES_DISCOVERED = "Images"
    private val storage = context.getSharedPreferences(DB, Context.MODE_PRIVATE)!!

    fun addImageDiscovered(index: Int) {
        val imagesDiscovered: ArrayList<Int> = ObjectSerializer.deserialize(
            storage.getString(
                IMAGES_DISCOVERED,
                ObjectSerializer.serialize(arrayListOf<Int>())
            )
        ) as ArrayList<Int>
        imagesDiscovered.add(index)
        storage.edit().putString(IMAGES_DISCOVERED, ObjectSerializer.serialize(imagesDiscovered))
            .apply()
    }

    fun getImagesDiscovered(): ArrayList<Int> {
        return ObjectSerializer.deserialize(
            storage.getString(
                IMAGES_DISCOVERED,
                ObjectSerializer.serialize(arrayListOf<Int>())
            )
        ) as ArrayList<Int>
    }
}