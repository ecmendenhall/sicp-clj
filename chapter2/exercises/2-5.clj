;; Exercise 2.5

;; We can represent pairs of nonnegative integers using only numbers
;; and arithmetic if we represent the pair [a b] as the integer that
;; is the product 2^{a} * 3^{b}:

(defn cons [a b]
  (int (* (Math/pow 2 a) (Math/pow 3 b))))

(defn car [p]
  (defn car-iter [p i]
    (if (= 0 (mod p 2))
      (car-iter (/ p 2) (inc i))
      i))
  (car-iter p 0))

(defn cdr [p]
  (defn cdr-iter [p i]
    (if (= 0 (mod p 2))
      (cdr-iter (/ p 3) (inc i))
      (- i 1)))
  (cdr-iter p 0))

;; ...but since the max integer in Clojure is  ~6.9 x 10^9, we can't
;; represent very big pairs:
(car (cons 1 20))
;; IllegalArgumentException Value out of range for int: 6.973568802E9
