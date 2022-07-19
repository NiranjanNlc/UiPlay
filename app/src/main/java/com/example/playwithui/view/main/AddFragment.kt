package com.example.playwithui.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.playwithui.R
import com.example.playwithui.databinding.SongsFragmentBinding
import com.example.playwithui.modal.database.SongDataBase
import com.example.playwithui.modal.repository.SongRepo
import com.example.playwithui.view.adapter.SongAdapter
import com.example.playwithui.viewmodal.MainViewModel
import com.example.playwithui.viewmodal.ViewModalFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class AddFragment : DialogFragment() {

    companion object {
        fun newInstance() =AddFragment()
    }

    private lateinit var binding: SongsFragmentBinding
    private lateinit var viewModel: MainViewModel
    private val adapter= SongAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("On activty create")
        println(" ON Activity creATED")
        binding   = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_add)
        bindData()
        println("Binded dat")
        initRecyclerView()
        println("recycler view initialied")
        observeChange()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }
    private fun initRecyclerView()
    {
        print(" recycler view initiated")
        binding.recyclerview.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.adapter=  adapter
        adapter.submitList(viewModel.songs.value)
    }
    fun bindData()
    {
        binding.lifecycleOwner=requireActivity()
        initialiseSampleViewModal()
        println(" Sample ViewModal  views "+ viewModel.viewButton.value.toString())
    }

    private fun initialiseSampleViewModal() {
        val applicationScope = CoroutineScope(SupervisorJob())
        val database = SongDataBase.getDatabase(requireActivity(), applicationScope)
        val repository = SongRepo(database.songDao())
        viewModel= ViewModalFactory( repository).create(MainViewModel::class.java)
    }
    private fun observeChange()
    {
        viewModel.dialogButton.observe(requireActivity(), Observer {
            //ii
            if (it==false)
            {
                dismiss()
            }
        })
        println("After observing0")
    }



}
