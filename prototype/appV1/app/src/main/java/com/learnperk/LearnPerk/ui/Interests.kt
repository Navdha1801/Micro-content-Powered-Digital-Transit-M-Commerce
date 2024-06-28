package com.learnperk.LearnPerk.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.learnperk.LearnPerk.Navigation.ScreenInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterestSelectionScreen(navController: NavController, userId: Int) {
    var selectedContentInterests by remember { mutableStateOf(emptySet<String>()) }
    var selectedProductInterests by remember { mutableStateOf(emptySet<String>()) }
    var currUser by remember {         mutableStateOf(User("",0, emptyList<String>(), emptyList<String>(), emptyList<String>())) }

    FetchUser(userId) { user ->
        currUser = user
        selectedContentInterests = (user.content_interests as List<String>).toSet()
        selectedProductInterests = (user.product_interests as List<String>).toSet()
    }

    var productCategories by remember { mutableStateOf(emptyList<String>()) }
    var contentCategories by remember { mutableStateOf(emptyList<String>()) }
    FetchContentCategoriesAndProductCategories { conCat,prodCat ->
        productCategories = prodCat
        contentCategories = conCat
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(red=210,green=165,blue=95),
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                ),
                title = { Text(text = "Select Interests") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
    ) { padding ->
        Surface(modifier = Modifier.fillMaxSize().padding(padding), color = Color.Black) {
            Column(modifier = Modifier.fillMaxSize()) {
                InterestSelectionContent(
                    title = "Content Categories",
                    interests = contentCategories,
                    selectedInterests = selectedContentInterests,
                    onSelectionChanged = { selectedContentInterests = it },
                    titleColor = Color(red = 198, green = 165, blue = 95)
                )
                InterestSelectionContent(
                    title = "Product Categories",
                    interests = productCategories,
                    selectedInterests = selectedProductInterests,
                    onSelectionChanged = { selectedProductInterests = it },
                    titleColor = Color(red = 198, green = 165, blue = 95)
                )
                Spacer(modifier = Modifier.height(16.dp))
                WhiteButton(
                    onClick = {
                        onDoneClicked(
                            userId,
                            selectedContentInterests.toList(),
                            selectedProductInterests.toList()
                        )
                        navController.navigate(ScreenInfo.LPHome.route + "/$userId/false")
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Done")
                }
            }
        }
    }
}

@Composable
fun InterestSelectionContent(
    title: String,
    interests: List<String>,
    selectedInterests: Set<String>,
    onSelectionChanged: (Set<String>) -> Unit,
    titleColor: Color
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineSmall,color = titleColor)
        interests.forEach { interest ->
            InterestCheckbox(
                text = interest,
                isChecked = interest in selectedInterests,
                onCheckedChange = { isChecked ->
                    val updatedInterests = selectedInterests.toggle(interest)
                    onSelectionChanged(updatedInterests)
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

    }
}

fun onDoneClicked(userId: Int, selectedContentInterests: List<String>, selectedProductInterests: List<String>) {
    updateInterests(userId = userId, listOfContentInterests = selectedContentInterests.toList(), listOfProductInterests = selectedProductInterests.toList())
}

@Composable
fun InterestCheckbox(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(checkedColor = Color(red=210,green=165,blue=95))
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color= Color(red=198,green=165,blue=95),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

fun <T> Set<T>.toggle(element: T): Set<T> =
    if (contains(element)) {
        minus(element)
    } else {
        plus(element)
    }
@Composable
fun WhiteButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(red=210,green=165,blue=95), // Set background color to white
            contentColor = Color.Black // Set content color (text color) to white
        ),
        modifier = modifier
    ) {
        content()
    }
}
