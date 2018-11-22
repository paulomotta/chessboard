# chessboard
I like to start projects with a proof of concept so that some concrete part is 
in place and helps organize the ideas. One main importance of having a PoC is
that it may be completely thrown away if necessary.

I will start with a Java project and create the main utilitary functions with
some unit tests to validate the initial idea.

I will accept the algebraic notation as Strings and will decode them to integers
so that they can be used as indexes to the chess board which is going to be
dynamic, but never smaller than the official 8x8.

The user will be able to grown the board on columns and rows, for the columns
once the alphabet limit is reached (i.e. 'z') the next values will use the same
notation of spreadsheets (i.e. 'aa', 'ab', etc).