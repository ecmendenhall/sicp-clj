;; Exercise 1.11

(defn recursive [n]
  (if (< n 3)
    n
    (+ (recursive (- n 1))
       (* 2 (recursive (- n 2)))
       (* 3 (recursive (- n 3))))))

(defn iterative [n]
  (defn iter [a b c counter]
    (if (= 0 counter)
      a
      (iter b
            c
            (+ c (* 2 b) (* 3 a))
            (- counter 1))))
  (iter 0 1 2 n))
