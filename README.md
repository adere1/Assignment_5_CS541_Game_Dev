
CS 541 - Game Development for Mobile Platforms {Spring 2019}

Project 5 - Android Application  :  Graph Generator

Animation and sound effects video (YouTube) - https://youtu.be/LQvwdtW5jnE

This is the android application to generate graphs.  It has client-server architecture, where this application works as client and we're hosting the lightweight Java based HTTP server on Google Cloud Platform.
Whenever user submits the request to generate graph, the request is sent to our server running on Google Cloud Platform, which generates and sends the graph in response back to the application.

You have to options to create your graph - 
1. Using the "dot" language representation	
2. Manually providing information about nodes and connections

In the first choice, you need to provide the "dot" language representation of your graph, and hit 'Generate Graph' button to render the graph.

If you don't have "dot" representation, no worries, we have you covered. Choose the second option.
Here,  first you need to provide number of nodes your graph contains, and for each node, you need to provide the node Name and Shape.
The shape can be selected using drop-down box - Circle, Box, Diamond, Ellipse, Oval, Doublecircle, Square, Parallelogram, Trapezium, and Egg.
Next thing is, you need provide the edges between the nodes using  'From Node' and 'To Node' drop-down.
All the details you have entered so far will be visible to you all the time on the screen.
Once you provide all the details, hit 'Generate Graph' button to render the graph.

Sample dot input is added to GitHub directory.

In-progress Implementations - Download Image and PDF format of the Graph

		
NOTE - For animation,  please see video at - https://youtu.be/LQvwdtW5jnE



