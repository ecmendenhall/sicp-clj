;; Exercise 1.26

;; Here's the original Fermat test code:

(defn square [n]
  (*' n n))

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (mod (square (expmod base (/ exp 2) m)) m)
        :else (mod (*' base (expmod base (-' exp 1) m)) m)))

(defn fermat-test [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (try-it (+' 1 (rand-int (-' n 1)))))

(defn fast-prime? [n times]
  (cond (= times 0) true
        (fermat-test n) (fast-prime? n (-' times 1))
        :else false))

;; Here's how Louis Reasoner rewrote expmod:

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (mod (* (expmod base (/ exp 2) m)
                            (expmod base (/ exp 2) m)) m)
        :else (mod (*' base (expmod base (-' exp 1) m)) m)))

;; Let's see what happens:

(expmod 2 8 3)

(cond (= 3 0) 1
      (even? 8) (mod (* (expmod 2 (/ 8 2) 3)
                        (expmod 2 (/ 8 2) 3)) 3)
      :else (mod (*' 2 (expmod 2 (-' 8 1) 3)) 3))

(mod (* (expmod 2 (/ 8 2) 3)
        (expmod 2 (/ 8 2) 3)) 3)

(mod (* (expmod 2 4 3)
        (expmod 2 4 3)) 3)

(mod (* ((cond (= 4 0) 1
        (even? 4) (mod (* (expmod 2 (/ 4 2) 3)
                          (expmod 2 (/ 4 2) 3)) 3)
        :else (mod (*' base (expmod 2 (-' 4 1) 3)) 3)))
        ((cond (= 4 0) 1
        (even? 4) (mod (* (expmod 2 (/ 4 2) 3)
                          (expmod 2 (/ 4 2) 3)) 3)
        :else (mod (*' 2 (expmod 2 (-' 4 1) 3)) 3)))) 3)

(mod (* (mod (* (expmod 2 (/ 4 2) 3)
                (expmod 2 (/ 4 2) 3)) 3)
        (mod (* (expmod 2 (/ 4 2) 3)
                (expmod 2 (/ 4 2) 3)) 3)) 3)

(mod (* (mod (* (expmod 2 2 3)
                (expmod 2 2 3)) 3)
        (mod (* (expmod 2 2 3)
                (expmod 2 2 3)) 3)) 3)

;; Uh-oh: This process is tree-recursive. Every time expmod is called,
;; it branches into two new calls to expmod. That makes an O(log n) process
;; an O(n) one: exponential growth negates the log n, leaving a linear
;; time process.
