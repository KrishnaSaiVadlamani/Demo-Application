  #include <iostream>
  #include <new>
  #include <climits>

  int main(void) {
      int negative = -1;
      int small = 1;
      int large = INT_MAX;
      try {
          new int[negative];           // negative size
      } catch(const std::bad_array_new_length &e) {
          std::cout << e.what() << '\n';
      }
      try {
          new int[small]{1, 2, 3};     // too many initializers
      } catch(const std::bad_array_new_length &e) {
          std::cout << e.what() << '\n';
      }
      try {
          new int[large][1000000];     // too large
      } catch(const std::bad_array_new_length &e) {
         std::cout << e.what() << '\n';
}
}