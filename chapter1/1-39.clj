;; Exercise 1.39

;; Let's write a tangent procedure! J.H. Lambert discovered a continued
;; fraction representation of the tangent function:
;; 
;;    n(x) = x if x = 1 else x^2
;;    d(x) = 1, 3, 5, 7, 9, ..
;;
;; Where iterated terms in are *subtracted* in the denominator.

(defn tan-cf [x k]
  (defn cont-frac [n d k]
    (defn iter [acc i]
      (if (= i 0)
        acc
        (recur (/ (n i) (+ (d i) acc))
               (-' i 1))))
    (iter (/ (n k) (d k)) (-' k 1)))

  (defn n [i]
    (if (= i 1) x
      (- (* x x))))

  (defn d [i]
    (- (* 2 i) 1))

  (cont-frac n d k))

(tan-cf 3.14159 100)
;; -2.6535897933138355E-6
