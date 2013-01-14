;; Exercise 1.41

;; Let's write a function that applies the function it's given twice:
(defn double-it [f]
  (fn [x] (f (f x))))

((double-it inc) 1)
;; 3

(((double-it (double-it double-it)) inc) 5)
;; 21
