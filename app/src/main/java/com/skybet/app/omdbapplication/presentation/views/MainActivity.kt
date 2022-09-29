package com.skybet.app.omdbapplication.presentation.views

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skybet.app.omdbapplication.presentation.view_model.MyOmdbDataViewModel
import com.workerx.mycomposeapp.presentationlayer.ui.theme.MyOmdbAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private  val TAG = "MainActivity"
    private val myOmdbDataViewModel:MyOmdbDataViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyOmdbAppTheme() {
                MainScreenUi(myOmdbDataViewModel)
            }
        }
    }


    @Composable
    fun MainScreenUi(mainViewModel: MyOmdbDataViewModel) {
        val searchWidgetState by mainViewModel.searchWidgetState
        val searchTextState by mainViewModel.searchTextState
        Scaffold(
            topBar = {
                MainAppBar(
                    searchWidgetState = searchWidgetState,
                    searchTextState = searchTextState,
                    onTextChange = {
                        mainViewModel.updateSearchTextState(newValue = it)
                    },
                    onCloseClicked = {
                        mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                    },
                    onSearchClicked = { searchData ->
                        Log.e("TAG", "MainScreenUi:  data " + searchData)
                        mainViewModel.searchMoviesbyName(searchData)

                    },
                    onSearchTriggered = {
                        mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                    }
                )
            }
        ) {

            it.calculateTopPadding()

            SetDataInUi()

        }
    }


    @Composable
    private fun SetDataInUi() {
        Surface(modifier = Modifier.fillMaxSize()) {

        }

    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview(){
        MyOmdbAppTheme() {
            MainScreenUi(myOmdbDataViewModel)
        }
    }


}

