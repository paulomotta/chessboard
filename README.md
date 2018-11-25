# chessboard
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