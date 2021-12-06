
# price and discount is float 

price=9.99
discount=0.2
result=price*(1-discount)
print(result)

# String 

name="Krishna"
name1="Sai"
print(name)
print(name*2)
print(name+" "+name1)


# excercise of creating variables

var1="hola"
var2="hola"

print(var1+" "+var2)

num1=8
num2=2

print(num1*num2)

# using f-string

name="Mr.Bean"

greeting=f"Hi,{name}"

print(greeting)


# using format

name="Bob"

greeting="Hi,{}"

with_name=greeting.format(name)
with_name_second=greeting.format("Rolf")
print(with_name)
print(with_name_second)
