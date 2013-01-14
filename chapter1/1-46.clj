;; Exercise 1.46

;; We've been repeating a pattern of iterative improvement: start with a
;; guess, check if it's good enough, and improve it if it's not. Let's
;; abstract this with an iterative-improve function that takes a method
;; for checking whether a guess is good enough, and a method for improving
;; a guess. 

(defn average [x y]
  (/ (+ x y) 2))

(defn square [x]
  (* x x))

(defn abs [x]
  (if (> x 0) x (- x)))

(defn iterative-improve [good-enough? improve]
  (fn [guess] (if (good-enough? guess) 
                guess 
                ((iterative-improve good-enough? improve) (improve guess)))))

;; Now we can write everything as confusing one-liners!
(defn sqrt [x]
  ((iterative-improve (fn [guess] (< (abs (- (square guess) x)) 0.001))
                      (fn [guess] (average guess (/ x guess))))
  1.0))

(defn fixed-point [f guess]
  ((iterative-improve (fn [guess] (< (abs (- (f guess) guess)) 0.00001))
                      (fn [guess] (f guess)))
     guess))
