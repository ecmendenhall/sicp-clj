;; Exercise 1.16

;; Section 1.2.4 — Exponentiation procedures

;; O(n) steps, O(n) space
(defn expt [b n]
  (if (= n 0) 1 (* b (expt b (- n 1)))))

;; O(n) steps, O(1) space
(defn expt-iter [b n]
  (defn iter [b counter product]
    (if (= counter 0)
      product
      (iter b
            (- counter 1)
            (* b product))))
  (iter b n 1))

;; Successive squaring: 
;; b^n = (b^(n/2))^2      if n is even
;;     = b * (b^(n-1))    if n is odd

;; O(log n) steps, O(log n) space
;; (Computing b^2n reqires only one more multiplication than b^n)
(defn fast-expt [b n]
  (cond (= n 0) 1
        (even? n) (square (fast-expt b (/ n 2)))
        :else (* b (fast-expt b (- n 1)))))

(defn fast-expt-iter [b n]
  (defn iter [b a counter]
    (cond (= counter 0) a
          (odd? counter) (iter b (* a b) (- counter 1))
          :else (iter (* b b) a (/ counter 2))))
  (iter b 1 n))
