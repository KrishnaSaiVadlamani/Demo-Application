class ClassTest:
    def instance_method(self):
        print(f"Called instance_method of {self}")
    
    @classmethod
    def class_method(cls):
        print(f"Called class_method of {cls}")

    @staticmethod
    def static_method():
        print("Called static_method")

ClassTest.static_method()

#########################################################3

class Book:
    Types=("hardcover","paperback")

    def __init__(self,name,book_type,weight):
        self.name=name
        self.book_type=book_type
        self.weight=weight

    def __repr__(self):
        return f"<Book {self.name} Book_type {self.book_type} Weight {self.weight}>"


    @classmethod
    def hardcover(cls,name,page_weight):
        return cls(name,cls.Types[0],page_weight+100)    

    @classmethod
    def paperback(cls,name,page_weight):
        return cls(name,cls.Types[1],page_weight)    

book = Book.hardcover('Harry Potter',1500)
light = Book.paperback('Python',2500)
print(book)
print(light)
