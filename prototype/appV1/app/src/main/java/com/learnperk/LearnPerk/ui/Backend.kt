package com.learnperk.LearnPerk.ui

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.persistentCacheSettings
import com.google.firebase.ktx.Firebase


fun enableOfflinePersistence() {
    val db = Firebase.firestore
    val settings = FirebaseFirestoreSettings.Builder()
        .setLocalCacheSettings(persistentCacheSettings {})
        .build()
    db.firestoreSettings = settings
}

@Composable
fun Fetch( completion: (List<VidDetails>) -> Unit) {
    val db = Firebase.firestore
    val collectionRef = db.collection("db2/doc/content")

    val data = mutableListOf<VidDetails>()

    collectionRef.get()
        .addOnSuccessListener { result ->
            for (document in result) {
                val id = document.id as? String
                val category = document.data["category"] as? String
                val title = document.data["title"] as? String
                val thumbnail = document.data["thumbnail"] as? String

                if (id != null && title != null && category != null && thumbnail != null) {
                    val v = VidDetails(videoId = id,title = title, category = category, imgUrl = thumbnail)
                    data.add(v)
                }
            }
            completion(data)
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: $exception")
            completion(data)
        }
}

fun FetchUser(userID: Int,completion:  (User) -> Unit) {
    val db = Firebase.firestore
    val collectionRef = db.collection("db2/doc/users").document("user$userID")


    collectionRef.get()
        .addOnSuccessListener { result ->
            var doc = result
            val pair = doc.data?.get("coins") as Long
            val content_interests = doc.data?.get("content_interests") as? List<String> ?: ArrayList()
            val product_interests = doc.data?.get("product_interests") as? List<String> ?: ArrayList()
            val userName = doc.data?.get("uname") as? String
            val imgUrl = doc.data?.get("pfp") as? String

            val data = User(userName, pair.toInt(), content_interests, product_interests, imgUrl)
            completion(data)
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: $exception")
            completion(User("",0, emptyList<String>(), emptyList<String>(), emptyList<String>()))
        }
}


fun FetchList(userId: Int, completion: (List<String>, List<String>, String) -> Unit) {
    val db = Firebase.firestore
    val collectionRef = db.collection("db2/doc/users").document("user$userId")

    collectionRef.get()
        .addOnSuccessListener { result ->
            var doc = result
            val userVideos = doc.data?.get("videos") as? List<String> ?: ArrayList()
            val userProducts = doc.data?.get("transactions") as? List<String> ?: ArrayList()
            val userName = doc.data?.get("uname") as? String

            if (userName != null) {
                completion(userVideos, userProducts, userName)
            }
        }.addOnFailureListener{
            Log.d(ContentValues.TAG, "Cannot Fetch!! : $it")
        }
}

fun fetchMetadata(completion: (List<metadata>) -> Unit) {
    val db = Firebase.firestore
    val collectionRef = db.collection("db2/doc/metadata")

    val data = mutableListOf<metadata>()

    collectionRef.get()
        .addOnSuccessListener { result ->
            for (document in result) {
                val name = document.id as? String
                val imgUrl = document.data["imgUrl"] as? String
                if (name!= null && imgUrl!=null){
                    val v = metadata(name, imgUrl)
                    data.add(v)
                }
            }
            completion(data)
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: $exception")
        }
}

data class Tsc(
    val type: String,
    val product: Product?,
    val Video: VidDetails?,
    val amount: Int,
    val time: com.google.firebase.Timestamp?

)

fun fetchProduct(ref: DocumentReference, listOfTsc: MutableList<Tsc>, completion: (Product) -> Unit) {

    ref.get()
        .addOnSuccessListener{ document ->
            val id = document.id as? String
            val name = document.data?.get("productName") as? String
            val price = when (val priceValue = document.data?.get("price")) {
                is Long -> priceValue.toDouble()
                is Double -> priceValue
                else -> null
            }
            val imgUrl = document.data?.get("imageUrl") as? String
            val description = document.data?.get("description") as? String
            val source = document.data?.get("source") as? String
            val category = document.data?.get("category") as? String

            if (id != null && name != null && price != null && imgUrl != null && description != null && source != null && category != null) {
                val v = Product(
                    id = id,
                    name = name,
                    price = price,
                    imgUrl = imgUrl,
                    description = description,
                    discount = calculateDiscountPercentage(0,0.0),
                    source = source,
                    category = category
                )
                completion(v)
            }
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: $exception")
        }
}

fun fetchVid(ref: DocumentReference, listOfTsc: MutableList<Tsc>, completion: (VidDetails) -> Unit) {

    ref.get()
        .addOnSuccessListener{ document ->
            val id = document.id as? String
            val category = document.data?.get("category") as? String
            val title = document.data?.get("title") as? String
            val thumbnail = document.data?.get("thumbnail") as? String

            if (id != null && title != null && category != null && thumbnail != null) {
                val v = VidDetails(videoId = id,title = title, category = category, imgUrl = thumbnail)
                completion(v)
            }
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: $exception")
        }
}

fun fetchTsc(userId: Int, completion: (List<Tsc>) -> Unit) {
    val db = Firebase.firestore
    val collectionRef = db.collection("db2/doc/tsc")

    val data = mutableListOf<Tsc>()

    collectionRef.get()
        .addOnSuccessListener { result ->
            for (document in result) {
                if (document.get("user") != "user$userId") {
                    continue
                }
                val typeOfTransaction = document.get("type") as? String
                val item = document.get("item") as? DocumentReference
                val amount = document.get("amount") as? Long
                val time = document.getTimestamp("time")

                if (item != null) {
                    if (typeOfTransaction == "credit"){
                        fetchVid(item, data) { vidData ->
                            if (amount != null) {
                                val v = Tsc(typeOfTransaction, null, vidData, amount.toInt(), time)
                                data.add(v)
                            }

                            if (data.size == result.size()) {
                                completion(data)
                            }
                        }
                    }
                    else{
                    fetchProduct(item, data) { productData ->
                        if (typeOfTransaction != null && amount != null) {
                            val v = Tsc(typeOfTransaction, productData, null, amount.toInt(), time)
                            data.add(v)
                        }


                            completion(data)

                    }
                    }
                }
            }
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: $exception")
        }
}


fun calculateDiscountPercentage(amount: Int, cost: Double): Double {
    val tierThresholds = listOf(500.0, 1000.0, 30000.0, 70000.0)
    val maxDiscountPercentages = listOf(10.0, 20.0, 30.0, 40.0)


    val tier = tierThresholds.indexOfFirst { cost <= it }
    val maxDiscountPercentage = maxDiscountPercentages.getOrNull(tier) ?: 40.0


    val originalDiscountPercentage = when {
        amount <= 20 -> 0.0
        amount <= 160 -> 5.0 + (amount - 20) * (35.0 / 140.0)
        else -> 40.0
    }


    val scaledDiscountPercentage = originalDiscountPercentage.coerceAtMost(maxDiscountPercentage)

    return scaledDiscountPercentage.round(1)
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}


fun fetchProductList(amount: Int,completion : (List<Product>)->Unit ) {
    val db = Firebase.firestore
    val collectionRef = db.collection("db2/doc/products")

    val data = mutableListOf<Product>()


    collectionRef.get()
        .addOnSuccessListener { result ->
            var i = 0;
            for (document in result) {
                i+=1;
                val id = document.id as? String
                val name = document.data["productName"] as? String
                val price = when (val priceValue = document.data["price"]) {
                    is Long -> priceValue.toDouble()
                    is Double -> priceValue
                    else -> null
                }

                val imgUrl = document.data["imageUrl"] as? String
                val description = document.data["description"] as? String
                val source = document.data["source"] as? String
                val category = document.data["category"] as? String

                Log.d(ContentValues.TAG, "Product: $id $name $price $imgUrl $description $source $category")
                var discount: Double = 0.0;
                if (price!=null){
                    discount = calculateDiscountPercentage(amount, price)
                }

                if (id != null && name != null && price != null && imgUrl != null && description != null && source != null && category != null) {
                    val v = Product(
                        id = id,
                        name = name,
                        price = price,
                        imgUrl = imgUrl,
                        description = description,
                        discount = discount,
                        source = source,
                        category = category
                    )
                    data.add(v)
                }
            }
            completion(data)
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: $exception")
            completion(emptyList())
        }
}
fun FetchContentCategoriesAndProductCategories(completion: (dataC:List<String>,dataP:List<String>) -> Unit){
    val db = Firebase.firestore
    val collectionRef = db.collection("db2/doc/catgeories")

    val dataC = mutableListOf<String>()
    val dataP = mutableListOf<String>()

    collectionRef.get()
        .addOnSuccessListener { result ->
            for (document in result) {
                val id = document.id as? String
                if (id.contentEquals("content")  ) {
                    val names = document.data["names"] as? List<String> ?: ArrayList()
                    for (name in names) {
                        dataC.add(name)
                        Log.d(ContentValues.TAG, "Content: $name")
                    }
                }
                else if (id.contentEquals("product")) {
                    val names = document.data["names"] as? List<String> ?: ArrayList()
                    for (name in names) {
                        dataP.add(name)
                        Log.d(ContentValues.TAG, "Product: $name")
                    }
                }
            }
            completion(dataC,dataP)
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: $exception")
        }

}


fun updateDocument(coins: MutableState<Int>, videoId: MutableState<String>, User: Int){
    val db = Firebase.firestore
    val collectionRef = db.collection("db2/doc/users").document("user$User")

    collectionRef.get()
        .addOnSuccessListener { result ->

            var doc = result
            val videos = doc.data?.get("videos") as? ArrayList<String> ?: ArrayList()
            videos.add(videoId.value)

            val pair1 = mapOf<String, Any>(Pair("coins",coins.value))

            db.collection("db2/doc/users")
                .document("user${User}")
                .update(pair1)
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG,"Updated")
                }
                .addOnFailureListener { e ->
                    Log.d(ContentValues.TAG,"Failed Updating")
                }

            val pair2 = mapOf<String, Any>(Pair("videos",videos))
            db.collection("db2/doc/users")
                .document("user${User}")
                .update(pair2)
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG,"Updated")
                }
                .addOnFailureListener {
                    Log.d(ContentValues.TAG,"Failed Updating")
                }

        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: $exception")

        }

}
fun updateTransaction(coins: MutableIntState, productId: String, User: Int){
    val db = Firebase.firestore
    val collectionRef = db.collection("db2/doc/users").document("user$User")

    collectionRef.get()
        .addOnSuccessListener { result ->

            var doc = result
            val transactions = doc.data?.get("transactions") as? ArrayList<String> ?: ArrayList()
            transactions.add(productId)

            val pair1 = mapOf<String, Any>(Pair("coins",coins.value))

            db.collection("db2/doc/users")
                .document("user${User}")
                .update(pair1)
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG,"Updated")
                }
                .addOnFailureListener { e ->
                    Log.d(ContentValues.TAG,"Failed Updating")
                }
            val pair2 = mapOf<String, Any>(Pair("transactions",transactions))
            db.collection("db2/doc/users")
                .document("user${User}")
                .update(pair2)
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG,"Updated")
                }
                .addOnFailureListener { e ->
                    Log.d(ContentValues.TAG,"Failed Updating")
                }

        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: $exception")

        }

}

fun updateInterests(userId: Int, listOfContentInterests: List<String>, listOfProductInterests: List<String>) {
    val db = Firebase.firestore
    val collectionRef = db.collection("db2/doc/users")

    collectionRef.get()
        .addOnSuccessListener { result ->
            val pair = mapOf<String, Any>(Pair("content_interests", listOfContentInterests))
            val pair2 = mapOf<String, Any>(Pair("product_interests", listOfProductInterests))

            val docRef = db.collection("db2/doc/users").document("user${userId}")
            docRef.update(pair)
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG, "Interests updated successfully")
                }
                .addOnFailureListener { e ->
                    Log.d(ContentValues.TAG, "Failed to update interests: $e")
                }

            docRef.update(pair2)
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG, "Interests updated successfully")
                }
                .addOnFailureListener { e ->
                    Log.d(ContentValues.TAG, "Failed to update interests: $e")
                }

        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: $exception")
        }
}

fun updateSearchHistory(searchTerm: String, User: Int){
    val db = Firebase.firestore
    val collectionRef = db.collection("db2/doc/users").document("user$User")

    collectionRef.get()
        .addOnSuccessListener { result ->
            var doc = result
            val searchHistory = doc.data?.get("search_history") as? ArrayList<String> ?: ArrayList()
            if (searchHistory.size >= 10) {
                searchHistory.removeAt(0)
            }
            if (searchHistory[searchHistory.size - 1] != searchTerm) {
                searchHistory.add(searchTerm)
            }

            val pair = mapOf<String, Any>(Pair("search_history",searchHistory))

            db.collection("db2/doc/users")
                .document("user${User}")
                .update(pair)
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG,"Updated")
                }
                .addOnFailureListener { e ->
                    Log.d(ContentValues.TAG,"Failed Updating")
                }

        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: $exception")

        }
}

fun fetchSearchHistory(User: Int, completion: (List<String>) -> Unit){
    val db = Firebase.firestore
    val collectionRef = db.collection("db2/doc/users").document("user$User")

    collectionRef.get()
        .addOnSuccessListener { result ->
            var doc = result
            val searchHistory = doc.data?.get("search_history") as? List<String> ?: ArrayList()
            completion(searchHistory)
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: $exception")
        }
}