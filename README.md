# Android Assignment

The goal of this test is to check how much you know about object oriented principles, the android framework, 
design patterns and how do you describe a problem along with its solutions.

## Description
Write an android application that displays a list of wifis and when you click on any of the items it will open another screen 
showing the details of the selected wifi.

![Mockup](https://github.com/Instabridge/android-assignment/blob/master/images/mock.png)

### List Screen
The main idea of the list screen is to display 2 groups of wifis: The **in range**) and the (**nearby**).

* The items on the list should contain at least the SSID of the corresponding wifi, along with an icon representing its signal level.
* The **in range** group should display the maximum of 3 wifis.
* The **nearby** group displays initially 3 items, along with a _SHOW MORE_ button.
* Whenever the _SHOW MORE_ button is clicked, append 3 more items (at most) to that group.

### Detail Screen
There are no specifications for this screen. Be creative and display the information you feel more adequate.

### Information Source
We included a library that provides fake wifis. Use the `FakeWiFiProvider` to populate the list.

**Nice to have**

Implement a real `WifiProvider`.
```
interface WifiProvider {
    fun onInRangeUpdate(items: List<WiFi>)
    fun onNearbyUpdate(items: List<WiFi>)
}
```
- Provide the real content of the phone's native wifi manager in the method `onInRangeUpdate`.
- Provide data from an external source (it could be a json file e.g.) in the method `onNearbyUpdate`.

## Notes
- You shouldn't spend more than 8 hours in this assignment.
- Submit your solution with a brief description on how did you tackle the problems.

Have fun!

Instabridge's Android Team
