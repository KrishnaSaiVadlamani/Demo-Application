def user_age_in_seconds():

    user_age=int(input("Enter Your Age: "))
    age_seconds=user_age * 365 * 24 * 60 * 60
    print(f"Your age is {age_seconds}")

user_age_in_seconds()

####################


def add(x,y):
    result=x+y
    print(result)

add(2,4)

####################################

def say_hello(name):
    print(f"hello,{name}")


say_hello("King")


####################################

def divide(dividend, divisor):
    if divisor!=0:
        print(dividend/divisor)
    else:
        print("Cannot Divide!!!!")

divide(dividend=15,divisor=0)

