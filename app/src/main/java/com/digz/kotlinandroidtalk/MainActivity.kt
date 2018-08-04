package com.digz.kotlinandroidtalk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.*
import java.io.IOException

//import kotlinx.coroutines.experimental.javafx.JavaFx as UI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity<MainActivity>("id" to 2, "name" to "Kotlin")
        toast("Hi there!")
        longToast("Wow, such duration")
        alert("Hi, I'm Roy", "Have you tried turning it off and on again?") {
            yesButton { toast("Oh…") }
            noButton {}
        }.show()

        val countries = listOf("Russia", "USA", "Japan", "Australia")
        selector("Where are you from?", countries, { dialogInterface, i ->
            toast("So you're living in ${countries[i]}, right?")
        })
        launch(Android) {
            try {
                val result = SampleClient.fetchPosts()

                result.await() // will suspend until the call is finished
            } catch (exception: IOException) {
                toast("Phone not connected or service down")
            }
        }
    }

    private fun doStuff() {

    }


}
