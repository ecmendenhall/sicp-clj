;; Exercise 2.3

;; Let's build up the point representations to handle rectangles, too:

(defn make-point [x y]
  (vector x y))

(defn x-point [point]
  (first point))

(defn y-point [point]
  (second point))

(defn make-segment [start end]
  (vector start end))

(defn start-segment [segment]
  (first segment))

(defn end-segment [segment]
  (second segment))

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

;; First, let's represent rectangles as two segments:
(defn make-seg-rect [a b]
  (vector a b))

(defn width-seg [rect]
  (first rect))

(defn height-seg [rect]
  (second rect))

(defn rect-width [rect]
  (+ (x-point (start-segment (width-seg rect)))
     (x-point (end-segment (width-seg rect)))))

(defn rect-height [rect]
  (+ (y-point (start-segment (height-seg rect)))
     (y-point (end-segment (height-seg rect)))))

(defn area [rect]
  (* (rect-width rect) (rect-height rect)))

(defn perimeter [rect]
  (* 2 (+ (rect-width rect) (rect-height rect))))

(def seg-rect (make-seg-rect (make-segment (make-point 10 0)
                                           (make-point 0 0))
                             (make-segment (make-point 0 0)
                                           (make-point 0 10))))

(area seg-rect)
;; 100

(perimeter seg-rect)
;; 40

;; Alternatively, two corner points:
(defn make-point-rect [a b]
  (vector a b))

(defn first-corner [rect]
  (first rect))

(defn second-corner [rect]
  (second rect))

(defn rect-width [rect]
  (+ (x-point (first-corner rect))
     (x-point (second-corner rect))))

(defn rect-height [rect]
  (+ (y-point (first-corner rect))
     (y-point (second-corner rect))))

(def point-rect (make-point-rect (make-point 0 0)
                                 (make-point 10 10)))

;; Check it out! Since we're so great at abstraction, we can use the same area and
;; perimeter functions!

(area point-rect)
;; 100

(perimeter point-rect)
;; 40
