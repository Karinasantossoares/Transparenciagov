package com.project.transparenciagov.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.project.transparenciagov.R
import com.project.transparenciagov.core.components.error.ErrorGovDialog
import com.project.transparenciagov.core.ext.loadImage
import com.project.transparenciagov.databinding.FragmentCongressPersonDetailBinding
import com.project.transparenciagov.detail.ui.action.DetailCongressPersonAction
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailCongressPersonFragment : Fragment() {

    private lateinit var binding: FragmentCongressPersonDetailBinding
    private val viewModel by viewModel<DetailCongressPersonViewModel> {
        parametersOf(arguments?.getInt(KEY_ARGS_ID))
    }
    private val adapter: ExpenseDetailCongressPersonAdapter by lazy {
        ExpenseDetailCongressPersonAdapter()
    }

    private val errorDialog by lazy { ErrorGovDialog() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCongressPersonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservable()
        setupListeners()
        binding.recyclerViewDetail.adapter = adapter
    }

    private fun setupListeners() {
        binding.category1.root.setOnClickListener {
            viewModel.navigateToFrontCongress()
        }
    }


    private fun setupObservable() {
        lifecycleScope.launch {
            viewModel.stateLiveData.collect { state ->
                with(binding) {
                    with(state.detailModel.lastStatus) {
                        toolbar.title = state.detailModel.civilName
                        imageCongressPerson.loadImage(urlFoto)
                        emailTextView.text = email
                        numberdateOfBirth.text = state.detailModel.dateOfBirthday
                        tvNameParty.text = siglaPartido
                        tvPhoneNumber.text = gabinete.telefone
                        statusSituation.text = situacao

                    }

                }
                adapter.lisExpense = state.listExpenseModel

            }
        }
        lifecycleScope.launch {
            viewModel.eventLiveData.collect { event ->
                when (event) {
                    is DetailCongressPersonAction.ShowError -> {
                        errorDialog.messageError = event.message
                        errorDialog.imageError = event.imageError
                        errorDialog.show(childFragmentManager, "")
                    }

                    is DetailCongressPersonAction.ShowToast -> {
                        Snackbar.make(binding.root, event.message, Snackbar.LENGTH_LONG).show()

                    }

                    is DetailCongressPersonAction.NavigateToFront -> {
                        findNavController().navigate(
                            R.id.detailToFront, bundleOf(
                                KEY_ARGS_ID to event.id
                            )
                        )
                    }

                }
            }
        }
    }

    companion object {
        const val KEY_ARGS_ID = "ID"
    }
}