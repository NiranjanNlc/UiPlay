package com.example.playwithui.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playwithui.R
import com.example.playwithui.databinding.MainActivityBinding
import com.example.playwithui.view.adapter.SongAdapter
import com.example.playwithui.viewmodal.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private val adapter= SongAdapter()
    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.main_activity)
        bindData()
        initRecyclerView()
        observeChange() 
        }
    fun bindData()
    {
        binding.lifecycleOwner=this
//        initialiseSampleViewModal()
        binding.viewModal=viewModel
        println(" Sample ViewModal  views "+ viewModel.viewButton.value.toString())
    }
    private fun initRecyclerView()
    {
        print(" recycler view initiated")
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.adapter=  adapter
        adapter.submitList(viewModel.songs.value)
    }

    private fun observeChange()
    {
        viewModel.songs.observe(this, Observer {
            println(" Observed "+it.toString())
            adapter.submitList(it)
        })
        println("After observing0")
    }
}
