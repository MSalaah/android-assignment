# Android Assignment
The goal of this test is to check how much you know about object oriented principles, the android framework, 
design patterns and how you approach a problem and present its solution.

## Description
Write an android application that displays a list of wifis, when you click on any of the items it will open another screen 
showing the details of the selected wifi.

![Mockup](https://github.com/Instabridge/android-assignment/blob/master/images/mock.png)

### List Screen
The main idea of the list screen is to display 2 groups of wifis: the **in range** and the **nearby**.

* The items on the list should contain at least the SSID of the corresponding wifi, along with an icon representing its signal level.
* The **in range** group should display a maximum of 3 items.
* The **nearby** group should also display a maximum of 3 items, along with a _SHOW MORE_ button.
* Whenever the _SHOW MORE_ button is clicked, append 3 more items (at most) to that group.

### Detail Screen
There are no specifications for this screen. Be creative and display the information you feel is most relevant.

### Information Source
We included a library that provides fake wifis, use the `FakeWiFiProvider` to populate the list.

**Nice to have**

Implement a real `WifiProvider`.
```
interface WifiProvider {
    fun onInRangeUpdate(items: List<WiFi>)
    fun onNearbyUpdate(items: List<WiFi>)
}
```
- Provide the real content of the phone's native wifi manager in the method `onInRangeUpdate`.
- Provide data from an external source (e.g. read the information from a file ) in the method `onNearbyUpdate`.

## Notes
- You shouldn't spend more than 8 hours on this assignment.
- Submit your solution with a brief description on how you tackled the problem and if you skipped some requirements or made any assumptions please mention them.

Have fun!

Instabridge's Android Team
