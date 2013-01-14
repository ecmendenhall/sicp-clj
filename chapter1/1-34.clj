;; Exercise 1.34

(defn f [g] (g 2))

(f (fn [x] (* x x)))
;; 4

(f (fn [z] (* z (+ z 1))))
;; 6

;; Let's try something perverse!
(f f)
(f 2)
(2 2)
;; ClassCastException java.lang.Long cannot be cast to clojure.lang.IFn...
;; You can't evaluate (2 2)! Stop doing that! 
