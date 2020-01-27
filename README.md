# Android Foursquare - Find Nearby Restaurants

## Covered As part of this project
- Clean architecture with MVVM
- 100% Kotlin code (except AutoValue model classes)
- Android arch components - ViewModel, LiveData, etc
- Rx for async calls
- Dagger for dependency injection
- Retrofit for network calls
- AutoValue and Gson for Data model parsing

## App Structure
Each feature screen will be added as new *package* in project. eg: nearbyrestaurant, restaurantdetail
Each feature *package* will follow - Clean architecture with MVVM.

#### View (Activity)
* can be found in *view* package
* handles input from user

#### ViewModel
* can be found in *viewmodel* package
* sets and removes callbacks in UseCases
* acts as a routing between UseCases and the View
* Can capture and save the UI states

#### UseCases (Domain logic)
* can be found in *domain* package
* has callbacks which point to *ViewModel*
* facilitates Rx observables with deals with the request asynchronously by calling synchronous repositories and calls callbacks as a result
* Cleanup: trackUseCase() method for registering UseCases. cleanup() method will make sure all UseCases unsubscribed their RX observables

#### Repositories
* Network repositories are only contacted from *UseCases*. They work synchronously making them very clean and easy to understand

#### Networking
* Networking is provided using Retrofit interface `FourSquareApi` in *core/network/api* package
* Have used an interceptor(AddClientKeyInterceptor) to add Foursquare's client secret key, id and version to every request

#### Model
* can be found in *core/network/model* package
* built on auto-value and parcelable

#### Dependency Injection
* can be found under *injection* package
* each View, ViewModel, Domain, Repository will have its own injection package class, so it could be plug and play anywhere in the app

#### Core package
* This will have all the utility classes shared across app

#### Dagger Scopes
* 2 Levels of Scoping - Application and Activity
