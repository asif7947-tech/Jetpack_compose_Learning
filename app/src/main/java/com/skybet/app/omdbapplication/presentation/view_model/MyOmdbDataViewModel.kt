package com.skybet.app.omdbapplication.presentation.view_model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skybet.app.omdbapplication.R
import com.skybet.app.omdbapplication.data.remote.ApiResponseStates
import com.skybet.app.omdbapplication.data.repository.MyOmdbDataRepo
import com.skybet.app.omdbapplication.presentation.views.SearchWidgetState
import com.skybet.app.omdbapplication.utils.NetworkUtils
import com.skybet.app.omdbapplication.utils.getStringResourceByName
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import androidx.compose.runtime.State
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyOmdbDataViewModel @Inject constructor(
    private val myAppDataRepo: MyOmdbDataRepo ,
    @ApplicationContext val context: Context
): ViewModel() {

    private val _searchWidgetState: MutableState<SearchWidgetState> = mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState


    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    val customerDataStateFlow: MutableState<ApiResponseStates> = mutableStateOf(ApiResponseStates.Empty)

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun searchMoviesbyName(search_name:String = "movie")=viewModelScope.launch{
        Log.e("TAG", "searchMoviesbyName:  "+search_name )
        if (NetworkUtils.isInternetAvailable(context)) {
            Log.e("TAG", "searchMoviesbyName: call api "+search_name )

            myAppDataRepo.searchMoviesbyName(movieName = search_name)
                .onStart {
                    Log.e("TAG", "searchMoviesbyName: Loading ", )
                    customerDataStateFlow.value = ApiResponseStates.Loading
                }
                .catch { e ->
                    Log.e("TAG", "searchMoviesbyName: error "+e.message )
                    e.message?.let {
                        customerDataStateFlow.value =ApiResponseStates.onFailure(it)
                    }
                }
                .collect { response ->
                    Log.e("TAG", "searchMoviesbyName: onsucess "+response.totalResults )
                    Log.e("TAG", "searchMoviesbyName: onsucess "+response.response )
                    Log.e("TAG", "searchMoviesbyName: onsucess "+response.moviesList.size )

                    response.let { appointResponse ->
                        customerDataStateFlow.value =
                            ApiResponseStates.onSuccessResponse(data = appointResponse.moviesList)
                    }
                }
        } else {
            customerDataStateFlow.value =
                ApiResponseStates.onNetworkFailure(context.getStringResourceByName(R.string.str_internet_connection))
        }
    }
}