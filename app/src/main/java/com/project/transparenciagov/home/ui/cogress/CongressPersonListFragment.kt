package com.project.transparenciagov.home.ui.cogress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.project.transparenciagov.R
import com.project.transparenciagov.core.components.error.ErrorGovDialog
import com.project.transparenciagov.core.components.bottomsheets.StatesBottomSheet
import com.project.transparenciagov.databinding.FragmentCongressPersonListBinding
import com.project.transparenciagov.detail.ui.DetailCongressPersonFragment.Companion.KEY_ARGS_ID
import com.project.transparenciagov.home.ui.cogress.action.CongressPersonAction
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class CongressPersonListFragment : Fragment() {

    private lateinit var binding: FragmentCongressPersonListBinding
    private val congressPersonAdapter by lazy {
        CongressPersonAdapter()
    }
    private val viewModel by viewModel<CongressPersonViewModel>()
    private val errorDialog by lazy { ErrorGovDialog() }
    private val statesBottomSheet by lazy { StatesBottomSheet() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCongressPersonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStop() {
        binding.shimmerViewContainer.stopShimmer()
        super.onStop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerPerson.adapter = congressPersonAdapter
        setupObservable()
        setupListener()
    }


    private fun setupObservable() {
        lifecycleScope.launch {
            with(binding) {
                viewModel.stateLiveData.collect { state ->
                    statesBottomSheet.list = state.listStatesModel
                    congressPersonAdapter.listCongressPerson = state.list
                    if (state.shimmerLoading.not()) {
                        shimmerViewContainer.stopShimmer()
                    } else {
                        shimmerViewContainer.startShimmer()
                    }

                    shimmerViewContainer.isVisible = state.shimmerLoading
                    recyclerPerson.isVisible = state.shimmerLoading.not()
                    customChip.bind(state.states.toMutableList())

                }
            }
        }
        lifecycleScope.launch {
            viewModel.eventLiveData.collect { event ->
                when (event) {
                    is CongressPersonAction.ShowError -> {
                        errorDialog.messageError = event.message
                        errorDialog.imageError = event.imageError
                        errorDialog.show(childFragmentManager, "")
                    }

                    is CongressPersonAction.ShowToast -> {
                        Snackbar.make(binding.root, event.message, Snackbar.LENGTH_LONG).show()

                    }

                    is CongressPersonAction.OpenBottomSheet -> statesBottomSheet.show(
                        requireActivity().supportFragmentManager
                    )

                }
            }
        }
    }

    private fun setupListener() {
        binding.customChip.onRemoveItem = {
            viewModel.confirmFilterStates(it)
            statesBottomSheet.removeFilterForChips(it.toMutableList())
        }
        binding.recyclerPerson.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.onPagination()
                }
            }
        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onClickSearch(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        binding.iconFilter.setOnClickListener {
            viewModel.openBottomSheet()
        }
        errorDialog.tryAgainListener = {
            viewModel.getAllCongressPerson(incrementPage = false)
        }

        errorDialog.okGotIt = {
            errorDialog.dismiss()
        }

        statesBottomSheet.requestStates = {
            viewModel.getAllStates()
        }
        statesBottomSheet.confirmButtonSelected = {
            viewModel.confirmFilterStates(states = it)
        }

        congressPersonAdapter.onClickItem = {
            findNavController().navigate(
                R.id.toCongressPersonDetail, bundleOf(
                    KEY_ARGS_ID to it.id
                )
            )
        }

    }
}