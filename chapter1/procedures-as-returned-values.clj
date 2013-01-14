;; Section 1.3.4: Procedures as returned values

;; We can redefine the average damping method as a function:

(defn average-damp [f]
  (fn [x] (/ (+ x (f x)) 2)))

;; This takes a function f as an argument and returns a function!

(defn square [x]
  (* x x))

((average-damp square) 10)
;; 55

;; Here's one more formulation of the square-root function:

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

(defn sqrt [x]
  (fixed-point
    (average-damp
      (fn [y] (/ x y)))
    1.0))

(sqrt 25)
;; 5.0

;; Abstraction like this makes it easier to reuse the parts of this
;; procedure, e.g. to calculate cube roots, too:

(defn cube-root [x]
  (fixed-point
    (average-damp
      (fn [y] (/ x (square y))))
    1.0))

;; Newton's method: if x => g(x) is a differentiable function, then a 
;; solution of the equation g(x) = 0 is a fixed point of the function 
;; x => f(x) where:
;;
;;     f(x) = x - g(x)/(Dg(x))
;; 
;; and Dg(x) is the derivative of g evaluated at x. What do we need to
;; implement this? First, derivatives. You're probably used to treating
;; dxs as abstractions, but here we can just use really small numbers:

(defn deriv [g]
  (let [dx 0.00001]
  (fn [x]
    (/ (- (g (+ x dx)) (g x))
       dx))))

(defn cube [x]
  (* x x x))

((deriv cube) 5) ;; = 75
;; 75.0014999664018

(defn newtons-method [g guess]
  (defn newton-transform [g]
    (fn [x]
      (- x (/ (g x)
              ((deriv g) x)))))
  (fixed-point (newton-transform g) guess))

;; With Newton's method, we can write yet another square root function:
(defn sqrt [x]
  (newtons-method
    (fn [y]
      (- (square y) x))
    1.0))

;; Both of these square root methods start with a function, then find a
;; fixed point of some transformation of that function. Let's express this
;; as a procedure, too:

(defn fixed-point-of-transform [g transform guess]
  (fixed-point (transform g) guess))

;; Now we can write even MORE square root functions!

(defn sqrt [x]
  (fixed-point-of-transform
    (fn [y] (/ x y))
    average-damp
    1.0))

(defn sqrt [x]
  (fixed-point-of-transform
    (fn [y] (- (square y) x))
    newton-transform
    1.0))


