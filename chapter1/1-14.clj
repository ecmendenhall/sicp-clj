;; Exercise 1.14

(defn count-change 
  "Returns the number of ways to make change for the given amount in cents."
  [amount]
  (defn first-denomination [kinds-of-coins]
    (cond (= kinds-of-coins 1) 1
          (= kinds-of-coins 2) 5
          (= kinds-of-coins 3) 10
          (= kinds-of-coins 4) 25
          (= kinds-of-coins 5) 50))
  (defn cc [amount kinds-of-coins]
    (cond (= amount 0) 1
          (or (< amount 0)
              (= kinds-of-coins 0)) 0
          :else (+ (cc amount (- kinds-of-coins 1))
                   (cc (- amount (first-denomination kinds-of-coins))
                       kinds-of-coins))))
    (cc amount 5))

                                                                          (count-change 11)
;;                                                                                |
;;                                                               Change 11 cents w/ (50, 25, 10, 5, 1)
;;                                                                           |       |
;;                                                                           |       |
;;                                              Change 11 cents w/ (25, 10, 5, 1)    How many ways w/ 50?: 0.
;;                                                           |    |                  50 >11.
;;                                                           |    |
;;                                                           |    |
;;                                Change 11 cents w/ (10, 5, 1)  How many ways w/ 25?: 0.
;;                                  |               |            25 > 11.
;;                                  |               |
;;                                  |               |
;;                                  |               |
;;           Change 11 cents w/ (5, 1)             Subtract the highest denomination (10) from the amount.
;;               |               |                 How many ways to change 1 cent w/ (10, 5, 1)?
;;               |               |                                  |                 |
;;               |               |                                  |                 |
;;               |               |                Change 1 cent w/ (5, 1)            How many ways w/ 10?: 0.
;;               |               |                            |     |                10 > 1.
;;               |               |                            |     |
;;               |               |          Change 1 cent w/ (1)    How many ways w/ 5?: 0.
;;               |               |                   |        |       > 1.
;;               |               |                   |        |
;;               |               |          No more coins.    How many ways w/ 1?: 1.
;;               |               |          Return 0.         
;;               |               |
;;               |               |
;;               |               |
;;               |               |
;;  Change 11 cents w/ (1)      Subtract the highest denomination (5) from the amount.
;;               |              How many ways to change 6 cents w/ (5, 1)?
;;               |                             |                     |
;;               |                             |                     |
;;           How many ways w/ 1?               |                  Subtract the highest denomination (5) from the amount.
;;           Just 1.                           |                  How many ways to change 1 cent w/ (5, 1)?
;;                                       How many ways                        |                      |
;;                                       w/ 1?: 1.                            |                      |
;;                                                                How many ways w/ 1?: 1.           How many ways w/ 5?: 0.
;;                                                                                                  5 > 1.
;;
;;
;;
;;  Space required here is proportional to the height of the tree, so it's O(n).
;;
;;  Number of steps is harder. This tree isn't a good example: 11 cents is a small amount, so the tree's size is limited.
;;  But we can think about how it would change as the number of types of coins change for some intution about how it grows.
;;  With only pennies, it would generate one linear tree (like the leftmost branch of the tree here). This is O(n): the steps
;;  grow linearly with the input. Adding more coins gets more complicated. Adding nickels adds the subtree starting with the 
;;  right-hand branch of "Change 11 cents w/ (5, 1)." This generates another linear penny tree each time a nickel is divided 
;;  into the remaining amount and subtracted: (n/5) O(n) penny trees. So these nickel trees are O(n^2). Adding dimes adds the 
;;  right-hand subtree starting at "Change 11 cents w/ (10, 5, 1)." This includes penny subtrees *and* nickel subtrees for 
;;  each dime division: (n/10) O(n^2) trees, so it's O(n^3). The pattern repeats with all five coins, for something like:
;;  (n^5)/50 + (n^4)/25 + (n^3)/10 + (n^2)/5 + n, which is O(n^5).
