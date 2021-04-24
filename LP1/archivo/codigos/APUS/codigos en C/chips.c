#include<stdio.h>

int main()
{
	int n, m, morsas, chips;
	scanf("%d%d", &n, &m);
	chips=m;
	morsas=0;

	while(chips>0 && chips>=morsas)
	{
		morsas++;
		if(chips>=morsas)
		{
			chips-=morsas;
		}
		if(morsas==n)
		{
			morsas=0;
		}
	}
	printf("%d", chips);
	return 0;
}
