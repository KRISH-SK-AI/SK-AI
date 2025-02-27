package com.yourcompany.skaI

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yourcompany.skaI.ui.theme.SKAITheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.yourcompany.skaI.settings.SettingsManager

class MainActivity : ComponentActivity() {
    // 1. Add a state variable to track the current theme (light or dark)
    // var isDarkTheme by remember { mutableStateOf(false) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 2. Use the isDarkTheme variable to determine the theme
            // SKAITheme(darkTheme = isDarkTheme) {
            SKAITheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    // 3. Add a theme toggle to the navigation drawer
                    // 4. When the toggle is clicked, update the isDarkTheme variable and save the theme to SharedPreferences
                }
            }
        }
    }

    fun savePerformanceProfile(profile: String) {
        val settingsManager = SettingsManager(this)
        settingsManager.savePerformanceProfile(profile)
    }

    fun getPerformanceProfile(): String {
        // 1. Load the performance profile from SharedPreferences
        val settingsManager = SettingsManager(this)
        return settingsManager.getPerformanceProfile()
    }

    fun saveTheme(theme: String) {
        val settingsManager = SettingsManager(this)
        settingsManager.saveTheme(theme)
    }

    fun getTheme(): String {
        val settingsManager = SettingsManager(this)
        return settingsManager.getTheme()
    }

    /**
     * A native method that is implemented by the 'skaI' native library, which is packaged
     * with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'skaI' library on application startup.
        init {
            System.loadLibrary("skaI")
        }
    }

    fun searchGGUFModels(query: String) {
        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl("https://huggingface.co/")
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()

        val service = retrofit.create(HuggingFaceService::class.java)

        val call = service.listModels(search = query, filter = "gguf")

        call.enqueue(object : retrofit2.Callback<List<HuggingFaceModel>> {
            override fun onResponse(call: Call<List<HuggingFaceModel>>, response: retrofit2.Response<List<HuggingFaceModel>>) {
                if (response.isSuccessful) {
                    val models = response.body()
                    // Get the list of models from the response
                    // The HuggingFaceModel data class contains the model ID, downloads, and likes
                    // Process the models
                    // 1. Display the models in the UI
                    // 2. Allow the user to select a model to download
                    // 3. Show a status indicator during the download
                    // 4. Save the downloaded model to local storage
                } else {
                    // Handle the error
                    // Display an error message in the UI
                }
            }

            override fun onFailure(call: Call<List<HuggingFaceModel>>, t: Throwable) {
                // Handle the error
            }
        })
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello " + name + "!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SKAITheme {
        Greeting("Android")
    }
}