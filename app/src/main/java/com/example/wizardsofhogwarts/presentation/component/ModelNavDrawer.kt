package com.example.wizardsofhogwarts.presentation.component

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable

@Composable
fun ModelNavDrawer(
    drawerContent: @Composable () -> Unit, drawerState: DrawerState,
    mainContent: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerContent = {
            drawerContent()
        },
        drawerState = drawerState
    ){
        mainContent()
    }

}