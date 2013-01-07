;; Exercise 1.27

;; Carmichael numbers fool the Fermat test. Here are a few from footnote
;; 1.47:

(def carmichaels '(561 1105 1729 2465 2821 6601))

(defn square [x]
  (* x x))

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (mod (square (expmod base (/ exp 2) m)) m)
        :else (mod (* base (expmod base (- exp 1) m)) m)))

(defn congruent [a n]
  (= (expmod a n n) a))

(defn our-and [a b]
  (and a b))

(defn carmichael-test [n]
  (reduce our-and (map #(congruent % n) (range n))))

(map carmichael-test carmichaels)
;; (true true true true true true)
