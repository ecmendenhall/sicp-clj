;; Exercise 1.28

;; The Miller-Rabin test is an unfoolable Fermat test. Here's how it works:
;; Pick a random number a < n and raise a to the (n-1)st power mod n using
;; expmod. At the squaring step inside expmod, check if we've discovered a
;; number not equal to 1 or (n-1) whose square is equal to 1 modulo n. If
;; such a square root exists, n is not prime.

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (mod (square (expmod base (/ exp 2) m)) m)
        :else (mod (* base (expmod base (- exp 1) m)) m)))

(defn fermat-test [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (try-it (+ 1 (rand-int (- n 1)))))

(defn square [x]
  (* x x))

(defn mr-square [x m]
  (if (and (not= x 1)
           (not= x (- m 1))
           (= (mod (square x) m) 1))
    0
    (mod (square x) m)))

(defn mr-expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (mr-square (mr-expmod base (/ exp 2) m) m)
        :else (mod (* base (mr-expmod base (- exp 1) m)) m)))

(defn miller-rabin-test [n]
  (defn try-it [a]
    (= (mr-expmod a (- n 1) n) 1))
  (try-it (+ 1 (rand-int (- n 1)))))

(def carmichaels '(561 1105 1729 2465 2821 6601))

(map miller-rabin-test carmichaels)
;; (false false false false true false)

;; Wait, WHAT?! I thought this was unfoolable! 
;; Not quite: it's unfoolable only because at least half the numbers a < n
;; will reveal a nontrivial square root. So we have to test more than 1!
;; Testing just one number a < n can return false positives. But in theory,
;; we could test all the numbers a < n to be absolutely sure. Let's plug 
;; it into fast-prime? so we can test multiple times.

(defn fast-prime? [n times]
  (cond (= times 0) true
        (miller-rabin-test n) (fast-prime? n (- times 1))
        :else false))

(map #(fast-prime? % 5) carmichaels)
;; (false false false false false false)

(map #(fast-prime? % 5) carmichaels)
;; (false false false false false false)

(map #(fast-prime? % 5) carmichaels)
;; (false false false false false false)

(map #(fast-prime? % 5) carmichaels)
;; (false false false false false false)

;; That's more like it!
