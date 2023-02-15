package com.betulnecanli.sailormoonapp.ui.list

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betulnecanli.sailormoonapp.R
import com.betulnecanli.sailormoonapp.data.remote.model.SailorMoon
import com.betulnecanli.sailormoonapp.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list), ListAdapter.OnItemClickListener{

    private lateinit var binding : FragmentListBinding
    private lateinit var mAdapter : ListAdapter
    private val viewModel : ListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentListBinding.bind(view)

        mAdapter = ListAdapter(this)
        binding.sailorRecyc.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        viewModel.characters.observe(viewLifecycleOwner){
            mAdapter.submitData(viewLifecycleOwner.lifecycle,it)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.taskEvent.collect { event ->
                when(event){
                    is ListViewModel.TaskEvent.NavigateToDetailScreen ->
                    {
                        val action = ListFragmentDirections.actionListFragmentToDetailFragment(event.character)
                         findNavController().navigate(action)
                    }
                    else -> {}
                }


            }
        }

    }

    override fun onItemClickListener(character: SailorMoon) {
        viewModel.openCharacterDetails(character)
    }


}