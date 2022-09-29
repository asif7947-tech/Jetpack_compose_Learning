package com.skybet.app.omdbapplication.presentation.views

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.skybet.app.omdbapplication.data.remote.ApiResponseStates
import com.skybet.app.omdbapplication.data.response.MovieModel
import com.skybet.app.omdbapplication.presentation.view_model.MyOmdbDataViewModel
import com.skybet.app.omdbapplication.presentation.theme.MyOmdbAppTheme
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

            SetDataInUi(myOmdbDataViewModel)

            mainViewModel.searchMoviesbyName()


        }
    }


    @Composable
    private fun SetDataInUi(mainViewModel: MyOmdbDataViewModel) {
        Surface(modifier = Modifier.fillMaxSize()) {
            when(val result =mainViewModel.customerDataStateFlow.value){
                is ApiResponseStates.Loading->{
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Column(modifier = Modifier.padding(10.dp),horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                        }
                    }
                    Log.e(TAG, "getDataFromServerAndSet:  Loading", )
                }
                is ApiResponseStates.onSuccessResponse->{
                    Log.e(TAG, "getDataFromServerAndSet:  onSuccessResponse ${result.data.toString()}", )



                    LazyColumn{
                        items(result.data){movie->
                            EachPostItemCart(movieData = movie)
                        }

                    }
                }
                is ApiResponseStates.onFailure->{
                    Log.e(TAG, "getDataFromServerAndSet:  onFailure  ${result.message}", )

                    showMessageBox(textMsg =  "Api call failed due to  ${result.message}")

                }
                is ApiResponseStates.onNetworkFailure->{
                    Log.e(TAG, "getDataFromServerAndSet:  onNetworkFailure" )
                    showMessageBox(textMsg =  "getDataFromServerAndSet:  onNetworkFailure ${result.message}")


                }
                else ->{
                    Log.e(TAG, "getDataFromServerAndSet:  else", )
                    showMessageBox(textMsg =  "getDataFromServerAndSet:  else")

                }
            }


        }

    }

    @Composable
    fun EachPostItemCart(movieData: MovieModel){
        Box(modifier = Modifier.fillMaxSize()) {
            Card( modifier = Modifier
                .padding(horizontal = 25.dp, vertical = 8.dp)
                .fillMaxWidth(),
                elevation = 2.dp,
                shape = RoundedCornerShape(4.dp)
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    Image(
                         painter = rememberAsyncImagePainter(movieData.poster),
                        contentDescription = "image",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Text(modifier = Modifier
                        .wrapContentSize()
                        .align(CenterHorizontally),
                        text = movieData.title,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .wrapContentSize()
                            .align(CenterHorizontally)
                    ) {
                        Text(
                            text ="type = "+ movieData.type+" ,",
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic
                        )
                        Spacer(modifier = Modifier.size(3.dp))
                        Text(
                            text ="Release In  = "+ movieData.year+"   ,",
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic
                        )
                    }
                    Spacer(modifier = Modifier.size(3.dp))
                    Text(modifier = Modifier
                        .wrapContentSize()
                        .align(CenterHorizontally),
                        text ="MovieId  = "+ movieData.imdbID+" ",
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic
                    )

                }
            }

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview(){
        MyOmdbAppTheme() {
            MainScreenUi(myOmdbDataViewModel)
        }
    }
    @Composable
    fun showMessageBox(textMsg : String){
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.padding(10.dp),horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = textMsg)
            }
        }
    }
}

