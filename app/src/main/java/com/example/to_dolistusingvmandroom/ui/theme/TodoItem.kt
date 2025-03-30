package com.example.to_dolistusingvmandroom.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.to_dolistusingvmandroom.viewmodel.TodoItem

@Composable
fun TodoItem(
    item: TodoItem,
    onToggle: (Int) -> Unit,
    onDelete: (Int) -> Unit
){
    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween

    ){
        ClickableText(
            text = AnnotatedString(item.text),
            onClick = { onToggle(item.id) }
        )
        Row {

            Checkbox(
                checked = item.isDone,
                onCheckedChange = { onToggle(item.id) }
            )



            Button(onClick = { onDelete(item.id) }) {
            Text(text = "X")
        }
        }







    }


}