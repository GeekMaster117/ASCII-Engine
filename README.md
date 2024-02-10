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

--------------------------------------------------------------
HOW TO USE ASCII Engine.h:-

-> canvas - a typedef structure that can be used to draw graphics.

-> object - a typedef structure that can work with physics and interact with other objects.

-> initialise_ascii_engine() - initialises the engine, may work without calling this function if not using any refresh functions.

-> set_canvas_dimensions() - sets the dimensions for the canvas; arguments - canvas pointer, width (int), height (int)

-> fill_canvas() - fills the entire canvas with a character; arguments - canvas pointer, paint character (char)

-> clear_canvas() - clears the entire canvas by replacing all the characters with ' '; arguments - canvas pointer

-> initialise_canvas() - initialises the canvas by setting the width and height to 100 and 25 and clears the canvas, the canvas can still be used without initialising; arguments - canvas pointer

-> initialise_canvases() - works the same as initialise_canvas() but initialises multiple canvases at once; arguments - canvas pointer array, length of the array (int)

-> insert_char() - inserts a single character in the specified position on the canvas; arguments - canvas pointer, paint character (char), x coordinate (int), y coordinate (int)

-> insert_x_line() - inserts a line from one position to another position on the canvas, when drawing the line it makes sure that every x coordinate from the start position to the end position has been filled; arguments - canvas pointer, paint character (char), start x coordinate (int), start y coordinate (int), end x coordinate (int), end y coordinate (int)

-> insert_y_line() - inserts a line from one position to another position on the canvas, when drawing the line it makes sure that every y coordinate from the start position to the end position has been filled; arguments - canvas pointer, paint character (char), start x coordinate (int), start y coordinate (int), end x coordinate (int), end y coordinate (int)

-> insert_rect() - inserts a rectangle from a specified position to the other opposite corner whose position is calculated using specified width and height; arguments - canvas pointer, paint character (char), x coordinate (int), y coordinate (int), width (int), height (int)

-> insert_solid_rect() - inserts a solid rectangle from a specified position to the other opposite corner whose position is calculated using specified width and height; arguments - canvas pointer, paint character (char), x coordinate (int), y coordinate (int), width (int), height (int)

-> insert_x_ellipse() - inserts an ellipse whose midpoint is the specified position and draws the ellipse using the specified major and minor axis, when drawing the ellipse it makes sure that every x coordinate from the one end of the major axis to the other end of the major axis has been filled; arguments - canvas pointer, paint character (char), midpoint x coordinate (int), midpoint y coordinate (int), major axis (int), minor axis (int)

-> insert_solid_x_ellipse() - inserts a solid ellipse whose midpoint is the specified position and draws the ellipse using the specified major and minor axis, when drawing the ellipse it makes sure that every x coordinate from the one end of the major axis to the other end of the major axis has been filled; arguments - canvas pointer, paint character (char), midpoint x coordinate (int), midpoint y coordinate (int), major axis (int), minor axis (int)

-> insert_y_ellipse() - inserts an ellipse whose midpoint is the specified position and draws the ellipse using the specified major and minor axis, when drawing the ellipse it makes sure that every x coordinate from the one end of the minor axis to the other end of the minor axis has been filled; arguments - canvas pointer, paint character (char), midpoint x coordinate (int), midpoint y coordinate (int), major axis (int), minor axis (int)

-> insert_solid_y_ellipse() - inserts a solid ellipse whose midpoint is the specified position and draws the ellipse using the specified major and minor axis, when drawing the ellipse it makes sure that every x coordinate from the one end of the minor axis to the other end of the minor axis has been filled; arguments - canvas pointer, paint character (char), midpoint x coordinate (int), midpoint y coordinate (int), major axis (int), minor axis (int)

-> clear_display() - clears the command prompt

-> initialise_object() - initialises the object by setting its x and y position to 0, width and height to 1, paint character to '*', velocity and acceleration to 0; arguments - object pointer

-> initialise_objects() -  works the same as initialise_object() but initialises multiple canvases at once; arguments - object pointer array, length of the array (int)

-> set_object_coords() - sets the position for the object; arguments - object pointer, x coordinate (int), y coordinate (int)

-> set_object_dimensions() - sets the dimensions for the object; arguments - object pointer, width (int), height (int)

-> paint_object() - paints the object with a character; arguments - object pointer, paint character (char)

-> set_hor_velocity() - sets the horizontal velocity for the object; arguments - object pointer, horizontal velocity (float)

-> set_ver_velocity() - sets the vertical velocity for the object; arguments - object pointer, vertical velocity (float)

-> set_velocity() - sets the velocity for the object; arguments - object pointer, horizontal velocity (float), vertical velocity (float)

-> set_hor_acceleration() - sets horizontal acceleration for the object; arguments - object pointer, horizontal acceleration (float)

-> set_ver_acceleration() - sets vertical acceleration for the object; arguments - object pointer, vertical acceleration (float)

-> set_acceleration() - sets acceleration for the object; arguments - object pointer, horizontal acceleration (float), vertical acceleration (float)

-> detect_collision_canvas() - checks whether an object is colliding with any character on the canvas other than ' '; arguments - object pointer, canvas pointer; return type - int

-> detect_collision_between() - checks whether two objects are colliding with each other; arguments - object pointer 1, object pointer 2; return type - int

-> detect_collision_between_multiple() - checks whether an object is colliding with any of the other objects specified; arguments - object pointer, object pointer array, length of the array (int); return type - int

-> detect_clipping_between() - checks whether two objects are clipping with each other; arguments - object pointer 1, object pointer 2; return type - int

-> detect_clipping_between_multiple() - checks whether an object is clipping with any of the other objects specified; arguments - object pointer, object pointer array, length of the array (int); return type - int

-> undo_clipping() - tries to pull objects away from each other to avoid clipping; arguments - object pointer 1, object pointer 2, which object has to be pulled 0 for first object 1 for the second object (int), the direction in which object has to be pulled 0 for north 1 for north-east 2 for east 3 for south-east 4 for south 5 for south-west 6 for west 7 for north-west (int), maximum number of tries the object has to be pulled to avoid clipping before giving up (int), resets both the objects if undoing clipping has failed 0 if resetting should be done 1 if resetting should not be done.

-> simulate_object() - simulates the object's interaction with physics by moving the object using its inherent velocity and acceleration; arguments - object pointer

-> simulate_objects() - works the same as simulate_object() but simulates multiple objects at once; arguments - object pointer array, length of the array (int)

-> display_object() - puts the object on the canvas; arguments - object pointer, canvas pointer

-> diaply_objects() - works the same as display_object() but puts multiple objects at once on the canvas; arguments - object pointer array, length of the array (int), canvas pointer

-> display_object_border() - works the same as display_object() but only puts the border of the object on the canvas; arguments - object pointer, canvas pointer

-> display_objects_border() - works the same as displat_objects() but only puts the border of the objects on the canvas; arguments - object pointer array, length of the array (int), canvas pointer

-> get_input() - takes input if the user gives it when the function is called; arguments - character pointer

-> set_refresh_rate() - sets the refresh rate of the command prompt; arguments - refresh rate (double)

-> display() - displays the canvas onto the command prompt; arguments - canvas pointer

-> timed_display() - works the same as display but does not execute if the time between when the last frame was displayed and the current time is less than 1 / refresh rate; arguments - canvas pointer

-> refresh() - works the same as display() but clears the command prompt before displaying the canvas; arguments - canvas pointer

-> timed_refresh() - works the same as timed_display() but clears the command prompt before displaying the canvas; arguments - canvas pointer
