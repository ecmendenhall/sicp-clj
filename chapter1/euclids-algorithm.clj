(defn gcd 
  "Computes the greatest common denominator of a and b"
  [a b]
  (if (= b 0)
    a
    (gcd b (mod a b))))

;; Lame's theorem: If Euclid's algorithm requires k steps to compute the
;; GCD of some pair, then thesmaller number in the pair must be greater
;; than or equal to the kth Fibonacci number.
