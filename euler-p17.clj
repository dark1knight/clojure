(require '[clojure.string :as str])

(def single-numbers 
  {
   1 "one"
   2 "two"
   3 "three"
   4 "four"
   5 "five"
   6 "six"
   7 "seven"
   8 "eight"
   9 "nine"
   10 "ten"
   11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"
   20 "twenty"
   30 "thirty"
   40 "forty"
   50 "fifty"
   60 "sixty"
   70 "seventy"
   80 "eighty"
   90 "ninety"
   100 "onehundred"
   1000 "onethousand"
   })

; count letters for a given number
(defn count-letters-for-number [number]
  (let [word (get single-numbers number)]
    (if (= 0 number)
      0
      (if (boolean word) ; the number is one of the words.
        (count word)
        (if (and (> number 100) (< number 1000))
          (+ (count 
               (str/join [(get single-numbers (quot number 100)) 
                          "hundred" 
                          (if (= 0 (rem number 100)) "" "and")]
                         ))
             (count-letters-for-number (rem number 100)))
          (if (< number 100)
            (+ (count (get single-numbers (* 10 (quot number 10))))
               (count (get single-numbers (rem number 10))))))
  ))))

; count the total number of letters in all numbers from number to terminus
(defn count-letters [number terminus]
  (if (>= number terminus) ; reached limit
    (count-letters-for-number number)
    (+ (count-letters (inc number) terminus) 
       (count-letters-for-number number))))

(println (count-letters 1 1000))
