package com.example.t_do

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.t_do.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: databaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = databaseHelper(this)

        //save data when button is clicked
        binding.saveBtn.setOnClickListener{
            val title = binding.editText.text.toString()
            val content = binding.contentEditText.text.toString()
            val note = note(0,title,content)
            db.insertNote(note)
            finish()
            Toast.makeText(this,"Task Saved",Toast.LENGTH_SHORT).show()
        }

    }
}