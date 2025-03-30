package com.example.to_dolistusingvmandroom.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_dolistusingvmandroom.viewmodel.TodoItem

@Composable
fun TodoItem(
    item: TodoItem,
    onToggle: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 12.dp), // ✅ Added padding for better spacing
        shape = RoundedCornerShape(12.dp), // ✅ Rounded corners for a modern look
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F4F6)) // ✅ Light background
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Clickable text with strikethrough effect if completed
            ClickableText(
                modifier = Modifier.weight(1f),
                text = AnnotatedString(item.text),
                onClick = { onToggle(item.id) },
                style = LocalTextStyle.current.copy(
                    fontSize = 18.sp,
                    textDecoration = if (item.isDone) TextDecoration.LineThrough else TextDecoration.None,
                    color = if (item.isDone) Color.Gray else Color.Black // ✅ Gray out completed tasks
                )
            )

            // Row for Checkbox and Delete button
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Checkbox(
                    checked = item.isDone,
                    onCheckedChange = { onToggle(item.id) }
                )

                // Delete button (smaller with red color)
                IconButton(onClick = { onDelete(item.id) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red // ✅ Red delete icon for visibility
                    )
                }
            }
        }
    }
}
