# Android Assignment

The goal of this assignment is to check how much you know about object oriented principles, the android framework, 
design patterns and written communication skills in the context of mobile development.

## Description
Write an android application that displays a list of wifis and when you click on any of the items it will open another screen 
showing the details of the selected wifi.

![Mockup](https://github.com/Instabridge/android-assignment/blob/master/images/mock.png)

### List Screen
The main idea of the list screen is to display 2 groups of wifis. The from the first group (**in range**) are the ones displayed from the device and the second group (**nearby**) are wifis from an external service.

* The items on the list should contain at least the SSID of the corresponding wifi, along with an icon representing its signal level
* The **in range** group should display the maximum of 3 wifis
* The **nearby** group displays initially 3 items, along with a _SHOW MORE_ button
* Whenever the _SHOW MORE_ button is clicked, append 3 more items (at most) to that group

### Detail Screen
There are no specifications for this screen. Be creative and display the information you feel more adequate.

### Information Source
We provided a library that displays fake wifis. You can use it at the beginning to speed up your development.

**Nice to have**
- Use the real data from the `WifiManager` to populate the **in range** group
