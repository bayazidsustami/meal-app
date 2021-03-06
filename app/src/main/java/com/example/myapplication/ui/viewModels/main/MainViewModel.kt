package com.example.myapplication.ui.viewModels.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.ResponseMealsCategory
import com.example.myapplication.data.repositories.categories.CategoryRepository
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CategoryRepository): ViewModel() {
    private val _state = MutableLiveData<Resource<ResponseMealsCategory>>()
    val states: LiveData<Resource<ResponseMealsCategory>> get() = _state

    fun loadAllCategories(){
        viewModelScope.launch {
            repository.getAllCategories()
                ?.onStart {
                    _state.postValue(Resource.onLoading(data = null))
                }
                ?.catch {
                    _state.postValue(Resource.onFailed(data = null, message = null))
                }
                ?.collect { category ->
                    _state.postValue(Resource.onSuccess(data = category))
                }
        }
    }
}