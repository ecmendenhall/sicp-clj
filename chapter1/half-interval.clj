;; Section 1.3.3: Half-interval method

(defn half-interval [f a b]

  (defn search
    "Returns the zero of function f between neg-point and pos-point."
    [f neg-point pos-point]
    
    (defn average [a b]
      (/ (+ a b) 2))

    (defn close-enough? [x y]
      (defn abs [x]
        (if (> 0 x) (- x) x))
      (> (abs (- x y)) 0.001 x))

    (let [midpoint (average neg-point pos-point)]
      (if (close-enough? neg-point pos-point)
        midpoint
        (let [test-value (f midpoint)]
          (cond (pos? test-value) (search f neg-point midpoint)
                (neg? test-value) (search f midpoint pos-point)
                :else midpoint)))))

  (let [a-value (f a)
        b-value (f b)]
    (cond (and (neg? a-value) (pos? b-value)) (search f a b)
          (and (neg? b-value) (pos? a-value)) (search f b a)
          :else (println "Values are not of opposite sign!"))))
