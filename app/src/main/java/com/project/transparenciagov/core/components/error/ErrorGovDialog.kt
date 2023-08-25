package com.project.transparenciagov.core.components.error

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.project.transparenciagov.databinding.ErrorGovComponentBinding


class ErrorGovDialog : DialogFragment() {

    private lateinit var binding: ErrorGovComponentBinding

    var messageError: String? = ""
    var imageError: Drawable? = null
    var tryAgainListener: (() -> Unit) = {}
    var okGotIt: (() -> Unit) = {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ErrorGovComponentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        setImage()
        setMessageError()
        onClickListener()
    }


    private fun setMessageError() {
        binding.message.text = messageError
    }

    private fun setImage() {
        binding.image.setImageDrawable(imageError)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if(isAdded) return
        super.showNow(manager, tag)
    }

    private fun onClickListener() {
        binding.okGotIt.setOnClickListener { dismiss()}
        binding.tryAgain.setOnClickListener {
            dismiss()
            tryAgainListener.invoke()
        }
    }
}
