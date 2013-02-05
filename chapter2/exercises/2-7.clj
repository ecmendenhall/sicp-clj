(defn add-interval [x y]
  (make-interval (+ (lower-bound x) (lower-bound y))
                 (+ (upper-bound x) (upper-bound y))))

(defn mul-interval [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
        p2 (* (lower-bound x) (upper-bound y))
        p3 (* (upper-bound x) (lower-bound y))
        p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))

(defn div-interval [x y]
  (mul-interval x
                (make-interval (/ 1.0 (upper-bound y))
                               (/ 1.0 (lower-bound y)))))

;; Exercise 2.7
(defn make-interval [a b]
  [a b])

(defn upper-bound [interval]
  (second interval))

(defn lower-bound [interval]
  (first interval))

;; Exercise 2.8
(defn sub-interval [x y]
  (make-interval (- (lower-bound x) (upper-bound y))
                 (- (upper-bound x) (lower-bound y))))

;; Exercise 2.9

(defn width [interval]
  (/ (- (upper-bound interval) (lower-bound interval))))

;; The width of the sum of two intervals is a function only of the
;; widths of the intervals being added:

(def interval-one (make-interval 10 12))
(def interval-two (make-interval 3 4))

(width interval-one)
;; 1/2

(width interval-two)
;; 1

(width (add-interval interval-one interval-two))
;; 1/3


