# Controlstick

Emulates keyboard and mouse input from a controller, allowing you to play games with little or no controller support.

+ Custom control schemes
+ Adjust sensitivity
+ Wireless - Works over bluetooth
+ Invertable axes

Controlstick uses [JInput](https://java.net/projects/jinput), a multiplatform API for interfacing with game controllers and joysticks. Controlstick adds a powerful and flexible interface on top of JInput which lets you change the behaviour of each individual button and axis on your controller.

### Testing

Controlstick has been tested with an official and an unofficial DualShock 3 controller with the following games on a 2010 MacBook Pro.

+ Assassin's Creed
+ Half Life
+ Minecraft
+ Portal

The only game so far to fail is the Stanley Parable, which is disappointing :'(. Also some games which distinguish between left and right shift keys may not work, as a generic _shift_ is the only key that is emulated.

### License

This project uses [JInput](https://java.net/projects/jinput) which is available under the _BSD 2-Clause License_. Which means it has a [disclaimer](http://opensource.org/licenses/bsd-license.php).

Any modifications/ forks of the Controlstick code must be non-commercial and have a clear attribution back to [me](http://javanut13.github.io) and why not [JInput](https://java.net/projects/jinput) too.

tl;dr --> Controlstick is provided as-is; it could break (it probably won't, but it might!) and if you want to use the code just contact me [@JavaNut13](http://twitter.com/javanut13) first.