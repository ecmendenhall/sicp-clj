;; Exercise 1.9

(defn inc [n]
  (+ n 1))

(defn dec [n]
  (- n 1))

(defn add1 [a b]
  (if (= a 0) b (inc (add1 (dec a) b))))

;; add1 is a recursive process. All the expressions below evaluate to 9.
(add1 4 5)
(if (= 4 0) 5 (inc (add1 (dec 4) 5)))
(inc (add1 (dec 4) 5))
(inc (add1 3 5))
(inc (if (= 3 0) 5 (inc (add1 (dec 3) 5))))
(inc (inc (add1 (dec 3) 5)))
(inc (inc (add1 2 5)))
(inc (inc (if (= 2 0) 5 (inc (add1 (dec 2) 5)))))
(inc (inc (inc (add1 (dec 2) 5))))
(inc (inc (inc (add1 1 5))))
(inc (inc (inc (if (= 1 0) 5 (inc (add1 (dec 1) 5))))))
(inc (inc (inc (inc (add1 (dec 1) 5)))))
(inc (inc (inc (inc (add1 0 5)))))
(inc (inc (inc (inc (if (= 0 0) 5 (inc (add1 (dec 0) 5)))))))
(inc (inc (inc (inc 5))))
(inc (inc (inc 6)))
(inc (inc 7))
(inc 8)
9

(defn add2 [a b]
  (if (= a 0) b (add2 (dec a) (inc b))))

;; add2 is an iterative process.
(add2 4 5)
(if (= 4 0) 5 (add2 (dec 4) (inc 5)))
(add2 (dec 4) (inc 5))
(add2 3 6)
(if (= 3 0) 6 (add2 (dec 3) (inc 6)))
(add2 (dec 3) (inc 6))
(add2 2 7)
(if (= 2 0) 7 (add2 (dec 2) (inc 7)))
(add2 (dec 2) (inc 7))
(add2 1 8)
(if (= 1 0) 8 (add2 (dec 1) (inc 8)))
(add2 (dec 1) (inc 8))
(add2 0 9)
(if (= 0 0) 9 (add2 (dec 0) (inc 9)))
9
