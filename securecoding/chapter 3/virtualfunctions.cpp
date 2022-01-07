#include<iostream>
using namespace std;

class a {
public:
void f(void) {
cout << "base f" << '\n';
};

virtual void g(void) {
cout << "base g" << '\n';
};
};

class b: public a {
public:
void f(void) {
cout << "derived f" << '\n';
};

void g(void) {
cout << "derived g" << '\n';
};
};

int main(void) {
a *my_b = new b();
my_b->f();
my_b->g();
return 0;
}