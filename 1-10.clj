;; Exercise 1.10

(defn a [x y]
  (cond (= y 0) 0
        (= x 0) (* 2 y)
        (= y 1) 2
        :else (a (- x 1)
                 (a x (- y 1)))))

(a 1 10)
;; 1024
(cond (= 10 0) 0
      (=  1 0) (* 2 10)
      (= 10 1) 2
      :else (a (- 1 1)
               (a 1 (- 10 1))))

(a (- 1 1) (a 1 (- 10 1)))

(a (- 1 1) (a 1 9))

(a (- 1 1) (cond (= 9 0) 0
                 (= 1 0) (* 2 9)
                 (= 9 1) 2
                 :else (a (- 1 1)
                       (a 1 (- 9 1)))))

(a (- 1 1) (a (- 1 1) (a 1 (- 9 1))))

(a (- 1 1) (a (- 1 1) (a 1 8)))

(a (- 1 1) 
   (a (- 1 1)
      (a (- 1 1) 
         (a 1 7))))

(a (- 1 1) 
   (a (- 1 1) 
      (a (- 1 1) 
         (a (- 1 1) 
            (a 1 6)))))
;; [...]

(a (- 1 1)
   (a (- 1 1)
      (a (- 1 1)
         (a (- 1 1)
            (a (- 1 1)
               (a (- 1 1)
                  (a (- 1 1)
                     (a (- 1 1)
                        (a (- 1 1)
                           (a 1 1))))))))))

(a (- 1 1)
   (a (- 1 1)
      (a (- 1 1)
         (a (- 1 1)
            (a (- 1 1)
               (a (- 1 1)
                  (a (- 1 1)
                     (a (- 1 1)
                        (a (- 1 1) 2)))))))))

(a (- 1 1)
   (a (- 1 1)
      (a (- 1 1)
         (a (- 1 1)
            (a (- 1 1)
               (a (- 1 1)
                  (a (- 1 1)
                     (a (- 1 1)
                        (a 0 2)))))))))

(a (- 1 1) 
   (a (- 1 1) 
      (a (- 1 1)  
         (a (- 1 1) 
            (a (- 1 1) 
               (a (- 1 1) 
                  (a (- 1 1) 
                     (a 0 4))))))))

;; [...]

(a (- 1 1)
   (a (- 1 1)
      (a 0 128)))

(a (- 1 1)
   (a 0 256))

(a 0 512)

1024
;; = 2^10

(a 2 4)
;; 65536

(cond (= 4 0) 0
      (= 2 0) (* 2 4)
      (= 4 1) 2
        :else (a (- 2 1)
                 (a 2 (- 4 1))))
(a (- 2 1)
   (a 2 (- 4 1)))

(a (- 2 1)
   (a 2 3))

(a (- 2 1)
   (cond (= 3 0) 0
         (= 2 0) (* 2 3)
         (= 3 1) 2
         :else (a (- 2 1)
               (a 2 (- 3 1)))))

(a (- 2 1)
   (a (- 2 1)
      (a 2 2)))

(a (- 2 1)
   (a (- 2 1)
      (cond (= 2 0) 0
            (= 2 0) (* 2 2)
            (= 2 1) 2
            :else (a (- 2 1)
                     (a 2 (- 2 1))))))

(a (- 2 1)
   (a (- 2 1)
      (a (- 2 1)
         (a 2 1))))

(a (- 2 1)
   (a (- 2 1)
      (a (- 2 1)
         (cond (= 1 0) 0
               (= 2 0) (* 2 1)
               (= 1 1) 2
               :else (a (- 2 1)
                        (a 2 (- 1 1)))))))

(a (- 2 1)
   (a (- 2 1)
      (a 1 2)))

(a (- 2 1)
   (a (- 2 1)
      (cond (= 2 0) 0
            (= 1 0) (* 2 2)
            (= 2 1) 2
            :else (a (- 1 1)
                     (a 1 (- 2 1))))))

(a (- 2 1)
   (a (- 2 1)
      (a (- 1 1)
         (a 1 1))))

(a (- 2 1)
   (a (- 2 1)
      (a (- 1 1)
         (cond (= 1 0) 0
               (= 1 0) (* 2 1)
               (= 1 1) 2
               :else (a (- 1 1)
                        (a 1 (- 1 1)))))))

(a (- 2 1)
   (a (- 2 1)
      (a 0 2)))

(a (- 2 1)
   (a (- 2 1)
      (cond (= 2 0) 0
            (= 0 0) (* 2 2)
            (= 2 1) 2
            :else (a (- 0 1)
                     (a 0 (- 2 1))))))

(a (- 2 1)
   (a 1 4))

;; We know (a 1 4) reduces to 2^4. In fact, I now see that I could have
;; started using this reduction several steps earlier. Oops.

(a 1 16)

65536

(a 3 3)
;; 65536 = 2^16

(cond (= 3 0) 0
      (= 3 0) (* 2 3)
      (= 3 1) 2
      :else (a (- 3 1)
               (a 3 (- 3 1))))

(a (- 3 1)
   (a 3 2))

(a (- 3 1)
   (cond (= 2 0) 0
         (= 3 0) (* 2 2)
         (= 2 1) 2
         :else (a (- 3 1)
                  (a 3 (- 2 1)))))

(a (- 3 1)
   (a (- 3 1)
      (a 3 1)))

(a (- 3 1)
   (a (- 3 1)
      (cond (= 1 0) 0
            (= 3 0) (* 2 1)
            (= 1 1) 2
            :else (a (- 3 1)
                     (a 3 (- 1 1))))))

(a (- 3 1)
   (a (- 3 1) 2))

(a (- 3 1)
   (a 2 2))

(a (- 3 1)
   (cond (= 2 0) 0
         (= 2 0) (* 2 2)
         (= 2 1) 2
         :else (a (- 2 1)
                  (a 2 (- 2 1)))))

(a (- 3 1)
   (a (- 2 1)
      (a 2 1)))

(a (- 3 1)
   (a (- 2 1)
      (cond (= 1 0) 0
            (= 2 0) (* 2 2)
            (= 1 1) 2
            :else (a (- 1 1)
                     (a 1 (- 2 1))))))

(a (- 3 1)
   (a (- 2 1) 2))

(a (- 3 1)
   (a 1 2))

(a (- 3 1)
   (cond (= 2 0) 0
         (= 1 0) (* 2 2)
         (= 2 1) 2
         :else (a (- 1 1)
                  (a 1 (- 2 1)))))
(a (- 3 1)
   (a (- 1 1)
      (a 1 1)))

(a (- 3 1)
   (a (- 1 1)
      (cond (= 1 0) 0
            (= 1 0) (* 2 1)
            (= 1 1) 2
            :else (a (- 1 1)
                     (a 1 (- 1 1))))))

(a (- 3 1)
   (a (- 1 1) 2))

(a (- 3 1)
   (a 0 2))

(a (- 3 1)
   (cond (= 2 0) 0
         (= 0 0) (* 2 2)
         (= 2 1) 2
         :else (a (- 0 1)
                  (a 0 (- 2 1)))))

(a (- 3 1) 4)

(a 2 4)

(cond (= 4 0) 0
      (= 2 0) (* 2 4)
      (= 4 1) 2
      :else (a (- 2 1)
               (a 2 (- 4 1))))

(a (- 2 1)
   (a 2 3))

(a (- 2 1)
   (cond (= 2 0) 0
         (= 3 0) (* 3 1)
         (= 2 1) 2
         :else (a (- 2 1)
                  (a 2 (- 3 1)))))

(a (- 2 1)
   (a (- 2 1)
      (a 2 2)))

(a (- 2 1)
   (a (- 2 1)
      (cond (= 2 0) 0
            (= 2 0) (* 2 2)
            (= 2 1) 2
            :else (a (- 2 1)
                     (a 2 (- 2 1))))))

(a (- 2 1)
   (a (- 2 1)
      (a (- 2 1)
         (a 2 1))))

(a (- 2 1)
   (a (- 2 1)
      (a (- 2 1)
         (cond (= 1 0) 0
               (= 2 0) (* 2 1)
               (= 1 1) 2
               :else (a (- 2 1)
                        (a 2 (- 1 1)))))))

(a (- 2 1)
   (a (- 2 1) 
      (a 1 2)))

(a (- 2 1)
   (a (- 2 1)
      (cond (= 2 0) 0
            (= 1 0) (* 2 2)
            (= 2 1) 2
            :else (a (- 1 1)
                     (a 1 (- 2 1))))))

(a (- 2 1)
   (a (- 2 1)
      (a (- 1 1)
         (a 1 (- 2 1)))))

(a (- 2 1)
   (a (- 2 1)
      (a (- 1 1)
         (a 1 1))))

(a (- 2 1)
   (a (- 2 1)
      (a (- 1 1)
         (cond (= 1 0) 0
               (= 1 0) (* 2 1)
               (= 1 1) 2
               :else (a (- 1 1)
                        (a 1 (- 1 1)))))))

(a (- 2 1)
   (a (- 2 1)
      (a (- 1 1) 2)))

(a (- 2 1)
   (a (- 2 1)
      (a 0 2)))

(a (- 2 1)
   (a (- 2 1)
      (cond (= 2 0) 0
            (= 0 0) (* 2 2)
            (= 2 1) 2
            :else (a (- 0 1)
                     (a 0 (- 2 1))))))

(a (- 2 1)
   (a (- 2 1) 4))

(a (- 2 1)
   (a 1 4))

(a (- 2 1) 16)

(a 1 16)

65536

(defn f
  "Computes 2n" 
  [n]
  (a 0 n))

(defn g
  "Computes 2^n"
  [n]
  (a 1 n))

(defn h
  "Computes 2^2^2... to exponential depth n: (h 2) = 2^2, (h 3) = 2^2^2"
  [n]
  (a 2 n))