#include<iostream>
#include<climits>

using namespace std;

int main(){

  signed int si = INT_MAX;
  cout<<si<<endl;
  unsigned int ui;
  if (si < 0) {
        cout<<"Value is less than Zero "<<endl;
  }
  else {
   ui = (unsigned int)si;

   cout<<"unsigned int value: "<<ui<<endl;

 }
}