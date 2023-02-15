package com.betulnecanli.sailormoonapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.betulnecanli.sailormoonapp.R
import com.betulnecanli.sailormoonapp.databinding.FragmentDetailBinding
import com.betulnecanli.sailormoonapp.utils.Constants.BASE_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding : FragmentDetailBinding
    val args : DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDetailBinding.bind(view)
        binding.apply {
            charName.text = args.character.name
            val imgLink = BASE_URL + args.character.image
            charImg.load(imgLink){
                crossfade(true)
                crossfade(1000)
            }

            charAge.text = "Age: "+args.character.age.toString()
            charBirthday.text = "Birthday: " + args.character.birthday.toString()
            charRealName.text = "Real Name: " + args.character.realName
            charSpecies.text = "Species: " + args.character.species

        }
    }


}