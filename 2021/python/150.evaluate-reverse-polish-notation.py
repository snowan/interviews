class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        stack = []
        for token in tokens:
            if token not in "+-*/":
                stack.append(int(token))
            else:
                right, left = stack.pop(), stack.pop()
                if token == "+":
                    stack.append(left + right)
                elif token == "-":
                    stack.append(left - right)
                elif token == "*":
                    stack.append(left * right)
                else:
                    stack.append((int)(float (left) / right))
        return stack.pop()
