;; Exercise 1.1

10
;; 10

(+ 5 3 4)
;; 12

(- 9 1)
;; 8

(/ 6 2)
;; 3

(+ (* 2 4) (- 4 6))
;; 6

(def a 5)
;; #'user/a

(def b (+ a 1))
;; #'user/b

(+ a b (* a b))
;; 41

(= a b)
;; false

(if (and (> b a) (< b (* a b)))
  b
  a)
;; 6

(cond
  (= a 4) 6
  (= b 4) (+ 6 7 a)
  :else 25)
;; 25

(+ 2 (if (> b a) b a))
;; 8

(* (cond
     (> a b) a
     (< a b) b
     :else -1)
   (+ a 1))
;; 36
