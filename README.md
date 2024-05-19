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

---------------------------------------------------------------------------------

--> Class - Entity - Provides methods to create objects that can interact with physics.

--> Constructor - Initialises the object by setting its x and y position to 0, width and height to 1, paint character to '*', velocity and acceleration to 0

--> Method - setDimensions() -  Sets the dimensions for the entity; width (int), height (int)

--> Method - getWidth() - Returns entity width; return type - boolean

--> Method - getHeight() - Returns entity height; return type - boolean

--> Method - setPosition() - Sets the position for the entity; arguments - x coordinate (int), y coordinate (int)

--> Method - getXPosition() - Returns entity x position; return type - int

--> Method - getYPosition() - Returns entity y position; return type - int

--> Method - setPaintChar() - Paints the entity with a character; arguments - paint character (char)

--> Method - getPaintChar() - Returns paint character of entity; return type - char

--> Method - setHorVelocity() - Sets the horizontal velocity for the entity; arguments - horizontal velocity (float)

--> Method - getHorVelocity() - Returns horizontal velocity for the entity; return type - double

--> Method - setVerVelocity() - Sets the vertical velocity for the entity; arguments - vertical velocity (float)

--> Method - getVerVelocity() - Returns vertical velocity for the entity; return type - double

--> Method - setVelocity() - Sets the velocity for the entity; arguments - horizontal velocity (float), vertical velocity (float)

--> Method - setHorAcceleration() - Sets horizontal acceleration for the entity; arguments - horizontal acceleration (float)

--> Method - getHorAcceleration() - Returns the horizontal acceleration for the entity; return type - double

--> Method - setVerAcceleration() - Sets vertical acceleration for the entity; arguments - vertical acceleration (float)

--> Method - getVerAcceleration() - Returns the vertical acceleration for the entity; return type - double

--> Method - setAcceleration() - Sets acceleration for the entity; arguments - horizontal acceleration (float), vertical acceleration (float)

--> Method - detectCanvasCollision() - Checks whether an entity is colliding with any character on the canvas other than ' '; arguments - Canvas object; return type - boolean

--> Method - detectCollision() - Checks whether the entity is colliding with another entity passed as an argument; arguments - Entity object; return type - boolean

--> Method - detectCollisionMultiple() - Checks whether the entity is colliding with any of the other entities specified; arguments - Entity array; return type - boolean

--> Method - detectCollisionUp() - Checks whether the entity is colliding with another entity passed as an argument on top; arguments - Entity object; return type - boolean

--> Method - detectCollisionDown() - Checks whether the entity is colliding with another entity passed as an argument below; arguments - Entity object; return type - boolean

--> Method - detectCollisionLeft() - Checks whether the entity is colliding with another entity passed as an argument on left; arguments - Entity object; return type - boolean

--> Method - detectCollisionRight() - Checks whether the entity is colliding with another entity passed as an argument on right; arguments - Entity object; return type - boolean

--> Method - detectClipping() - Checks whether the entity is clipping with another entity passed as an argument; arguments - Entity object; return type - boolean

--> Method - detectClippingMultiple() - Checks whether the entity is clipping with any of the other entities specified; arguments - Entity array; return type - boolean

--> Method - undoClipping() - Tries to pull the entity away from the other entity to avoid clipping; arguments - maximum number of tries the object has to be pulled to avoid clipping before giving up (int), reset if failed (boolean)

--> Method - simulateEntity() - Simulates the entity's interaction with physics by moving the object using its inherent velocity and acceleration

--> Method - simulateEntities() - Works the same as simulateEntity() but simulates multiple objects at once; arguments - Entity array

--> Method - displayEntity() - Puts the entity on the canvas; arguments - Canvas object

--> Method - displayEntities() - Works the same as displayEntity() but puts multiple objects at once on the canvas; arguments - Entity array, Canvas object

--> Method - displayEntityBorder() - Works the same as displayEntity() but only displays border; arguments - Canvas object

--> Method - displayEntitiesBorder() - Works the same as displayEntities() but only displays border; arguments - Canvas object

-------------------------------------------------------------------------------------
