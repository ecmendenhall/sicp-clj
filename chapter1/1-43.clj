;; Exercise 1.43

;; If f is a numerical function and n is a positive integer, we can form
;; the nth repeated application of f, which is the function whose value at
;; x is f(f(...(f(x))...))
(defn compose [f g]
  (fn [x] (f (g x))))

(defn square [x] (* x x))

(defn repeated [f n]
  (if (= n 1)
    f
    (compose f (repeated f (- n 1)))))

((repeated square 2) 5)
;; 625

((repeated square 3) 5)
;; 390625
