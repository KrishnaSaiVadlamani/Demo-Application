import sqlite3

connection=sqlite3.connect('Mydata.db')

cursor=connection.cursor()

create_table="CREATE TABLE if NOT exists users(id INTEGER PRIMARY KEY,username text,password text)"
cursor.execute(create_table)

create_table="CREATE TABLE if NOT exists items(name text,price real)"
cursor.execute(create_table)


connection.commit()

connection.close()