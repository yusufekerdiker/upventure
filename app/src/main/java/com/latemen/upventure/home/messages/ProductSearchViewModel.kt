package com.latemen.upventure.home.messages
/*
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.latemen.upventure.extensions.Constants
import androidx.paging.cachedIn
import com.latemen.upventure.extensions.Event

class ProductSearchViewModel  : ViewModel() {

    private var currentUserSearch: String = ""
    private var pagingSource: ProductSearchPagingSource? = null
        get() {
            if (field == null || field?.invalid == true) {
                field = ProductSearchPagingSource(currentUserSearch) { localException ->
                    // Notify our LiveData of an issue from the PagingSource
                    _localExceptionEventLiveData.postValue(Event(localException))
                }
            }

            return field
        }

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            prefetchDistance = Constants.PREFETCH_DISTANCE,
            enablePlaceholders = false
        )
    ) {
        pagingSource!!
    }.flow.cachedIn(viewModelScope)

    // For error handling propagation
    private val _localExceptionEventLiveData = MutableLiveData<Event<ProductSearchPagingSource.LocalException>>()
    val localExceptionEventLiveData: LiveData<Event<ProductSearchPagingSource.LocalException>> = _localExceptionEventLiveData

    fun submitQuery(userSearch: String) {
        currentUserSearch = userSearch
        pagingSource?.invalidate()
    }
}*/
