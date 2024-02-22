# APM
APM projects from uni
Subject requirement:
Implement an app that allows monitoring a person's fitness. 
For each day, the app stores the total numbers of steps, hours of sleep, and a list of physical activities with their associated move minutes.
Database input example:
{
2023.01.28, 7.5, morning_walk 12, afternoon_walk 23
2023.02.04, 3660, 8, afternoon_walk 38
2023.02.13, 364, 6, 
2023.02.10, 259, 7.5, swimming 30
2023.01.18, 2580, 8.5, morning_walk 25, gymnastics 60
}

Write the app using JavaFX GUI and allow:
1a)Visualising all activities in a list, with their information
1b)sort ascending by date
When the app starts populate the list automatically
2)Allow the user to filter activities, by showing only those for which the total number of move minutes is within a given range and all physical activities contain a given string.
Use java streams
3)Add a new activity, by giving the date, number of steps, hours of sleep, physical activity and move minutes.
If input date already exists, the number of steps and hours of sleep will be updated and the physical activity will be added to the list of physical activities.
Save these changes to the database.
4)When the app starts, allow the user to input a minimum number of steps per day and congratulate them when they add a new activity for which the number of steps is higher than the set threshold.
