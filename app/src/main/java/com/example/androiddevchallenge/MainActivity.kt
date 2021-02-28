/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                Scaffold(topBar = {
                    TopAppBar(title = {
                        Text(text = "Adopt a cat")
                    })
                }) {
                    Surface(color = MaterialTheme.colors.background) {
                        MainList { position -> Intent(this, DetailActivity::class.java).apply {
                            putExtra(DetailActivity.EXTRA_KEY_PET_ID, position)
                            startActivity(this)
                        } }
                    }
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MainList(itemClick: (position: Int) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        items(35) { position ->
            ListItem(modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
                .clickable {
                    itemClick(position)
                })
        }
    }
}

@Composable
fun ListItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp, 8.dp)
    ) {

        Surface(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterVertically),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                modifier = Modifier.padding(4.dp),
                painter = painterResource(id = R.drawable.ic_cat),
                contentDescription = "avatar"
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "Title", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "Desc", style = MaterialTheme.typography.body2)
            }
        }

        Icon(
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterVertically),
            imageVector = Icons.Rounded.FavoriteBorder,
            contentDescription = "favorite"
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(text = "Adopt a cat")
            })
        }) {
            Surface(color = MaterialTheme.colors.background) {
                MainList { }
            }
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(text = "Adopt a cat")
            })
        }) {
            Surface(color = MaterialTheme.colors.background) {
                MainList { }
            }
        }
    }
}
