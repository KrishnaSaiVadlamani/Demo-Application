from os import name


def multiply(*args):
    print(args)
    total=1
    for arg in args:
        total=total*arg
    
    return total

def apply(*args,operator):
    if(operator=="*"):
        return multiply(*args)
    elif operator=="+":
        return sum(args)
    else:
        return "No Valid Operator"

print(apply(1,5,9,operator="+"))

##########################################3

def add(x,y):
    return x+y

nums={"x":15,"y":20}
print(add(**nums))



########################################

def named(**kwargs):
    print(kwargs)

named(name="Bob",age=25)


#########################################

def named(name,age):
    print(name,age)

details={"name":"Bob","age":26}
named(**details)