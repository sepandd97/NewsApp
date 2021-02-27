package com.roshastudio.newsapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.roshastudio.newsapp.R
import com.roshastudio.newsapp.databinding.NewsDescFragmentBinding
import com.roshastudio.newsapp.model.BookMarks

import com.roshastudio.newsapp.viewmodel.NewsDescVM

class FragmentNewsDesc : Fragment() {
    private var binding: NewsDescFragmentBinding? = null
    private lateinit var newsDescVM: NewsDescVM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.news_desc_fragment, container, false)
        newsDescVM = ViewModelProvider(this).get(NewsDescVM::class.java)


        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.apply {
            val id = arguments?.getInt("id")
            if (id != null) {
                newsDescVM.getFullNews(id).observe(viewLifecycleOwner, Observer {
                    this!!.dscnews = it
                })
                //change the book mark icon depend on exigence of the news id in
                //book_mark_tbl.
                newsDescVM.ifExist(id).observe(viewLifecycleOwner, Observer {
                    if (it == true) {
                        descFragBookMark.contentDescription = "blue"
                        descFragBookMark.setImageResource(R.drawable.ic_baseline_bookmark_24)
                    } else {
                        descFragBookMark.contentDescription = "black"
                        descFragBookMark.setImageResource(R.drawable.ic_baseline_bookmark_24_black)

                    }
                })

            }
            //Setting book mark
            descFragBookMark.setOnClickListener {

                if (descFragBookMark.contentDescription.equals("black")) {
                    Toast.makeText(context, "پست در بوک مارک ها ذخیره شد", Toast.LENGTH_SHORT).show()
                    descFragBookMark.contentDescription = "blue"
                    descFragBookMark.setImageResource(R.drawable.ic_baseline_bookmark_24)
                    newsDescVM.saveBookmarks(BookMarks(id))
                } else {
                    Toast.makeText(context, "پست از بوک مارک ها حذف شد", Toast.LENGTH_SHORT).show()
                    descFragBookMark.contentDescription = "black"
                    descFragBookMark.setImageResource(R.drawable.ic_baseline_bookmark_24_black)
                    newsDescVM.delete(BookMarks(id))

                }
            }
        }
    }
}