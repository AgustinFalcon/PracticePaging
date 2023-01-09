package com.agusstkd.practicepaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import com.agusstkd.practicepaging.databinding.ActivityMainBinding
import com.agusstkd.practicepaging.quotable.QuotePagingAdapter
import com.agusstkd.practicepaging.quotable.QuoteViewModel

import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch





@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var madapter: QuotePagingAdapter
    private val quoteViewModel: QuoteViewModel by viewModels()


    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        madapter = QuotePagingAdapter()

        /*lifecycleScope.launch {
            characterViewModel.photoList.collect {
                madapter.submitData(it)
                Log.d("aaa", "load: ${it.toString()}")
            }
        }*/

        lifecycleScope.launch {
            quoteViewModel.list.collectLatest {
                madapter.submitData(lifecycle, it)
            }
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            /*layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )*/
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = madapter
        }

    }
}