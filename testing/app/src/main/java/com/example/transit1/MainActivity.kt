package com.example.transit1

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.transit1.databinding.ActivityMainBinding
import com.example.transit1.graphs.RootNavigationGraph
import com.example.transit1.ui.theme.BottomNavigationTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            BottomNavigationTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }

    }

}

@Composable
private fun FetchDataAndDisplay() {
    val database = FirebaseFirestore.getInstance()
    val dataCollection = database.collection("testCollection").document("doc1")

    // Define and initialize dataList as an empty mutable list using remember
    var dataList by remember { mutableStateOf(emptyList<String>()) }


    dataCollection.get()
        .addOnSuccessListener { documentSnapshot ->
            val data = documentSnapshot.getString("msg")
            Log.d(ContentValues.TAG, "Render data")

            Log.d("YourTag", data ?: "Data is null")
            if (data != null) {

                // Update the state directly (without .value)
                dataList = listOf(data)
            }
        }
        .addOnFailureListener { e ->
            showError("Error fetching data: ${e.message}")
        }

}


private fun showError(errorMessage: String) {
    // Handle and display errors as needed
    // You can use a Toast, AlertDialog, or any other method to show errors
}




