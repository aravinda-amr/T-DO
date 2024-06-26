package com.example.t_do

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class noteAdapter(private var tasks: List<note>, context: Context):
    RecyclerView.Adapter<noteAdapter.taskViewHolder>() {

        private val db: databaseHelper = databaseHelper(context)

    class taskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.titleTV)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTV)
        val updateButton: ImageView = itemView.findViewById(R.id.edit_btn)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete_btn)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return taskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: taskViewHolder, position: Int) {
        val task = tasks[position]
        holder.titleTextView.text = task.title
        holder.contentTextView.text = task.content

        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context, updateActivity::class.java).apply {
                putExtra("task_id", task.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener{
            db.deleteNote(task.id)
            refreshData(db.getAllTasks())
            Toast.makeText(holder.itemView.context, "Task Deleted", Toast.LENGTH_SHORT).show()
        }


    }

    //refresh the list when adding one
    fun refreshData(newTasks: List<note>){
        tasks = newTasks
        notifyDataSetChanged()
    }

}