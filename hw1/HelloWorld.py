count = int(input())
names = []
for x in range(count):
    name = input()
    names.append(name)
for x in range(count):
    print("hello, " + names.pop(0) + "!")