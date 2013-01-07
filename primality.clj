;; Section 1.2.6: Testing Primality

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn divides? [a b]
  (= (mod b a) 0))

(defn square [x]
  (* x x))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (+ test-divisor 1))))

(defn prime? [n]
  (= n (smallest-divisor n)))

;; Now something more complicated.
;;
;; Fermat's Little Theorem: If n is a prime number and a is any 
;; positive integer less than n, then a raised to the nth power is 
;; congruent to a modulo n. Two numbers are congruent modulo n if they
;; both have the same remainder when divided by n. The remainder of a
;; number a when divided by n is also referred to as the remainder of
;; a modulo n, or simply as a modulo n.
;;
;; n = 7 
;; a = 3
;;
;; a^n = 2187
;;
;; (mod 3 7)    = 3
;; (mod 2187 7) = 3
;;
;; We can use this to write a pretty good prime test, because most 
;; numbers a < n won't have the same relation. Something like this:
;; Given a number n, pick a number less than n and compute a^n mod
;; n. If the result isn't a, n is definitely not prime. If it's equal
;; to a chances are good that n is prime. Now try again with another
;; number a < n. If it returns the same result, we can be a little
;; more sure that n is prime. (Maybe).

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
