#include <stdio.h>
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
	int x1, x2, y1, y2;
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
	int i,j,canvas_width = ptr -> width,canvas_height = ptr -> height;
	for(i = 0;i < canvas_height;i++)for(j = 0;j < canvas_width;j++)ptr -> data[i][j] = ' ';
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

void insert_rect(canvas *ptr,char paint_char,int x1,int y1,int x2,int y2)
{
	insert_y_line(ptr,paint_char,x1,y1,x2,y1);
	insert_y_line(ptr,paint_char,x1,y1,x1,y2);
	insert_y_line(ptr,paint_char,x1,y2,x2,y2);
	insert_y_line(ptr,paint_char,x2,y1,x2,y2);
}

void insert_solid_rect(canvas *ptr,char paint_char,int x1,int y1,int x2,int y2)
{
	int i;
	if(x1 < x2)for(i = x1;i <= x2;i++)insert_y_line(ptr,paint_char,i,y1,i,y2);
	else for(i = x2;i <= x1;i++)insert_y_line(ptr,paint_char,i,y1,i,y2);
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

void set_object_coords(object *ptr, int x1, int y1, int x2, int y2)
{
	ptr -> x1 = x1;
	ptr -> x2 = x2;
	ptr -> y1 = y1;
	ptr -> y2 = y2;
}

int detect_collision(object *optr, canvas *cptr)
{
	int i, x1 = optr -> x1, y1 = optr -> y1, x2 = optr -> x2, y2 = optr -> y2, canvas_width = cptr -> width, canvas_height = cptr -> height;
	if(x1 > canvas_width && x2 > canvas_width)return 0;
	if(x1 < 0 && x2 < 0)return 0;
	if(y1 > canvas_height && y2 > canvas_height)return 0;
	if(y1 < 0 && y2 < 0)return 0;
	if(x1 < 0)x1 = 0;
	if(x2 < 0)x2 = 0;
	if(y1 < 0)y1 = 0;
	if(y2 < 0)y2 = 0;
	if(x1 >= canvas_width)x1 = canvas_width - 1;
	if(x2 >= canvas_width)x2 = canvas_width - 1;
	if(y1 >= canvas_height)y1 = canvas_height - 1;
	if(y2 >= canvas_height)y2 = canvas_height - 1;
	if(x2 < x1)
	{
		x1 += x2;
		x2 = x1 - x2;
		x1 -= x2;
	}
	if(y2 < y1)
	{
		y1 += y2;
		y2 = y1 - y2;
		y1 -= y2;
	}
	if(y1 != 0)for(i = x1;i <= x2;i++)if(cptr -> data[y1 - 1][i] != ' ')return 1;
	if(y2 != canvas_height - 1)for(i = x1;i <= x2;i++)if(cptr -> data[y2 + 1][i] != ' ')return 1;
	if(x1 != 0)for(i = y1;i <= y2;i++)if(cptr -> data[i][x1 - 1] != ' ')return 1;
	if(y2 != canvas_width - 1)for(i = y1;i <= y2;i++)if(cptr -> data[i][x2 + 1] != ' ')return 1;
	return 0;
}

void display_object(object *optr, canvas *cptr, char paintchar)
{
	insert_solid_rect(cptr, paintchar, optr -> x1, optr -> y1, optr -> x2, optr -> y2);
}

void display_object_border(object *optr, canvas *cptr, char paintchar)
{
	insert_rect(cptr, paintchar, optr -> x1, optr -> y1, optr -> x2, optr -> y2);
}

void set_refresh_rate(float refresh_rate)
{
	fps = refresh_rate;
}

void display(canvas *ptr)
{
	int i,j,canvas_width = ptr -> width,canvas_height = ptr -> height;
	for(i = 0;i < canvas_height;i++)printf("%s\n",ptr -> data[i]);
}

void timed_display(canvas *ptr)
{
	float frametime = (float)1/fps;
	if((double)(clock()/CLOCKS_PER_SEC) < (double)(last_frame/CLOCKS_PER_SEC) + frametime)return;
	last_frame = clock();
	int i,j,canvas_width = ptr -> width,canvas_height = ptr -> height;
	for(i = 0;i < canvas_height;i++)printf("%s\n",ptr -> data[i]);
}

void refresh(canvas *ptr)
{
	system("cls");
	int i,j,canvas_width = ptr -> width,canvas_height = ptr -> height;
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
	int i,j,canvas_width = ptr -> width,canvas_height = ptr -> height;
	for(i = 0;i < canvas_height;i++)printf("%s\n",ptr -> data[i]);
}