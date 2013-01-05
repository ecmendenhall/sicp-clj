;; Exercise 1.20

(defn gcd 
  "Computes the greatest common denominator of a and b"
  [a b]
  (if (= b 0)
    a
    (gcd b (mod a b))))

;; What if the interpreter used normal order evaluation? How many times 
;; would it call mod?

(gcd 206 40)

(if (= 40 0) 
  206 
  (gcd 40 (mod 206 40)))

(gcd 40 (mod 206 40))

(if (= (mod 206 40) 0)
  40 
  (gcd (mod 206 40) (mod 40 (mod 206 40))))

(if (= 6 0)                                 ;; 1 mod operation
  40
  (gcd (mod 206 40) (mod 40 (mod 206 40)))) 

(gcd (mod 206 40) (mod 40 (mod 206 40)))

(if (= (mod 40 (mod 206 40)) 0)
  (mod 206 40)
  (gcd (mod 40 (mod 206 40)) 
       (mod 6 (mod 40 (mod 206 40)))))

(if (= 4 0)                                ;; 2 mod operations
  (mod 206 40)
  (gcd (mod 40 (mod 206 40))
       (mod (mod 206 40) 
            (mod 40 
                 (mod 206 40)))))

(gcd (mod 40 (mod 206 40)) 
     (mod (mod 206 40) 
          (mod 40 
               (mod 206 40))))

(if (= (mod (mod 206 40)
            (mod 40
                 (mod 206 40)))
       0)
  (mod 40 (mod 206 40))
  (gcd (mod (mod 206 40)
            (mod 40
                 (mod 206 40)))
       (mod (mod 40 (mod 206 40))
            (mod (mod 206 40)
                 (mod 40
                      (mod 206 40))))))

(if (= 2 0)                                ;; 4 mod operations
  (mod 40 (mod 206 40))
  (gcd (mod (mod 206 40)
            (mod 40
                 (mod 206 40)))
       (mod (mod 40 (mod 206 40))
            (mod (mod 206 40)
                 (mod 40
                      (mod 206 40))))))

(gcd (mod (mod 206 40)
          (mod 40
               (mod 206 40)))
     (mod (mod 40 (mod 206 40))
          (mod (mod 206 40)
               (mod 40
                    (mod 206 40)))))

(if (= (mod (mod 40 (mod 206 40))
          (mod (mod 206 40)
               (mod 40
                    (mod 206 40))))
       0)
  (mod (mod 206 40)
            (mod 40
                 (mod 206 40)))
  (gcd (mod (mod 40 (mod 206 40))
          (mod (mod 206 40)
               (mod 40
                    (mod 206 40))))
       (mod (mod (mod 206 40)
            (mod 40
                 (mod 206 40)))
            (mod (mod 40 (mod 206 40))
                 (mod (mod 206 40)
                      (mod 40
                           (mod 206 40)))))))

(if (= 0 0)                                   ;; 7 mod operations
  (mod (mod 206 40)
            (mod 40
                 (mod 206 40)))
  (gcd (mod (mod 40 (mod 206 40))
          (mod (mod 206 40)
               (mod 40
                    (mod 206 40))))
       (mod (mod (mod 206 40)
            (mod 40
                 (mod 206 40)))
            (mod (mod 40 (mod 206 40))
                 (mod (mod 206 40)
                      (mod 40
                           (mod 206 40)))))))

(mod (mod 206 40)
            (mod 40
                 (mod 206 40)))

2                                              ;; 4 mod operations
                                               ;; 18 total operations

;; Applicative order:

(gcd 206 40)
(if (= 40 0) 206 (gcd 40 (mod 206 40)))
(if false 206 (gcd 40 (mod 206 40)))
(gcd 40 (mod 206 40))
(gcd 40 6)                           ;; 1 mod operation
(if (= 6 0) 40 (gcd 6 (mod 40 6)))
(if false 40 (gcd 6 (mod 40 6)))
(gcd 6 (mod 40 6))
(gcd 6 4)                            ;; 1 mod operation
(if (= 4 0) 6 (gcd 4 (mod 6 4)))
(if false 6 (gcd 4 (mod 6 4)))
(gcd 4 (mod 6 4))
(gcd 4 2)                            ;; 1 mod operation
(if (= 2 0) 4 (gcd 2 (mod 4 2)))
(if false 4 (gcd 2 (mod 4 2)))
(gcd 2 (mod 4 2))
(gcd 2 0)                            ;; 1 mod operation
(if (= 0 0) 2 (gcd 0 (mod 2 0)))
2                                    ;; 4 total calls to mod
