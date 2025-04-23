package com.example.gptyopta

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import android.view.WindowManager
import kotlinx.coroutines.delay
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.AnimatedVisibility
import android.widget.TextView
import android.text.method.LinkMovementMethod
import androidx.compose.runtime.remember
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.toArgb // ✅ ВАЖНО добавить этот импорт
import androidx.compose.ui.text.style.TextAlign
import io.noties.markwon.Markwon
import io.noties.markwon.core.CorePlugin
import io.noties.markwon.ext.strikethrough.StrikethroughPlugin
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.image.coil.CoilImagesPlugin

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.rememberCoroutineScope
import java.util.concurrent.TimeUnit
import android.util.Log





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE) // ✅ Разрешаем сжимать экран при клавиатуре
        setContent {
            ChatScreen()
        }
    }
}

// 🔹 Вставляем Toolbar из XML
@Composable
fun ChatTopBar() {
    val context = LocalContext.current
    AndroidView(
        factory = { LayoutInflater.from(context).inflate(R.layout.toolbar, null) as Toolbar },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun MarkdownText(markdown: String) {
    val context = LocalContext.current
    val markwon = rememberMarkwon()

    AndroidView(
        factory = { TextView(it).apply {
            movementMethod = LinkMovementMethod.getInstance()
            textSize = 17f
            setTextIsSelectable(true) // ✅ Позволяет выделять текст
        }},
        update = { textView ->
            markwon.setMarkdown(textView, markdown)
            textView.setTextColor(Color.Black.toArgb()) // ✅ Устанавливаем цвет текста
        }
    )
}

