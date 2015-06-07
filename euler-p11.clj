(require '[clojure.string :as str])

;; Because read-string can't deal with leading zeroes and the java function can
(defn parse-int [s] (Integer/parseInt s))

;; read all lines from file and convert them to string
(defn readlines []
    (line-seq (java.io.BufferedReader. *in*)))

;; take the lines and parse them into a map of lists of integers.
(defn parse-lines [lines]
     (map 
       (fn [s] (into [] (map parse-int (str/split s #" "))))
       lines))

(defn row-max [row]
  (if (= 4 (count row))
    (reduce * row)
    (max (reduce * (take 4 row))
         (row-max (drop 1 row)))))

(def parsed-vals (parse-lines (readlines)))

(defn transpose [m]
  (apply mapv vector m))

(defn down-right [row col step mp]
  (nth
    (nth mp (+ row step) ())
    (+ col step) 1))

(defn down-left [row col step mp]
  (nth 
    (nth mp (+ row step) ()) 
    (- col step) 1))

;;  based on the current row, grab the diagonal entries
(defn take-diagonal [func row col mp]
  (into [] 
        [(func row col 0 mp)
        (func row col 1 mp)
        (func row col 2 mp)
        (func row col 3 mp)]))

;; horizontally
(println (reduce max (map row-max parsed-vals)))
;; vertically
(println (reduce max (map row-max (transpose parsed-vals))))

;; diagonally
(defn best-diagonal [mp func]
  (reduce max
  (map
  (fn [row]
    (reduce max
    (map
      (fn [cl] (reduce * (take-diagonal func row cl mp)))
    (range (count (nth mp row))))))
  (range (count mp)))))
    
(println (best-diagonal parsed-vals down-right))
(println (best-diagonal parsed-vals down-left))

