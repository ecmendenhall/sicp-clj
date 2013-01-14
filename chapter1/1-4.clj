;; Exercise 1.4

(defn a-plus-abs-b [a b]
  ((if (> b 0) + -) a b))

;; "Our model of evaluation allows for combinations whose operators
;; are compound expressions." That is, the operator in this function
;; is a compound if expression, which reduces by replacement to the
;; operator + or -. If b is positive, the function returns the sum of 
;; a and b. If b is negative, it returns a minus b: in effect, a + |b|.
