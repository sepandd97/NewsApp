package com.roshastudio.newsapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.roshastudio.newsapp.R
import com.roshastudio.newsapp.databinding.BookmarkFragmentBinding
import com.roshastudio.newsapp.model.News
import com.roshastudio.newsapp.view.adapters.AllNewsAdp
import com.roshastudio.newsapp.viewmodel.BookMarkVm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentBookMark : Fragment(),AllNewsAdp.Interaction{
    private var binding: BookmarkFragmentBinding? = null
    private lateinit var bookMarkVm: BookMarkVm
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bookmark_fragment, container, false)
        bookMarkVm = ViewModelProvider(this).get(BookMarkVm::class.java)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBookMarks()
    }

    private fun setBookMarks() {
        val allNewsAdp = AllNewsAdp(requireContext(),this)

        binding!!.apply {

            bookMarksNewsRv.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            bookMarksNewsRv.adapter = allNewsAdp
            bookMarkVm.allBookMarks.observe(viewLifecycleOwner, Observer { it ->
                lifecycleScope.launch(Dispatchers.Main) {
                allNewsAdp.submitList(bookMarkVm.getFullNews(it))}
                 })


        }
    }
//navigate to new fragment
    override fun onItemSelected(position: Int, item: News) {
        val bundle = Bundle()
        bundle.putInt("id",item.id)
        Navigation.findNavController(binding!!.bookMarksNewsRv).navigate(R.id.action_fragmentBookMark_to_fragmentNewsDesc,bundle)
    }
}