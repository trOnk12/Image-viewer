package dog.snow.androidrecruittest.feature.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mateusz.pokemon.common.functional.Result
import com.app.mateusz.pokemon.common.functional.SingleLiveData
import dog.snow.androidrecruittest.core.interactor.None
import dog.snow.androidrecruittest.domain.usecase.GetPhotoCombinedData
import dog.snow.androidrecruittest.feature.detail.model.Detail
import dog.snow.androidrecruittest.feature.list.model.ListItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListFragmentViewModel
@Inject constructor(
    private val getPhotoCombinedData: GetPhotoCombinedData
) : ViewModel() {

    private val _listFragmentViewEvent =
        SingleLiveData<ListFragmentViewEvent>()
    val listFragmentViewEvent: LiveData<ListFragmentViewEvent>
        get() = _listFragmentViewEvent

    private val _listItem = MutableLiveData<List<ListItem>>()
    val listItem: LiveData<List<ListItem>>
        get() = _listItem

    fun fetchData() {
        viewModelScope.launch {
            when (val result = getPhotoCombinedData(None())) {
                is Result.Success -> _listItem.value =
                    result.data.map {
                        ListItem(
                            ListItem.PhotoInfo(
                                it.photo.id,
                                it.photo.title,
                                it.photo.thumbnailUrl
                            ),
                            ListItem.AlbumInfo(
                                it.photo.thumbnailUrl
                            )
                        )
                    }
                is Result.Error -> _listFragmentViewEvent.value =
                    ListFragmentViewEvent.ShowErrorMessage(result.exception.message)
            }
        }
    }

}

sealed class ListFragmentViewEvent {
    data class ShowErrorMessage(val message: String?) : ListFragmentViewEvent()
}

