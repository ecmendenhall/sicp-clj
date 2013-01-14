;; Exercise 1.30

;; This procedure is recursive...
(defn sum-sequence
  [term a next-one b]
  (if (> a b)
    0
    (+' (term a)
        (sum-sequence term (next-one a) next-one b))))

;; But it can be iterative with an accumulator.
(defn sum-sequence
  [term a next-one b]
  (defn iter
    [a result]
    (if (> a b)
      result
      (iter (next-one a) (+ a result))))
  (iter a 0))
