package com.learnperk.LearnPerk.ui

import androidx.activity.compose.BackHandler
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import com.learnperk.LearnPerk.R
import com.google.relay.compose.RelayContainer
import com.learnperk.LearnPerk.Navigation.ScreenInfo
import androidx.compose.material3.*
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.google.relay.compose.BoxScopeInstanceImpl.align
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

fun sortProductsByInterest(product_interests: List<String>, listOfProduct: List<Product>): List<Product> {

    val indexMap = mutableMapOf<String, Int>()
    product_interests.forEachIndexed { index, category ->
        indexMap[category] = index
    }

    return listOfProduct.sortedBy { product ->
        indexMap[product.category] ?: Int.MAX_VALUE
    }
}
fun filterAndSortByMatchPercentageProducts(videoList: List<Product>, searchString: String, search: Boolean): List<Product> {
    if (!search){
        return  videoList
    }
    val filteredList = videoList.filter { it.name.contains(searchString, ignoreCase = true) }

    return filteredList.sortedByDescending {
        val matchPercentage = calculateMatchPercentage(searchString, it.name)
        matchPercentage
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ecommerce(navController: NavHostController, User: Int) {
    BackHandler(onBack = {
        navController.navigate(ScreenInfo.LPHome.route + "/${User}/false")
    })
    var listOfMetadata by remember {
        mutableStateOf<List<metadata>>(emptyList())
    }
    val listOftransferdata = remember {
        mutableListOf<transferdata>()
    }
    fetchMetadata {
        listOfMetadata = it
    }
    for (i in listOfMetadata.indices) {
        val tempdata =  transferdata("",false)
        tempdata.source = listOfMetadata[i].name
        tempdata.select = false

        listOftransferdata.add(tempdata)
    }

    var finallist by remember {
        mutableStateOf<List<Product>>(emptyList())
    }

    var totalitems: List<Product> = emptyList()

    val coins = remember {
        mutableIntStateOf(0)
    }
    var currUser by remember {
        mutableStateOf(User("",0, emptyList<String>(), emptyList<String>(), emptyList<String>()))
    }
    FetchUser(User){
        currUser = it
        coins.intValue = it.coins as Int
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val tap = remember {
        mutableStateOf(false)
    }
    val textColor = remember { mutableStateOf(Color.Black) }


    fetchProductList(coins.intValue) { datalist ->
        finallist = datalist
        finallist = sortProductsByInterest(currUser.product_interests as List<String>, finallist)
        totalitems = finallist
    }


    RelayContainer {
        Scaffold(
            modifier = Modifier.fillMaxSize().background(color = Color.Black),
            snackbarHost = { SnackbarHost(snackbarHostState, Modifier.align(Alignment.BottomCenter)) },
            topBar = {  Top_app_bar(navController = navController, coins = coins, User, textColor) }
        ) {innerPadding ->


            var searchText by remember { mutableStateOf("") }
            var searchState by remember { mutableStateOf(false) }
            var items by remember { mutableStateOf<List<String>>(emptyList()) }


            Column (modifier = Modifier.padding(innerPadding)){
                var chipdata : List<transferdata>
                Chips(listOfmetadata = listOfMetadata, listOftransferdata) {
                        filterlist ->
                    chipdata = filterlist

                    finallist = if (!checkchipselected(chipdata)) {
                        totalitems
                    } else {

                        val productlist: MutableList<Product> = mutableListOf()
                        for (i in chipdata.indices) {
                            if (chipdata[i].select == true) {
                                for (j in totalitems.indices) {
                                    if (totalitems[j].source == chipdata[i].source) {
                                        productlist.add(totalitems[j])
                                    }
                                }
                            }
                        }
                        productlist
                    }
                }

                SearchBar(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .padding(top = 5.dp)
                        .padding(bottom = 5.dp)
                        .fillMaxWidth(),
                    query = searchText,
                    onQueryChange = {
                        searchText = it
                        items = generateRecommendations(searchText)
                    },
                    onSearch = {
                        searchState = false
                       finallist = if (searchText.isEmpty()) {
                            totalitems
                        } else {
                            filterAndSortByMatchPercentageProducts(totalitems, searchText, true)
                        }
                    },
                    active = searchState,
                    onActiveChange = {
                        searchState = it
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
                            Row(modifier = Modifier.padding(all = 20.dp)) {
                                Icon(
                                    modifier = Modifier.padding(end = 10.dp),
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Recommendation Icon"
                                )
                                Text(text = it)
                            }
                        }
                    }
                }
                Product_listing(coins,finallist, tap, User, scope, snackbarHostState, listOfMetadata, textColor = textColor)
            }
        }


    }

}
data class metadata(
    val name: String,
    val imgUrl: String
)

data class transferdata(
    var source: String,
    var select: Boolean
)

fun calculateAmountFromDiscountPercentage(discountPercentage: Double): Int {
    return when {
        discountPercentage < 5.0 -> 0
        discountPercentage < 40.0 -> ((discountPercentage - 5.0) / (35.0 / 140.0) + 20).toInt()
        else -> 160
    }
}

@Composable
fun Product_listing(
    coins: MutableIntState,
    productList: List<Product>, tap: MutableState<Boolean>, User: Int, scope: CoroutineScope, snackbarHostState: SnackbarHostState, listOfmetadata: List<metadata>, textColor: MutableState<Color>) {
    LazyColumn(
        modifier=Modifier
            .background(color=Color.Black).fillMaxSize()
    ) {
        items(productList) { product ->
            ProductItem(coins,product, tap, User, scope, snackbarHostState, listOfmetadata, textColor)
        }
    }
}

@Composable
fun ProductItem(
    coins: MutableIntState,
    product: Product,
    tap: MutableState<Boolean>, User: Int,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    listOfmetadata: List<metadata>,
    textColor: MutableState<Color>) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { /* Handle card click if needed */ },
        shape = RoundedCornerShape(16.dp),
        colors = CardColors(
            //containerColor = MaterialTheme.colorScheme.background,
            containerColor = Color(red=198,green=165,blue=95),
            disabledContainerColor = MaterialTheme.colorScheme.onSurface,
            disabledContentColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation =  CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)

        ) {
            val request = ImageRequest.Builder(LocalContext.current)
                .data(product.imgUrl)
                .build()
            val Img = rememberImagePainter(request = request)

            Image(
                painter = Img,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = product.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "₹${(product.price * (1 - (product.discount)*0.01)).roundToInt()}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Discount: ${product.discount}% OFF",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Black
            )
            Text(
                text = "Coins to be spent: ${calculateAmountFromDiscountPercentage(product.discount)} ",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        val coinsToBeSpent = calculateAmountFromDiscountPercentage(product.discount)

                        if (coins.intValue>=coinsToBeSpent) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Purchase Successful. You've spent ${coinsToBeSpent} LearnCoins.",
                                    withDismissAction = true,
                                    duration = SnackbarDuration.Short
                                )
                            }
                            val initial = coins.intValue
                            if (coins.intValue > 0) {
                                coins.intValue -= coinsToBeSpent
                            }
                            val final = coins.intValue
                            updateTransaction(coins = coins, product.id, User)
                            tap.value = true

                            makeTransaction(User, "debit", coinsToBeSpent, null, product)
                            if (tap.value && (initial - final) > 0) {
                                scope.launch {
                                    textColor.value = Color.Red
                                    delay(2000)
                                    textColor.value = Color.Black
                                }
                            }
                        }else{
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Purchase Unsuccesfull. You Don't Have Enough LearnCoins.",
                                    withDismissAction = true,
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                        colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    val imgUrl = searchSource(product.source, listOfmetadata)
                    val request1 = ImageRequest.Builder(LocalContext.current)
                        .data(imgUrl)
                        .build()
                    val Img1 = rememberImagePainter(request = request1)
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Buy Now on")
                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                    Image(painter = Img1, contentDescription = "Source")

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductList() {
    //    ProductList()
}




data class Product(
    val name: String,
    val price: Double,
    val category: String,
    val description: String,
    val discount: Double,
    val imgUrl: String,
    val id: String,
    val source: String)



fun searchSource(source: String, listOfmetadata: List<metadata>): String {
    for (metadata in listOfmetadata){
        if (metadata.name == source){
            return  metadata.imgUrl
        }
    }
    return ""
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Top_app_bar(navController: NavHostController, coins: MutableIntState, User: Int, textColor: MutableState<Color>) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            //containerColor = MaterialTheme.colorScheme.primary,
            containerColor = Color(red=210,green=165,blue=95),
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black
        ),
        title = { Text(text = "E-commerce")},
        navigationIcon = {
            IconButton(onClick = { navController.navigate(ScreenInfo.LPHome.route+"/$User/false") }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go back")
            }
        },

        actions = {
            IconButton(modifier = Modifier.padding(20.dp), onClick = { navController.navigate(ScreenInfo.Interests.route+ "/${User}") }) {
                Icon(imageVector = Icons.Default.Build, contentDescription = "Change Interests", tint = Color.Black)
            }
            Image(painter = painterResource(id = R.drawable.coins), contentDescription ="Coin desc",
                Modifier
                    .size(20.dp, 20.dp)
                    .padding(2.dp)
                    .offset(x = (-20).dp)
            )
            Text(text = coins.intValue.toString(), modifier = Modifier
                .padding(4.dp)
                .offset(x = (-20).dp), style = TextStyle(fontSize = 20.sp), color = textColor.value
            )

        }
    )
}

@Composable
fun Chips(listOfmetadata: List<metadata>, transferdatalist: MutableList<transferdata>, onFilter: (List<transferdata>) -> Unit) {
    Surface(
        color = Color.Black
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier
                .height(50.dp)
                .background(Color.Black), verticalAlignment = Alignment.CenterVertically
        ) {
            items(listOfmetadata) { eachsource ->
                SingleChip(eachsource, transferdatalist, onFilter,Color(red=198,green=165,blue=95),Color(red=220,green=165,blue=95))
            }
        }
    }
}
@Composable
fun SingleChip(
    metadata: metadata,
    transferdatalist: MutableList<transferdata>,
    onFilter: (List<transferdata>) -> Unit,
    chipColor: Color,
    selectedChipColor: Color
) {
    //var chipColor= Color(red=198,green=165,blue=95)
    var selected by remember { mutableStateOf(false) }
    val listdata by remember {
        mutableStateOf(transferdatalist)
    }

    FilterChip(
        selected = selected,
        onClick = { selected = !selected ; listdata[findmatch(
            transferdatalist = transferdatalist,
            metadata = metadata
        )].select = selected ;transferdatalist[findmatch(
            transferdatalist = transferdatalist,
            metadata = metadata
        )].select = selected;onFilter(listdata);},
        label = { Text(text = metadata.name,color=Color.Black) },
        leadingIcon = if (selected) {
            {
                Icon(imageVector = Icons.Default.Done, contentDescription = "null")
            }
        } else {
            null
        },
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .background(color = if (selected) selectedChipColor else chipColor, shape = RoundedCornerShape(12.dp)) // Use chipColor directly
            .height(30.dp)
    )

}

fun findmatch(transferdatalist: List<transferdata>, metadata: metadata) : Int {
    var index = 0
    for (i in transferdatalist.indices) {
        if (transferdatalist[i].source == metadata.name) {
            index = i
            break
        }
    }

    return index
}


fun checkchipselected(transferdatalist: List<transferdata>) : Boolean {
    var index = false
    for (i in transferdatalist.indices) {
        if (transferdatalist[i].select == true) {
            index = true
        }
    }
    return index
}