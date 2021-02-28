package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    petId: String,
    viewModel: DetailViewModel,
    onBackClick: () -> Unit,
    onStarClick: () -> Unit,
) {

    val pet = viewModel.pet.observeAsState().value
    val isStarred = viewModel.isStarred.observeAsState().value
    val isAdopted = viewModel.isAdopted.observeAsState().value

    val petAvatar = painterResource(R.drawable.ic_cat)

    if (pet != null && isStarred != null && isAdopted != null) {
        Scaffold(topBar = {
            TopAppBar {
                IconButton(onBackClick, Modifier.align(Alignment.CenterVertically)) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
                Text(
                    text = petId,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
                IconButton(
                    onStarClick,
                    Modifier.align(Alignment.CenterVertically)
                ) {
                    if (isStarred) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "favorite"
                        )
                    } else {
                        Icon(
                            Icons.Filled.FavoriteBorder,
                            contentDescription = "favorite"
                        )
                    }

                }
            }
        }) {
            Column {
                Image(modifier = Modifier.size(80.dp), painter = petAvatar, contentDescription = "avatar")

                Spacer(modifier = Modifier.padding(top = 10.dp))

                if (!isAdopted) {
                    FloatingActionButton(onClick = { }, backgroundColor = Color.DarkGray) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "adopt")
                    }
                }
            }

        }
    }
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightDetailPreview() {
    MyTheme {
        DetailScreen(petId = "Cat_01", DetailViewModel(), {}, {})
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkDetailPreview() {
    MyTheme(darkTheme = true) {
        DetailScreen(petId = "Cat_01", DetailViewModel(), {}, {})
    }
}