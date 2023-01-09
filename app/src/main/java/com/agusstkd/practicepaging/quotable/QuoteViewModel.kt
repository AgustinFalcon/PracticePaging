package com.agusstkd.practicepaging.quotable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class QuoteViewModel @Inject constructor(private val quoteRepository: QuoteRepository): ViewModel() {

    val list = quoteRepository.getQuotes().asFlow().cachedIn(viewModelScope)



}