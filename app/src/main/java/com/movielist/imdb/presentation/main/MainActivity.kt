package com.movielist.imdb.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.movielist.imdb.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    /*override fun onBackPressed() {
        findNavController(R.id.nav_host).let { navController ->
            navController.currentDestination.let { currentDestination ->
                if (currentDestination?.id == R.id.movies_fragment) {
                    super.onBackPressed()
                } else {
                    navController.navigateUp()
                }
            }
        }
    }*/
}