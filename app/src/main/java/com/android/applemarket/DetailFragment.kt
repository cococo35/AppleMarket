package com.android.applemarket

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.applemarket.databinding.FragmentDetailBinding
class DetailFragment : Fragment() {
    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = arguments?.getParcelable<AppleItem>("Apple_Key")

        data?.let {
            binding.imgDetailSample.setImageResource(it.appleImg)
            binding.txDetailSeller.text = it.appleSeller
            binding.txDetailAddress.text = it.appleAddress
            binding.txDetailName.text = it.appleName
            binding.txDetailIntro.text = it.appleIntro
            binding.txDetailPrice.text = it.applePrice
        }

        binding.btnDetailBack.setOnClickListener{
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
        }

        binding.txDetailManner.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        return binding.root
    }
}