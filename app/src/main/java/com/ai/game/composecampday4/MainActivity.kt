package com.ai.game.composecampday4

import android.content.ClipData
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ai.game.composecampday4.model.ListItem
import com.ai.game.composecampday4.ui.theme.ComposeCampDay4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Overriding Callbacks", "OnCreate")
        setContent {
            ComposeCampDay4Theme {
                // A surface container using the 'background' color from the theme
                DefaultPreview()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("Overriding Callbacks", "OnStart")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Overriding Callbacks", "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Overriding Callbacks", "OnDestroy")
    }
}


@Composable
fun DiceApp(){
    var diceNum by remember {
        mutableStateOf(1)
    }

    var imageRes = when(diceNum){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_1
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null
        )
        Button(
            onClick = {
                diceNum = (1..6).random()
                Log.i("Dice Number", diceNum.toString())
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = "ROLL",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,

            )
        }
    }
}

@Composable
fun TipTime(){
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Calculate Tip",
            fontSize = 36.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
        DisplayText()
    }
}

@Composable
fun DisplayText(){
    var data by remember {
        mutableStateOf("")
    }
    Column {
        TextField(
            value = data,
            onValueChange = {str ->
                data = str
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Tip(data)
    }
}

@Composable
fun Tip(text: String){
    Text(
        text = "Your Tip = $text",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

fun LoadListItems() : List<ListItem> {
    return listOf<ListItem>(
        ListItem(R.drawable.image1, "Image 1"),
        ListItem(R.drawable.image2, "Image 2"),
        ListItem(R.drawable.image3, "Image 3"),
        ListItem(R.drawable.image4, "Image 4"),
        ListItem(R.drawable.image5, "Image 5"),
        ListItem(R.drawable.image6, "Image 6"),
        ListItem(R.drawable.image7, "Image 7"),
        ListItem(R.drawable.image8, "Image 8"),
        ListItem(R.drawable.image9, "Image 9"),
        ListItem(R.drawable.image10, "Image 10")
    )
}

@Composable
fun SingleListItem(item: ListItem){
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 5.dp
    ) {
        Column() {
            Image(
                painter = painterResource(id = item.img),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = item.text,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun ShowListItems(){
    var list = LoadListItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    )
    {
        items(list){ index ->
            SingleListItem(item = index)
        }
    }
}

@Composable
fun DropDownAnimation(){
    var drop by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                ))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.image1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(5.dp),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_drop_down_24),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .padding(end = 5.dp)
                        .clickable {
                            drop = !drop
                        }

                )
            }
            if(drop == true) ShowText()
        }
    }
}

@Composable
fun ShowText(){
    Text(
        text = "Image",
        modifier = Modifier.padding(16.dp),
        fontSize = 24.sp
    )
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeCampDay4Theme {
        DropDownAnimation()
    }
}