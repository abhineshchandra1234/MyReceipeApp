package com.example.myreceipeapp

sealed class Screen(val route: String) {

    object RecipeScreen: Screen("recipescreen")
    object DetailScreen: Screen("detailscreen")
}