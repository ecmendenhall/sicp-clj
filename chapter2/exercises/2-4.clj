;; Exercise 2.4

;; Here's an alternate definition of vectors/pairs:

(defn vector [x y]  
  (fn [m] (m x y)))  
  
(defn first [z]  
  (z (fn [p q] p)))

(vector 1 2)
;; #<user$vector$fn__323 user$vector$fn__323@47a6ac39>

(first (vector 1 2))
;; 1

(vector 1 (vector (vector 2 3) 5))
;; #<user$vector$fn__323 user$vector$fn__323@1b189cbb>

(first (vector 1 (vector (vector 2 3) 5)))
;; 1

;; Let's show that this works for any objects x and y. Under
;; this new definition of a vector, the vector [x y] is:
(fn [m] (m x y))

;; Substituting into first:

(first (fn [m] (m x y)))
((fn [m] (m x y)) (fn [p q] p))
((fn [p q] p) x y)
x

;; So defining second is as easy as:
(defn second [z]
  (z (fn [p q] q)))
