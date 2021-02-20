package com.example.myapplication.ui.viewModels.filters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.ResponseFilters
import com.example.myapplication.data.repositories.filters.FiltersRepository
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FilterViewModel(private val repository: FiltersRepository): ViewModel() {
    private val _dataFilter = MutableLiveData<Resource<ResponseFilters>>()
    val getDataFilter: LiveData<Resource<ResponseFilters>>
        get() = _dataFilter

    fun setDataFilter(params: Map<String, String>){
        viewModelScope.launch {
            repository.getFiltersBy(params)
                ?.onStart {
                    _dataFilter.postValue(Resource.onLoading(data = null))
                }
                ?.catch {
                    _dataFilter.postValue(Resource.onFailed(data = null, message = null))
                }
                ?.collect {
                    _dataFilter.postValue(Resource.onSuccess(data = it))
                }
        }
    }
}