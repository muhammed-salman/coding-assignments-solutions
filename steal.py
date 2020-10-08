'''
Program to perform maximize stealing amount
without setting off an alarm
Alarm will be raised if any two adjacent house are raided
First & Last house are adjacent (Circular Link Concept) 
'''
no_of_houses = int(input())
houses = []

for i in range(0,no_of_houses):
    houses.append(int(input()))

print(houses)

max_money = 0
for i in range(0,no_of_houses):
    total_money = 0
    total_money += houses[i]
    j = no_of_houses-1
    prevJ = -1
    while j > i:
        if i==0 and j==no_of_houses-1:
            pass
        elif j != i+1 and j !=i-1:
            if j+1 != prevJ:
                total_money += houses[j]
                prevJ = j
        j -= 1
    print('==',i,total_money)
    max_money = total_money if total_money > max_money else max_money

print(max_money)
      