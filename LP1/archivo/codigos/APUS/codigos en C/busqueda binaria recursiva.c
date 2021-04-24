#include<stdio.h>

/*busqueda binaria recursiva*/

int busquedaBR(int inferior, int superior, int clave)
{
    int central;
    if(inferior>superior)
    {
        return -1;
    }
    else
    {
        central=(inferior+superior)/2;
        if(a[central]==clave)
            return central;
        else if(a[central]<clave)
            return busquedaBR(central+1,superior,clave);
        else
            return busquedaBR(inferior,central-1,clave);
    }
}
