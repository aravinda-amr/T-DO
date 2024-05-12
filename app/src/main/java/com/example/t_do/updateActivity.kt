package com.example.t_do

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.t_do.databinding.ActivityUpdateBinding

class updateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: databaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = databaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if(noteId == -1){
            finish()
            return
        }

        val note = db.getNoteBYID(noteId)
        binding.updateeditText.setText(note.title)
        binding.updatecontentEditText.setText(note.content)

        binding.updatesaveBtn.setOnClickListener{
            val newTitle = binding.editTaskHeading.text.toString()
            val newCon = binding.updatecontentEditText.text.toString()
            val updatedNote = note(noteId,newTitle,newCon)
            db.updateTask(updatedNote)
            finish()
            Toast.makeText(this,"Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }
}