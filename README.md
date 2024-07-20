# Receipe App
- Created a receipe app using jetpack compose and MVVM
---
## Features
- there will show a list of different dishes
- we can see a detailed receipe by clicking on them
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

## View
### Navigation 
- At source, we have a single activity `MainActivity.kt`
- `MainActivity.kt` will host and shows all our app screens
- `MainActivity.kt` contains composable `ReceipeApp`
- `ReceipeApp` is acting as **NavHostFragment** means it will host the navigation and where the desired screens swapping will take place
- `ReceipeApp` contains the function `NavHost` in which the whole **Navigation Graph** is defined where `ReceipeApp` is its host
- `MainActivity.kt` is passing **NavController** to `ReceipeApp` or `NavHost` to remember current position in navigation graph.
- **NavController** is also helping to swap the screens as we move through the navigation graph

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
