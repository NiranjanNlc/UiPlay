package com.example.playwithui.viewmodal


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.playwithui.modal.data.Song
import com.example.playwithui.modal.repository.SongRepo
import com.example.playwithui.util.Coroutines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: SongRepo) : ViewModel()
{
    private lateinit var job: Job
    val song= MutableLiveData<Song>()
    var viewButton= MutableLiveData<Boolean> (true)
    var addButton= MutableLiveData<Boolean> (true)
    var dialogButton= MutableLiveData<Boolean> (true)
    val _songs =repository.allSongs
    val songs  = _songs
    fun addSinger()
    {
    }
    fun viewFragment()
    {
        println("Heelo view fragment"+ viewButton.value)
       viewButton.value=false
        println("Heelo view fragment"+ viewButton.value)
    }
    fun addFragment()
    {
        println("Heelo view fragment"+addButton.value)
        addButton.value=false
        println("Heelo view fragment"+ addButton.value)
    }
    fun getSinger(): LiveData<List<Song>> {

        return repository.allSongs
    }
    fun cancelDialog()
    {
      dialogButton.value=false
       // addButton.value=true
    }
    fun addDialog()
    {
        println("Niranjan Lamichhane        ${song.value} ")
        job  = Coroutines.ioThenMain(
            {repository.insert(song.value!!)},
            {song.value = null }
        )
     cancelDialog()
    }
    init {
        println("INitialized view modl")
    }
    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}
