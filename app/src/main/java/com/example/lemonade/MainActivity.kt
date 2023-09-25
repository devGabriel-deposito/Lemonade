package com.example.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            window.statusBarColor = getColor(R.color.yello_lemonade_dark)
            window.navigationBarColor = getColor(R.color.yello_lemonade)

            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lemonade(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lemonade(modifier: Modifier = Modifier) {

    var lemonadeState by remember { mutableStateOf(1) }

    val lemonadeImage = when (lemonadeState) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val lemonadeContentDescription = when (lemonadeState) {
        1 -> R.string.lemon_tree_content_description
        2 -> R.string.lemon_squeeze_content_description
        3 -> R.string.lemon_drink_content_description
        else -> R.string.lemon_restart_content_description
    }

    val lemonadeLabel = when (lemonadeState) {
        1 -> R.string.lemon_tree_label
        2 -> R.string.lemon_squeeze_label
        3 -> R.string.lemon_drink_label
        else -> R.string.lemon_restart_label
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = colorResource(id = R.color.yello_lemonade),
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text("Lemonade", fontWeight = FontWeight.Bold)
                }
            )
        },
        content = {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        val incrementLemonadeState = {
                            lemonadeState = if (lemonadeState == 4) 1 else lemonadeState + 1
                        }

                        if (lemonadeState == 2) {
                            val randomNumber = (2..4).random()

                            if (randomNumber == 4) {
                                incrementLemonadeState()
                            }
                        } else {
                            incrementLemonadeState()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(50.dp, 0.dp),
//                .background(color = ),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(195, 236, 210, 255)
                    )
                ) {
                    Image(
                        painter = painterResource(id = lemonadeImage),
                        contentDescription = stringResource(id = lemonadeContentDescription),
                        modifier = Modifier
                            .wrapContentSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = stringResource(id = lemonadeLabel))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Lemonade(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}