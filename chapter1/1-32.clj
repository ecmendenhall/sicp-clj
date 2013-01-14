;; Exercise 1.32

;; The sum and product procedures from earlier exercises...

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

;; Can be abstracted further. Ther're really both special cases of an
;; accumulate function that combines a collection of terms using some
;; accumulation function:
;;
;; (fn [combiner null-value term a next-one b] ... )
;;
;; And it only takes two changes:

(defn accumulate 
  [combiner null-value term a next-one b]
  (defn iter
    [a result]
    (if (> a b)
      result
      (recur (next-one a) (combiner (term a) result))))
  (iter a null-value))

;; Here's a recursive version, but the iterative one should be better
;; optimized for Clojure.

(defn accumulate-recursive
  [combiner null-value term a next-one b]
  (if (> a b)
    null-value
    (combiner (term a)
              (accumulate-recursive combiner
                                    null-value
                                    term 
                                    (next-one a) 
                                    next-one 
                                    b))))

;; The recursive version blows the stack
(time (accumulate-recursive *' 1 identity 1 inc 100000))
;; StackOverflowError   user/accumulate-recursive (NO_SOURCE_FILE:9)

;; But the iterative one doesn't:
(time (accumulate *' 1 identity 1 inc 100000))
;; "Elapsed time: 9258.812 msecs"
