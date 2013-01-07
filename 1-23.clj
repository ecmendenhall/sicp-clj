;; Exercise 1.23

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn slow-smallest-divisor [n]
  (slow-find-divisor n 2))

(defn divides? [a b]
  (= (mod b a) 0))

(defn square [x]
  (* x x))

(defn our-next [x]
  (if (= 2 x) 3 (+ 2 x)))

(defn slow-find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (+ test-divisor 1))))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (our-next test-divisor))))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn slow-prime? [n]
  (= n (slow-smallest-divisor n)))

(defn timed-prime-test [n]
  (time (prime? n)))

(time (slow-prime? 17))
;; "Elapsed time: 0.104 msecs"

(time (prime? 17))
;; "Elapsed time: 0.082 msecs"
;; 1.27 times faster

(time (slow-prime? 137))
;; "Elapsed time: 0.073 msecs"

(time (prime? 137))
;; "Elapsed time: 0.041 msecs"
;; 1.78 times faster

(time (slow-prime? 5672383951))
;; "Elapsed time: 0.274 msecs"

(time (prime? 5672383951))
;; "Elapsed time: 0.133 msecs"
;; 2.06 times faster

;; It looks like the ratio approaches the value we expected (2) as the
;; input grows larger. This might be because we replaced some of the work
;; saved with the if test in our-next. For small input, the if test is
;; still a significant portion of the total number of steps. As the size
;; of the input increases, work saved by testing only odd divisors 
;; increases as a proportion of total work.
