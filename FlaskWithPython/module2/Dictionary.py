friend_ages={"Rolf":24,"Adam":27,"Kane":32}

friend_ages['Adam']=30

print(friend_ages)



######################


student_attendence={"Rolf":96,"Adam":88,"Anne":97}

for student,attendence in student_attendence.items():
    print(f"{student}: {attendence}")



###################### Dictionary Comprehension######

users=[
    (0,"ravi","123"),
    (1,"holika","password"),
    (2,"kane","kane123")
]

username_mapping = {user[1]: user for user in users}

username_input=input("Enter Your Username: ")
password_input=input("Enter Your PassWord: ")

_,username, password = username_mapping[username_input]

if password_input == password:
    print("Your Details are Correct!!!")

else:
    print("Your are details are incorrect")