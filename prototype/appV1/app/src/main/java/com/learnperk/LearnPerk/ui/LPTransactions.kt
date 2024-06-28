package com.learnperk.LearnPerk.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.learnperk.LearnPerk.Navigation.ScreenInfo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun seperateTransactions(transactions: List<Tsc>): Pair<List<Tsc>, List<Tsc>> {
    val debited = transactions.filter { it.type == "debit"}
    val credited = transactions.filter { it.type == "credit" }
    return Pair(debited, credited)
}

fun makeTransaction(User: Int, type: String, amount: Int, video: VidDetails?, product: Product?){
    val db = Firebase.firestore
    if (type == "debit"){
        if (amount > 0){
            val ref: DocumentReference? = product?.let { db.collection("/db2/doc/products/").document(it.id) }
            val transaction = hashMapOf(
                "type" to type,
                "amount" to amount,
                "item" to ref,
                "time" to com.google.firebase.Timestamp.now(),
                "product" to product,
                "user" to "user$User"
            )
            db.collection("/db2/doc/tsc").add(transaction)
        }
    }
    else{
        val ref: DocumentReference? = video?.let { db.collection("/db2/doc/content").document(it.videoId) }
        val transaction = hashMapOf(
            "type" to type,
            "amount" to amount,
            "item" to ref,
            "product" to product,
            "time" to com.google.firebase.Timestamp.now(),
            "user" to "user$User",
        )
        db.collection("/db2/doc/tsc").add(transaction)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LP_transactions(navController: NavHostController, User: Int){

    val userName = remember {
        mutableStateOf("")
    }

    BackHandler(onBack = {
        navController.navigate(ScreenInfo.LPHome.route + "/${User}/false")
    })

    var selected by remember {
        mutableStateOf(0)
    }

    FetchUser(User){
        userName.value = it.uname.toString()
    }

    var tscList by remember{
        mutableStateOf<List<Tsc>>(emptyList());
    }

    fetchTsc (userId = User){
        tscList = it

    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(red=210,green=165,blue=95),
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                ),
                title = { Text(text = "Transactions")},
                navigationIcon = {
                    IconButton(onClick = {
                      navController.navigate(ScreenInfo.LPHome.route + "/${User}/false")
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go back")
                    }
                }
            )
        }
    ) {innerPadding ->


        Column (modifier = Modifier
            .padding(innerPadding)
            .background(Color.Black)){
            Box(modifier = Modifier
                .fillMaxWidth()
                .size(width = 0.dp, height = 50.dp), Alignment.Center) {
                Text(text = "Hello, ${userName.value}", fontSize = 25.sp,color=Color(red=198,green=165,blue=95))
            }
            Row(Modifier.fillMaxWidth()) {
                Box(modifier = Modifier
                    .background(
                        if (selected == 0) Color(
                            red = 210,
                            green = 165,
                            blue = 95
                        ) else Color.White
                    )
                    .weight(1f)
                    .size(width = 0.dp, height = 50.dp)
                    .clickable { selected = 0 }, Alignment.Center) {
                    Text(text = "Videos", color = if (selected == 0)  Color.Black else Color.Black)
                }
                Box(modifier = Modifier
                    .background(
                        if (selected == 1) Color(
                            red = 210,
                            green = 165,
                            blue = 95
                        ) else Color.White
                    )
                    .weight(1f)
                    .size(width = 0.dp, height = 50.dp)
                    .clickable { selected = 1 }, Alignment.Center) {
                    Text(text = "Purchases", color = if (selected == 1) Color.Black else Color.Black)
                }
            }
            var (list1, list2) = seperateTransactions(tscList)
            Showlist(selected = selected, list1.reversed(), list2.reversed())
        }
    }
}

@Composable
fun TransactionPage(transactions: List<Tsc>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(transactions) { transaction ->
            TransactionItem(transaction = transaction)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TransactionDetailsPopup(
    transaction: Tsc,
    onClosePopup: () -> Unit // Function to handle closing the popup
) {
    Dialog(
        onDismissRequest = { onClosePopup() }, // Handle dismiss event
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(red=198,green=165,blue=95), contentColor = Color.Black),
        ) {
            // Customize the content of the popup according to your needs
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val date = transaction.time?.toDate()
                val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
                val dateFormat = SimpleDateFormat("d'${date?.let { getDayOfMonthSuffix(it) }}' MMM yyyy", Locale.getDefault())
                val formattedDate = date?.let { dateFormat.format(it) }
                val formattedTime = date?.let { timeFormat.format(it) }

                if (transaction.type == "credit"){
                    val url = transaction.Video?.imgUrl
                    val req = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .build()

                    Image(painter = rememberImagePainter(req), contentDescription = null)
                }
                else if (transaction.type == "debit"){
                    val url = transaction.product?.imgUrl
                    val req = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .build()
                    Image(painter = rememberImagePainter(req), contentDescription = null)
                }


                if (transaction.type == "credit") {
                    val videoTitle = transaction.Video?.title ?: "Unknown Video"
                    Text(text = "Coins Earned: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = "${transaction.amount}", fontSize = 16.sp)
                    Text(text = "Watched Video: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = "$videoTitle", fontSize = 16.sp)
                } else if (transaction.type == "debit") {
                    val productName = transaction.product?.name ?: "Unknown Product"
                    Text(text = "Coins Spent: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = "${transaction.amount}", fontSize = 16.sp)
                    Text(text = "Product: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = "$productName", fontSize = 16.sp)
                }

                Text(text = "Time: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = "$formattedTime", fontSize = 16.sp)
                Text(text = "Date: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = "$formattedDate", fontSize = 16.sp)
            }
        }
    }
}
fun getDayOfMonthSuffix(date: Date): String {
    val cal = Calendar.getInstance()
    cal.time = date
    val day = cal.get(Calendar.DAY_OF_MONTH)
    return when {
        day in 11..13 -> "th"
        day % 10 == 1 -> "st"
        day % 10 == 2 -> "nd"
        day % 10 == 3 -> "rd"
        else -> "th"
    }
}
@Composable
fun TransactionItem(transaction: Tsc) {
    var showDialog by remember { mutableStateOf(false) }
    Card(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable ( onClick = { showDialog = true }),
        colors = CardDefaults.cardColors(containerColor = Color(red=198,green=165,blue=95), contentColor = Color.Black)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.weight(0.8f)) {
                Text(
                    text = if (transaction.type == "credit") "Watched Video: ${transaction.Video?.title}" else "Purchased Product: ${transaction.product?.name}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (transaction.type == "debit") "-${transaction.amount}" else "+${transaction.amount}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    color = if (transaction.type == "debit") Color.Red else Color(
                        red = 41,
                        green = 82,
                        blue = 42,
                        alpha = 255
                    )
                )
            }
        }
    }
    if (showDialog) {
        TransactionDetailsPopup(transaction = transaction, onClosePopup = { showDialog = false })
    }
}

@Composable
fun Showlist(selected: Int,list1: List<Tsc>, list2: List<Tsc>) {
    if (selected == 0) {
        TransactionPage(transactions = list2)
    }
    if (selected == 1) {
        TransactionPage(transactions = list1)
    }
}