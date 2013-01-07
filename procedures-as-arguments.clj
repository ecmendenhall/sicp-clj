;; Section 1.3.1: Procedures as arguments

(defn cube [x]
  (* x x x))

(defn sum-integers
  "Computes the sum of the integers a through b."
  [a b]
  (if (> a b)
    0
    (+ a (sum-integers (+ a 1) b))))

(defn sum-cubes
  "Computes the sum of the cubes of the integers in the given range."
  [a b]
  (if (> a b)
    0
    (+ (cube a) (sum-integers (+ a 1) b))))

(defn pi-sum
  "Computes the sum of a sequence of terms in the series:
  
  1/(1 * 3) + 1/(5 * 7) + 1/(9 * 11) + ...
  
  which converges very slowly to pi/8.
  "
  [a b]
  (if (> a b)
    0
    (+ (/ 1.0 (* a (+ a 2))) (pi-sum (+ a 1) b))))

;; All three of these procedures have a common pattern. The only significant
;; difference is the function applied to a before it's added to the final
;; sum. This is where passing procedures as arguments comes in handy. 
;; Here's one way to abstract it a little:

(defn sum-sequence
  [term a next-one b]
  (if (> a b)
    0
    (+' (term a)
       (sum-sequence term (next-one a) next-one b))))

;; Now, sum-cubes can become:

(defn sum-cubes [a b]
  (sum-sequence cube a inc b))

;; We can write sum-integers with an identity procedure (this is built in
;; to clojure.core):

(defn sum-integers [a b]
  (sum-sequence identity a inc b))

;; And we can write pi-term similarly:
(defn pi-sum [a b]
  (defn pi-term [x]
    (/ 1.0 (*' x (+' x 2))))
  (defn pi-next [x]
    (+' x 4))
  (sum-sequence pi-term a pi-next b))

;; There's all kinds of cool stuff we can do by adding up sequences.
;; Like calculus! The definite integral of a function f between limits a
;; and b can be approximated by:
;;
;; [f(a + dx/2) + f(a + dx + dx/2) + f(a + 2dx + dx/2) + ...]dx
;;
;; when dx is small. (The sequence adds a dx term to the sum inside the 
;; parentheses with every iteration, if it's not immediately obvious)

(defn integral
  "Approximates the integral of the function f between a and b."
  [f a b dx]
  (defn add-dx [x] (+ x dx))
  (* (sum-sequence f (+ a (/ dx 2.0)) add-dx b) dx))
