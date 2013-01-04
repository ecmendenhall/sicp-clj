;; Exercise 1.19

;; Let's write a clever fibonacci algorithm!
;; Remember the way the state variables transform when generating the next number:
;;
;; a <- a + b
;; b <- a
;;
;; Let's abstract this. Call this transformation T. Starting with a=1 and b=0,
;; applying T n times produces fib(n+1) and fib(n):
;;
;; a = 1  
;; b = 0
;;
;; Apply T: a <- 1 + 0, b <- 1
;; a = 1
;; b = 1
;;
;; Apply T: a <- 1 + 1, b <- 1
;; a = 2
;; b = 1
;;
;; Apply T: a <- 2 + 1, b <- 2
;; a = 3
;; b = 2
;;
;; Apply T: a <- 3 + 2, b <- 3
;; a = 5
;; b = 3
;;
;; etc. 
;;
;; Okay, I get it now.
;; (It's important that this transformation is simultaneous! Assigning the
;; transformed value of a to the variable b will mess everything up!)
;;
;; Now, let's abstract T even more: consider it the special case p=0 q=1 in a
;; family of transformations that transform the pair (a,b) according to:
;;
;; a <- bq + aq + ap
;; b <- bp + aq
;;
;; Ugh. But okay, that means our original transformation is:
;;
;; a <- b(1) + a(1) + a(0) = b + a
;; b <- b(0) + a(1) + a(0) = a
;; 
;; That's not so bad.
;;
;; Now here's another interesting thing about this transformation: applying it twice
;; has the same effect as a single transformation of the same form, with different
;; values of p and q. That means we can square transformations, and reduce a lot of
;; work the same way we used succesive squaring to calculate exponents. So what
;; happens when we apply the transformation twice?
;;
;; First transformation:
;;
;; a = a
;; b = b
;;
;; Apply T: 
;; a <- bq + aq + ap
;; b <- bp + aq
;;
;; a = bq + aq + ap
;; b = bp + aq
;;
;; Apply T:
;; a <- (bp + aq)q + (bq + aq + ap)q + (bq + aq + ap)p
;; b <- (bp + aq)p + (bq + aq + ap)q
;;
;; a = bpq + aq^2 + bq^2 + aq^2 + apq + bqp + aqp + ap^2
;;   = 2bpq + 2apq + 2aq^2 + bq^2 + ap^2
;;
;; b = bp^2 + aqp + bq^2 + aq^2 + apq
;;   = 2apq + bp^2 + bq^2 + aq^2
;;
;; But we want these in terms of a and b, to see how the transformation works on 
;; p and q:
;;
;; a = (2pq + 2q^2 + p^2)a  + (2pq + q^2)b
;; b = b(p^2 + q^2) + a(2pq + q^2)
;;
;; Look at the transformation rule for b: b <- bp + aq. It looks similar to b
;; above! 
;;
;; if b = bp' + aq' after the second transformation,
;;
;; p' = (p^2 + q^2) 
;; q' = (2pq + q^2)
;;
;; in terms of the original values for p and q.
;;
;; Because I'm bad at math and it helps to write everything out, Let's check that
;; these values work for a, as well.
;;
;; if a <- bq' + aq' + ap'
;;
;; a = b(2pq + q^2) + a(2pq + q^2) + a(p^2 + q^2)
;;   = 2pqb + bq^2 + 2pqa + aq^2 + ap^2 + aq^2
;;
;; And here's what we had above after applying T twice:
;;
;; a = 2bpq + 2apq + 2aq^2 + bq^2 + ap^2
;;   = 2pqb + bq^2 + 2pqa + aq^2 + ap^2 + aq^2
;;
;; Great! I didn't mess it up. Now we can start the problem, by completing the
;; given procedure.

(defn fib [n]
  (defn fib-iter [a b p q counter]
    (cond (= counter 0)   b
          (even? counter)
            ;; Squared transformation: compute p' and q'. 
            (fib-iter a
                      b
                      (+ (* p p) (* q q))   ;; p' = (p^2 + q^2)
                      (+ (* 2 p q) (* q q)) ;; q' = (2pq + q^2)
                      (/ counter 2))
          :else
            ;; Normal transformation: compute p and q according to the normal rule.
            (fib-iter (+ (* b q)
                         (* a q)
                         (* a p)) ;; bq + aq + ap
                      (+ (* b p)  ;; bp + aq
                         (* a q))
                      p
                      q
                      (- counter 1))))
  (fib-iter 1 0 0 1 n))

;; Hey, that was pretty easy! Thanks, math!
