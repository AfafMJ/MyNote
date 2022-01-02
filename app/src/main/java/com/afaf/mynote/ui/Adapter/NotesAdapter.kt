package com.afaf.mynote.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.afaf.mynote.Model.Notes
import com.afaf.mynote.R
import com.afaf.mynote.databinding.ItemNotesBinding
import com.afaf.mynote.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, val notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesViewholder>() {
    class notesViewholder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewholder {
        return notesViewholder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: notesViewholder, position: Int) {
      holder.binding.notesTitle.text = notesList[position].title
        holder.binding.notesSubTitle.text = notesList[position].subTitle
        holder.binding.notesDate.text = notesList[position].date
        when(notesList[position].priority){
            "1"->{
                holder.binding.viewPrierity.setBackgroundResource(R.drawable.green_dot)
            }
            "2"->{
                holder.binding.viewPrierity.setBackgroundResource(R.drawable.red_dot)
            }
            "3"->{
                holder.binding.viewPrierity.setBackgroundResource(R.drawable.yallow_dot)
            }
        }
        holder.binding.root.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(notesList[position])
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount()= notesList.size


}