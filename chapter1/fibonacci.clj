;; Section 1.2.2: Tree Recursion

(defn fib-recur
  "Computes the nth Fibonacci number recursively."
  [n]
  (cond (= n 0) 0
        (= n 1) 1
        :else (+ (fib (- n 1)) 
                 (fib (- n 2)))))

(defn fib-iter
  "Computes the nth Fibonacci number iteratively."
  [n]
  (defn iter [a b count]
    (if (= count 0) 
      b
      (iter (+ a b) a (- count 1))))
  (iter 1 0 n))
