;; Exercise 1.40

(defn newtons-method [g guess]

  (defn newton-transform [g]
    (fn [x]
      (- x (/ (g x)
              ((deriv g) x)))))

  (fixed-point (newton-transform g) guess))

(defn fixed-point [f first-guess]
  (let [tolerance 0.00001]

    (defn close-enough? [v1 v2]

      (defn abs [x]
        (if (> 0 x) (- x) x))

      (< (abs (- v1 v2)) tolerance))

    (defn try-it [guess]
      (let [next-guess (f guess)]
        (if (close-enough? guess next-guess)
          next-guess
          (try-it next-guess))))
    
    (try-it first-guess)))

(defn square [x]
  (* x x))

(defn cube [x]
  (* x x x))

(defn cubic [a b c]
  (fn [x] (+ (cube x) (* a (square x)) (* b x) c)))

(newtons-method (cubic 2 (- 3) 1) 5.0)
