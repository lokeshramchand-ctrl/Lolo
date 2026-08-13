/*/  LC 203 — Remove Linked List Elements

## Problem

Given the `head` of a linked list and an integer `val`, remove **all** nodes whose value equals `val`, and return the new head.

Example:

```
Input:
head = [1,2,6,3,4,5,6]
val = 6

Output:
[1,2,3,4,5]
```

---

# Intuition

We need to visit every node.

For each node:

- If the node value is **NOT** `val` → keep it.
- If the node value **IS** `val` → skip it.

The tricky part is when the **head itself needs to be removed**.

Instead of writing special logic for the head, we create a **dummy node**.

```
Dummy -> 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6
```

Now every node has a previous node.

---

# Why Dummy Node?

Without Dummy:

```
6 -> 1 -> 2
↑
Head itself should be removed.
```

Head changes.

With Dummy:

```
Dummy -> 6 -> 1 -> 2
```

Now we simply skip `6`.

No special case.

---

# Pointers Used

```
Dummy
  |
  v
0 -> 1 -> 2 -> 6 -> 3 -> NULL
     ^
     current
```

We only move **current**.

---

# Java Solution (Dry Run Inside Comments)
*/
public class removeElements {

    public ListNode removeElements(ListNode head, int val) {

        // Step 1:
        // Create a dummy node.
        // Dummy points to the original head.
        //
        // Before:
        //
        // head
        //  |
        //  v
        // 1 -> 2 -> 6 -> 3 -> null
        //
        // After:
        //
        // dummy -> 1 -> 2 -> 6 -> 3 -> null
        //
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // current always stays one node BEFORE
        // the node we are checking.
        //
        // current
        //   |
        //   v
        // dummy -> 1 -> 2 -> 6 -> 3
        //
        ListNode current = dummy;

        // Traverse until there is no next node.
        while (current.next != null) {

            // Example:
            //
            // dummy -> 1 -> 2 -> 6 -> 3
            //               ^
            //           current.next
            //
            if (current.next.val == val) {

                // Remove current.next.
                //
                // Before:
                //
                // current
                //   |
                //   v
                // 2 -> 6 -> 3
                //
                // current.next = current.next.next
                //
                // After:
                //
                // current
                //   |
                //   v
                // 2 --------> 3
                //
                current.next = current.next.next;

            } else {

                // Current node is good.
                //
                // Move current forward.
                //
                // Before:
                //
                // current
                //   |
                //   v
                // dummy -> 1 -> 2 -> 3
                //
                // After:
                //
                // dummy -> 1 -> 2 -> 3
                //              ^
                //           current
                //
                current = current.next;
            }
        }

        // Return actual head.
        //
        // dummy -> 1 -> 2 -> 3
        //
        // Return:
        //
        // 1 -> 2 -> 3
        //
        return dummy.next;
    }
}


/*  Complete Dry Run

Input

```
head = [1,2,6,3,4,5,6]
val = 6
```

### Initial State

```
dummy -> 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6 -> null
 ^
current
```

---

### Iteration 1

```
current.next = 1

1 != 6

Move current
```

```
dummy -> 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6
          ^
       current
```

---

### Iteration 2

```
current.next = 2

2 != 6

Move current
```

```
dummy -> 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6
               ^
            current
```

---

### Iteration 3

```
current.next = 6

Found target
```

Before

```
2 -> 6 -> 3
```

Execute

```java
current.next = current.next.next;
```

After

```
2 -------> 3
```

List becomes

```
dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
               ^
            current
```

Notice that **current does not move** after deletion because there might be another `6` immediately after.

---

### Continue

```
3 != 6
Move

4 != 6
Move

5 != 6
Move
```

Finally

```
dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
                              ^
                           current
```

---

### Last Iteration

```
current.next = 6

Delete it.
```

Before

```
5 -> 6 -> null
```

After

```
5 -> null
```

Final List

```
dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> null
```

Return

```
1 -> 2 -> 3 -> 4 -> 5
```

---

# Why Do We Check `current.next` Instead of `current`?

If we wrote:

```java
if (current.val == val)
```

and wanted to delete `current`, we'd first need to know its **previous node** to reconnect the list.

By keeping `current` **one node behind**, we always have direct access to the previous node, making deletion simple:

```java
current.next = current.next.next;
```

No backward traversal is needed.

---

# Pattern Learned

```
Create Dummy
        ↓
Keep Pointer Before Target Node
        ↓
Check current.next
        ↓
Delete Using
current.next = current.next.next
        ↓
Move Only When No Deletion Happens
```

> This **Dummy Node + Traversal** pattern is reused in many Linked List interview problems, including LC 19 (Remove Nth Node From End), LC 21 (Merge Two Sorted Lists), and LC 24 (Swap Nodes in Pairs).
*/