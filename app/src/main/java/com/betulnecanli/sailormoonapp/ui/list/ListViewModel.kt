package com.betulnecanli.sailormoonapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.betulnecanli.sailormoonapp.data.remote.model.SailorMoon
import com.betulnecanli.sailormoonapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    val characters = repository.getAllCharacters().asLiveData()

    private val taskEventChannel = Channel<TaskEvent>()

    val taskEvent = taskEventChannel.receiveAsFlow()



    fun openCharacterDetails(details: SailorMoon) = viewModelScope.launch {
        taskEventChannel.send(TaskEvent.NavigateToDetailScreen(details))

    }

    sealed class TaskEvent{

        data class NavigateToDetailScreen(val character: SailorMoon) : TaskEvent()

    }

}