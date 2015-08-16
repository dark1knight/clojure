; factorial digit sum
;

(defn factorial [number]
  (if (>= 1 number)
    1
    (* number (factorial (dec number)))))

(println 
  (reduce + (map
            (fn [character] (- (int character) 48))
            (str (factorial (bigint 100))))))
