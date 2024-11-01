package com.example.myflexiblefragment.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myflexiblefragment.R
import com.example.myflexiblefragment.databinding.FragmentCategoryBinding


class CategoryFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCat.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_cat){
            val detailFragment = DetailFragment()
            //mengirim data antar fragment menggunakan objek bundle atau bisa disebut intent-nya fragment
            val bundle = Bundle()
            bundle.putString(DetailFragment.EXTRA_NAME, "LifeStyle")
            val description = "Kategori ini akan berisi produk-produk lifestyle"

            detailFragment.arguments = bundle
            detailFragment.description = description

            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.frame_container, detailFragment, DetailFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }

        }
    }

}