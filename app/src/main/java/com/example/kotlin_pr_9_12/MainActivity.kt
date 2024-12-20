package com.example.kotlin_pr_9_12

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.kotlin_pr_9_12.ui.theme.CustomTheme
import com.example.kotlin_pr_9_12.ui.theme.Kotlin_pr_9_12Theme
import com.example.kotlin_pr_9_12.ui.theme.Pink40
import com.example.kotlin_pr_9_12.ui.theme.Purple40
import com.example.kotlin_pr_9_12.ui.theme.Purple80
import kotlinx.coroutines.launch
import androidx.work.NetworkType
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomTheme {
                Greeting()
            }
        }
    }
}

// Greeting - это основной экран с навигацией и элементами UI
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    // Создание контроллера для навигации
    val navController = rememberNavController()
    // Scaffold - основной контейнер для UI
    Scaffold(Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                PR9(
                    name = "Margarita",
                    surname = "Bagautdinova",
                    groupp = "IKBO-12-22",
                    navController
                )
            }
            composable("cat") { ScreenWithCat(navController) }
        }
    }
}

// AppBar - компонент для отображения панели приложения с заголовком
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 16.sp
            )},
        Modifier.fillMaxHeight(0.08f)
    )
}

// Здесь описан стартовый экран
@Composable
fun PR9(name: String, surname: String, groupp: String, navController: NavHostController) {
    val animals: List<String> = mutableListOf(
        "Cat", "Dog", "Elephant", "Tiger", "Lion", "Giraffe", "Zebra", "Kangaroo", "Panda", "Rabbit", "Horse")

    // Создание состояния для Drawer (бокового меню)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // ModalNavigationDrawer - боковое меню, которое появляется при клике на кнопку
    ModalNavigationDrawer(
        drawerState = drawerState, // Устанавливаем состояние меню
        drawerContent = {
            ModalDrawerSheet {
                Column {
                    Text("Pets")
                    Button(
                        onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isOpen) close() else open()
                                }
                            }
                            navController.navigate("cat")
                        }
                    ) {
                        Text("Show a cat")
                    }
                }
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                AppBar(
                    title = "Done by $name $surname, $groupp"
                )
            },
            bottomBar = {
                AppBar(
                    title = "This is a bottom bar"
                )
            },
            content = { innerPadding ->
                Modifier.padding(innerPadding)
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxHeight(0.6f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .background(Purple40)
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f)
                    ) {
                        Text(
                            text = "First element",
                        )
                        Text(
                            text = "Second element",
                        )
                    }
                    Box(
                        Modifier
                            .background(Purple80)
                            .fillMaxHeight(0.5f)
                            .fillMaxWidth()

                    ) {
                        LazyRow {
                            items(animals) { item ->
                                Text(
                                    text = item,
                                    Modifier.padding(2.dp),
                                    fontSize = 30.sp
                                )
                            }
                        }
                    }
                    Card(
                        Modifier
                            .fillMaxHeight(0.8f)
                            .fillMaxWidth(0.8f)
                            .padding(10.dp),

                    ) {
                        Text(
                            text = "This is a card!",
                            fontSize = 40.sp
                        )
                    }
                }
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                Log.d("rrr", isClosed.toString())
                                if (isClosed) open() else close()
                            }
                        }

                    }
                ) { Text("FAB") }
            }
        )
    }
}

// BgProcess - пример фонового процесса, который выполняется через WorkManager
class BgProcess(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.d("rrr", "Some bg thing")
        return Result.success() // Возвращаем успешный результат работы
    }
}

// Практика 10
@Composable
fun ScreenWithCat(navController: NavController) {
    // Создание ограничений для фонового процесса
    val constraints = androidx.work.Constraints.Builder().setRequiresBatteryNotLow(true).setRequiredNetworkType(NetworkType.CONNECTED).build()
    val workRequest = OneTimeWorkRequestBuilder<BgProcess>().setConstraints(constraints).build()
    Scaffold(Modifier.fillMaxSize()) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {

            val url = "https://funik.ru/wp-content/uploads/2018/10/17478da42271207e1d86.jpg"

            //библиотека для загрузки изображений из Интернета — rememberImagePainter из coil
            val painter = rememberImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .crossfade(true)  // Включаем плавную анимацию
                    .build()
            )

            // Отображаем изображение
            Image(painter = painter, contentDescription = null)

            Box(
                Modifier
                    .fillMaxWidth()
                    .background(Purple80)
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth()
            )
            Button(
                onClick = { navController.navigate("home")
                WorkManager.getInstance().enqueue(workRequest)}) // Запуск фоновой задачи
            {
                Text(text = "Back to home", textAlign = TextAlign.Center)
            }
        }

    }
}

// Превью компонента Greeting с использованием кастомной темы
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun GreetingPreview() {
    Kotlin_pr_9_12Theme {
        Greeting() // Отображаем Greeting в превью
    }
}