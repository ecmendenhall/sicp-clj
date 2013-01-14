;; Exercise 1.38

;; Let's approximate e! Euler figured out that e - 2 is equal to the 
;; continued fraction where n_i is always 1 and d_i is the series
;; 1, 2, 1, 1, 4, 1, 1, 6, 1, 1, 8, ...

(defn cont-frac [n d k]
  (defn iter [acc i]
    (if (= i 0)
      acc
      (recur (/ (n i) (+ (d i) acc))
             (-' i 1))))
  (iter (/ (n k) (d k)) (-' k 1)))

(defn n [i] 1.0)

(defn d [i]
  (let [r (mod i 3)]
    (if (or (= r 0)
            (= r 1)) 1
      (* 2 (/ (+ i 1) 3)))))

(+ 2 (cont-frac n d 100))
;; 2.7182818284590455
