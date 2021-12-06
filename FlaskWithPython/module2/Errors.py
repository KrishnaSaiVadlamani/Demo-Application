def divide(dividend,divisor):
    if divisor==0:
        raise ZeroDivisionError("Divisor cannot be 0")

    return dividend/divisor


students=[
    {"name":"Bob","grades":[75,90]},
    {"name":"Jen","grades":[95,84]}
]


print("Welcome to the average grade program.")

try:
    for student in students:
        name=student["name"]
        grades=student["grades"]
        average=divide(sum(grades),len(grades))
        print(f"{name} averaged {average}")

except ZeroDivisionError:
    print(f"ERROR: {name} has no grades")

else:
    print("-- ALl student averages calculated -- ")

finally:
    print("-- End of student average calculation --")