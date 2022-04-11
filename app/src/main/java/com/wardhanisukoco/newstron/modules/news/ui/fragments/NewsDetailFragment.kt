package com.wardhanisukoco.newstron.modules.news.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso
import com.wardhanisukoco.newstron.R
import com.wardhanisukoco.newstron.databinding.FragmentNewsDetailBinding
import com.wardhanisukoco.newstron.modules.news.domain.models.Article

class NewsDetailFragment(private val article: Article) : DialogFragment(){
    private var _binding: FragmentNewsDetailBinding? = null
    private val binding: FragmentNewsDetailBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentNewsDetailBinding.inflate(
            layoutInflater,
            null,
            false
        )
        return binding.root
    }
    private fun setupView() {
        binding.toolbar.setNavigationOnClickListener { dismiss() }
        Picasso.with(context)
            .load(article.urlToImage)
            .placeholder(R.drawable.bg_gradient)
            .into(binding.image)
        binding.title.text = article.title
        binding.desc.text = article.description
        binding.content.text = article.content

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
            dialog.window?.setWindowAnimations(R.style.DialogSide)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
