package dog.snow.androidrecruittest.feature.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.SnowDogApp
import dog.snow.androidrecruittest.core.extension.observe
import dog.snow.androidrecruittest.feature.list.adapter.ListAdapter
import dog.snow.androidrecruittest.feature.list.model.ListItem
import kotlinx.android.synthetic.main.layout_search.*
import kotlinx.android.synthetic.main.list_fragment.*
import javax.inject.Inject

class ListFragment : Fragment(R.layout.list_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var adapter: ListAdapter

    private val listItemViewModel: ListFragmentViewModel by lazy {
        viewModelFactory.create(ListFragmentViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        initializeDaggerDependency()
        super.onAttach(context)
    }

    private fun initializeDaggerDependency() {
        DaggerListFragmentComponent
            .builder()
            .coreComponent(SnowDogApp.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()

        observe(listItemViewModel.listFragmentViewEvent, ::onViewEvent)

        listItemViewModel.fetchData()
    }

    private fun initializeView() {
        adapter = ListAdapter(::onItemClick, ::onFilterResult).also {
            rv_items.adapter = it
        }

        et_search.addTextChangedListener(object : OnSearchTermChangedListener {
            override fun onSearchTermChanged(p0: CharSequence?) {
                p0?.let {
                    adapter.filterItems(p0)
                }
            }
        })
    }

    private fun onItemClick(listItem: ListItem, position: Int, imageView: ImageView) {
        val extras = FragmentNavigatorExtras(
            imageView to listItem.albumInfo.thumbnailUrl
        )
        val action = ListFragmentDirections.actionListFragmentToDetailsFragment(
            listItem.photoInfo.id,
            listItem.albumInfo.thumbnailUrl
        )
        findNavController().navigate(action, extras)
    }

    private fun onViewEvent(viewEvent: ListFragmentViewEvent) {
        when (viewEvent) {
            is ListFragmentViewEvent.LoadListItems -> {
                onListItemLoaded(viewEvent.listItems)
            }
            is ListFragmentViewEvent.ShowErrorMessage -> showError(viewEvent.message)
        }
    }

    private fun onFilterResult(list: List<ListItem>) {
        if (list.isEmpty()) {
            empty_view.visibility = View.VISIBLE
            rv_items.visibility = View.GONE
        } else {
            empty_view.visibility = View.GONE
            rv_items.visibility = View.VISIBLE
            adapter.submitList(list)
        }
    }

    private fun onListItemLoaded(listItems: List<ListItem>) {
        adapter.setData(listItems)

        rv_items.visibility = View.VISIBLE
        empty_view.visibility = View.GONE
    }

    private fun showError(errorMessage: String?) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }

}


