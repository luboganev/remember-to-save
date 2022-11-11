# Introduction
This repository contains a playground project that enables testing of
different behavior of remember and rememberSavable Compose functions
when observing LiveData coming from a fake persisted storage data
that can be updated while on same screen or different screen. The screen navigation
uses Compose navigation library

### The not so interesting components:
- A DateTimeUseCase which simply serves the purpose of our data source which lives longer than the UI.
- A simple main-detail Compose navigation between two screens - main screen and details screen,
where the detail screen can only navigate back
- Each screen has a ViewModel
    - MainViewModel is responsible for showing the data from the DateTimeUseCase in the UI
    - DetailViewModel is responsible for updating the data in DateTimeUseCase
- The DetailScreen is not interesting, it contains only a button to modify data and one to navigate back

### The actually interesting components:

- The MainScreen contains the RememberExperiment composable which gets has input the formatted time as a string and
contains three switches with state defined as following:
    - Inside remember(keys=input the formatted time)
    - Inside rememberSaveable(inputs=input the formatted time)
    - Inside rememberSaveable(inputs=input the formatted time, key=input the formatted time)
- In addition the MainScreen observes the changing time in 3 different ways, i.e. ObserveAsStateExperiment:
    - observeAsState("initial") - with initial value
    - observeAsState() and RememberExperiment wrapped into an ?.let{}
    - observeAsState() and RememberExperiment called with the nullable value

### How to use

- Test without navigation
    - Switch on all interesting switches
    - Tap update time
    - Notice the differences in the checked state of the switches
- Test with navigation
    - Switch on all interesting switches
    - Open detail screen
    - Tap update time or don't tap it depending on how you want to test
    - Navigate back
    - Notice the differences in the checked state of the switches



