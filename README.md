# TweetsPlay
# Project 3 - *TweetsPlay*

**Name of your app** is an android app that allows a user to view his Twitter timeline and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **12** hours spent in total

## User Stories

The following **required** functionality is completed:

* [ ]	User can **sign in to Twitter** using OAuth login
* [ ]	User can **view tweets from their home timeline**
  * [ ] User is displayed the username, name, and body for each tweet
  * [ ] User can view more tweets as they scroll with [infinite pagination](http://guides.codepath.com/android/Endless-Scrolling-with-AdapterViews-and-RecyclerView). Number of tweets is unlimited.
    However there are [Twitter Api Rate Limits](https://dev.twitter.com/rest/public/rate-limiting) in place.
* [ ] User can **compose and post a new tweet**
  * [ ] User can click a “Compose” icon in the Action Bar on the top right
  * [ ] User can then enter a new tweet and post this to twitter
 
The following **bonus** features are implemented:
Persisted data into SQLite using DBFlow. 


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://imgur.com/qz9KAx7.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
