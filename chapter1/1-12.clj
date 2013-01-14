;; Exercise 1.12

(defn pascal 
  "Returns the nth row of Pascal's triangle as a vector."
  [n]
  (cond (= n 1) [1]
        (= n 2) [1 1]
        :else (into [] 
                    (cons 1 
                          (conj (into [] 
                                      (for [item (partition 2 1 (pascal (- n 1)))]
                                           (reduce + item))) 
                                1)))))

(defn print-rows
  "Prints rows 1 to n of Pascal's triangle."
  [n]
  (doseq [item (map pascal (range 1 (+ n 1)))] (println item)))
