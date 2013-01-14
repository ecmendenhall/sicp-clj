;; Exercise 1.7

;; Here's the previous Newton's Method code:

(defn average [x y]
  (/ (+ x y) 2))

(defn square [x]
  (* x x))

(defn abs [x]
  (if (> x 0) x (- x)))

(defn good-enough? [guess x]
  (< (abs (- (square guess) x )) 0.001))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x) x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

;; This method doesn't work very well for small numbers:

(sqrt 0.000004)
;;0.03167509508023218

;; Or for very large ones:

(sqrt 9182309812472107419204701927398124)
;; StackOverflowError   java.math.MutableBigInteger.divide...

;; The error tolerance is too large for small numbers, and the procedure
;; never terminates for very large ones.

;; Let's follow what's happening to the value of guess inside good-enough?:

(defn good-enough? [guess x]
  (println guess)
  (< (abs (- (square guess) x )) 0.001))

(sqrt 4)
;; 1.0
;; 2.5
;; 2.05
;; 2.000609756097561
;; 2.0000000929222947
;; 2.0000000929222947

(sqrt 0.00004)
;; 1.0
;; 0.50002
;; 0.25004999840006403
;; 0.12510498320374316
;; 0.06271235733613596
;; 0.03167509508023218
;; 0.03167509508023218

;; Now, let's redefine good-enough? to watch how guess changes between
;; iterations.

(defn good-enough? [guess lastguess x]
  (< (/ (abs (- lastguess guess)) guess) 0.001))

;; Lastguess also has to be passed in the recursive call:

(defn sqrt-iter [guess lastguess x]
  (if (good-enough? guess lastguess x)
    guess
    (sqrt-iter (improve guess x) guess x)))

;; And has to have some value for which good-enough? returns false to 
;; start the procedure:

(defn new-sqrt [x]
  (sqrt-iter 1.0 0 x))

;; The new method is much better!

(new-sqrt 0.000004)
;; 0.0020000003065983023

(new-sqrt  9182309812472107419204701927398124)
;; 9.5824369623670048E16
