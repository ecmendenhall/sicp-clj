;; Exercise 2.1

;; Our original version of make-rat didn't normalize negative arguments:
(defn make-rat [n d]
  [n d])

;; This one does:
(defn make-rat [n d]
  (defn abs [x]
    (if (< x 0) (- x) x))
  (if (pos? (/ n d)) 
    (vector (abs n) (abs d))
    (vector n (abs d))))

(defn numer [x]
  (first x))

(defn denom [x]
  (second x))

(defn print-rat [x]
  (print (numer x))
  (print "/")
  (print (denom x))
  (println))

(defn add-rat [x y]
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn sub-rat [x y]
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn mul-rat [x y]
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))

(defn div-rat [x y]
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))

(defn equal-rat? [x y]
  (= (* (numer x) (denom y))
     (* (numer y) (denom x))))
