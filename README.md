# Chessboard
I like to start projects with a proof of concept so that some concrete part is 
in place and helps organize the ideas. One main importance of having a PoC is
that it may be completely thrown away if necessary.

I will start with a Java project and create the main utilitary functions with
some unit tests to validate the initial idea.

I will accept the algebraic notation as Strings and will decode them to integers
so that they can be used as indexes to the chess board which is going to be
dynamic, but never smaller than the official 8x8.

The user will be able to grown the board on columns and rows. The columns will 
be limited by the size of the alphabet (i.e. 'z'). At first I thought on using
a spresheet like indexing however it is very hard to go back from 27 to "ab" 
since it could be both "ab" or "ba" and solving this ambiguity will provide 
little extra value for now. It could be addressed in a future version with a
different board indexing scheme. 

##Structure
The project has a core library that implements the business rules as POJOs

The core is exposed as a service through the api project and uses Spring to 
become a self-contained executable jar. It is configured to listen on 8090.
It also uses Spring Data to persist information to the H2 database using JPA.

The web client is based on Java Server Faces and uses Spring to become a 
self-contained executable war. It is configured to listen on 8080.

##Deployed version
The project is running at AWS and may be accessed here:
http://ec2-3-17-28-134.us-east-2.compute.amazonaws.com:8080/index.xhtml

##Timeline of the development
The project was refactored many times on the core where the algorithms to solve
the problem were implemented. 
The first idea was to generate all the possible moves for a certain piece on a
certain position regardless of these positions being valid or not. After that 
higher level method processes the resulting positions to check if they are valid
or not on the current board, if so they are accumulated on each level and the
previously generated positions are used to calculate the next possibilities for
the next turn. 
This algorithm may be used for as many turns as we want and since it uses a
ChessPieceMover interface with a Factory we can implement different movers for
the other pieces.

##Tools
I have used NetBeans 8.2 as my IDE so all the builds were executed inside the 
IDE, I have also created the spotbugs:spotbugs action to check for possible 
bugs during the development. Currently there are some style (less than minor) 
bugs of possible null returns inside the service that were reported by spotbugs
but couldn't be corrected.

##Miscelaneous Comments
- At first I considered creating a separated service for the algebraic notation but it felt like to much infrastructure just o have another service. Although there are many parts that use the translation from position to algebraic notation it would be costly to send the Strings back and forth for all the computation steps.
- The core Position class uses a hardcoded maximum value of 25 and it could use the Chessboard constant, however that would incur in a circular reference and I couldn't decide on what would be the lesser evil at the time.
- The UI chessboard algorithm is very similar to the internal one but focused on the structure of the board to be drawn to the screen, it was a late night piece and need a good refactoring
- It would be interesting to have Selenium to automate tests over the UI
- The way that the projects interact and are self-contained it would be relatively easy to dockerize it. Right now the webclient is a self-contained executable war and the chessboard service is a self-contained executable jar running in separated process on a AWS instance, so it is semantically similar to use docker, but without the isolation
- One thing that ended up missing was logging to the a file using SLF4J but the debugging messages were used only in the beginning to create the unit tests. After the core was working correctly there was little benefit in including the log messages