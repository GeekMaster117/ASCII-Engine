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
