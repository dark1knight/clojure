; double-base palindromes
; find sum of all numbers from 0 -> 1000000 that are palindromic in base 10 and 2
;

(defn base2 [number]
  (Integer/toString number 2))

(defn get-split-index [string]
  (let [even-letters (= 0 (rem (count string) 2))]
    (if even-letters
      (let [midpoint (/ (count string) 2)]
        {:to-take midpoint :to-drop midpoint}
        )
      (let [midpoint (int (/ (count string) 2))]
        {:to-take midpoint :to-drop (inc midpoint)}
        )
      )))

(defn is-palindrome [string]
  (let [split-index (get-split-index string)]
    (=
     (take (:to-take split-index) string)
     (reverse (drop (:to-drop split-index) string))
     )))

(defn solve []
(reduce +
        (filter (fn [number] 
                  (and (is-palindrome (str number))
                       (is-palindrome (base2 number))
                       ))
                (range 1 1000000)
                )))

(println (solve))
