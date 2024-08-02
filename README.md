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
### UI
- At first, we have `MainActivity` as the base
- `MainActivity` is displaying the `ReceipeApp` screen using scaffold
- `ReceipeApp` screen is acting as a NavHost for `RecipeScreen` and `CategoryDetailScreen`
- `ReceipeApp` screen will show a list of recipes
- `ReceipeApp` screen has a base of box layout.
- Box layout is showing `CircularProgressIndicator` when data is loading
- It shows `error text` when there is an error fetching data
- It passes a list of categories to `CategoryScreen` when data is fetched successfully
- `CategoryScreen` uses LazyVerticalGrid to display recipes or categories in the custom layout
- LazyVerticalGrid loops through recipes and passes them to the custom layout `CategoryItem`
- `CategoryItem` uses a `Column` which contains `Image` and `Text`
- `Image` uses the `rememberAsyncImagePainter` method of the `Coil` library to fetch images asynchronously by just using the URL
- `Text` will display the recipe name
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
- category is an object fetched from the internet which contains all the dish details
- `CategoryItem` is just displaying the thumbnail and dish name to the users
- `navigateToDetail` will reciprocate to its implementation in `NavHost` by `RecipeScreen` using lambda expression
- `navigateToDetail` lambda implementation contains logic to navigate to `DetailScreen` using **NavController**
- **Serialization**
- `CategoryItem` passes Category parcelable object to lambda expression `navigateToDetail`
- We need to convert the data class to parcelable object to pass between activities
- `navigateToDetail` is using `NavController` to save this data in the current back stack entry using a bundle where the key is `cat`
- In `NavHost` before navigating to `CategoryDetailScreen`, `NavController` is used to fetch `Category` parcelable object from previous back stack entry
- Later this Category parcelable object is passed to `CategoryDetailScreen`, which uses its properties like - `strCategoryThumb` and `strCategoryDescription` to display the image and description of the recipe
---
## ViewModel
- we have `MainViewModel` which holds all the data related to the UI
- `MainViewModel` is initialized by the `NavHost` `ReceipeApp`
- when `MainViewModel` is initialized it fetches data from the internet and stores it in the object `RecipeState`
- Error is stored in the `RecipeState`, if there was some issue fetching data from the internet
- `RecipeState` is a data class which has three attributes ie loading (boolean), list (category list) and error (string)
- when data is being fetched `loading -> true, list -> empty, error -> null`, data is fetched successfully `loading -> false, list -> categories list, error -> null`
- when error occurs fetching data `loading -> false, list -> empty, error -> error message`
- `ReceipeApp` fetches this read-only data using the immutable object `categoriesState` and stores it in the variable `viewstate`
- Later `ReceipeApp` passes this `viewstate` object to `RecipeScreen` to display the list of recipes, which later passes it to `CategoryDetailScreen` to show details of the recipe.
---
## Model
- we do not have repository-level abstraction, viewmodel is directly interacting with the model layer ie service class
- we have created a service interface named `ApiService`
- This interface is responsible for fetching data from the internet
- we have created a retrofit instance named `retrofit` using the base URL of mealdb
- we have then created a service instance named `receipeService` using this `retrofit` instance
- Inside the `ApiService` we have defined the suspend function `getCategories`
- `getCategories` uses @GET annotation, to customize the base URL to fetch required data
- @GET annotation can also be used to query or filter the data
- `getCategories` has a return type of `CategoriesResponse`
- `CategoriesResponse` is used to receive data from the internet
- `CategoriesResponse` is the data class, whose input type is the list of category
- we are fetching a list of category objects from the server
- Inside the `Category` data class we can define the attributes which we want to fetch, there is no need to describe all the attributes
- we can also define some extra attributes not present in the server, retrofit will handle it without throwing any error
- `getCategories` suspend function is called inside the coroutine launched by the viewmodel `MainViewModel`
- `getCategories` suspend function will run on the main thread, it will pause when fetching data from the server without affecting the main thread, fetching data will happen on a separate thread apart from the main thread
- once data is fetched `getCategories` suspend function will resume again automatically, transferring control back to the main thread and providing data to viewmodel `MainViewModel`
- Earlier callback method was used, which was called on the main thread, data fetching used to happen on a separate thread, once data was fetched, control was transferred to the main thread again.
- Coroutines are based on this same callback approach
- By default, all coroutines run on the main thread, if you want some coroutines tasks to run on more suitable threads like- Dispatchers.IO, Dispatchers.Default, you can use dispatchers
- The IO thread is optimized for disk and network IO off the main thread and the Default thread is optimized for CPU-intensive work off the main thread.   
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
