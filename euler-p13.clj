(require '[clojure.string :as str])

(defn parse-big-int [s] (BigInteger. s))

;; read all lines from file and convert them to string
(defn readlines []
    (line-seq (java.io.BufferedReader. *in*)))

(println 
  (str/join "" 
      (take 10 
            (str
              (reduce +' (map parse-big-int (readlines)))))))
