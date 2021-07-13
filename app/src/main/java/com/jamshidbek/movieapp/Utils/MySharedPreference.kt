package com.jamshidbek.movieapp.Utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jamshidbek.movieapp.Model.MovieModel

object MySharedPreference {
    private const val NAME = "ToCashMemory"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var objectString: ArrayList<MovieModel>
        get() = gsonStringToArray(preferences.getString("object", "[]")!!)
        set(value) = preferences.edit {
            if (value != null) {
                it.putString("object", arrayToGsonString(value))
            }
        }

    fun arrayToGsonString(arrayList: ArrayList<MovieModel>): String {
        return Gson().toJson(arrayList)
    }

    fun gsonStringToArray(gsonString: String): ArrayList<MovieModel> {
        val typeToken = object : TypeToken<ArrayList<MovieModel>>() {}.type
        return Gson().fromJson(gsonString, typeToken)
    }
}