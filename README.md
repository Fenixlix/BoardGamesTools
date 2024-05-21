# BoardGamesTools

An easy to use app for helping with the process of playing board games with friends

## Description

Offering it user with a set of different tools that can be utilize in various board games.
Some of them are tools for specific games, like the one made for Triomino, or the Chess clock.
Others are more adaptable like the "Points Counters" , or the "Coin Toss".
Finally it offers simple games like the "Craps Game".

## Technical Specifications

* Coded using **Kotlin and Java**
* The UI was made using **Jetpack Compose**
* Implements data persistence using **Room and DataStorePreference**
* Architecture **MVI + Clean**
* Dependency injection framework **Dagger Hilt**
* Uses **Material Design 3**
* Custom app Icon and vector images were made using **Inkscape**

## Screenshots
<br>  "Home screen".<br>
<img src = "https://github.com/Fenixlix/BoardGamesTools/blob/main/screenshots/New_Home_Screen.png" width = "25%" height = "25%" alt = "Main Screen"> 
<br>  "Craps" game.<br>
<img src = "https://github.com/Fenixlix/BoardGamesTools/blob/main/screenshots/Craps_Screen_1.png" width = "25%" height = "25%" alt = "Craps1">
<img src = "https://github.com/Fenixlix/BoardGamesTools/blob/main/screenshots/Craps_Screen_2.png" width = "25%" height = "25%" alt = "Craps2"> 
<br>  "Triomino" game tool.<br> 
<img src = "https://github.com/Fenixlix/BoardGamesTools/blob/main/screenshots/Triomino_OpeningDialog.png" width = "25%" height = "25%" alt = "Triomino1">
<img src = "https://github.com/Fenixlix/BoardGamesTools/blob/main/screenshots/Triomino_Screen.png" width = "25%" height = "25%" alt = "Triomino2"> 
<br>  "Chess Clock" and "Coin Toss" game tools<br>
<img src = "https://github.com/Fenixlix/BoardGamesTools/blob/main/screenshots/Chess_Clock_Screen.png" width = "25%" height = "25%" alt = "ChessClock">
<img src = "https://github.com/Fenixlix/BoardGamesTools/blob/main/screenshots/Coin_Toss_Screen.png" width = "25%" height = "25%" alt = "TossCoin"> 
<br>  "Points Counters" game tool<br>
<img src = "https://github.com/Fenixlix/BoardGamesTools/blob/main/screenshots/PointsCounters_Screen_AddPlayer.png" width = "25%" height = "25%" alt = "PointsCounters1"> 
<img src = "https://github.com/Fenixlix/BoardGamesTools/blob/main/screenshots/PointsCounters_Screen_3P.png" width = "25%" height = "25%" alt = "PointsCounters2">
<img src = "https://github.com/Fenixlix/BoardGamesTools/blob/main/screenshots/PointsCounters_Screen_4P.png" width = "25%" height = "25%" alt = "PointsCounters3">
<img src = "https://github.com/Fenixlix/BoardGamesTools/blob/main/screenshots/PointsCounters_Screen_4P_rdialog.png" width = "25%" height = "25%" alt = "PointsCounters4"> 

## XML and Views migration to Jetpack Compose

This app was **migrated** in totality from the xml and views UI to the modern **Jetpack Compose**.
This change brings a lot of improvements, one of them was a better user experience because of
the more **clear and visual appealing UI**, also the quality of life updates that the migration
process gif the opportunity to implement.
<br>
<img src = "https://github.com/Fenixlix/BoardGamesTools/blob/main/screenshots/ComparativaCraps_2024_05.png" width = "25%" height = "25%" alt = "Craps comparative">
<br>
<img src = "https://github.com/Fenixlix/BoardGamesTools/blob/main/screenshots/ComparativaTriomino_2024_05.png" width = "25%" height = "25%" alt = "Triomino comparative">
<br>
Another point to take in importance is the facility that Jetpack Compose brings to develop more
content with less time, making more fun and fast to add new content to the app.
Last but not least this change is part of the process to **implement Kotlin Compose Multiplatform**.

## Future plans

* Add a tool that can be used for character creation needed games.
* Improve the Chess Clock tools with the different formats that are played.
* Add animations to various components.
* Migrate to Kotlin Compose Multiplatform (Desktop).
* And more...