;; Exercise 1.15

(defn abs [x] (if (< x 0) (- x) x))
(defn cube [x] (* x x x))
(defn p [x]
  (println "p called!") 
  (- (* 3 x) (* 4 (cube x))))
(defn sine [angle]
  (if (not (> (abs angle) 0.1))
    angle
    (p (sine (/ angle 3.0)))))

;; p is called 5 times: It takes 5 divisions before |angle| < 0.1.

;; The approximation function p nests as the angle is divided, e.g.:

(p (sine (/ 12.15 3)))
(p (p (sine (/ 4.05 3))))
(p (p (p (sine (/ 1.35 3)))))

;; ...so the space and steps required follow the same pattern.
;; To figure out exactly what it is, let's think about how the number of steps
;; change as the size of the angle increases. An additional division (and call
;; to p) is necessary when the input angle increases by a factor of 3. That's 
;; a logarithmic relationship, so this is O(log n).
