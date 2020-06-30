# TreasureHunt
application made for group 4 by Daniël KvG

This is and android application made in android studio.
The main idea for this applicatoin was to connect to the smart product and receive the location and the code to win the game.
Due to some complexity with BLE, this connection could not be made with the knoledge I have at the moment.
To make this prototype application as real as possible, loading screens are added on all the places the user must wait for other players.
The user can add and remove names if he is the first player to "connect to the smart product". The first player is also able to change
settings for the game such as number of hiders, time to hide with the seeker waiting and time to seek.
When the first player starts the game, all players will be able to choose their role based on the random role divider. In the massproduct,
this will be done automatically.
In the map for the seeker, a static point is shown instead of the circel produced around the location of the smart product.
If the application was able to receive strings from the arduino ble, the latlng information could be cut out of the string and used to produce the circles.
When the seeker has found the smart product, he can read the code from the smart product to confirm that he/she has found it.
For the prototype, this code is made static: 6249.
