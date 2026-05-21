# AppFactory

An automated factory for generating Android Play Store-ready applications.

## Overview

AppFactory is a comprehensive toolset for rapidly creating, building, and deploying Android applications to the Google Play Store. It provides templates, automation scripts, and CI/CD pipelines to streamline the entire app development lifecycle.

## Features

- 🚀 **Quick App Generation**: Bootstrap new Android apps in seconds
- 📦 **Play Store Ready**: Pre-configured for Google Play Store deployment
- 🔐 **Secure Signing**: Automated key management and app signing
- 🔄 **CI/CD Integration**: GitHub Actions workflows for automated builds and releases
- 📱 **Modern Stack**: Kotlin, Jetpack Compose, Material 3
- 🧪 **Testing**: Unit and UI testing frameworks included
- 📊 **Analytics Ready**: Integrated analytics tracking
- 🎨 **Customizable**: Easy to adapt and extend

## Quick Start

### Prerequisites

- Java 11+
- Android SDK (API 33+)
- Gradle 8.0+
- Git

### Create Your First App

```bash
python3 generate_app.py \
  --app-name "MyAwesomeApp" \
  --package-name "com.example.myapp" \
  --version "1.0.0"
```

This will:
1. Generate a complete Android project structure
2. Set up Gradle configurations
3. Create necessary signing keys
4. Initialize git repository
5. Configure GitHub Actions workflows

## Project Structure

```
AppFactory/
├── templates/              # App templates and boilerplate
├── scripts/               # Generation and build scripts
├── gradle/                # Gradle configurations
├── workflows/             # GitHub Actions workflows
├── docs/                  # Documentation
└── examples/              # Example apps
```

## Building & Releasing

### Build for Testing
```bash
./gradlew assembleDebug
```

### Build for Play Store
```bash
./gradlew bundleRelease
```

### Automated Release
Push to `release/*` branch to trigger automatic Play Store deployment.

## Configuration

See `docs/configuration.md` for detailed setup instructions including:
- Google Play Store API key setup
- App signing configuration
- GitHub Actions secrets
- Version management

## Documentation

- [Getting Started](docs/getting-started.md)
- [Configuration Guide](docs/configuration.md)
- [CI/CD Pipeline](docs/cicd.md)
- [Play Store Deployment](docs/playstore.md)
- [Custom Templates](docs/templates.md)

## License

MIT

## Contributing

Contributions welcome! See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

---

Created with ❤️ by Kawhooped
