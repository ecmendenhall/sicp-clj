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

(def x [1 2])
;; #'user/x

(first x)
;; 1

(second x)
;; 2

(defn make-rat [n d]
  [n d])

(defn numer [x]
  (first x))

(defn denom [x]
  (second x))

(defn print-rat [x]
  (print (numer x))
  (print "/")
  (print (denom x))
  (println))

(def one-half (make-rat 1 2))
;; #'user/one-half

(print-rat one-half)
;; 1/2

(def one-third (make-rat 1 3))
;; #'user/one-third

(print-rat (add-rat one-half one-third))
;; 5/6

(print-rat (mul-rat one-half one-third))
;; 1/6

(print-rat (add-rat one-third one-third))
;; 6/9


(defn gcd 
  "Computes the greatest common denominator of a and b"
  [a b]
  (if (= b 0)
    a
    (gcd b (mod a b))))

(defn make-rat [n d]
  (let [g (gcd n d)]
    (vector (/ n g) (/ d g))))

(print-rat (add-rat one-third one-third))
