#include <stdio.h>      
#include <stdlib.h>     
int main ()
{
  FILE * pFile;
  pFile= fopen ("myfile.txt","r");
  if (pFile == NULL)
  {
    fputs ("error opening file\n",stderr);
    abort();
  }


  fclose (pFile);
  return 0;
}
