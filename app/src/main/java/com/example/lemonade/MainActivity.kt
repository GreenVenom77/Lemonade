package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color.White
                ) {
                    ProcessPreview()
                }
            }
        }
    }
}

@Composable
fun LemonadeProcess(modifier: Modifier = Modifier) {
    var phase by remember { mutableStateOf(1) }
    val maxInteractions by remember { mutableStateOf(4) }
    var requiredSqueezes: Int? = null
    var squeezed: Int = 0
    var image: Painter = painterResource(R.drawable.lemon_tree)
    var text: String = stringResource(R.string.tree_phase)
    var cdescription: String = "Lemon Tree"


    when(phase) {
        1 -> {
            image = painterResource(R.drawable.lemon_tree)
            text = stringResource(R.string.tree_phase)
            cdescription = "Lemon Tree"
        }
        2 -> {
            image = painterResource(R.drawable.lemon_squeeze)
            text = stringResource(R.string.tapping)
            cdescription = "A Lemon"
        }
        3 -> {
            image = painterResource(R.drawable.lemon_drink)
            text = stringResource(R.string.drinking)
            cdescription = "Full Lemon Glass"
        }
        4 -> {
            image = painterResource(R.drawable.lemon_restart)
            text = stringResource(R.string.again)
            cdescription = "Empty Lemon Glass"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color.Yellow)
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 20.sp
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = image,
                contentDescription = cdescription,

                modifier = Modifier
                    .clickable {
                        if (phase < maxInteractions) {
                            if(phase == 2) {
                                if(requiredSqueezes == null) {
                                    requiredSqueezes = (1..4).random()
                                    squeezed = 0
                                } else {
                                    if(squeezed != requiredSqueezes) {
                                        squeezed += 1
                                    } else {
                                        phase += 1
                                    }
                                }
                            } else {
                                phase += 1
                            }
                        } else {
                            phase = 1
                            requiredSqueezes = null
                        }
                    }
                    .background(Color.LightGray)
                    .wrapContentSize()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = text,
                fontSize = 18.sp
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun ProcessPreview() {
    LemonadeTheme {
        LemonadeProcess()
    }
}