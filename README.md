# TMDbApp
Native Android mobile app that searches for Movies &amp; TV shows using TMDb API

### Application Screens
 #### Search Screen
Contains an input field so that the user can type and perform search and a recycler view to display the results .Each displayed item has
a thumbnail of the show poster , the show title along with an icon indicating if the show is a movie or a TV show,the show release date,
the show average rating and a portion of the show overview. Upon selection users are navigated to the show Details Screen. Upon having at least one show inside the watch list, a choice is available in the Search Screen using an icon next to the search bar that displays all watch list items. Pagination is used for Search Screen.

<img src="https://github.com/mpavlopoulou/TMDbApp/blob/master/media/search_screen.jpg" width="200" style="max-width:100%;"> </br></br>

#### Details Screen
Display the following details of the show: 
- Backdrop image
- Poster Image
- Title
- Release date
- Genre (only first)
- Average rating
- Description
- Trailer (if applicable)

User has the ability to add/remove the movie/show to/from a watch list. Basic information are saved to a local persistent storage 
(database) to be able to be retrieved between different sessions.

<img src="https://github.com/mpavlopoulou/TMDbApp/blob/master/media/details_1.jpg" width="200" style="max-width:100%;">   <img src="https://github.com/mpavlopoulou/TMDbApp/blob/master/media/details_2.jpg" width="200" style="max-width:100%;"></br></br>

#### Watchlist Screen
When the user presses the watchlist icon in the Search Screen is navigated to Watchlist Screen, where all watchlist items are displayed.
The user can also filter the watchlist items using the search bar at the top of the screen.

<img src="https://github.com/mpavlopoulou/TMDbApp/blob/master/media/watchlist.jpg" width="200" style="max-width:100%;"> </br></br>


### App Specs
* Minimum SDK 17
* [Java8](https://java.com/en/download/faq/java8.xml) (in master branch) & [Kotlin](https://kotlinlang.org/) (in kotlin_support branch)
* [Retrofit 2](https://square.github.io/retrofit/) for API integration.
* [Gson](https://github.com/google/gson) for serialisation.
* [Glide](https://github.com/bumptech/glide) for image loading.
* Custom Views: [AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView), [android-youtube-player](https://github.com/PierfrancescoSoffritti/android-youtube-player)
