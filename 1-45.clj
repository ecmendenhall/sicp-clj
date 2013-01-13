;; Exercise 1.45

(defn average-damp [f]
  (fn [x] (/ (+ x (f x)) 2)))

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

(defn compose [f g]
  (fn [x] (f (g x))))

(defn repeated [f n]
  (if (= n 1)
    f
    (compose f (repeated f (- n 1)))))

(defn fixed-point-of-transform [g transform guess]
  (fixed-point (transform g) guess))

(defn sqrt [x]
  (fixed-point-of-transform
    (fn [y] (/ x y))
    average-damp
    1.0))

(defn cubert [x]
  (fixed-point-of-transform
    (fn [y] (/ x (* y y)))
    average-damp
    1.0))

(defn fourth-rt [x]
  (fixed-point-of-transform
    (fn [y] (/ x (* y y y)))
    (repeated average-damp 2)
    1.0))

(defn fifth-rt [x]
  (fixed-point-of-transform
    (fn [y] (/ x (* y y y y)))
    (repeated average-damp 2)
    1.0))

(defn sixth-rt [x]
  (fixed-point-of-transform
    (fn [y] (/ x (* y y y y y)))
    (repeated average-damp 2)
    1.0))

(defn seventh-rt [x]
  (fixed-point-of-transform
    (fn [y] (/ x (* y y y y y y)))
    (repeated average-damp 2)
    1.0))


(sqrt 4)
;; 2.000000000000002

(cubert 8)
;; 1.9999981824788517

(fourth-rt 16)
;; 2.0000000000021965

(fifth-rt 32)
;; 2.0000015129957607

(sixth-rt 64)
;; 2.0000029334662086

(seventh-rt 128)
;; 2.0000035538623377

;; Wait, I can be lazier about this:

(defn exp [x n]
  (reduce * (repeat n x)))

(defn nth-rt [x pow n-damps]
  (fixed-point-of-transform
    (fn [y] (/ x 
               (exp y (- pow 1))))
    (repeated average-damp n-damps)
    1.0))

(nth-rt 128 7 2)
;; 2.0000035538623377

(nth-rt 256 8 2)
;; StackOverflowError   clojure.lang.LazySeq.sval (LazySeq.java:42)
;; Hmm... did my exp function blow the stack? That seems unlikely, but
;; I'll try another one:

(defn exp [x n]
  (defn iter [acc n]
    (if (= 0 n) acc
        (recur (* x acc) (- n 1))))
  (iter 1 n))

(nth-rt 256 8 2)
;; StackOverflowError   clojure.lang.RT.boundedLength (RT.java:1633)

;; Oh, hurr durr...i's the average damping. The fixed point function is
;; oscillating indefinitely.

(nth-rt 256 8 3)
;; 2.0000000000039666

;;  pow  n-damps
;; --------------
;;   2     1
;;   3     1
;;   4     2
;;   5     2
;;   6     2
;;   7     2
;;   8     3

(nth-rt (exp 2 16) 16 3)
;; StackOverflowError   clojure.lang.RT.boundedLength (RT.java:1633)

(nth-rt (exp 2 16) 16 4)
;; 2.0000000000769576

;; Okay, I get it. The nth root needs log2(n) dampings, rounded down to 
;; the nearest integer. I'm going to do this one the easy way:

(require '[clojure.contrib.generic.math-functions :as math])

(defn logn [n x]
  (/ (math/log x) (math/log n)))

(logn 2 16)
;; 4.0

;; So here's a simple nth-root function:

(defn nth-root [x n]
  (let [n-damps (int (math/floor (logn 2 n)))]
    (fixed-point-of-transform
      (fn [y] (/ x 
                 (exp y (- n 1))))
      (repeated average-damp n-damps)
      1.0)))

(nth-root 4294967296 32)
;; 2.000000000000006
