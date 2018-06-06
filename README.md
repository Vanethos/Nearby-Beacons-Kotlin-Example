# Nearby API Beacons Example

This project aims to show how tio implement Nearby Messages Api to interact with beacons, using Clean Architecture, Android Jetpack, MVVM, Dagger and RXJava 2.

## Getting Started
To run this project, you will need to:
* Create a project and get an API key, as seen on the [Get Started Guide](https://developers.google.com/nearby/messages/android/get-started)
* Change the `keys.properties` file on the project’s root and insert your Google API Key:

Then, proceed to the [Google Beacon Dashboard](https://developers.google.com/beacons/dashboard/), add at least one beacon to your project, and add the following attachment as a String to your beacon:

```
{
    "id" : 0,
    "title" : "Beacon #1",
    "imageUrl" : "https://images.pexels.com/photos/261628/pexels-photo-261628.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "message" : "This is going to be a really nifty message that you can put on your beacon as an attachment",
    "actionMessage" : "Beacon Docs",
    "actionUrl" : "https://developers.google.com/nearby/messages/android/get-started"
}
```

This is going to be the message that the user will get from the beacon.

## Limitations
This approach uses a `Background` beacon scanning approach, which is less reliable than the `Foreground` scanning. 

However, if we were to implement the `Foreground` scan, we would be required to give an `Activity` context since it’s considered a high-power operation intended to be only used when the app is opened. 

We could circumvent this by creating a `Singleton` that holds the Nearby Messages client and use the `Activity` to initialise it, however, once the application is killed, even if we start a service, we won’t be able to restart the client without opening the app again.


