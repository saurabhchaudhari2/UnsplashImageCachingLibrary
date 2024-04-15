## UnsplashImageCachingLibrary

This is an Android library that helps with caching and displaying images from Unsplash.

### Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/saurabhchaudhari2/UnsplashImageCachingLibrary.git
   ```

2. **Open the project in Android Studio:**

   - Open Android Studio.
   - Select "Open an existing Android Studio project".
   - Navigate to the project directory you cloned in step 1.

3. **Verify dependencies:**

   - Go to "File" -> "Project Structure" -> "Dependencies".
   - Ensure all dependencies listed in the `build.gradle` file are present.

4. **Run the app:**

   - Make sure you have an active internet connection.
   - Click the "Run" button in Android Studio to run the app on an emulator or a physical device.

### Usage

The app displays a grid of images fetched from Unsplash.

- **Online mode:**
  - The app fetches and displays images from Unsplash.

- **Offline mode:**
  - App will show network error in offline mode

- **Loader:**
  - Observe the loader while the app fetches images for the first time.
  - The loader disappears once the images are loaded.

