;; Exercise 1.37

;; An infinite continued fraction is an expression of the form:
;;
;;                n_1
;;    f = ----------------------
;;         d_1 +       n_2
;;               ---------------
;;                d_2 +    n_3
;;                     ---------
;;                     d_3 + ... 
;;
;; If n_i and d_i = 1, this produces 1/phi!
;;
;; An okay approximation is just truncating this after some number of
;; iterations. This is called a "k-term finite continued fraction."
;;
;; Here's a procedure that takes two functions that return n_i and d_i
;; and computes the calue of their k-term finite continued fraction:

(defn cont-frac [n d k]
  (defn recur-frac [i]
    (if (< i k)
     (/ (n i) (+ (d i) (recur-frac (+ i 1))))
     (/ (n i) (d i)))
    (recur-frac 1)))

(cont-frac (fn [x] 1.0) (fn [y] 1.0) 10)
;; java.lang.StackOverflowError

(defn cont-frac-iter [n d k]
  (defn iter [acc i]
    (if (= i 0)
      acc
      (recur (/ (n i) (+ (d i) acc))
             (-' i 1))))
  (iter (/ (n k) (d k)) (-' k 1)))

(cont-frac-iter (fn [x] 1.0) (fn [y] 1.0) 10)
;; 0.6179775280898876

(/ 1 1.6180339887)
;; 0.618033988768953

(cont-frac-iter (fn [x] 1.0) (fn [y] 1.0) 11)
;; 0.6180555555555556

(cont-frac-iter (fn [x] 1.0) (fn [y] 1.0) 12)
;; 0.6180257510729613

;; k must be greater than 12 to be accurate to 4 decimal places.
