package com.android.example.AboutMe

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.done_button).setOnClickListener {
            addNickname(it)
        }
        findViewById<TextView>(R.id.nickname_text).setOnClickListener {
            updateNickname(it)
        }
    }

    fun addNickname(view: View) {
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val nickNameTextView = findViewById<TextView>(R.id.nickname_text)
        nickNameTextView.text = editText.text
        editText.visibility = View.GONE
        view.visibility = View.GONE
        nickNameTextView.visibility = View.VISIBLE
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun updateNickname(view: View) {
        // val nickNameTextView = findViewById<TextView>(R.id.nickname_text)
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val doneButton = findViewById<Button>(R.id.done_button)
        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE
    }
}