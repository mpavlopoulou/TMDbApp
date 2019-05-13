# TMDbApp
Native Android mobile app that searches for Movies &amp; TV shows using TMDb API

### Application Screens
 #### Search Screen
Contains an input field so that the user can type and perform search and a recycler view to display the results .Each displayed item has
a thumbnail of the show poster , the show title along with an icon indicating if the show is a movie or a TV show,the show release date,
the show average rating and a portion of the show overview. Upon selection users are navigated to the show Details Screen. Upon having at 
least one show inside the watch list, a choice is available in the Search Screen using an icon next to the search bar that displays all 
watch list items. Pagination is used for Search Screen.

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

#### Watchlist Screen
When the user presses the watchlist icon in the Search Screen is navigated to Watchlist Screen, where all watchlist items are displayed.
The user can also filter the watchlist items using the search bar at the top of the screen.
