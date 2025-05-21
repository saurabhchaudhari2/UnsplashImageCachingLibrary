# Unsplash Image Caching Library (Compose Multiplatform)

## Project Overview

This project is an image caching library designed to fetch and display images, originally for Android. It has been restructured to use **Compose Multiplatform** with a **Clean Architecture** approach, enabling shared UI and business logic across platforms (Android and iOS).

## Module Structure

The project primarily consists of the `app` module:

-   **`app`**: Main application module.
    -   **`commonMain`**: Contains the core shared Kotlin Multiplatform code. This includes:
        -   **Domain Layer**: Business logic, use cases, and domain models.
        -   **Data Layer**: Repository interfaces, data source interfaces (e.g., for network API), and concrete repository implementations that use these interfaces. Includes the PagingSource for fetching images.
        -   **Presentation Layer**: ViewModels (shared business logic for UI), and Composable UI screens and components built with Jetpack Compose.
    -   **`androidMain`**: Android-specific implementations and configurations:
        -   Android Application class and Activity.
        -   Platform-specific Koin setup (`androidModule.kt`) including Retrofit for network calls.
        -   `AndroidManifest.xml` and Android resources.
        -   Actual implementation for platform-specific functionalities like image loading (using Coil).
    -   **`iosMain`**: iOS-specific implementations and configurations:
        -   Placeholder for platform-specific functionalities (e.g., image loading uses a placeholder Box).
        -   (Future) iOS application entry point and Koin setup for iOS.

## Architecture

The shared code in `commonMain` follows the principles of **Clean Architecture**:

-   **Domain Layer**: Contains the core business logic, defined by use cases (e.g., `GetImagesUseCase`) and domain models (e.g., `ImageResponse`, `ImageUrls`). This layer is independent of any framework or platform.
-   **Data Layer**: Implements the repository pattern (`ImageRepository` and `ImageRepositoryImpl`). It defines interfaces for data sources (`UnsplashApi`) and manages data fetching and caching logic. The `UnsplashPagingSource` for handling paginated image loading from the Unsplash API resides here.
-   **Presentation Layer**: Contains ViewModels (`ImageListViewModel`) responsible for preparing and managing UI-related data, and Composable UI elements (`ImageListScreen`, `NetworkImage`, `ExpectedCoilImage`) built with Compose Multiplatform.

**Dependency Injection**: [Koin](https://insert-koin.io/) is used for dependency injection across the shared `commonMain` code and in platform-specific modules (`androidMain`).

## Platform-Specific Code

The `expect`/`actual` mechanism is utilized for platform-specific functionalities:

-   **Image Loading**:
    -   `commonMain`: Defines `expect composable fun ExpectedCoilImage(...)`.
    -   `androidMain`: Provides the `actual` implementation using `coil.compose.AsyncImage` for efficient image loading on Android.
    -   `iosMain`: Provides a placeholder `actual` implementation (a `Box` with text) as a stand-in for a native iOS image loading solution.

## API Key Configuration

To fetch images from Unsplash, an API key is required. This needs to be configured in:

-   **File**: `app/src/androidMain/kotlin/com/saurabh/imagecachinglibrary/di/AndroidModule.kt`
-   **Constant**: `UNSPLASH_API_KEY_VALUE`

Replace `"YOUR_ACCESS_KEY"` with your actual Unsplash API key.

## Known Issues

The project currently faces a Gradle build issue that prevents successful Android and iOS builds:

-   When `RepositoriesMode.PREFER_SETTINGS` is enabled in `settings.gradle.kts`, the build fails with the error:
    `Could not resolve all dependencies for configuration ':app:detachedConfiguration5'. > Could not find :kotlin-native-prebuilt-linux-x86_64:1.9.0.`
-   The previous setting `RepositoriesMode.FAIL_ON_PROJECT_REPOS` also caused a build failure related to an 'ivy' repository being unexpectedly added by `app/build.gradle.kts`.

This issue needs to be resolved to proceed with building and running the application on either platform.

## How to Build (Aspirational)

Once the Gradle build issue (mentioned above) is resolved, the project should be buildable using standard Gradle tasks:

-   **Android App**:
    ```bash
    ./gradlew :app:assembleDebug
    ```
-   **iOS Framework** (for integration into an Xcode project):
    ```bash
    ./gradlew :app:linkDebugFrameworkIosSimulatorArm64
    ```
    (Or other iOS targets like `linkDebugFrameworkIosArm64` or `linkReleaseFrameworkIos...`)

---

*Existing setup instructions and usage details specific to the old Android-only version have been removed or updated to reflect the new KMP architecture.*
