#include<iostream>

class intHandle {
public:
explicit intHandle(int *anInt)
: i_(anInt) { }  // acquire resource
~intHandle()
{ delete i_; } // release resource
intHandle &operator =(const int i)  {
*i_ = i;
return *this;
};
int *get()
{ return i_; } // access resource
private:
intHandle(IntHandle&) = delete;
void operator=(intHandle&) = delete;
int *i_;
};

void f(void) {
intHandle ih( new int );
ih = 5;
 /* ... */
}