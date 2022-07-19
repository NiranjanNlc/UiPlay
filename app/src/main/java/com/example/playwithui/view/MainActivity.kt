package com.example.playwithui.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.playwithui.R
import com.example.playwithui.databinding.MainActivityBinding
import com.example.playwithui.modal.database.SongsDataBase
import com.example.playwithui.modal.repository.SongRepository
import com.example.playwithui.ui.main.MainFragment
import com.example.playwithui.view.adapter.SongAdapter
import com.example.playwithui.view.main.AddFragment
import com.example.playwithui.viewmodal.MainViewModel
import com.example.playwithui.viewmodal.ViewModalFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainActivity : AppCompatActivity()
{
    private val adapter= SongAdapter()
    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.main_activity)
        bindData()
        }
    fun bindData()
    {
        binding.lifecycleOwner=this
        initialiseSampleViewModal()
        binding.viewModal=viewModel
        println(" Sample ViewModal  views "+ viewModel.viewButton.value.toString())
    }

    private fun initialiseSampleViewModal() {
        val applicationScope = CoroutineScope(SupervisorJob())
        val database = SongsDataBase.getDatabase(this, applicationScope)
        val repository = SongRepository(database.songDao())
        viewModel= ViewModalFactory( repository).create(MainViewModel::class.java)
    }
    private fun observeChange()
    {
        viewModel.viewButton.observe(this, Observer{
            println("visibility"+it)
            if(it==false) {
                supportFragmentManager.beginTransaction().add(R.id.container, MainFragment())
                    .commitNow()
                println("rdfghjkl")
            }
        })
        viewModel.addButton.observe(this, Observer{
            println("visibility"+it)
            if(it==false) {
               //code to add dialog
             AddFragment().showsDialog
            }
        })
        
    }
}
