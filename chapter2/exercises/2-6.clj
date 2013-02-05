;; Exercise 2.6

;; Let's represent pairs as Church numerals!

(def zero (fn [f] (fn [x] x)))

(def add-1 [n]
  (fn [f] (fn [x] (f ((n f) x)))))

;; So how would we represent one?
(add-1 zero)                       
(add-1 (fn [f] (fn [x] x)))        
(fn [f] (fn [x] (f (((fn [f] (fn [x] x)) f) x)))) 
(fn [f] (fn [x] (f (((fn [x] x) x))))) 
(fn [f] (fn [x] (f ((x)))))
(fn [f] (fn [x] (f x))) ; Check it out, it's the Church numeral for one!

;; \n.\f.\x.(f ((n f) x)) \f.\x.x
;; [\f.\x.x/n](\f.\x.(f ((n f) x)))
;; \f.\x.(f ((\f.\x.x f) x))
;; \f.\x.(f (\x.x x))
;; \f.\x.f(x)

;; Two, then, is
(fn [f] (fn [x] (f (f x))))

;; How about addition?
;; We have a successor function add-1, and numbers, so adding a and b is just
;; applying the successor function a times to b:

(fn [a] (fn [b] (fn [f] (fn [x] ((a f) ((b f) x)))))) ; \a.\b.\f.\x.a f (b f x)
