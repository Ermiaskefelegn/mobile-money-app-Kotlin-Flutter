# Mobile Money App - Kotlin

A modern mobile money transfer application built with Kotlin and Jetpack Compose, following Material Design principles and MVVM architecture.

## Features

### Core Functionality
- **Home Screen**: Main dashboard with balance display and quick actions
- **Add Money**: Multiple funding options including bank transfers and international remittances
- **Send to Bank**: Complete bank transfer flow with form validation
- **PIN Confirmation**: Secure transaction confirmation with PIN entry
- **Recent Transfers**: View transaction history with pull-to-refresh
- **Transfer Details**: Detailed view of individual transactions

### Technical Features
- **MVVM Architecture**: Clean separation of concerns
- **Jetpack Compose**: Modern declarative UI
- **Room Database**: Local data persistence
- **Hilt Dependency Injection**: Efficient dependency management
- **Navigation Component**: Type-safe navigation
- **StateFlow**: Reactive state management
- **Form Validation**: Real-time input validation
- **Material Design 3**: Modern UI components

## Screenshots

### Home Screen
![Home Screen](screenshots/home_screen.png)

### Add Money Options
![Add Money](screenshots/add_money_screen.png)

### Bank Transfer Form
![Bank Form](screenshots/bank_form_screen.png)

### PIN Confirmation
![PIN Confirmation](screenshots/pin_confirmation_screen.png)

### Recent Transfers
![Recent Transfers](screenshots/recent_transfers_screen.png)

## Project Structure

app/src/main/java/com/mobilemoney/
├── ui/
│   ├── screens/
│   │   ├── HomeScreen.kt
│   │   ├── AddMoneyScreen.kt
│   │   ├── ReceiveFromAbroadScreen.kt
│   │   ├── BankFormScreen.kt
│   │   ├── ConfirmationScreen.kt
│   │   ├── RecentTransfersScreen.kt
│   │   └── TransferDetailsScreen.kt
│   └── theme/
│       ├── Theme.kt
│       └── Type.kt
├── model/
│   ├── BankTransferData.kt
│   └── Transfer.kt
├── viewmodel/
│   ├── BankTransferViewModel.kt
│   └── RecentTransfersViewModel.kt
├── data/
│   ├── TransferDao.kt
│   ├── TransferDatabase.kt
│   ├── Converters.kt
│   └── TransferRepository.kt
├── di/
│   └── DatabaseModule.kt
├── navigation/
│   └── MobileMoneyNavigation.kt
├── MainActivity.kt
└── MobileMoneyApplication.kt

## Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture pattern:

- **Model**: Data classes and Room entities
- **View**: Jetpack Compose UI screens
- **ViewModel**: Business logic and state management
- **Repository**: Data access abstraction layer

## Key Components

### Navigation
- Uses Navigation Compose for type-safe navigation
- Centralized navigation logic in \`MobileMoneyNavigation.kt\`

### State Management
- ViewModels use StateFlow for reactive state management
- UI state is managed through data classes

### Database
- Room database for local data persistence
- Pre-seeded with sample transfer data
- Type converters for enum handling

### Validation
- Real-time form validation in bank transfer screen
- Minimum amount validation (25 Birr)
- Account number format validation

## Setup Instructions

1. **Clone the repository**
   \`\`\`bash
   git clone https://github.com/yourusername/mobile-money-app-kotlin.git
   cd mobile-money-app-kotlin
   \`\`\`

2. **Open in Android Studio**
    - Open Android Studio
    - Select "Open an existing project"
    - Navigate to the cloned repository

3. **Build and Run**
    - Sync project with Gradle files
    - Run the app on an emulator or physical device

## Requirements

- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Kotlin**: 1.9.0
- **Compose BOM**: 2024.04.01

## Dependencies

### Core
- Jetpack Compose
- Navigation Compose
- Material Design 3
- Lifecycle ViewModel Compose

### Architecture
- Hilt (Dependency Injection)
- Room (Database)
- StateFlow (State Management)

### UI
- Accompanist Swipe Refresh
- Material Icons

## Testing

The project includes:
- Unit tests for ViewModels
- UI tests for Compose screens
- Repository tests with Room

Run tests using:
./gradlew test
./gradlew connectedAndroidTest



## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

For questions or support, please open an issue on GitHub.
Ermias Kefelegn
ekefelegn1488@gmail.com