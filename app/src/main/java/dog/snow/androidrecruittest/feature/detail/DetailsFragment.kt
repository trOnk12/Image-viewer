package dog.snow.androidrecruittest.feature.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.SnowDogApp
import dog.snow.androidrecruittest.core.extension.observe
import dog.snow.androidrecruittest.databinding.DetailsFragmentBinding
import kotlinx.android.synthetic.main.details_fragment.*
import javax.inject.Inject

class DetailsFragment : Fragment(R.layout.details_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val args: DetailsFragmentArgs by navArgs()

    private lateinit var detailsFragmentBinding: DetailsFragmentBinding
    private val detailsFragmentViewModel: DetailsFragmentViewModel by lazy {
        viewModelFactory.create(DetailsFragmentViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        initializeDaggerDependency()
        super.onAttach(context)
    }

    private fun initializeDaggerDependency() {
        DaggerDetailsFragmentComponent
            .builder()
            .coreComponent(SnowDogApp.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsFragmentBinding = DetailsFragmentBinding.inflate(inflater, container, false)

        return detailsFragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(detailsFragmentViewModel.detailsFragmentEvent, ::onViewEvent)

        detailsFragmentBinding.ivPhoto.transitionName = args.imageUri

        detailsFragmentViewModel.getPhoto(args.photoId)
    }

    private fun onViewEvent(viewEvent: DetailsViewEvent) {
        when (viewEvent) {
            is DetailsViewEvent.ShowPhotoEvent -> detailsFragmentBinding.detail = viewEvent.detail
        }
    }

}