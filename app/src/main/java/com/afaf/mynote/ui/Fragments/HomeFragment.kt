package com.afaf.mynote.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.afaf.mynote.R
import com.afaf.mynote.ViewModel.NotesViewModel
import com.afaf.mynote.databinding.FragmentHomeBinding
import com.afaf.mynote.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater , container ,false)

        viewModel.getNotes().observe(viewLifecycleOwner,{ notesList ->
          binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
        })

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNoteFragment)
        }
        return binding.root
    }


}