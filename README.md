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

--> Method - changeDimensions() - Changes the dimensions of the canvas.

--> Method - clearCanvas() - Clears the entire canvas by replacing all the characters with ' '.

--> Method - fillCanvas() -  Fills the entire canvas with a character; arguments - paint character (char)

--> Method - insertChar() - Inserts a single character in the specified position on the canvas; arguments - paint character (char), x coordinate (int), y coordinate (int)

--> Method - insertXLine() - Inserts a line from one position to another position on the canvas, when drawing the line it makes sure that every x coordinate from the start position to the end position has been filled; arguments - paint character (char), start x coordinate (int), start y coordinate (int), end x coordinate (int), end y coordinate (int)

--> Method - insertYLine() - Inserts a line from one position to another position on the canvas, when drawing the line it makes sure that every y coordinate from the start position to the end position has been filled; arguments - paint character (char), start x coordinate (int), start y coordinate (int), end x coordinate (int), end y coordinate (int)

--> Method - insertRect() - Inserts a rectangle from a specified position to the other opposite corner whose position is calculated using specified width and height; arguments - paint character (char), x coordinate (int), y coordinate (int), width (int), height (int)

--> Method - insertSolidRect() - Inserts a solid rectangle from a specified position to the other opposite corner whose position is calculated using specified width and height; arguments - paint character (char), x coordinate (int), y coordinate (int), width (int), height (int)

--> Method - insertXEllipse() - Inserts an ellipse whose midpoint is the specified position and draws the ellipse using the specified major and minor axis, when drawing the ellipse it makes sure that every x coordinate from the one end of the major axis to the other end of the major axis has been filled; arguments - paint character (char), midpoint x coordinate (int), midpoint y coordinate (int), major axis (int), minor axis (int)

--> Method - insertEllipse() - Inserts a solid ellipse whose midpoint is the specified position and draws the ellipse using the specified major and minor axis, when drawing the ellipse it makes sure that every x coordinate from the one end of the major axis to the other end of the major axis has been filled; arguments - paint character (char), midpoint x coordinate (int), midpoint y coordinate (int), major axis (int), minor axis (int)

--> Method - insertSolidXEllipse() - Inserts a solid ellipse whose midpoint is the specified position and draws the ellipse using the specified major and minor axis, when drawing the ellipse it makes sure that every x coordinate from the one end of the major axis to the other end of the major axis has been filled; arguments - paint character (char), midpoint x coordinate (int), midpoint y coordinate (int), major axis (int), minor axis (int)

--> Method - insertYEllipse() - Inserts an ellipse whose midpoint is the specified position and draws the ellipse using the specified major and minor axis, when drawing the ellipse it makes sure that every x coordinate from the one end of the minor axis to the other end of the minor axis has been filled; arguments - paint character (char), midpoint x coordinate (int), midpoint y coordinate (int), major axis (int), minor axis (int)

--> Method - insertSolidYEllipse() - Inserts a solid ellipse whose midpoint is the specified position and draws the ellipse using the specified major and minor axis, when drawing the ellipse it makes sure that every x coordinate from the one end of the minor axis to the other end of the minor axis has been filled; arguments - paint character (char), midpoint x coordinate (int), midpoint y coordinate (int), major axis (int), minor axis (int)

---------------------------------------------------------------------------------

-> Class - Screen - Provides methods to interact with the command prompt.

--> Method - (Marked for Removal) (Deprecated) StartConsole() - Useful when the application needs to be packaged into a jar file. Jar files do not start the command prompt when clicked, so to start the command prompt the StartConsole can be called from inside the jar file to run a Java program on the command prompt, the java program running on the newly started command prompt will have all the function calls to draw the graphics while the java file which used StartConsole function call sole purpose is to start the command prompt; arguments - Path to the java file which has all the function calls required to draw the graphics (Path object)

--> Method - display() - Displays the canvas onto the command prompt; arguments - Canvas object

--> Method - preRefresh() - Clears the command prompt and then displays the canvas onto it; arguments - Canvas object

--> Method - postRefresh() - Displays the canvas onto the command prompt then clears it; arguments - Canvas object

--> Method - clearConsole() - Clears the command prompt.
