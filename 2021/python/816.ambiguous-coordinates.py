class Solution:
    def ambiguousCoordinates(self, s: str) -> List[str]:
        if not s or len(s) == 0:
            return []
        s = s[1:-1]
        def validStrs(s):
            if len(s) == 1:
                return [s]
            if s[0] == '0' and s[-1] == '0':
                return []
            if s[0] == '0':
                return [s[0] + "." + s[1:]]
            if s[-1] == '0':
                return [s]
            return [s[:i] + "." + s[i:] for i in range(1, len(s))] + [s]
        
        res = []
        for i in range(1, len(s)): 
            for left in validStrs(s[:i]):
                for right in validStrs(s[i:]): 
                    res.append(f"({left}, {right})")
        return res
            
        
