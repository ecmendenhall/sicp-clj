;; Exercise 1.25

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

(fast-prime? 137 5)
;; true

;; Now, let's see what happens when we rewrite expmod to use fast-expt:

(defn fast-expt [b n]
  (cond (= n 0) 1
        (even? n) (square (fast-expt b (/ n 2)))
        :else (*' b (fast-expt b (-' n 1)))))

(defn expmod [base exp m]
  (mod (fast-expt base exp) m))

(fast-prime? 137 5)
;; ArithmeticException integer overflow  
;; clojure.lang.Numbers.throwIntOverflow (Numbers.java:1388)

;; The original expmod procedure is more or less like fast-expt, but it
;; does modulo arithmetic in place. It recursively multiplies the result
;; of expmod, *which is always something modulo m*. This means it won't
;; grow very large.
;;
;; When expmod is rewritten to use fast-expt, it calculates the full
;; exponent for each step, and then finds the remainder. This means
;; fast-expt has to calculate big, slow numbers, which take lots of time
;; and space. So much, in fact, that testing the primality of 137 throws an
;; integer overflow.

(clojure-version)
;; "1.4.0"

(Integer/MAX_VALUE)
;; 2147483647

(Long/MAX_VALUE)
;; 9223372036854775807

;; Clojure 1.3 and forward will not automatically convert integers to Long
;; or BigInts when they exceed the maximum size, which is why it throws
;; an ArithmeticException. This ensures that primitive arithmetic happens
;; the way it's supposed to in Java, which is under the hood. Adding an 
;; apostrophe to basic math operations (+', -', *', inc', dec') will
;; automatically promote integers to BigInts. Doing so here allows
;; fast-prime? to finish without throwing an exception, but it takes about
;; twice as long.
