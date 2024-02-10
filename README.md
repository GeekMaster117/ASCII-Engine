ASCII-Engine
--------------------------------------------------------------
HOW IT WORKS:-

-> Both the library for C programming language and jar file for Java programming language provide various methods to implement simple ASCII graphics.

-> The library for C programming language only provides functions that can be called.

-> The jar file for Java programming language provides the same functions available in the library for C programming language but puts similar methods into a class.

--------------------------------------------------------------
ADVANTAGES OF ASCII Engine.h over ASCII Engine.jar:-

-> Has over twice the features compared to the other.

-> Can be used with or without compiling .h file into .a file, enhancing flexibility.

-> Computational speed bottlenecks at a much higher refresh rate compared to the other, leading to flexibility of usage of higher refresh rates.

-> All the features can be used irrespective of the operating system.

--------------------------------------------------------------
ADVANTAGES OF ASCII Engine.jar over ASCII Engine.h:-

-> Has better organisation by putting similar methods into a class than the other, increasing workflow.

-> Provides robust customised exceptions to handle any which occur during runtime.

-> Decreases the no.of lines required for initialisation as constructors in classes take care of it.

-> Does not have to be compiled to enhance speed unlike the other.

----------------------------------------------------------------------------------
HOW TO USE ASCII Engine.jar:-

-> Class - Canvas - Provides methods to draw graphics
.
--> Constructor - Creates a canvas object with specified width and height.

--> Method - ChangeDimensions() - Changes the dimensions of the canvas.

--> Method - ClearCanvas() - Clears the entire canvas by replacing all the characters with ' '.

--> Method - FillCanvas() -  Fills the entire canvas with a character; arguments - paint character (char)

--> Method - InsertChar() - Inserts a single character in the specified position on the canvas; arguments - paint character (char), x coordinate (int), y coordinate (int)

--> Method - InsertXLine() - Inserts a line from one position to another position on the canvas, when drawing the line it makes sure that every x coordinate from the start position to the end position has been filled; arguments - paint character (char), start x coordinate (int), start y coordinate (int), end x coordinate (int), end y coordinate (int)

---------------------------------------------------------------------------------

-> Class - Screen - Provides methods to interact with the command prompt.

--> Method - StartConsole() - Useful when the application needs to be packaged into a jar file. Jar files do not start the command prompt when clicked, so to start the command prompt the StartConsole can be called from inside the jar file to run a Java program on the command prompt, the java program running on the newly started command prompt will have all the function calls to draw the graphics while the java file which used StartConsole function call sole purpose is to start the command prompt; arguments - Path to the java file which has all the function calls required to draw the graphics (Path object)

--> Method - Display() - Displays the canvas onto the command prompt; arguments - Canvas object

--> Method - PreRefresh() - Clears the command prompt and then displays the canvas onto it; arguments - Canvas object

--> Method - PostRefresh() - Displays the canvas onto the command prompt then clears it; arguments - Canvas object

--> Method - ClearConsole() - Clears the command prompt.
