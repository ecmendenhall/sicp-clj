;; Exercise 1.22

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

;; This exercise includes a Scheme procedure that uses the runtime
;; primitive to measure the time elapsed in a procedure:
;;
;; (define (timed-prime-test n)
;;   (newline)
;;   (display n)
;;   (start-prime-test n (runtime))
;;
;; (define (start-prime-test n start-time)
;;   (if (prime? n)
;;       (report-prime (- (runtime)
;;                        (start-time)))))
;;
;; (define (report-prime elapsed-time)
;;   (display " *** ")
;;   (display elapsed-time))
;;
;; Clojure doesn't have a runtime primitive, but the time macro, a 
;; clojure.core builtin, makes this even easier.

(defn timed-prime-test [n]
  (time (prime? n)))

(timed-prime-test 17)
;; "Elapsed time: 0.029 msecs"
;; true

(defn search-for-primes 
  "Returns the first 3 primes above the given value."
  [above]
  (take 3 (keep #(if (prime? %) %) 
                (filter odd? (iterate inc (+ 1 above))))))

;; Things I learned translating this to Clojure:
;; It's hard to create an infinite lazy sequence with a step using range.
;; (filter step-function (iterate inc start)) works, though.
;; The syntax of #(if (prime? %) %) is shorthand for an anonymous lambda.

;; Now let's test the runtime, which should be O(sqrt(n)). 

(time (search-for-primes 1000))
;; "Elapsed time: 0.073 msecs"
;; (1009 1013 1019)

(time (search-for-primes 10000))
;; "Elapsed time: 0.042 msecs"
;; (10007 10009 10037)

(time (search-for-primes 100000))
;; "Elapsed time: 0.043 msecs"
;; (100003 100019 100043)

(time (search-for-primes 1000000))
;; "Elapsed time: 0.051 msecs"
;; (1000003 1000033 1000037)
