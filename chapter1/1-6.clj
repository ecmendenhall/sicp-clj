;; Exercise 1.6

(defn new-if [predicate then-clause else-clause]
  (cond 
    predicate then-clause
    :else else-clause))

(new-if (= 2 3) 0 5)
;; 5

(new-if (= 1 1) 0 5)
;; 0

(defn sqrt-iter [guess x]
  (new-if (good-enough? guess x)
          guess
          (sqrt-iter (improve guess x) x)))

;; This sqrt-iter will never terminate, since cond uses applicative-order
;; evaluation. The special form if uses normal-order evaluation.
