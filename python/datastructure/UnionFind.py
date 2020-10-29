from collections import *


class UF:
    def __init__(self, n: int) -> None:
        self.nodes = [i for i in range(n)]
        self.count = n

    def union(self, p: int, q: int) -> None:
        p_root = self.find(p)
        q_root = self.find(q)
        if p_root != q_root:
            self.nodes[p_root] = q_root
            self.count -= 1
    
    def find(self, p: int) -> int:
        t = self.nodes[p]
        while t != self.nodes[t]:
            parent = self.nodes[t]
            # path compression
            self.nodes[t] = self.nodes[parent]
            t = parent
        return self.nodes[t]

    def connected(self, p: int, q: int) -> bool:
        return self.find(p) == self.find(q)

    def count(self) -> int:
        return self.count
