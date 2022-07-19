package com.example.playwithui.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playwithui.viewmodal.MainViewModel
import com.example.playwithui.R
import com.example.playwithui.databinding.SongsFragmentBinding
import com.example.playwithui.modal.database.SongDataBase
import com.example.playwithui.modal.repository.SongRepo
import com.example.playwithui.view.adapter.SongAdapter
import com.example.playwithui.viewmodal.ViewModalFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainFragment : Fragment() {



    private lateinit var binding: SongsFragmentBinding
    private lateinit var viewModel: MainViewModel
    private val adapter= SongAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        println("On activty create")
        println(" ON Activity creATED")
        binding   = DataBindingUtil.setContentView(requireActivity(), R.layout.songs_fragment)
        bindData()
        println("Binded dat")
        initRecyclerView()
        println("recycler view initialied")
        return inflater.inflate(R.layout.songs_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeChange()

    }

    private fun initRecyclerView()
    {
        print(" recycler view initiated")
        binding.recyclerview.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.adapter=  adapter
        adapter.submitList(viewModel.songs.value)
    }
    private fun bindData()
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
        viewModel.songs.observe(requireActivity(), Observer {
            println(" Observed "+it.toString())
            adapter.submitList(it)
        })
        println("After observing0")
    }

}
