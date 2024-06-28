package com.example.transit1.ui.datadisplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.example.transit1.databinding.FragmentDataDisplayBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.example.transit1.R
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Column


class DataDisplayFragment : Fragment() {

    private var _binding: FragmentDataDisplayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataDisplayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ComposeView>(R.id.composeContainer)?.setContent {
            FetchDataAndDisplay()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            if (data != null) {
                // Update the state directly (without .value)
                dataList = listOf(data)
            }
        }
        .addOnFailureListener { e ->
            showError("Error fetching data: ${e.message}")
        }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            for (item in dataList) { // Iterate directly over dataList
                Text(item)
            }
        }
    }
}


private fun showError(errorMessage: String) {
    // Handle and display errors as needed
    // You can use a Toast, AlertDialog, or any other method to show errors
}
