;; Exercise 1.33

;; Let's add a filter to the accumulator procedure:

(defn accumulate 
  [combiner null-value term a next-one b]
  (defn iter
    [a result]
    (if (> a b)
      result
      (recur (next-one a) (combiner (term a) result))))
  (iter a null-value))

(defn filtered-accumulate
  [filter-predicate combiner null-value term a next-one b]
  (defn iter
    [a result]
    (if (> a b)
      result
      (if (filter-predicate a)
        (recur (next-one a) (combiner (term a) result))
        (recur (next-one a) result))))
  (iter a null-value))

;; From our earlier code to test primality:
(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (+ test-divisor 1))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn divides? [a b]
  (= (mod b a) 0))

(defn square [x]
  (* x x))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn sum-squared-primes
  "Returns sum of the squares of the prime numbers in the interval a to b"
  [a b]
  (if (= 1 a)
    (filtered-accumulate prime? + 0 square 2 inc b)
    (filtered-accumulate prime? + 0 square a inc b)))

(defn relative-primes-product 
  "Returns the product of positive integers less than n that are relatively
  prime to n (i.e., all i < n such that GCD(i,n) = 1"
  [n]
  (defn gcd 
    "Computes the greatest common denominator of a and b"
    [a b]
    (if (= b 0)
      a
      (gcd b (mod a b))))
  (filtered-accumulate (fn [i] (= 1 (gcd i n))) 
                       *
                       1
                       identity
                       1
                       inc
                       n))
