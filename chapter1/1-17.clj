;; Exercise 1.17

(defn mult [a b]
  (if (= b 0)
    0
    (+ a (mult a (- b 1)))))

(defn double-int [a]
  (* a 2))

(defn halve-int [a]
  (/ a 2))

;; a * b = a0 + a1 + a2 + ... + ab

;; 2 * 8 = 2 + 2 + 2 + 2 + 2 + 2 + 2 + 2
;;       = (double-int (+ 2 2 2 2))
;;       = (double-int (double-int(+ 2 2))
;;       = (double-int (double-int (double-int 2)))
;;
;; 3 * 7 = 3 + 3 + 3 + 3 + 3 + 3 + 3
;;       = (+ 3 (+ 3 3 3 3 3 3))
;;       = (+ 3 (double-int(+ 3 3 3)))
;;       = (+ 3 (double-int(+ 3 (double-int 3))))

(defn fast-mult [a b]
  (cond (= b 0) 0
        (even? b) (fast-mult (double-int a) (halve-int b))
        :else (+ a (fast-mult a (- b 1)))))
