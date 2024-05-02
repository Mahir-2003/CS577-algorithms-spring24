def __main__():
    instances = int(input())

    for i in range(instances):
        inversions = input().split(" ")
        inversions = list((int(i) for i in inversions)) #convert strings to int in list
        temp = [0]*len(inversions) #creating a temp list with same size as list "inversions"
        #[0]*len(x) is a common Python idiom for creating a new list with a specific length and default value (i.e: 0)
        # In the case above, it is creating a new list that has the same length as "inversions" list and every element in the list is init to 0

        print(countInversions(inversions, temp, 0, len(inversions) - 1))
 

def countInversions(array, temp, leftStart, rightEnd):
    
    if leftStart >= rightEnd:
        return 0
    
    midpoint = (leftStart + rightEnd) // 2
    count = 0
    count += countInversions(array, temp, leftStart, midpoint) #front half recursive call
    count += countInversions(array, temp, midpoint + 1, rightEnd) #back half recursive call
    count += merge(array, temp, leftStart, rightEnd) #merge and count array halves

    return count

def merge(array, temp, leftStart, rightEnd):
    leftEnd = (leftStart + rightEnd) // 2 #midpoint
    rightStart = leftEnd + 1 #midpoint + 1
    size = rightEnd - leftStart + 1

    left = leftStart
    right = rightStart
    index = leftStart
    inversions = 0

    while (left <= leftEnd and right <= rightEnd):
        if array[left] <= array[right]:
            temp[index] = array[left]
            left += 1
        else: 
            temp[index] = array[right]
            right += 1
            inversions += leftEnd - left + 1
        index += 1

    array[leftStart:rightEnd+1] = temp[leftStart:rightEnd+1]

    return inversions
        
if __name__ == "__main__":
    __main__()