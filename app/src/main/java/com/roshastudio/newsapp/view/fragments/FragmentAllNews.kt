package com.roshastudio.newsapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.roshastudio.newsapp.R
import com.roshastudio.newsapp.databinding.AllnewsFragmentBinding
import com.roshastudio.newsapp.model.News
import com.roshastudio.newsapp.view.adapters.AllNewsAdp
import com.roshastudio.newsapp.viewmodel.AllNewsVM

class FragmentAllNews : Fragment(), AllNewsAdp.Interaction {
    private var binding: AllnewsFragmentBinding? = null
    private lateinit var allNewsVM: AllNewsVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.allnews_fragment, container, false)
        allNewsVM = ViewModelProvider(this).get(AllNewsVM::class.java)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRvAllNews()

    }

    private fun setRvAllNews() {
        binding!!.apply {
val adp =  AllNewsAdp(requireContext(),this@FragmentAllNews)
            allNewsRv.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

            allNewsRv.adapter=   adp

            allNewsVM.allNews.observe(viewLifecycleOwner, Observer { News ->
                // Update the cached copy of the News in the adapter.
                News?.let { adp.submitList(it) }
            })
        }
    }
//navigate to new fragment
    override fun onItemSelected(position: Int, item: News) {
        val bundle = Bundle()
        bundle.putInt("id", item.id)
        Navigation.findNavController(binding!!.allNewsRv)
            .navigate(R.id.action_fragmentNews_to_fragmentNewsDesc, bundle)
    }
}