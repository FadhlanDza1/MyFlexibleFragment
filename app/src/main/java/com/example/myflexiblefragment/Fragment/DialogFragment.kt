package com.example.myflexiblefragment.Fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myflexiblefragment.R
import com.example.myflexiblefragment.databinding.FragmentDialogBinding


class DialogFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogBinding
    private var optionDialogListener: OnOptionDialogListener? = null

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnChoose.setOnClickListener {
            val checkedRBID = binding.rdSaf.checkedRadioButtonId
            if (checkedRBID != -1) {
                val coach: String? = when (checkedRBID) {
                    R.id.rb_saf -> binding.rbSaf.text.toString().trim()
                    R.id.rb_mou -> binding.rbMou.text.toString().trim()
                    R.id.rb_lvg -> binding.rbLvg.text.toString().trim()
                    R.id.rb_moyes -> binding.rbMoyes.text.toString().trim()
                    else -> null
                }
                optionDialogListener?.onOptionChosen(coach)
                dialog?.dismiss()
            }
        }
        binding.btnClose.setOnClickListener {
            dialog?.cancel()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment

        if (fragment is DetailFragment) {
            this.optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }


}
