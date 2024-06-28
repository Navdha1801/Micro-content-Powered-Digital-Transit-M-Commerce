package com.example.transit1.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.transit1.databinding.FragmentHomeBinding
import com.example.transit1.R

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Log.d(TAG, "No such document")

        view.findViewById<ComposeView>(R.id.composeContainer)?.setContent {
            PickupDestination(modifier = Modifier)
            GetData(navController = findNavController(), modifier = Modifier)
            Log.d(TAG, "No such document")

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

@Preview
@Composable
fun Forpreview() {
    PickupDestination(modifier = Modifier)
}

@Composable
fun PickupDestination(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.8f)) {
            Text(stringResource(R.string.button_name))
        }
        Text(text = stringResource(R.string.search_vehicles))
        Box(
            Modifier.background(MaterialTheme.colorScheme.onBackground)
        ) {
            EnterPickAndDestination(modifier = modifier)
        }
    }
}

@Composable
fun EnterPickAndDestination(modifier: Modifier) {
    var pickup by remember { mutableStateOf("") }
    var dest by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = pickup, onValueChange = { pickup = it },modifier = modifier, label = { Text(
            text = stringResource(R.string.enter_pickup)
        )
        })
        TextField(value = dest, onValueChange = {dest = it},modifier = modifier, label = { Text(
            text = stringResource(R.string.enter_destination)
        )
        })
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.search_vehicles))
        }
    }

}

@Composable
fun GetData(navController: NavController, modifier: Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = { navController.navigate(R.id.navigation_data_display) }) {
            Text(text = stringResource(R.string.get_data))
        }
    }
}