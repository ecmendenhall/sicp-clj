;; Section 1.3.2: Using let to create local variables

;; Suppose we want to compute:
;;
;; f(x,y) = x(1+xy)^2 + y(1-y) + (1+xy)(1-y)
;;
;; Which we could also express as:
;;
;;      a = 1 + xy
;;      b = 1 - y
;; f(x,y) = xa^2 + yb + ab
;;
;; We want to include local variables to hold intermediate results like a
;; and b. One way is something like this:

(defn f [x y]
  (defn f-helper [a b]
    (+ (* x (square a))
       (* y b)
       (* a b)))
  (f-helper (+ 1 (* x y)) (- 1 y)))

;; But f-helper doesn't really need a name:

(defn f [x y]
  ((fn [a b] 
     (+ (* x (square a)) (* y b) (* a b)))
     (+ 1 (* x y))
     (- 1 y)))

;; It's still all functions, but this works like holding local variables.
;; This is so useful, there's a special form called 'let':

(defn f [x y]
  (let [a (+ 1 (* x y))
        b (- 1 y)]
    (+ (* x (square a))
       (* y b)
       (* a b))))

;; Now the scope of your local variables is clearly delineated by the 
;; parentheses around the let expression that contains them. Pretty great!
