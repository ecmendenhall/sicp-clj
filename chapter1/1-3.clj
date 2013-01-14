;; Exercise 1.3

(defn square [x]
  (* x x))

(defn smaller [a b]
  (if (< a b) a b))

(defn smallest [a b c]
  (smaller (smaller a b) c))

(defn add-two-largest-squared-naive [a b c]
  (- (+ (square a)
        (square b)
        (square c))
     (square (smallest a b c))))

(defn add-two-largest-squared [a b c]
  (reduce + (map square (take-last 2 (sort [a b c])))))
