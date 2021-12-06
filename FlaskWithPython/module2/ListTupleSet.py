l=["krishna","kane","Bob"]
t=("krishna","kane","Bob")
s={"krishna","kane","Bob"}

print(l[2])
print(t[1])


# advanced set operations 

friends={"Bob","Rolf","Anne"}
abroad={"Bob","Anne"}

local_friends=abroad.difference(friends)
print(local_friends)


# union operation

local={'Krishna'}
abroad={"rakesh","sahithi"}

friends=local.union(abroad)
print(friends)


# intersection operation

art={"krishna","kane","Bob","holika"}
science={"krishna","kane","Bob","rakesh"}

both=art.intersection(science)
print(both)