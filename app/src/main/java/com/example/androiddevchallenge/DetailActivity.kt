package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddevchallenge.ui.DetailScreen
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_KEY_PET_ID = "pet_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val petIdInt = intent.getIntExtra(EXTRA_KEY_PET_ID, 0)
        val petId = if (petIdInt < 10) "Cat_0$petIdInt" else "Cat_$petIdInt"

        setContent {
            MyTheme {
                DetailScreen(
                    petId = petId,
                    viewModel = DetailViewModel(),
                    onBackClick = {
                        finish()
                    },
                    onStarClick = {

                    })
            }
        }
    }
}
