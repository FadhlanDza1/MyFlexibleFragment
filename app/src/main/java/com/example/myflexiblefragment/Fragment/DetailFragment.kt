package com.example.myflexiblefragment.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myflexiblefragment.databinding.FragmentDetailBinding
import com.example.myflexiblefragment.Fragment.DialogFragment

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    var description: String? = null
    companion object{
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESC = "extra_desc"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null){
            val descFromBundle = savedInstanceState.getString(EXTRA_DESC)
            description = descFromBundle
        }
        if (arguments != null){
            //untuk pengambilan data yang dikirim oleh objek bundle di catFragment
            val catName = arguments?.getString(EXTRA_NAME)
            binding.catVw.text = catName
            binding.catDescVw.text = description
        }

        binding.btnDialog.setOnClickListener {
            val optionDialogFragment = DialogFragment()

            val fragmentManager = childFragmentManager
            //lalu internal var yang meng-herite dari dialog fragment ditampilkan ke layout
            optionDialogFragment.show(fragmentManager, DialogFragment::class.java.simpleName)
        }

        binding.btnProfile.setOnClickListener {
            //penggunaan requireActivity() diwajibkan karna fungsi tersebut untuk mendapatkan context dari activity tempat fragment ditempel
            val mIntent = Intent(requireActivity(), ProfileFragment::class.java)
            startActivity(mIntent)
        }
    }
    //menggunakan interface OnOptionDialogListener dari FragmentDialog
    internal var optionDialogListener: DialogFragment.OnOptionDialogListener = object : DialogFragment.OnOptionDialogListener {
        override fun onOptionChosen(text: String?) {
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }

    }
}