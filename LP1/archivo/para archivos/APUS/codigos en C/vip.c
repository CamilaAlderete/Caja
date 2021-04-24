#include<stdio.h>

int main()
{
	char puerta[6];
	scanf("%s", &puerta);

	int a;
	scanf("%d", &a);
	if(puerta=='frente')
	{
		if(a==1)
		{
			printf("L");
		}else
		{
			printf("R");
		}
	}else if(puerta=='atras')
	{
		if(a==1)
		{
			printf("R");
		}else
		{
			printf("L");
		}
	}

	return 0;
}
