;; Exercise 1.31

;; Here's our iterative sum procedure:

(defn sum-sequence
  [term a next-one b]
  (defn iter
    [a result]
    (if (> a b)
      result
      (iter (next-one a) (+ a result))))
  (iter a 0))

;; It's pretty easy to make it a product procedure instead:

(defn sequence-product
  [term a next-one b]
  (defn iter
    [a result]
    (if (> a b)
      result
      (iter (next-one a) (* (term a) result))))
  (iter a 1))

;; (Here's a recursive version)
(defn sequence-product-recursive
  [term a next-one b]
  (if (> a b)
    1
    (*' (term a)
       (sequence-product-recursive term (next-one a) next-one b))))

;; We can define factorial like this:
(defn factorial
  "Calculates and returns n! = n * (n-1) * (n-2) ... 1"
  [n]
  (sequence-product identity 1 inc n))

;; Or use it to approximate pi:
(defn approximate-pi 
  "Approximates pi using the formula:
  (pi/4) = (2 * 4 * 4 * 6 * 6 * 8 ...) / (3 * 3 * 5 * 5 * 7 * 7)
  where n is the number of multiplications."
  [n]
  (defn pi-sequence [x]
    (cond (= 1 x) (/ 2 3)
          (even? x) (/ (+ 2 x) (+ 1 x))
          (odd? x) (/ (+ 1 x) (+ 2 x))))
  (float (* 4.0 (sequence-product pi-sequence 1 inc n))))
