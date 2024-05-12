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
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = databaseHelper(this)

        noteId = intent.getIntExtra("task_id", -1)
        if (noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteBYID(noteId)
        binding.updateTitleET.setText(note.title)
        binding.updateContentET.setText(note.content)

        binding.updatesavebtn.setOnClickListener{
            val newTitle = binding.updateTitleET.text.toString()
            val newCon = binding.updateContentET.text.toString()
            val updatedNote = note(noteId,newTitle,newCon)
            db.updateTask(updatedNote)
            finish()
            Toast.makeText(this,"Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }
}