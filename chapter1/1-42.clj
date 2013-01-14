;; Exercise 1.42

;; Let's write a composition function!
(defn compose [f g]
  (fn [x] (f (g x))))

(defn square [x]
  (* x x))

((compose square inc) 6)
;; 49
