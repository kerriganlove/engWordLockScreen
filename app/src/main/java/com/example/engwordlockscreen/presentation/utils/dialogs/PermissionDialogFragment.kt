package com.example.engwordlockscreen.presentation.utils.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.databinding.DialogGeneralFormBinding
import com.example.engwordlockscreen.databinding.DialogPermissionPopupBinding

class PermissionDialogFragment(
    private val onPositiveBtnClick : () -> Unit = {},
    private val onNegativeBtnClick : () -> Unit = {}
) : DialogFragment()
{
    private var _binding : DialogGeneralFormBinding? = null
    private val binding get() = _binding!!

    /*
     *  View Size value
     */

    private val defaultWidth by lazy {resources.getDimensionPixelSize(R.dimen.common_dialog_width)}
    private val defaultHeight by lazy { resources.getDimensionPixelSize(R.dimen.common_dialog_height)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.dialog_general_form, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            imageSrc = ContextCompat.getDrawable(requireContext(), R.drawable.ic_warning)
            mainDescriptionText = resources.getText(R.string.first_permission_popup_text)
            positiveBtnClick = {
                onPositiveBtnClick()
                dismiss()
            }
            negativeBtnClick = {
                onNegativeBtnClick()
                dismiss()
            }
        }
    }

    override fun onResume() {
        dialog?.window?.setLayout(defaultWidth,defaultHeight)
        super.onResume()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}