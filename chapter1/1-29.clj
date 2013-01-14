;; Exercise 1.29

;; Procedures from section 1.3.1:

(defn cube [x]
  (* x x x))

(defn sum-sequence
  [term a next-one b]
  (if (> a b)
    0
    (+' (term a)
       (sum-sequence term (next-one a) next-one b))))

(defn integral
  "Approximates the integral of the function f between a and b."
  [f a b dx]
  (defn add-dx [x] (+ x dx))
  (* (sum-sequence f (+ a (/ dx 2.0)) add-dx b) dx))

;; Simpson's rule is a better method of numerical integration than the
;; integral function above. It approximates the integral of function f 
;; between a and b as:
;;
;; (h/3) * (y0 + 4y1 + 2y2 + 4y3 + 2y4 + ... + 2yn-n + 4yn-1 + yn)
;;
;; where h = (b - a)/n for some even integer n
;; and  yk = f(a + kh)
;;
;; Let's use this to write a better integral procedure.

(defn simpson-integral
  "Approximates the integral of the function f between a and b."
  [f a b n]
  (if (= 1 (mod n 2)) 
    (println "n must be even.")
    (let [h (/ (- b a) n)]
      (defn simpson-sequence [k]
        (let [ysubk (f (+ a (* h k)))]
          (cond (= k 0) ysubk
                (even? k) (* 2 ysubk)
                :else (* 4 ysubk))))
       (* (/ h 3) (sum-sequence simpson-sequence 0 inc n)))))

(integral cube 0 1 0.01)
;; 0.24998750000000042

(simpson-integral cube 0 1 10.0)
;; 0.2833333333333334

(simpson-integral cube 0 1 100.0)
;; 0.25333333333333324

(simpson-integral cube 0 1 1000.0)
;; 0.2503333333333336

;; It works! But the sequence needs a lot of terms (i.e., n must be pretty
;; high) to approximate the real answer (.25) better than the old procedure.
