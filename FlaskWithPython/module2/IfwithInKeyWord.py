movies_watched={"maharshi","SLN","Matrix"}

user_movie=input("Enter Movie You have Watched: ")

if(user_movie in movies_watched):
    print(f"I've Watched {user_movie}!!!")

else:
    print("I haven't watched that yet")


#####

number = 7

user_input=input("Enter 'y' if you would like to play: ")

if user_input in ('y','Y'):
    user_number=int(input("Guess Number: "))

    if user_number == number:
        print("You Guessed Correctly!!!")
    
    else:
        print("Wrong!!!!")


