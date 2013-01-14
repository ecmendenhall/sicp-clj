;; From section 1.2.1: Linear Recursion and Iteration

(defn recursive-factorial 
  "Recursively computes n factorial."
  [n]
  (if (= n 1) 1 (* n (recursive-factorial (- n 1)))))

(defn iterative-factorial
  "Iteratively computes n factorial."
  [n]
  (defn iter [product counter]
    (if (> counter n)
      product
      (iter (* counter product)
            (+ counter 1))))
  (iter 1 1))

;; But hey, isn't that second procedure recursive, too? It calls itself!
;; Well, yes: it's a recursive *procedure*, because the defintion refers
;; to itself, but it's not a recursive *process*, which refers to how it
;; evolves, not the syntax of how it's written. Since the state of the 
;; process is captured completely by the three state variables (n, product,
;; counter), it's iterative. 
