package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.LemonadeConstants.LEMONADE_PART_FIRST
import com.example.lemonade.LemonadeConstants.LEMONADE_PART_FOURTH
import com.example.lemonade.LemonadeConstants.LEMONADE_PART_SECOND
import com.example.lemonade.LemonadeConstants.LEMONADE_PART_THIRD
import com.example.lemonade.LemonadeConstants.ZERO
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Body()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Body() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.app_name)
                    )
                },
                backgroundColor = MaterialTheme.colors.onPrimary
            )
        },
        content = {
            ImageClick(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
    )
}

@Composable
fun ImageClick(modifier: Modifier = Modifier) {

    val journeyValue = remember { mutableStateOf(1) }
    val numberClicks = remember { mutableStateOf((2..4).random()) }
    val dataLemonade: DataLemonade = when (journeyValue.value) {
        LEMONADE_PART_FIRST -> DataLemonade(
            imageView = R.drawable.lemon_tree,
            descriptionImage = stringResource(id = R.string.lemon_tree)
        )

        LEMONADE_PART_SECOND -> DataLemonade(
            imageView = R.drawable.lemon_squeeze,
            descriptionImage = stringResource(id = R.string.lemon)
        )

        LEMONADE_PART_THIRD -> DataLemonade(
            imageView = R.drawable.lemon_drink,
            descriptionImage = stringResource(id = R.string.glass_of_lemonade)
        )

        else -> {
            DataLemonade(
                imageView = R.drawable.lemon_restart,
                descriptionImage = stringResource(id = R.string.empty_glass)
            )
        }
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = dataLemonade.imageView),
            contentDescription = dataLemonade.descriptionImage,
            modifier = Modifier.clickable {
                when (journeyValue.value) {
                    LEMONADE_PART_FIRST -> {
                        journeyValue.value = LEMONADE_PART_SECOND
                    }
                    LEMONADE_PART_SECOND -> {
                        if (numberClicks.value == ZERO) {
                            journeyValue.value = LEMONADE_PART_THIRD
                        } else {
                            numberClicks.value = --numberClicks.value
                        }
                    }
                    LEMONADE_PART_THIRD -> {
                        journeyValue.value = LEMONADE_PART_FOURTH
                    }
                    else -> {
                        journeyValue.value = LEMONADE_PART_FIRST
                    }
                }
            }
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(text = dataLemonade.descriptionImage, fontSize = 18.sp)
    }
}





