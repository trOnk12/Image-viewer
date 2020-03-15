package dog.snow.androidrecruittest.feature.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mateusz.pokemon.common.functional.Result
import com.app.mateusz.pokemon.common.functional.SingleLiveData
import dog.snow.androidrecruittest.core.interactor.None
import dog.snow.androidrecruittest.domain.usecase.DownloadPhotoDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel
@Inject constructor(
    private val getPhotoUseCase: DownloadPhotoDataUseCase
) : ViewModel() {

    private val _splashViewEvent = SingleLiveData<SplashViewEvent>()
    val splashViewEvent: LiveData<SplashViewEvent>
        get() = _splashViewEvent

    init {
        fetchData()
    }

    private fun fetchData() {
        _splashViewEvent.value = SplashViewEvent.IsLoading

        viewModelScope.launch {
            when (val result = getPhotoUseCase(None())) {
                is Result.Success -> _splashViewEvent.value = SplashViewEvent.NavigateToMainScreen
                is Result.Error -> _splashViewEvent.value =
                    SplashViewEvent.ShowErrorMessage(result.exception.message)
            }
        }
    }

}

sealed class SplashViewEvent {
    object IsLoading : SplashViewEvent()
    object NavigateToMainScreen : SplashViewEvent()
    data class ShowErrorMessage(val message: String?) : SplashViewEvent()
}