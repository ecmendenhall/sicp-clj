;; Section 1.3.3: Fixed points

(defn fixed-point [f first-guess]
  (let [tolerance 0.00001]

    (defn close-enough? [v1 v2]

      (defn abs [x]
        (if (> 0 x) (- x) x))

      (< (abs (- v1 v2)) tolerance))

    (defn try-it [guess]
      (let [next-guess (f guess)]
        (if (close-enough? guess next-guess)
          next-guess
          (try-it next-guess))))
    
    (try-it first-guess)))

;; What a cool procedure! But check this out: square root computation can
;; also be expressed as a fixed-point search. The square root of x is some
;; y such that y^2 = x. Divide both sides by y for y = x/y. The square 
;; root you're looking for is the fixed point of the function f(y) = x/y
;; (e.g. f(2) = 4/2, f(3) = 9/3). Try something like this:

(defn sqrt [x]
  (fixed-point (fn [y] (/ x y)) 1.0))

(sqrt 5)
;; 1.0

;; Wait, what? Turns out, this fixed-point search doesn't converge. Guess
;; y1 becomes guess y2 = x/y1, becomes guess y3 = x/x/y1) = y1. One way 
;; to fix this oscillation is to keep the guesses from changing so much,
;; by averaging y with x/y. That is, the next guess after y is now
;; 1/2(y + x/y).

(defn sqrt [x]
  (fixed-point (fn [y] (/ (+ y (/ x y)) 2.0)) 1.0))

;; This is okay because y = 1/2(y + x/y) is a transformation of y = x/y:
;;  
;;    y     = x/y
;;    y + y = x/y + y
;;   2y     = x/y + y
;;    y     = 1/2(x/y + y) 
;; 
;; This technique is called average damping, and it works with other
;; transformations, too.
