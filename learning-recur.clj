;; Learning recur
;; Or: Why I kept blowing the stack trying to write Scheme in Clojure
;; Or: Time to get used to using accumulators

;; Here are the iterative accumulator procedures from section 1.3.1:
(defn sum-sequence
  [term a next-one b]
  (defn iter
    [a result]
    (if (> a b)
      result
      (iter (next-one a) (+ a result))))
  (iter a 0))

(defn sequence-product
  [term a next-one b]
  (defn iter
    [a result]
    (if (> a b)
      result
      (iter (next-one a) (*' (term a) result))))
  (iter a 1))

;; Both iter functions recursively call themselves. But Clojure doesn't
;; do tail call optimization. Even if the recursive calls are in tail
;; position, the compiler won't recognize it. But the special form 'recur'
;; is clever and doesn't consume stack, so it's usually just as good.
;; If your recursive calls are already in tail position, just change
;; the function call to the special form 'recur'.
;;
;; More here: http://www.gettingclojure.com/cookbook:functional-programming

(time (sum-sequence #(* % %) 1000 inc 100000))
;; StackOverflowError   clojure.lang.Numbers.gt (Numbers.java:227)

(time (sequence-product identity 1 inc 1000))
;; "Elapsed time: 3.193 msecs"

(defn sum-sequence
  [term a next-one b]
  (defn iter
    [a result]
    (if (> a b)
      result
      (recur (next-one a) (+ a result))))
  (iter a 0))

(defn sequence-product
  [term a next-one b]
  (defn iter
    [a result]
    (if (> a b)
      result
      (recur (next-one a) (*' (term a) result))))
  (iter a 1))

(time (sum-sequence #(* % %) 1000 inc 100000))
;; "Elapsed time: 24.336 msecs"
;; 4999550500

(time (sequence-product identity 1 inc 1000))
;; "Elapsed time: 1.437 msecs"
