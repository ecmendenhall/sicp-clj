;; Exercise 1.24

(defn square [x]
  (* x x))

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (mod (square (expmod base (/ exp 2) m)) m)
        :else (mod (* base (expmod base (- exp 1) m)) m)))

(defn fermat-test [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (try-it (+ 1 (rand-int (- n 1)))))

(defn fast-prime? [n times]
  (cond (= times 0) true
        (fermat-test n) (fast-prime? n (- times 1))
        :else false))

;; Let's try timing the Fermat test algorithm. It has O(log n) growth,
;; so testing primes near 1000 should only take three times as long as 
;; primes near ten. We'll test them each 10 times. 

(defn timed-prime-test [n]
  (time (fast-prime? n 1000)))

;; Primes from Exercise 1.22:
;; (1009 1013 1019)
;; (10007 10009 10037)
;; (100003 100019 100043)
;; (1000003 1000033 1000037)

(filter #(not= (type %) java.lang.Boolean)
        (map timed-prime-test '(1009    1013    1019 
                                10007   10009   10037 
                                100003  100019  100043 
                                1000003 1000033 1000037)))

;; 1009   ("Elapsed time: 0.419 msecs"
;; 1013    "Elapsed time: 0.182 msecs"
;; 1019    "Elapsed time: 0.148 msecs"
;; 10007   "Elapsed time: 0.298 msecs"
;; 10009   "Elapsed time: 0.262 msecs"
;; 10037   "Elapsed time: 0.167 msecs"
;; 100003  "Elapsed time: 0.185 msecs"
;; 100019  "Elapsed time: 0.245 msecs"
;; 100043  "Elapsed time: 0.339 msecs"
;; 1000003 "Elapsed time: 0.348 msecs"
;; 1000033 "Elapsed time: 0.352 msecs"
;; 1000037 "Elapsed time: 0.373 msecs")
