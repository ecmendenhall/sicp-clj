;; Exercise 1.44

;; If f is a function and dx is some small number, the smoothed version of
;; f is the function whose value at point x is the average of f(x - dx),
;; f(x), and f(x + dx).

(defn compose [f g]
  (fn [x] (f (g x))))

(defn repeated [f n]
  (if (= n 1)
    f
    (compose f (repeated f (- n 1)))))

(defn smooth [f]
 (let [dx 0.00001] 
  (fn [x] (/ (+ (f (- x dx)) (f x) (f (+ x dx))) 3))))

(defn n-fold-smooth [f n]
  (repeated (smooth f) n))
