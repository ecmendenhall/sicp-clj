;; Exercise 1.8

;; Improved Newton's method code:

(defn average [x y]
  (/ (+ x y) 2))

(defn square [x]
  (* x x))

(defn abs [x]
  (if (> x 0) x (- x)))

(defn good-enough? [guess lastguess x]
  (< (/ (abs (- lastguess guess)) guess) 0.001))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess lastguess x]
  (if (good-enough? guess lastguess x)
    guess
    (sqrt-iter (improve guess x) guess x)))

(defn new-sqrt [x]
  (sqrt-iter 1.0 0 x))

;; To implement the same procedure for cube roots, we just need
;; to change the improve procedure, and make sure the new function
;; is used in the other functions:

(defn cube-improve [guess x]
  (/ (+ (/ x (square y)) (* 2 y)) 
     3))

(defn cubert-iter [guess lastguess x]
  (if (good-enough? guess lastguess x)
    guess
    (cubert-iter (cube-improve guess x) guess x)))

(defn cubert [x]
  (cubert-iter 1.0 0 x))
