package com.u20201a963.appsuperzound.feature.Home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.u20201a963.appsuperzound.R
import com.u20201a963.appsuperzound.navigation.BottomAppBarNavigation
import com.u20201a963.appsuperzound.ui.theme.AppSuperZoundTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home (navController: NavController) {
    Scaffold(bottomBar = {
        BottomAppBar(
            modifier = Modifier.fillMaxWidth(),
            content = { BottomAppBarNavigation(navController = navController) })
    }) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color(0xfff2f2f2)) {
            AppSuperZoundTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hero),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}