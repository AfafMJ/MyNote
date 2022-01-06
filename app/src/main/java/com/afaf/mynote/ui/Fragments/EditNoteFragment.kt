package com.afaf.mynote.ui.Fragments

import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.afaf.mynote.Model.Notes
import com.afaf.mynote.R
import com.afaf.mynote.ViewModel.NotesViewModel
import com.afaf.mynote.databinding.FragmentEditNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class EditNoteFragment : Fragment() {

    val oldnotes by navArgs<EditNoteFragmentArgs>()
    var priority: String = "1"
    lateinit var binding:FragmentEditNoteBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditNoteBinding.inflate(layoutInflater,container, false)
        setHasOptionsMenu(true)
        binding.edttitle.setText(oldnotes.data.title)
        binding.edtSubTitle.setText(oldnotes.data.subTitle)
        binding.edtNotes.setText(oldnotes.data.notes)

        when(oldnotes.data.priority){
            "1"->{
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
            "2"->{
                priority = "2"
                binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "3"->{
                priority = "3"
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pGreen.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
        }

        binding.pRed.setOnClickListener {
            priority = "2"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)

        }
        binding.pYellow.setOnClickListener {
            priority = "3"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)

        }


        binding.btnEditNotes.setOnClickListener {

            updateNotes(it)
        }



        return binding.root
    }


    private fun updateNotes(it: View?){
        val title = binding.edttitle.text.toString()
        val subtitle = binding.edtSubTitle.text.toString()
        val notes = binding.edtNotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)
        Log.e("####", "creatnotes: $notesDate")

        val data = Notes(
            oldnotes.data.id,
            title = title,
            subTitle = subtitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.updateNote(data)
        Toast.makeText(requireContext(), "Notes updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId== R.id.menu_delete){
            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dalog_delete)
            val textviewyes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textviewno = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textviewyes?.setOnClickListener {
                viewModel.deleteNotes(oldnotes.data.id!!)

                Navigation.findNavController(this.requireView()).navigate(R.id.action_editNoteFragment_to_homeFragment)
                bottomSheet.dismiss()


            }
            textviewno?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()


        }
        return super.onOptionsItemSelected(item)
    }


}