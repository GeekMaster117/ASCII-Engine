#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

double fps = 30.00;
clock_t last_frame;

struct canvas
{
	char data[100][500];
	int width,height;
};

struct canvas_list
{
	struct canvas list[100];
}canvas_pointer;

void initialise_ascii_engine()
{
	int i,j,k;
	for(k = 0;k < 100;k++)
	{
		canvas_pointer.list[k].width = 0;
		canvas_pointer.list[k].height = 0;
		for(i = 0;i < 100;i++)for(j = 0;j < 500;j++)canvas_pointer.list[k].data[i][j] = ' ';
	}
	last_frame = clock();
}

void set_canvas_dimensions(int index,int width,int height)
{
	if(index < 0 || index >= 100 || width < 0 || width > 500 || height < 0 || height > 100)return;
	canvas_pointer.list[index].width = width;
	canvas_pointer.list[index].height = height;
}

void fill_canvas(int index,char paint_char)
{
	if(index < 0 || index >= 100)return;
	int i,j,canvas_width = canvas_pointer.list[index].width,canvas_height = canvas_pointer.list[index].height;
	for(i = 0;i < canvas_height;i++)for(j = 0;j < canvas_width;j++)canvas_pointer.list[index].data[i][j] = paint_char;
}

void clear_canvas(int index)
{
	if(index < 0 || index >= 100)return;
	int i,j,canvas_width = canvas_pointer.list[index].width,canvas_height = canvas_pointer.list[index].height;
	for(i = 0;i < canvas_height;i++)for(j = 0;j < canvas_width;j++)canvas_pointer.list[index].data[i][j] = ' ';
}

void insert_char(int index,char paint_char,int x,int y)
{
	if(index < 0 || index >= 100 || x < 0 || x >= 500 || y < 0 || y >= 100)return;
	int canvas_width = canvas_pointer.list[index].width,canvas_height = canvas_pointer.list[index].height;
	if(x >= canvas_width || y >= canvas_height)return;
	canvas_pointer.list[index].data[y][x] = paint_char;
}

void insert_x_line(int index,char paint_char,int x1,int y1,int x2,int y2)
{
	if(index < 0 || index >= 100 || (x1 < 0 && x2 < 0) || (y1 < 0 && y2 < 0) || (x2 >= 500 && x2 >= 500) || (y1 >= 100 && y2 >= 100))return;
	int canvas_width = canvas_pointer.list[index].width,canvas_height = canvas_pointer.list[index].height;
	if((x1 >= canvas_width && x2 >= canvas_width) || (y2 >= canvas_height && y2 >= canvas_height))return;
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
		canvas_pointer.list[index].data[y1][x1] = paint_char;
	}
	else if(x1 == x2)
	{
		int i;
		if(y1 < y2)for(i = y1;i <= y2;i++)canvas_pointer.list[index].data[i][x1] = paint_char;
		else for(i = y2;i <= y1;i++)canvas_pointer.list[index].data[i][x1] = paint_char;
	}
	else if(y1 == y2)
	{
		int i;
		if(x1 < x2)for(i = x1;i <= x2;i++)canvas_pointer.list[index].data[y1][i] = paint_char;
		else for(i = x2;i <= x1;i++)canvas_pointer.list[index].data[y1][i] = paint_char;
	}
	else
	{
		int i;
		float slope = (float)(y1 - y2)/(x1 - x2),y_point;
		canvas_pointer.list[index].data[y1][x1] = paint_char;
		canvas_pointer.list[index].data[y2][x2] = paint_char;
		if(x1 < x2)for(i = x1 + 1;i < x2;i++)
		{
			y_point = (float)(slope * (i - x1)) + y1;
			if(y_point - floor(y_point) < 0.5)y_point = floor(y_point);
			else y_point = ceil(y_point);
			canvas_pointer.list[index].data[(int)y_point][i] = paint_char;
		}
		else for(i = x2 + 1;i < x1;i++)
		{
			y_point = (float)(slope * (i - x1)) + y1;
			if(y_point - floor(y_point) < 0.5)y_point = floor(y_point);
			else y_point = ceil(y_point);
			canvas_pointer.list[index].data[(int)y_point][i] = paint_char;
		}
	}
}

void insert_y_line(int index,char paint_char,int x1,int y1,int x2,int y2)
{
	if(index < 0 || index >= 100 || (x1 < 0 && x2 < 0) || (y1 < 0 && y2 < 0) || (x2 >= 500 && x2 >= 500) || (y1 >= 100 && y2 >= 100))return;
	int canvas_width = canvas_pointer.list[index].width,canvas_height = canvas_pointer.list[index].height;
	if((x1 >= canvas_width && x2 >= canvas_width) || (y2 >= canvas_height && y2 >= canvas_height))return;
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
		canvas_pointer.list[index].data[y1][x1] = paint_char;
	}
	else if(x1 == x2)
	{
		int i;
		if(y1 < y2)for(i = y1;i <= y2;i++)canvas_pointer.list[index].data[i][x1] = paint_char;
		else for(i = y2;i <= y1;i++)canvas_pointer.list[index].data[i][x1] = paint_char;
	}
	else if(y1 == y2)
	{
		int i;
		if(x1 < x2)for(i = x1;i <= x2;i++)canvas_pointer.list[index].data[y1][i] = paint_char;
		else for(i = x2;i <= x1;i++)canvas_pointer.list[index].data[y1][i] = paint_char;
	}
	else
	{
		int i;
		float slope = (float)(y1 - y2)/(x1 - x2),x_point;
		canvas_pointer.list[index].data[y1][x1] = paint_char;
		canvas_pointer.list[index].data[y2][x2] = paint_char;
		if(y1 < y2)for(i = y1 + 1;i < y2;i++)
		{
			x_point = (float)(((i - y1)/slope) + x1);
			if(x_point - floor(x_point) < 0.5)x_point = floor(x_point);
			else x_point = ceil(x_point);
			canvas_pointer.list[index].data[i][(int)x_point] = paint_char;
		}
		else for(i = y2 + 1;i < y1;i++)
		{
			x_point = (float)(((i - y1)/slope) + x1);
			if(x_point - floor(x_point) < 0.5)x_point = floor(x_point);
			else x_point = ceil(x_point);
			canvas_pointer.list[index].data[i][(int)x_point] = paint_char;
		}
	}
}

void insert_rect(int index,char paint_char,int x1,int y1,int x2,int y2)
{
	insert_y_line(index,paint_char,x1,y1,x2,y1);
	insert_y_line(index,paint_char,x1,y1,x1,y2);
	insert_y_line(index,paint_char,x1,y2,x2,y2);
	insert_y_line(index,paint_char,x2,y1,x2,y2);
}

void insert_solid_rect(int index,char paint_char,int x1,int y1,int x2,int y2)
{
	int i;
	if(x1 < x2)for(i = x1;i <= x2;i++)insert_y_line(index,paint_char,i,y1,i,y2);
	else for(i = x2;i <= x1;i++)insert_y_line(index,paint_char,i,y1,i,y2);
}

void insert_x_ellipse(int index,char paint_char,int centre_x,int centre_y,int major_axis,int minor_axis)
{
	if(index < 0 || index >= 100 || major_axis <= 0 || minor_axis <= 0)return;
	int i,canvas_width = canvas_pointer.list[index].width,canvas_height = canvas_pointer.list[index].height,left_x_point = (int)centre_x - major_axis,right_x_point = (int)centre_x + major_axis;
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
			if(y1 > 0 && y1 < canvas_height)canvas_pointer.list[index].data[(int)y1][i] = paint_char;
			if(y2 > 0 && y2 < canvas_height)canvas_pointer.list[index].data[(int)y2][i] = paint_char;
		}
	}
}

void insert_solid_x_ellipse(int index,char paint_char,int centre_x,int centre_y,int major_axis,int minor_axis)
{
	if(index < 0 || index >= 100 || major_axis <= 0 || minor_axis <= 0)return;
	int i,j,canvas_width = canvas_pointer.list[index].width,canvas_height = canvas_pointer.list[index].height,left_x_point = (int)centre_x - major_axis,right_x_point = (int)centre_x + major_axis;
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
			for(j = (int)y2;j <= (int)y1;j++)if(j > 0 && j < canvas_height)canvas_pointer.list[index].data[j][i] = paint_char;
		}
	}
}

void insert_y_ellipse(int index,char paint_char,int centre_x,int centre_y,int major_axis,int minor_axis)
{
	if(index < 0 || index >= 100 || major_axis <= 0 || minor_axis <= 0)return;
	int i,canvas_width = canvas_pointer.list[index].width,canvas_height = canvas_pointer.list[index].height,upper_y_point = (int)centre_y - minor_axis,lower_y_point = (int)centre_y + minor_axis;
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
			if(x1 > 0 && x1 < canvas_width)canvas_pointer.list[index].data[i][(int)x1] = paint_char;
			if(x2 > 0 && x2 < canvas_width)canvas_pointer.list[index].data[i][(int)x2] = paint_char;
		}
	}
}

void insert_solid_y_ellipse(int index,char paint_char,int centre_x,int centre_y,int major_axis,int minor_axis)
{
	if(index < 0 || index >= 100 || major_axis <= 0 || minor_axis <= 0)return;
	int i,j,canvas_width = canvas_pointer.list[index].width,canvas_height = canvas_pointer.list[index].height,upper_y_point = (int)centre_y - minor_axis,lower_y_point = (int)centre_y + minor_axis;
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
			for(j = (int)x2;j <= (int)x1;j++)if(j > 0 && j < canvas_width)canvas_pointer.list[index].data[i][j] = paint_char;
		}
	}
}

void clear_display()
{
	system("cls");
}

void set_refresh_rate(float refresh_rate)
{
	fps = refresh_rate;
}

void display(int index)
{
	if(index < 0 || index >= 100)return;
	int i,j,canvas_width = canvas_pointer.list[index].width,canvas_height = canvas_pointer.list[index].height;
	for(i = 0;i < canvas_height;i++)
	{
		for(j = 0;j < canvas_width;j++)printf("%c",canvas_pointer.list[index].data[i][j]);
		printf("\n");
	}
}

void timed_display(int index)
{
	if(index < 0 || index >= 100)return;
	float frametime = (float)1/fps;
	if((double)(clock()/CLOCKS_PER_SEC) < (double)(last_frame/CLOCKS_PER_SEC) + frametime)return;
	last_frame = clock();
	int i,j,canvas_width = canvas_pointer.list[index].width,canvas_height = canvas_pointer.list[index].height;
	for(i = 0;i < canvas_height;i++)
	{
		for(j = 0;j < canvas_width;j++)printf("%c",canvas_pointer.list[index].data[i][j]);
		printf("\n");
	}
}

void refresh(int index)
{
	system("cls");
	if(index < 0 || index >= 100)return;
	int i,j,canvas_width = canvas_pointer.list[index].width,canvas_height = canvas_pointer.list[index].height;
	for(i = 0;i < canvas_height;i++)
	{
		for(j = 0;j < canvas_width;j++)printf("%c",canvas_pointer.list[index].data[i][j]);
		printf("\n");
	}
}

void timed_refresh(int index)
{
	if(index < 0 || index >= 100)return;
	float frametime = (float)1/fps;
	if(((double)clock())/CLOCKS_PER_SEC < ((double)last_frame)/CLOCKS_PER_SEC + frametime)
	{
		return;
	}
	last_frame = clock();
	system("cls");
	int i,j,canvas_width = canvas_pointer.list[index].width,canvas_height = canvas_pointer.list[index].height;
	for(i = 0;i < canvas_height;i++)
	{
		for(j = 0;j < canvas_width;j++)printf("%c",canvas_pointer.list[index].data[i][j]);
		printf("\n");
	}
}