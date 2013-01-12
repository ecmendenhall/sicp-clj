;; Exercise 1.35

;; The golden ratio phi is a fixed point of f(x) = 1 + (1/x):

(let [phi 1.6180339887]
  ((fn [x] (+ 1 (/ 1 x))) phi))
;; 1.618033988768953

;; So we can compute it using fixed-point:

(defn fixed-point [f first-guess]
    (defn close-enough? [v1 v2]

      (defn abs [x]
        (if (> 0 x) (- x) x))

      (< (abs (- v1 v2)) tolerance))

    (defn try-it [guess]
      (let [next-guess (f guess)]
        (if (close-enough? guess next-guess)
          next-guess
          (try-it next-guess))))
    
    (try-it first-guess))


(defn compute-phi [tolerance]
  (fixed-point (fn [x] (+ 1 (/ 1 x))) 1.0))

(compute-phi 0.000001)
;; 1.6180327868852458
