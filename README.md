# Live Cricket Info

Live Cricket Info is an Android application that provides live updates on cricket matches. The application uses the JSoup library to extract data from official cricket websites and displays live updates across various fragments and activities using Android components such as services, singleton design patterns, adapters, and async tasks. Additionally, the app utilizes Firebase Cloud Messaging to trigger notifications when there are changes in match data.

## Features

- **Live Cricket Updates:** Displays real-time updates for ongoing cricket matches.
- **Web Scraping:** Utilizes JSoup library for parsing HTML and CSS from official cricket websites.
- **Dynamic Notifications:** Uses Firebase Cloud Messaging to send notifications when match data is updated.
- **Efficient Data Handling:** Implements services, singleton design patterns, adapters, and async tasks for smooth performance.

## Libraries and Tools

- **JSoup:** For extracting and parsing HTML from web pages.
- **Firebase Cloud Messaging:** For real-time notifications.
- **Android SDK:** For building the Android application.

## Prerequisites

- Android Studio
- Java Development Kit (JDK)
- An active Firebase project

## Getting Started

Follow these steps to set up and run the Live Cricket Info application on your local development environment:

### 1. Clone the Repository

```bash
git clone https://github.com/divyanshusingh700/live-cricket-info.git
cd live-cricket-info
### 2. Open the Project in Android Studio

- Launch Android Studio.
- Select "Open an existing Android Studio project."
- Navigate to the `live-cricket-info` directory and open it.

### 3. Install Dependencies

- Open `build.gradle` files (both project-level and app-level) and ensure all dependencies are correctly added.
- Sync the project with Gradle files by clicking the "Sync Now" button in the toolbar.

### 4. Set Up Firebase

1. Go to the [Firebase Console](https://console.firebase.google.com/).
2. Create a new project or use an existing one.
3. Add your Android app to the Firebase project and download the `google-services.json` file.
4. Place the `google-services.json` file in the `app/` directory of your project.
5. Follow the instructions to add Firebase dependencies to your `build.gradle` files.

### 5. Configure Permissions

Ensure you have the necessary permissions for internet access and Firebase messaging in your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

<application
    ...>
    <service
        android:name=".MyFirebaseMessagingService"
        android:exported="false">
        <intent-filter>
            <action android:name="com.google.firebase.MESSAGING_EVENT"/>
        </intent-filter>
    </service>
    ...
</application>
6. Build the APK or AAB
To generate an APK or AAB file, follow these steps:

Open Build Menu:

Go to Build in the top menu.
Generate APK:

Select Build Bundle(s) / APK(s).
Choose Build APK(s).
Android Studio will build the APK, and you will receive a notification once it's done. You can find the APK file in the app/build/outputs/apk/ directory.
Generate AAB:

Select Build Bundle(s) / APK(s).
Choose Build Bundle(s).
Android Studio will build the AAB, and you will receive a notification once it's done. You can find the AAB file in the app/build/outputs/bundle/ directory.
7. Run the Application
Connect an Android device or start an emulator.
Click the "Run" button (green play arrow) in Android Studio.
Select your device or emulator from the list.
The app will be installed and launched on your device/emulator, and you can start using it to view live cricket match updates.

Contributing
Feel free to contribute by submitting issues or pull requests. For major changes, please open an issue first to discuss what you would like to change.

License
This project is licensed under the MIT License. See the LICENSE file for details.

Contact
For any questions or feedback, please reach out to [divyanshusingh9314@gmail.com] or [divyanshu0924@gmail.com].

