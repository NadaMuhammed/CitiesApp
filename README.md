# 🌍 Cities App

A modern Android app built with Jetpack Compose that displays a list of cities loaded from a local JSON file. The app supports fast, responsive search using binary search on a pre-sorted list.

## ✨ Features

- 🚀 **Loads Cities Locally**  
  Parses cities from a local JSON file using Kotlin serialization.

- 🔎 **Efficient Search**
    - Filters out special characters and whitespace for clean search matching.
    - Uses binary search for fast results on the sorted dataset.
    - Real-time search with debounce for optimal UX.

- 🧱 **Architecture**
    - Follows MVVM Architectural pattern.
    - Follows Clean Architecture
    - Built using StateFlow, coroutines, and Hilt for dependency injection.

- 🎨 **Jetpack Compose UI**
    - Modern, declarative UI components.
    - Clean separation between UI and state logic.

## 🛠 Tech Stack

- Kotlin
- Jetpack Compose
- StateFlow & Coroutines
- Hilt (DI)
- MVVM Architecture