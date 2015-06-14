(require '[clojure.string :as str])

; function to recursively calculate factorial
; assumption: num >= 0
(defn factorial [num]
  (if (<= num 1)
    1
    (* num (factorial (dec num)))))

(defn get-digits [number array]
  (if (< number 10)
    (cons number array)
    (get-digits 
      (/ (- number (rem number 10)) 10)
      (cons (rem number 10) array))))

(defn biggest-n-digit-number [n]
  (read-string
    (str/join
      (repeat n "9"))))

(defn factorial-sum [number]
  (reduce +
          (map factorial
               (get-digits number []))))

(defn find-calc-limit []
  (loop [digits 1]
    (let [number (biggest-n-digit-number digits)]
      (if (<= (factorial-sum number) number)
        number
        (recur (inc digits))))))

(println (reduce + 0
        (filter (fn [number]
                  (= number (factorial-sum number)))
                (range 10 (find-calc-limit)))))


