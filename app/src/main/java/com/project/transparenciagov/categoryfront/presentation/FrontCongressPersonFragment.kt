package com.project.transparenciagov.categoryfront.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.project.transparenciagov.categoryfront.presentation.action.FrontAction
import com.project.transparenciagov.core.components.bottomsheets.ResultsFrontBottomSheet
import com.project.transparenciagov.core.components.error.ErrorGovDialog
import com.project.transparenciagov.databinding.FragmentFrontCongressPersonBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

const val OPEN_URL_TEXT = "BAIXAR PDF"

class FrontCongressPersonFragment : Fragment() {

    private lateinit var binding: FragmentFrontCongressPersonBinding
    private val viewModel by viewModel<FrontCongressPersonViewModel> {
        parametersOf(arguments?.getInt(KEY_ARGS_ID))
    }
    private val adapter: FrontCongressPersonAdapter by lazy {
        FrontCongressPersonAdapter()
    }

    private val errorDialog by lazy { ErrorGovDialog() }
    private val frontBottomSheet by lazy { ResultsFrontBottomSheet() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFrontCongressPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservable()
        setupListeners()
        binding.recyclerPerson.adapter = adapter
    }

    private fun setupListeners() {
        frontBottomSheet.openUrl = {
            startActivity(
                Intent(Intent.ACTION_VIEW, it.toUri())
            )
        }
        adapter.onKnowMore = {
            viewModel.onKnowMore(it)
        }
    }


    private fun setupObservable() {
        lifecycleScope.launch {
            viewModel.stateLiveData.collect { state ->
                adapter.listFront = state.list
                binding.loading.isVisible = state.loading
            }
        }
        lifecycleScope.launch {
            viewModel.eventLiveData.collect { event ->
                when (event) {
                    is FrontAction.ShowError -> {
                        errorDialog.messageError = event.message
                        errorDialog.imageError = event.imageError
                        errorDialog.show(childFragmentManager, "")
                    }

                    is FrontAction.OpenBottomSheet -> {
                        frontBottomSheet.show(childFragmentManager)
                    }

                    is FrontAction.UpdateDataBottomSheet -> {
                        frontBottomSheet.setupData(event.resultFrontCongress)
                    }

                }
            }
        }
    }

    companion object {
        const val KEY_ARGS_ID = "ID"
    }
}