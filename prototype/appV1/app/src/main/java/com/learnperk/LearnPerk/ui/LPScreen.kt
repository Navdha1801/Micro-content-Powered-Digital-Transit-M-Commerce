
package com.learnperk.LearnPerk.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.relay.compose.RelayContainer
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import com.learnperk.LearnPerk.Navigation.ScreenInfo
import  androidx.compose.material3.SearchBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.relay.compose.BoxScopeInstanceImpl.align
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun sortVideosByInterest(content_interests: List<String>, listOfVid: List<VidDetails>): List<VidDetails> {

    val indexMap = mutableMapOf<String, Int>()
    content_interests.forEachIndexed { index, category ->
        indexMap[category] = index
    }

    return listOfVid.sortedBy { video ->
        indexMap[video.category] ?: Int.MAX_VALUE
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LPscreen(navController: NavHostController, userID: Int, toggleSB: Boolean) {
    BackHandler {
        navController.popBackStack(ScreenInfo.Users.route, inclusive = false)
    }

    var listOfVid by remember {
        mutableStateOf(emptyList<VidDetails>())
    }
    var finalList by remember {
        mutableStateOf(emptyList<VidDetails>())
    }

    var currUser by remember {
        mutableStateOf(User("",0, emptyList<String>(), emptyList<String>(), emptyList<String>()))
    }

    val coins = remember { mutableIntStateOf(0) }
    FetchUser (userID){
        currUser = it
        coins.intValue = it.coins as Int
    }
    Fetch{
        listOfVid = it
        finalList = it
        if (it.isNotEmpty()) {
            Log.d(TAG, "Fetched Successfully")
        }else{
            Log.d(TAG, "Fetch is Unsuccessful")
        }
    }

    val currID = remember { mutableStateOf("") }

    var searchText by remember { mutableStateOf("") }
    var searchState by remember { mutableStateOf(false) }
    var items by remember { mutableStateOf<List<String>>(emptyList()) }



    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val textColor = remember {
        mutableStateOf(Color.Black)
    }



    finalList = sortVideosByInterest(currUser.content_interests as List<String>, finalList )

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState, Modifier.align(Alignment.BottomCenter)) },
        floatingActionButton = { FAB_lp(
        navController = navController,
        userID = userID
    )}) { padding->
        if(toggleSB){
       LaunchedEffect(Unit){
           scope.launch {
               snackbarHostState.showSnackbar(
                   "You have earned 10 LPCoins!!!!",
                   withDismissAction = true,
                   duration = SnackbarDuration.Short
               )
           }
               scope.launch {
                   textColor.value = Color.Green
                   delay(2000)
                   textColor.value = Color.Black
           }
       } }

    RelayContainer {
        Box(Modifier.fillMaxSize()) {
            Top_bar(navController, coins, userID, textColor)
            SearchBar(
                modifier = Modifier
                    .padding(top = 70.dp)
                    .fillMaxWidth(),
                query = searchText,
                onQueryChange = {
                    searchText = it
                },
                onSearch = {
                    updateSearchHistory(searchText, userID)
                    searchState = false
                    finalList = if (searchText.isEmpty()) {
                        listOfVid
                    } else {
                        filterAndSortByMatchPercentage(listOfVid, searchText, true)
                    }
                },
                active = searchState,
                onActiveChange = {
                    searchState = it
                    fetchSearchHistory(userID) {
                        items = it
                    }
                },
                placeholder = {
                    Text(text = "Search")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                },
                trailingIcon = {
                    if (searchState) {
                        Icon(
                            modifier = Modifier.clickable {
                                if (searchText.isEmpty()) {
                                    searchState = false
                                } else {
                                    searchText = ""
                                    items = emptyList()
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon"
                        )
                    }
                }
            ) {
                if (items.isNotEmpty()) {
                    items.forEach {
                        Row(modifier = Modifier.padding(all = 30.dp).fillMaxWidth().clickable{ searchText = it }) {
                            Icon(
                                modifier = Modifier.padding(end = 10.dp),
                                imageVector = Icons.Default.Search,
                                contentDescription = "Recommendation Icon"
                            )
                            Text(text = it)
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(30.dp))
            }
            LPhome(
                navController,
                listPassed = finalList,
                videoId = currID,
                coins = coins,
                userID
            )

        }
    }
}
}

fun generateRecommendations(searchString: String): List<String> {
    return listOf(searchString)
}

@Composable
fun LPhome(navController: NavHostController,
           listPassed: List<VidDetails>,
           videoId: MutableState<String>,
           coins: MutableState<Int>,
           userID: Int) {

    var loadedItems by remember { mutableIntStateOf(20) }
    val lazyListState = rememberLazyListState()

    listPassed.ifEmpty {
        Surface(color = Color.Black,
                modifier = Modifier.padding(top=60.dp),
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 90.dp)
                    .align(Alignment.Center),
            ) {
                Text(
                    text = "No results",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color(red=198,green=165,blue=95)
                    )
                )
            }
        }
    }

    if (listPassed.isNotEmpty()) {
        Box(
            modifier = Modifier.padding(top = 60.dp)
        ) {
            Surface(
                color = Color.Black
            )
            {
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 90.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(loadedItems) { index ->
                        if (index < listPassed.size) {
                            SearchCards(
                                navController,
                                n = listPassed[index],
                                videoId = videoId,
                                coins = coins,
                                userID = userID
                            )
                        }
                        if (index == loadedItems - 1 && index < listPassed.size - 1) {
                            loadedItems += 20
                        }
                    }
                }
            }
        }
    }
}


data class VidDetails(
    val title: String,
    val category: String,
    val imgUrl: String,
    val videoId: String
)

data class User(
    val uname: Any?,
    val coins: Any?,
    val content_interests: Any?,
    val product_interests: Any?,
    val imgUrl: Any?
)
fun filterAndSortByMatchPercentage(videoList: List<VidDetails>, searchString: String, search: Boolean): List<VidDetails> {
    if (!search){
        return  videoList
    }
    val filteredList = videoList.filter { it.title.contains(searchString, ignoreCase = true) }

    return filteredList.sortedByDescending {
        val matchPercentage = calculateMatchPercentage(searchString, it.title)
        matchPercentage
    }
}

fun calculateMatchPercentage(query: String, title: String): Int {
    val normalizedQuery = query.lowercase()
    val normalizedTitle = title.lowercase()

    val matchCount = normalizedTitle.windowed(query.length) {
        it == normalizedQuery
    }.count { it }

    return (matchCount * 100) / title.length
}


@Composable
fun SearchCards(navController: NavHostController,
        n: VidDetails,
         videoId: MutableState<String>,
         coins: MutableState<Int>,
         userID: Int){

    Box(modifier = Modifier) {
        Row {
            val request = ImageRequest.Builder(LocalContext.current)
                .data(n.imgUrl)
                .build()
            val img = rememberImagePainter(request = request)
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(red=198,green=165,blue=95)
                ),
                modifier = Modifier
                    .clickable {
                        videoId.value = n.videoId
                        coins.value += 10
                        Log.d(TAG, "Adding 10 Coins!")
                        updateDocument(coins, videoId, userID)
                        makeTransaction(userID,"credit", 10, n, null)

                        navController.navigate("Player/${n.videoId}/${userID}")
                    }
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .size(150.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3f / 2f)) {
                            Image(painter = img, contentDescription = "thumbnail",
                                Modifier
                                    .fillMaxSize()
                                    .padding(top = 5.dp))
                        }
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .weight(0.3f)) {
                            Text(text = n.category, Modifier.align(Alignment.Center))
                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .weight(0.9f)) {
                            Text(text = n.title, Modifier.padding(horizontal = 8.dp, vertical = 8.dp))
                        }
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .weight(0.3f)) {
                            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
                                Box(Modifier.weight(0.3f)) {

                                }
                                Box(Modifier.weight(1f)) {
                                    Row(Modifier) {
                                        Image(painter = painterResource(id = com.learnperk.LearnPerk.R.drawable.like), contentDescription = "null",
                                            Modifier
                                                .fillMaxHeight()
                                                .size(20.dp))
                                        Spacer(modifier = Modifier.padding(2.dp))
                                        Box(Modifier.fillMaxHeight()) {
                                            Text(text = "1k", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 10.
                                            sp)
                                            )
                                        }
                                    }
                                }
                                Box(Modifier.weight(1f)) {
                                    Row(Modifier) {
                                        Image(painter = painterResource(id = com.learnperk.LearnPerk.R.drawable.views), contentDescription = "null",
                                            Modifier
                                                .fillMaxHeight()
                                                .size(20.dp))
                                        Spacer(modifier = Modifier.padding(2.dp))
                                        Box(Modifier.fillMaxHeight()) {
                                            Text(text = "500k", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 10.
                                            sp))
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.padding(3.dp))
                                Box(Modifier.weight(1f)) {
                                    Row(Modifier) {
                                        Image(painter = painterResource(id = com.learnperk.LearnPerk.R.drawable.trending), contentDescription = "null",
                                            Modifier
                                                .fillMaxHeight()
                                                .size(20.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.padding(5.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Top_bar(navController: NavHostController, coins: MutableState<Int>, userID: Int, textColor: MutableState<Color>) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(red=210,green=165,blue=95),
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black,
                ),
                title = { Text(text = "LearnPerk", fontWeight = FontWeight.Bold, fontSize = 25.sp)},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack(route = ScreenInfo.Users.route, inclusive = false)}) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go back")
                    }
                },
                actions = {
                        Image(painter = painterResource(id = com.learnperk.LearnPerk.R.drawable.coins), contentDescription ="Coin desc",
                            Modifier
                                .size(20.dp, 20.dp)
                                .padding(2.dp))
                        Text(text = coins.value.toString(), modifier = Modifier.padding(3.dp), style = TextStyle(fontSize = 20.sp), color = textColor.value)

                    IconButton(onClick = { navController.navigate(ScreenInfo.LPStore.route + "/${userID}") }) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "E-Commerce", tint = Color.Black)
                    }
                    Menu(navController, userID)
                }
            )
        }
    ) {innerPadding ->
        Row (modifier = Modifier.padding(innerPadding)){

        }
    }
}

@Composable
fun Menu(navController: NavHostController, userID: Int) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "null")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { Text(text = "Users") }, onClick = { navController.popBackStack(route = ScreenInfo.Users.route , inclusive = false) }, leadingIcon = { Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "null"
            )})
            DropdownMenuItem(text = { Text(text = "Interests") }, onClick = { navController.navigate(ScreenInfo.Interests.route + "/${userID}") }, leadingIcon = { Icon(
                imageVector = Icons.Default.Build,
                contentDescription = "null"
            )})
        }
    }
}

@Composable
fun FAB_lp(navController: NavHostController, userID: Int) {
    Column {
        FloatingActionButton(
            onClick = { navController.navigate(ScreenInfo.LPTransactions.route + "/${userID}") },
            containerColor = Color.White,
            contentColor = Color.White,
            modifier = Modifier.padding(vertical = 32.dp, horizontal = 20.dp).size(80.dp)
        ) {
            Image(
                painter = painterResource(id = com.learnperk.LearnPerk.R.drawable.history), // Replace "your_image" with your image resource
                contentDescription = "Large floating action button",
                modifier = Modifier.size(60.dp)
            )
        }
    }
}


@Composable
fun Demo() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .clickable { }
            .fillMaxWidth()
            .size(width = 240.dp, height = 180.dp)
    ) {
        Row {
            Column {
                Box(
                    Modifier
                        .size(150.dp, 100.dp) ){
                    Image(imageVector = Icons.Default.Add, contentDescription = "null",Modifier.size(150.dp,90.dp))
                }
                Box(Modifier.size(150.dp,50.dp)) {
                    Text(text = "My name is keshava",Modifier.align(Alignment.Center))

                }
            }
            Column {
                Box(modifier = Modifier.size(250.dp,100.dp)) {
                    Text(text = "My mane is keshava kishora nanda",Modifier.padding(0.dp,10.dp,0.dp,0.dp))
                }
                Box(Modifier.size(250.dp,50.dp)) {
                    Row {
                        Box {
                            Image(painter = painterResource(id = com.learnperk.LearnPerk.R.drawable.like), contentDescription = "null")
                            Text(text = "10k")
                        }
                        Box {
                            Image(painter = painterResource(id = com.learnperk.LearnPerk.R.drawable.views), contentDescription = "null")
                            Text(text = "5k")
                        }
                        Image(painter = painterResource(id = com.learnperk.LearnPerk.R.drawable.trending), contentDescription = "null")
                    }

                }
            }

        }
    }
}

@Preview
@Composable
fun Demo_preview() {
    Demo()
}

