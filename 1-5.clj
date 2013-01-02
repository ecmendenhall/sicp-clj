;; Exercise 1.5

(defn p [] (p))

(defn our-test [x y]
  (if (= x 0) 0 y))

(our-test 0 (p))

;; Applicative-order evaluation (like in Clojure)
;; will loop indefinitely:
;;
;; (our-test 0 (p))
;; Replace (p) with (p)
;; (our-test 0 (p))
;; Replace (p) with (p)
;; 
;; etc. In Clojure, this eventually throws a StackOverflowError.

;; Normal-order evaluation will return 0:
;; (our-test 0 (p))
;; (if (= 0 0) 0 (p))
;; (if true 0 (p))
;; 0
;; (p) is never evaluated, and so does not loop.
;;
;; However, evaluating 

(our-test 0 p)

;; Will return 0, since p is never called.
