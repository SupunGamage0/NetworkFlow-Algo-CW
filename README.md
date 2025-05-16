# Network Flow Algorithm Coursework  
**University of Westminster | SSENG003W Algorithms**  

This project computes the **maximum flow** in a directed graph using the **Ford-Fulkerson algorithm** (with BFS for shortest paths).  

---

## 📝 Input Format  
Create a `.txt` file like `input.txt`:  
```plaintext  
4           # Total nodes (source=0, target=3)  
0 1 6       # Edge from node 0 to 1 with capacity 6  
0 2 4       # Edge from node 0 to 2 with capacity 4  
1 2 2  
1 3 3  
2 3 5  
```  

---

## 📤 Sample Output  
After running the code, you’ll see:  
```plaintext  
Maximum Flow: 8  
Augmenting Paths:  
1. 0 → 2 → 3 (Flow: 4)  
2. 0 → 1 → 3 (Flow: 3)  
3. 0 → 1 → 2 → 3 (Flow: 1)  
``

```  

**Q: Why is the Ford-Fulkerson algorithm used?**  
A: It’s a classic method for finding maximum flow in networks by iteratively finding augmenting paths.  

---
