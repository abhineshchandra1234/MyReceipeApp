# Receipe App
- Created a recipe app using Jetpack compose and MVVM
---
## Features
- there will be a list of different dishes
- we can see a detailed recipe by clicking on them
---
## Screenshots
<p align="center">
<img src = "https://raw.githubusercontent.com/abhineshchandra1234/MyReceipeApp/master/app/src/main/res/drawable/screenshots/Screenshot_20240711_194410.png" height=300px/>
  <img src = "https://raw.githubusercontent.com/abhineshchandra1234/MyReceipeApp/master/app/src/main/res/drawable/screenshots/Screenshot_20240711_194509.png" height=300px/>
</p>

---
## Topics Covered
- View Model
- MVVM
- Retrofit
- Navigation
- Safe args
- Grid
---
# MVVM architecture
- we will try to cover the MVVM architecture of this project at a high level, covering all important features
## View
### Navigation 
- At the source, we have a single activity `MainActivity.kt`
- we are following single-screen architecture, where we have one screen MainActivity, it will swap or show all screens or composable
- `MainActivity.kt` will host and show all our app screens
- `MainActivity.kt` contains composable `ReceipeApp`
- `ReceipeApp` is acting as **NavHostFragment**, which means it will host the navigation and where the desired screen swapping will take place
- `ReceipeApp` contains the function `NavHost` in which the whole **Navigation Graph** is defined where `ReceipeApp` is its host
- `MainActivity.kt` is passing **NavController** to `ReceipeApp` or `NavHost` to remember the current position in the navigation graph.
- **NavController** is also helping to swap the screens as we move through the navigation graph
- **Navigation Flow or Callbacks**
- At first, we have `MainActivity.kt` which displays the `ReceipeApp` screen
- When we reach the `ReceipeApp` screen, its `NavHost` is triggered
- `NavHost` has a start destination of `RecipeScreen`, which will show a list of recipes
- In `RecipeScreen` lambda function or callback `navigateToDetail` is passed
- `RecipeScreen` passes this callback `navigateToDetail` to `CategoryScreen` after a successful network call
- `CategoryScreen` uses the grid to display items and will pass the callback `navigateToDetail` to `CategoryItem`
- `CategoryItem` contains a custom layout for each item. When the user clicks on the item, the `navigateToDetail` callback is called, with the category passed as a parameter
- `navigateToDetail` is the anonymous function which has input parameter of category and return type as Unit
---
## 📝 License
```
Copyright 2024 Abhinesh Chandra

Licensed under the Apache License, Version 2.0 (the "License");

you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
