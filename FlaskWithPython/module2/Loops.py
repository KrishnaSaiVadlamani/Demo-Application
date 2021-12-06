number = 7

while True:
 
    user_input=input("Enter 'y' if you would like to play?(Y/n) ")
 
    if user_input == 'n':
        break

    user_number=int(input("Guess Number: "))

    if user_number == number:
        print("You Guessed Correctly!!!")
    
    elif abs(number - user_number) == 1:
        print("Oops You have Missed by 1")
    
    else:
        print("Wrong!!!!")



##############################################################

grades=[35,30,100,98,75]
total=0
students=len(grades)

for grade in grades:
    total+=grade

print(total/students)



###################### List Comprehensions #########

numbers=[1,3,5]
double=[num * 2 for num in numbers]
print(double)


friends=["krishna","rakesh","kane","david"]

starts_k=[friend for friend in friends if friend.startswith('k')]

print(starts_k)
        
