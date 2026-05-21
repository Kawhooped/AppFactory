# AppFactory Templates

Comprehensive templates and components for building production-ready Android apps.

## 📦 Included Templates

### Authentication (`auth/`)
- **LoginScreen.kt**: Complete login and signup screens with form validation
- Features:
  - Email and password validation
  - Error handling and user feedback
  - Loading states
  - Sign-up flow integration

### Dashboard (`dashboard/`)
- **DashboardScreen.kt**: Professional dashboard with statistics and activity
- Features:
  - Statistics cards with key metrics
  - Recent activity timeline
  - Responsive layout
  - Material Design 3 components

### Settings (`settings/`)
- **SettingsScreen.kt**: Complete settings management
- Features:
  - Account settings (email, password, profile)
  - User preferences (notifications, dark mode, sync)
  - About section (version, policies, terms)
  - Organized sections and toggles

### E-Commerce (`ecommerce/`)
- **ProductScreen.kt**: Full e-commerce product interface
- Features:
  - Product listing with cards
  - Product detail view
  - Ratings and pricing
  - "Add to Cart" functionality
  - Discount display

### Database (`database/`)
- **DatabaseHelper.kt**: Room database setup with entities and DAOs
- Includes:
  - User entity and operations
  - Product entity and operations
  - Order entity and operations
  - Coroutine-based queries
  - Flow-based reactive updates

### API Integration (`api/`)
- **ApiClient.kt**: Retrofit setup for backend communication
- Services:
  - Authentication (login, signup, logout)
  - Products (list, search, details)
  - Orders (create, retrieve, update)
  - Logging and error handling

### Payment Processing (`payment/`)
- **PaymentProcessor.kt**: Complete payment handling system
- Features:
  - Payment method management
  - Transaction processing
  - Refund handling
  - Payment status tracking
  - Error handling

### Utilities
- **NotificationHelper.kt**: Push notifications with channels
  - High, default, and low-priority channels
  - Progress notifications
  - Notification management

- **SharedPreferencesHelper.kt**: Secure local storage
  - String, Int, Boolean, Float, Long storage
  - Key management
  - Preferences persistence

## 🚀 Quick Start

### 1. Copy Templates to Your Project

```bash
# Copy authentication templates
cp templates/auth/*.kt your_project/src/main/java/com/yourapp/auth/

# Copy dashboard templates
cp templates/dashboard/*.kt your_project/src/main/java/com/yourapp/dashboard/

# Copy other templates as needed
```

### 2. Update Package Names

Replace `com.appfactory` with your actual package name:

```bash
find . -name "*.kt" -type f -exec sed -i 's/com.appfactory/com.yourcompany.yourapp/g' {} \;
```

### 3. Add Dependencies to build.gradle

```gradle
dependencies {
    // Jetpack Compose
    implementation 'androidx.compose.ui:ui:1.5.0'
    implementation 'androidx.compose.material3:material3:1.0.0'
    
    // Room Database
    implementation 'androidx.room:room-runtime:2.5.1'
    kapt 'androidx.room:room-compiler:2.5.1'
    implementation 'androidx.room:room-ktx:2.5.1'
    
    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.11.0'
    
    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1'
    
    // Serialization
    implementation 'org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1'
    
    // Notifications
    implementation 'androidx.core:core:1.10.0'
}
```

### 4. Initialize Components in MainActivity

```kotlin
import com.appfactory.database.AppDatabase
import com.appfactory.utilities.NotificationHelper
import com.appfactory.utilities.SharedPreferencesHelper

class MainActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase
    private lateinit var notificationHelper: NotificationHelper
    private lateinit var preferencesHelper: SharedPreferencesHelper
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize helpers
        database = AppDatabase.getInstance(this)
        notificationHelper = NotificationHelper(this)
        preferencesHelper = SharedPreferencesHelper(this)
        
        setContent {
            AppTheme {
                MainScreen()
            }
        }
    }
}
```

## 🎨 Customization

### Theme Colors

Update `Material3` theme in your `Theme.kt`:

```kotlin
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC6),
    tertiary = Color(0xFF018786)
)
```

### API Base URL

Update in `ApiClient.kt`:

```kotlin
private const val BASE_URL = "https://your-api.com/v1/"
```

### Database Name

Change in `AppDatabase.kt`:

```kotlin
"app_database" // Change to your desired name
```

## 📱 Building for Play Store

### 1. Generate Signing Key

```bash
keytool -genkey -v -keystore release.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias appkey
```

### 2. Configure Signing in build.gradle

```gradle
android {
    signingConfigs {
        release {
            storeFile file('release.keystore')
            storePassword System.getenv('KEYSTORE_PASSWORD')
            keyAlias System.getenv('KEY_ALIAS')
            keyPassword System.getenv('KEY_PASSWORD')
        }
    }
    
    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
```

### 3. Build Release APK/AAB

```bash
# Build AAB (recommended for Play Store)
./gradlew bundleRelease

# Build APK
./gradlew assembleRelease
```

## 🔐 Security Best Practices

1. **Store sensitive data** using Android Keystore
2. **Use HTTPS** for all API calls
3. **Validate user input** on all screens
4. **Implement ProGuard/R8** obfuscation
5. **Use Firebase** for crash reporting
6. **Keep dependencies updated**

## 📚 Documentation

- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Room Database Documentation](https://developer.android.com/training/data-storage/room)
- [Retrofit Documentation](https://square.github.io/retrofit/)
- [Google Play Store Guidelines](https://play.google.com/console)

## 🤝 Support

For issues or feature requests, create an issue in the repository.

## 📄 License

MIT License - Feel free to use in your projects
