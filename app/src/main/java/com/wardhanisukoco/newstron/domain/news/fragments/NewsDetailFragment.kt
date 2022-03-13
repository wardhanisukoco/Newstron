package com.wardhanisukoco.newstron.domain.news.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso
import com.wardhanisukoco.newstron.R
import com.wardhanisukoco.newstron.databinding.FragmentNewsDetailBinding
import com.wardhanisukoco.newstron.domain.news.models.Article

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
            LayoutInflater.from(context),
            null,
            false
        )
        return binding.root
    }
    private fun setupView() {
        binding.toolbar.setNavigationOnClickListener { dismiss() }
        Picasso.with(context)
            .load(article.urlToImage)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.image)
        binding.title.text = article.title
        binding.content.text = article.description

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
