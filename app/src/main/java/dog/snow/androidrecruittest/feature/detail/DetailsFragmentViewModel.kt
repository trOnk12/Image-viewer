package dog.snow.androidrecruittest.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mateusz.pokemon.common.functional.Result
import com.app.mateusz.pokemon.common.functional.SingleLiveData
import dog.snow.androidrecruittest.domain.usecase.GetAlbumCombinedData
import dog.snow.androidrecruittest.feature.detail.model.Detail
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragmentViewModel @Inject constructor(
    private val getAlbumPhoto: GetAlbumCombinedData
) : ViewModel() {

    private val _detailsFragmentEvent =
        SingleLiveData<DetailsViewEvent>()
    val detailsFragmentEvent: LiveData<DetailsViewEvent>
        get() = _detailsFragmentEvent

    private val _detail = MutableLiveData<Detail>()
    val detail: LiveData<Detail>
        get() = _detail

    fun getPhoto(photoId: Int) {
        viewModelScope.launch {
            when (val result = getAlbumPhoto(photoId)) {
                is Result.Success -> _detail.value =
                    Detail(
                        result.data.photo.id,
                        result.data.photo.title,
                        result.data.album.title,
                        result.data.user.userName,
                        result.data.user.email,
                        result.data.user.phone,
                        result.data.photo.url
                    )

                is Result.Error -> _detailsFragmentEvent.value =
                    DetailsViewEvent.ShowErrorMessage(result.exception.message)
            }
        }
    }

}

sealed class DetailsViewEvent {
    data class ShowErrorMessage(val message: String?) : DetailsViewEvent()
}