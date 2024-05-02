import random

def input_reader():
    n = int(input().strip())
    m = int(input().strip())
    clauses = []
    for _ in range(m):
        x, y, z = map(int, input().strip().split())
        clauses.append((x, y, z))
    return n, m, clauses

def evaluate(variables, clauses):
    count = 0
    for x, y, z in clauses:
        clause_x = variables[abs(x)-1] == (x > 0)
        clause_y = variables[abs(y)-1] == (y > 0)
        clause_z = variables[abs(z)-1] == (z > 0)
        if clause_x or clause_y or clause_z:
            count += 1
    return count

def max_3sat(n, m, clauses):
    variables = [random.choice([True, False]) for _ in range(n)]
    best_score = evaluate(variables, clauses)
    improvements = True

    while improvements:
        improvements = False
        for i in range(n):
            variables[i] = not variables[i]  # Flip variable
            current_score = evaluate(variables, clauses)
            if current_score > best_score:
                best_score = current_score
                improvements = True
            else:
                variables[i] = not variables[i]  # Flip back if no improvement

    return [1 if x else -1 for x in variables]

def main():
    n, m, clauses = input_reader()
    solution = max_3sat(n, m, clauses)
    for i in solution:
        print(i,end = " ")

if __name__ == "__main__":
    main()
