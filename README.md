# flickr-demo-app

Create an app using the Flickr API which displays recent photos. The app should allow for the searching of photos as well as displaying images in a grid view by default.
Requirements
- Set up a project from scratch.
- The app should have a Text box that will let users enter text and search for images,
  When enter/button is hit, if user does not enter any text, it should grab recent photos,
  otherwise it should query based on the text user entered
- The app should display Images in a grid view with 3 columns. Images should be square.
- There should be error handling for network calls and loading UI, simple spinning circular
  progress and snackbar for error will do.
- The app should allow simple pagination when scrolling. * Please do not use the Paging
  lib from google and have your own implementation *
- You can use any libraries you want to use ( compose is cool if you are proficient in it)

Develop branch is containing the app without paging
Paging branch is containing the app with paging.

## Main patterns/tech...

- Use of Clean Architecture and separation of concerns throuhg presentation, domain and data layer
- Use of MVVM patter and databinding and bindingAdapter
- StateFlow
- CoRoutine
- Glide
- Hilt for dependency injection

## Features:

- Load Recent photos when app is launched
- If the user enter a keyword, display the search result. In case, the keyword is empty, the recent Photos are pulled
- When clicking on a picture, it's opening a detailed view with bigger image and ratio is respected. Image name and owner name are displayed if available
- Backend Error are returned and toast is displayed. A Network error view is displayed and let the user retry
- Loading spinner
- In case the list of photos returned is empty, a "No Photos" is displayed. User has to either user the search bar with empty string to gather the recent photos or enter a search term.
- 3 columns grid

## What I Wanted to add

- When no photos, add a button to pull recent photos or just automatic trigger after few seconds
- Support of Network Time-out in addition of error management
- Pagination mechanism on develop branch
- Add Unit test cases