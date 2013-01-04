;; Exercise 1.18

(defn mult [a b]
  (if (= b 0)
    0
    (+ a (mult a (- b 1)))))

(defn double-int [a]
  (* a 2))

(defn halve-int [a]
  (/ a 2))

;; Problem: make this iterative
(defn fast-mult [a b]
  (cond (= b 0) 0
        (even? b) (fast-mult (double-int a) (halve-int b))
        :else (+ a (fast-mult a (- b 1)))))

(defn fast-mult-iter [a b]
  (defn mult-iter [a b c]
    (cond (= b 0) c
          (even? b) (mult-iter (double-int a) (halve-int b) c)
          :else (mult-iter a (- b 1) (+ a c))))
  (mult-iter a b 0))

;; It's the Russian Peasant algorithm!
;; Here's some scratchwork where I tested it by hand:
;;
;;  a  b  c
;; ---------
;;  5  4  0
;; 10  2  0
;; 20  1  0
;; 20  0 20

;;  7  3  0
;;  7  2  7
;; 14  1  7
;; 14  0 21

;; 10 10  0
;; 20  5  0
;; 20  4 20
;; 40  2 20
;; 80  1 20
;; 80  0 100
