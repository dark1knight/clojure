(defn pow [n power]
  (if (= 1 power)
    n
    (* n (pow n (- power 1)))))

(defn solve []
(reduce + (map 
            (fn [ch] (- (int ch) 48)) 
            (str (pow (bigint 2) 1000))))
  )


(println (solve))
