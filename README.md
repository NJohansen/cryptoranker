# Cryptoranker
[![platform](https://img.shields.io/badge/platform-Android-brightgreen.svg)](https://www.android.com)

:iphone: Android app which displays top 50 currencies with live data.

Cryptoranker is an Android app that lists top 50 of cryptocurrencies. 
This list includes prices in USD, logos and 24 hours changes in percentage. 

## Features

* Displays A list with asset name, price, latest 24 hours percent change and asset logo for the top 50 cryptos
* Comes with a content provider so Crypto data can be used in other apps through the provided ContentProvider 
* Uses fragtments to provide single views for each crypto with logo, crypto description and price.

## Libraries Used

* <a href="https://developer.android.com/topic/libraries/architecture/room">Room</a>: Library used for easier database access and as abstraction layer on top of SQlite.
* <a href="https://github.com/square/retrofit">Retrofit2</a>: Library used to retrieve JSON from Coinmarketcap API via a REST based webservice. 
* <a href="https://github.com/bumptech/glide">Glide</a>: Library used for loading and caching images and display them. Primarily the logos.
* <a href="https://github.com/hdodenhof/CircleImageView">CircleImageView</a>: Library used for creating round images for the crypto logos.
* <a href="https://github.com/google/gson">GSON</a>: Library used to serialize JSON response from the REST API into Java objects.

## Permissions
 
`android.permission.INTERNET`

This is required for internet connection so we can get data for the Coinmarketcap REST API.

## Sources

* <a href="https://coinmarketcap.com/">CoinMarketCap</a>: Provides all the data on the currencies
