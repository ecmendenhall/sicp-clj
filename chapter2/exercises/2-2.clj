;; Exercise 2.2

;; We can build a data structure with constructors and selectors to represent points
;; and segments in a plane, too:

;; Points are just vector pairs:
(defn make-point [x y]
  (vector x y))

(defn x-point [point]
  (first point))

(defn y-point [point]
  (second point))

;; And segments are just vector pairs of points:
(defn make-segment [start end]
  (vector start end))

(defn start-segment [segment]
  (first segment))

(defn end-segment [segment]
  (second segment))

;; Here's a midpoint procedure:
(defn midpoint-segment [segment]
  (make-point (/ (+ (x-point (start-segment segment))
                    (x-point (end-segment segment)))
                 2)
              (/ (+ (y-point (start-segment segment))
                    (y-point (end-segment segment)))
                 2)))

(defn print-point [point]
  (print "(")
  (print (x-point point))
  (print ",")
  (print (y-point point))
  (print ")")
  (println))

(print-point (midpoint-segment (make-segment (make-point 0 0) (make-point 10 10))))
