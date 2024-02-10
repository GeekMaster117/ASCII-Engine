#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

double fps = 30.00;
clock_t last_frame;

typedef struct canvas
{
	char data[100][500];
	int width,height;
}canvas;

typedef struct object
{
	int x, y;
	int width, height;
	char paint_char;
	float hor_velocity;
	float ver_velocity;
	float hor_acceleration;
	float ver_acceleration;
}object;

void initialise_ascii_engine()
{
	last_frame = clock();
}

void set_canvas_dimensions(canvas *ptr,int width,int height)
{
	if(width < 0 || width > 500 || height < 0 || height > 100)return;
	ptr -> width = width;
	ptr -> height = height;
}

void fill_canvas(canvas *ptr,char paint_char)
{
	int i,j,canvas_width = ptr -> width,canvas_height = ptr -> height;
	for(i = 0;i < canvas_height;i++)for(j = 0;j < canvas_width;j++)ptr -> data[i][j] = paint_char;
}

void clear_canvas(canvas *ptr)
{
	fill_canvas(ptr, ' ');
}

void initialise_canvas(canvas *ptr)
{
	ptr -> width = 100;
	ptr -> height = 25;
	clear_canvas(ptr);
}

void initialise_canvases(canvas *ptr[], int length)
{
	int i;
	for(i = 0;i < length;i++)
	{
		ptr[i] -> width = 100;
		ptr[i] -> height = 25;
		clear_canvas(ptr[i]);
	}
}

void insert_char(canvas *ptr, char paint_char,int x,int y)
{
	if(x < 0 || x >= 500 || y < 0 || y >= 100)return;
	int canvas_width = ptr -> width,canvas_height = ptr -> height;
	if(x >= canvas_width || y >= canvas_height)return;
	ptr -> data[y][x] = paint_char;
}

void insert_x_line(canvas *ptr, char paint_char,int x1,int y1,int x2,int y2)
{
	if((x1 < 0 && x2 < 0) || (y1 < 0 && y2 < 0) || (x1 >= 500 && x2 >= 500) || (y1 >= 100 && y2 >= 100))return;
	int canvas_width = ptr -> width,canvas_height = ptr -> height;
	if((x1 >= canvas_width && x2 >= canvas_width) || (y1 >= canvas_height && y2 >= canvas_height))return;
	if(x1 < 0)x1 = 0;
	if(x2 < 0)x2 = 0;
	if(y1 < 0)y1 = 0;
	if(y2 < 0)y2 = 0;
	if(x1 >= canvas_width)x1 = canvas_width - 1;
	if(x2 >= canvas_width)x2 = canvas_width - 1;
	if(y1 >= canvas_height)y1 = canvas_height - 1;
	if(y2 >= canvas_height)y2 = canvas_height - 1;
	if(x1 == x2 && y1 == y2)
	{
		ptr -> data[y1][x1] = paint_char;
	}
	else if(x1 == x2)
	{
		int i;
		if(y1 < y2)for(i = y1;i <= y2;i++)ptr -> data[i][x1] = paint_char;
		else for(i = y2;i <= y1;i++)ptr -> data[i][x1] = paint_char;
	}
	else if(y1 == y2)
	{
		int i;
		if(x1 < x2)for(i = x1;i <= x2;i++)ptr -> data[y1][i] = paint_char;
		else for(i = x2;i <= x1;i++)ptr -> data[y1][i] = paint_char;
	}
	else
	{
		int i;
		float slope = (float)(y1 - y2)/(x1 - x2),y_point;
		ptr -> data[y1][x1] = paint_char;
		ptr -> data[y2][x2] = paint_char;
		if(x1 < x2)for(i = x1 + 1;i < x2;i++)
		{
			y_point = (float)(slope * (i - x1)) + y1;
			if(y_point - floor(y_point) < 0.5)y_point = floor(y_point);
			else y_point = ceil(y_point);
			ptr -> data[(int)y_point][i] = paint_char;
		}
		else for(i = x2 + 1;i < x1;i++)
		{
			y_point = (float)(slope * (i - x1)) + y1;
			if(y_point - floor(y_point) < 0.5)y_point = floor(y_point);
			else y_point = ceil(y_point);
			ptr -> data[(int)y_point][i] = paint_char;
		}
	}
}

void insert_y_line(canvas *ptr, char paint_char,int x1,int y1,int x2,int y2)
{
	if((x1 < 0 && x2 < 0) || (y1 < 0 && y2 < 0) || (x1 >= 500 && x2 >= 500) || (y1 >= 100 && y2 >= 100))return;
	int canvas_width = ptr -> width,canvas_height = ptr -> height;
	if((x1 >= canvas_width && x2 >= canvas_width) || (y1 >= canvas_height && y2 >= canvas_height))return;
	if(x1 < 0)x1 = 0;
	if(x2 < 0)x2 = 0;
	if(y1 < 0)y1 = 0;
	if(y2 < 0)y2 = 0;
	if(x1 >= canvas_width)x1 = canvas_width - 1;
	if(x2 >= canvas_width)x2 = canvas_width - 1;
	if(y1 >= canvas_height)y1 = canvas_height - 1;
	if(y2 >= canvas_height)y2 = canvas_height - 1;
	if(x1 == x2 && y1 == y2)
	{
		ptr -> data[y1][x1] = paint_char;
	}
	else if(x1 == x2)
	{
		int i;
		if(y1 < y2)for(i = y1;i <= y2;i++)ptr -> data[i][x1] = paint_char;
		else for(i = y2;i <= y1;i++)ptr -> data[i][x1] = paint_char;
	}
	else if(y1 == y2)
	{
		int i;
		if(x1 < x2)for(i = x1;i <= x2;i++)ptr -> data[y1][i] = paint_char;
		else for(i = x2;i <= x1;i++)ptr -> data[y1][i] = paint_char;
	}
	else
	{
		int i;
		float slope = (float)(y1 - y2)/(x1 - x2),x_point;
		ptr -> data[y1][x1] = paint_char;
		ptr -> data[y2][x2] = paint_char;
		if(y1 < y2)for(i = y1 + 1;i < y2;i++)
		{
			x_point = (float)(((i - y1)/slope) + x1);
			if(x_point - floor(x_point) < 0.5)x_point = floor(x_point);
			else x_point = ceil(x_point);
			ptr -> data[i][(int)x_point] = paint_char;
		}
		else for(i = y2 + 1;i < y1;i++)
		{
			x_point = (float)(((i - y1)/slope) + x1);
			if(x_point - floor(x_point) < 0.5)x_point = floor(x_point);
			else x_point = ceil(x_point);
			ptr -> data[i][(int)x_point] = paint_char;
		}
	}
}

void insert_rect(canvas *ptr,char paint_char,int x,int y,int width,int height)
{
	if(width <= 0 || height <= 0)return;
	width--;
	height--;
	insert_y_line(ptr,paint_char,x,y,x + width,y);
	insert_y_line(ptr,paint_char,x,y,x,y + height);
	insert_y_line(ptr,paint_char,x,y + height,x + width,y + height);
	insert_y_line(ptr,paint_char,x + width,y,x + width,y + height);
}

void insert_solid_rect(canvas *ptr,char paint_char,int x,int y,int width,int height)
{
	int i;
	if(width <= 0 || height <= 0)return;
	width--;
	height--;
	for(i = x;i <= x + width;i++)insert_y_line(ptr,paint_char,i,y,i,y + height);
}

void insert_x_ellipse(canvas *ptr,char paint_char,int centre_x,int centre_y,int major_axis,int minor_axis)
{
	if(major_axis <= 0 || minor_axis <= 0)return;
	int i,canvas_width = ptr -> width,canvas_height = ptr -> height,left_x_point = (int)centre_x - major_axis,right_x_point = (int)centre_x + major_axis;
	float y1,y2,equ;
	for(i = left_x_point;i <= right_x_point;i++)
	{
		equ = (float)sqrt((1 - (pow(i - centre_x,2)/pow(major_axis,2))) * pow(minor_axis,2));
		y1 = (float)centre_y + equ;
		y2 = (float)centre_y - equ;
		if(y1 - floor(y1) < 0.5)y1 = floor(y1);
		else y1 = ceil(y1);
		if(y2 - floor(y2) < 0.5)y2 = floor(y2);
		else y2 = ceil(y2);
		if(i > 0 && i < canvas_width)
		{
			if(y1 > 0 && y1 < canvas_height)ptr -> data[(int)y1][i] = paint_char;
			if(y2 > 0 && y2 < canvas_height)ptr -> data[(int)y2][i] = paint_char;
		}
	}
}

void insert_solid_x_ellipse(canvas *ptr,char paint_char,int centre_x,int centre_y,int major_axis,int minor_axis)
{
	if(major_axis <= 0 || minor_axis <= 0)return;
	int i,j,canvas_width = ptr -> width,canvas_height = ptr -> height,left_x_point = (int)centre_x - major_axis,right_x_point = (int)centre_x + major_axis;
	float y1,y2,equ;
	for(i = left_x_point;i <= right_x_point;i++)
	{
		equ = (float)sqrt((1 - (pow(i - centre_x,2)/pow(major_axis,2))) * pow(minor_axis,2));
		y1 = (float)centre_y + equ;
		y2 = (float)centre_y - equ;
		if(y1 - floor(y1) < 0.5)y1 = floor(y1);
		else y1 = ceil(y1);
		if(y2 - floor(y2) < 0.5)y2 = floor(y2);
		else y2 = ceil(y2);
		if(i > 0 && i < canvas_width)
		{
			for(j = (int)y2;j <= (int)y1;j++)if(j > 0 && j < canvas_height)ptr -> data[j][i] = paint_char;
		}
	}
}

void insert_y_ellipse(canvas *ptr,char paint_char,int centre_x,int centre_y,int major_axis,int minor_axis)
{
	if(major_axis <= 0 || minor_axis <= 0)return;
	int i,canvas_width = ptr -> width,canvas_height = ptr -> height,upper_y_point = (int)centre_y - minor_axis,lower_y_point = (int)centre_y + minor_axis;
	float x1,x2,equ;
	for(i = upper_y_point;i <= lower_y_point;i++)
	{
		equ = (float)sqrt((1 - (pow(i - centre_y,2)/pow(minor_axis,2))) * pow(major_axis,2));
		x1 = (float)centre_x + equ;
		x2 = (float)centre_x - equ;
		if(x1 - floor(x1) < 0.5)x1 = floor(x1);
		else x1 = ceil(x1);
		if(x2 - floor(x2) < 0.5)x2 = floor(x2);
		else x2 = ceil(x2);
		if(i > 0 && i < canvas_height)
		{
			if(x1 > 0 && x1 < canvas_width)ptr -> data[i][(int)x1] = paint_char;
			if(x2 > 0 && x2 < canvas_width)ptr -> data[i][(int)x2] = paint_char;
		}
	}
}

void insert_solid_y_ellipse(canvas *ptr,char paint_char,int centre_x,int centre_y,int major_axis,int minor_axis)
{
	if(major_axis <= 0 || minor_axis <= 0)return;
	int i,j,canvas_width = ptr -> width,canvas_height = ptr -> height,upper_y_point = (int)centre_y - minor_axis,lower_y_point = (int)centre_y + minor_axis;
	float x1,x2,equ;
	for(i = upper_y_point;i <= lower_y_point;i++)
	{
		equ = (float)sqrt((1 - (pow(i - centre_y,2)/pow(minor_axis,2))) * pow(major_axis,2));
		x1 = (float)centre_x + equ;
		x2 = (float)centre_x - equ;
		if(x1 - floor(x1) < 0.5)x1 = floor(x1);
		else x1 = ceil(x1);
		if(x2 - floor(x2) < 0.5)x2 = floor(x2);
		else x2 = ceil(x2);
		if(i > 0 && i < canvas_height)
		{
			for(j = (int)x2;j <= (int)x1;j++)if(j > 0 && j < canvas_width)ptr -> data[i][j] = paint_char;
		}
	}
}

void clear_display()
{
	system("cls");
}

void initialise_object(object *optr)
{
	optr -> x = 0;
	optr -> y = 0;
	optr -> width = 1;
	optr -> height = 1;
	optr -> paint_char = '*';
	optr -> hor_velocity = 0.00;
	optr -> ver_velocity = 0.00;
	optr -> hor_acceleration = 0.00;
	optr -> ver_acceleration = 0.00;
}

void initialise_objects(object *optr[], int length)
{
	int i;
	for(i = 0;i < length;i++)
	{
		optr[i] -> x = 0;
		optr[i] -> y = 0;
		optr[i] -> width = 1;
		optr[i] -> height = 1;
		optr[i] -> paint_char = '*';
		optr[i] -> hor_velocity = 0.00;
		optr[i] -> ver_velocity = 0.00;
		optr[i] -> hor_acceleration = 0.00;
		optr[i] -> ver_acceleration = 0.00;
	}
}

void set_object_coords(object *optr, int x, int y)
{
	optr -> x = x;
	optr -> y = y;
}

void set_object_dimensions(object *optr, int width, int height)
{
	if(width <= 0 || height <= 0)return;
	optr -> width = width;
	optr -> height = height;
}

void paint_object(object *optr, char paint_char)
{
	optr -> paint_char = paint_char;
}

void set_hor_velocity(object *optr, float hor_velocity)
{
	optr -> hor_velocity = hor_velocity;
}

void set_ver_velocity(object *optr, float ver_velocity)
{
	optr -> ver_velocity = ver_velocity;
}

void set_velocity(object *optr, float hor_velocity, float ver_velocity)
{
	optr -> hor_velocity = hor_velocity;
	optr -> ver_velocity = ver_velocity;
}

void set_hor_acceleration(object *optr, float hor_acceleretion)
{
	optr -> hor_acceleration = hor_acceleretion;
}

void set_ver_acceleration(object *optr, float ver_acceleration)
{
	optr -> ver_acceleration = ver_acceleration;
}

void set_acceleration(object *optr, float hor_acceleretion, float ver_acceleration)
{
	optr -> hor_acceleration = hor_acceleretion;
	optr -> ver_acceleration = ver_acceleration;
}

int detect_collision_canvas(object *optr, canvas *cptr)
{
	int i, x = optr -> x, y = optr -> y, width = optr -> width, height = optr -> height, canvas_width = cptr -> width, canvas_height = cptr -> height;
	width--;
	height--;
	if(x >= canvas_width)return 0;
	if(x + width < 0)return 0;
	if(y >= canvas_height)return 0;
	if(y + height < 0)return 0;
	if(x < 0)x = 0;
	if(y < 0)y = 0;
	if(x + width >= canvas_width)width = canvas_width - 1 - x;
	if(y + height >= canvas_height)height = canvas_height - 1 - y;
	if(x != 0)for(i = y;i <= y + height;i++)if(cptr -> data[i][x - 1] != ' ')return 1;
	if(x + width != canvas_width - 1)for(i = y;i <= y + height;i++)if(cptr -> data[i][x + width + 1] != ' ')return 1;
	if(y != 0)for(i = x;i <= x + width;i++)if(cptr -> data[y - 1][i] != ' ')return 1;
	if(y + height != canvas_height - 1)for(i = x;i <= x + width;i++)if(cptr -> data[y + height + 1][i] != ' ')return 1;
	return 0;
}

int detect_collision_between(object *ptr1, object *ptr2)
{
	int x1 = ptr1 -> x, y1 = ptr1 -> y, width1 = ptr1 -> width, height1 = ptr1 -> height, x2 = ptr2 -> x, y2 = ptr2 -> y, width2 = ptr2 -> width, height2 = ptr2 -> height;
	width1--;
	height1--;
	width2--;
	height2--;
	if(x2 - 1 > x1 + width1)
		return 0;
	if(x2 + width2 + 1 < x1)
		return 0;
	if(y2 - 1 > y1 + height1)
		return 0;
	if(y2 + height2 + 1 < y1)
		return 0;
	return 1;
}

int detect_collision_between_multiple(object *ptr, object *arr[], int length)
{
	int i;
	for(i = 0;i < length;i++)if(detect_collision_between(ptr, arr[i]))return 1;
	return 0;
}

int detect_clipping_between(object *ptr1, object *ptr2)
{
	int x1 = ptr1 -> x, y1 = ptr1 -> y, width1 = ptr1 -> width, height1 = ptr1 -> height, x2 = ptr2 -> x, y2 = ptr2 -> y, width2 = ptr2 -> width, height2 = ptr2 -> height;
	width1--;
	height1--;
	width2--;
	height2--;
	if(x2 > x1 + width1)
		return 0;
	if(x2 + width2 < x1)
		return 0;
	if(y2 > y1 + height1)
		return 0;
	if(y2 + height2 < y1)
		return 0;
	return 1;
}

int detect_clipping_between_multiple(object *ptr, object *arr[], int length)
{
	int i;
	for(i = 0;i < length;i++)if(detect_clipping_between(ptr, arr[i]))return 1;
	return 0;
}

void undo_clipping(object *ptr1, object *ptr2, int object_to_be_moved, int direction, int maximum_tries, int reset_if_failed)
{
	object *objects[2] = {ptr1, ptr2};
	int i = 0, x = objects[object_to_be_moved] -> x, y = objects[object_to_be_moved] -> y;
	for(;detect_clipping_between(ptr1, ptr2) && i < maximum_tries;i++)
	{
		switch(direction)
		{
			case 0: objects[object_to_be_moved] -> y --;
					break;
			case 1: objects[object_to_be_moved] -> x --;
					objects[object_to_be_moved] -> y --;
					break;
			case 2: objects[object_to_be_moved] -> x --;
					break;
			case 3: objects[object_to_be_moved] -> x --;
					objects[object_to_be_moved] -> y ++;
					break;
			case 4: objects[object_to_be_moved] -> y ++;
					break;
			case 5: objects[object_to_be_moved] -> x ++;
					objects[object_to_be_moved] -> y ++;
					break;
			case 6: objects[object_to_be_moved] -> x ++;
					break;
			case 7: objects[object_to_be_moved] -> x ++;
					objects[object_to_be_moved] -> y --;
		}
	}
	if(reset_if_failed == 1 && detect_clipping_between(ptr1, ptr2))
	{
		objects[object_to_be_moved] -> x = x;
		objects[object_to_be_moved] -> y = y;
	}
}

void simulate_object(object *optr)
{
	optr -> hor_velocity += optr -> hor_acceleration;
	optr -> ver_velocity += optr -> ver_acceleration;
	optr -> x += (int)floor(optr -> hor_velocity);
	optr -> y += (int)floor(optr -> ver_velocity);
}

void simulate_objects(object *optr[], int length)
{
	int i;
	for(i = 0;i < length;i++)
	{
		optr[i] -> hor_velocity += optr[0] -> hor_acceleration;
		optr[i] -> ver_velocity += optr[i] -> ver_acceleration;
		optr[i] -> x += (int)floor(optr[i] -> hor_velocity);
		optr[i] -> y += (int)floor(optr[i] -> ver_velocity);
	}
}

void display_object(object *optr, canvas *cptr)
{
	insert_solid_rect(cptr, optr -> paint_char, optr -> x, optr -> y, optr -> width, optr -> height);
}

void display_objects(object *optr[], int length, canvas *cptr)
{
	int i;
	for(i = 0;i < length;i++)insert_solid_rect(cptr, optr[i] -> paint_char, optr[i] -> x, optr[i] -> y, optr[i] -> width, optr[i] -> height);
}

void display_object_border(object *optr, canvas *cptr)
{
	insert_rect(cptr, optr -> paint_char, optr -> x, optr -> y, optr -> width, optr -> height);
}

void display_objects_border(object *optr[], int length, canvas *cptr)
{
	int i;
	for(i = 0;i < length;i++)insert_rect(cptr, optr[i] -> paint_char, optr[i] -> x, optr[i] -> y, optr[i] -> width, optr[i] -> height);
}

void get_input(char *c)
{
	if(kbhit())*c = getch();
}

void set_refresh_rate(double refresh_rate)
{
	if(refresh_rate < 1.00)return;
	fps = refresh_rate;
}

void display(canvas *ptr)
{
	int i,canvas_width = ptr -> width,canvas_height = ptr -> height;
	for(i = 0;i < canvas_height;i++)printf("%s\n",ptr -> data[i]);
}

void timed_display(canvas *ptr)
{
	float frametime = (float)1/fps;
	if((double)(clock()/CLOCKS_PER_SEC) < (double)(last_frame/CLOCKS_PER_SEC) + frametime)return;
	last_frame = clock();
	int i,canvas_width = ptr -> width,canvas_height = ptr -> height;
	for(i = 0;i < canvas_height;i++)printf("%s\n",ptr -> data[i]);
}

void refresh(canvas *ptr)
{
	system("cls");
	int i,canvas_width = ptr -> width,canvas_height = ptr -> height;
	for(i = 0;i < canvas_height;i++)printf("%s\n",ptr -> data[i]);
}

void timed_refresh(canvas *ptr)
{
	float frametime = (float)1/fps;
	if(((double)clock())/CLOCKS_PER_SEC < ((double)last_frame)/CLOCKS_PER_SEC + frametime)
	{
		return;
	}
	last_frame = clock();
	system("cls");
	int i,canvas_width = ptr -> width,canvas_height = ptr -> height;
	for(i = 0;i < canvas_height;i++)printf("%s\n",ptr -> data[i]);
}
