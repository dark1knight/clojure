(require '[clojure.string :as str])

(def playing-cards 
  {
   "2" 0
   "3" 1
   "4" 2
   "5" 3
   "6" 4
   "7" 5
   "8" 6
   "9" 7
   "T" 8
   "J" 9
   "Q" 10
   "K" 11
   "A" 12
   })

(def suits { "D" 1 "C" 2 "H" 3 "S" 4 })

; the potential poker hands and their scores
(def hands
  { :high-card 1 :one-pair 2 :two-pair 3 :three-of-a-kind 4 :straight 5 
    :flush 6 :full-house 7 :four-of-a-kind 8 :straight-flush 9 :royal-flush 10 })

;; parsing routines for cards
(defn parse-card [card]
  {
  :number (get playing-cards (str (first card)))
  :suit (get suits (str (last card)))
   })

; returns first and second player's hands sorted by card number.
(defn parse-line [line]
  (let [cards (-> line (str/split #" "))]
  {
   :first-hand (sort-by :number (map parse-card (take 5 cards)))
   :second-hand (sort-by :number (map parse-card (drop 5 cards)))
   }))

(parse-line "8C TS KC 9H 4S 7D 2S 5D 3S AC")

(defn readlines []
  (line-seq (java.io.BufferedReader. *in*)))

;; there must be an easier way to do this...
(defn check-straight [hand]
  (let [numbers (map :number hand)]
    (loop [this-number (first numbers)
           next-number (second numbers)
           index 1
           is-last (= (count numbers) index)]
      (if is-last
        true
        (if-not (= 1 (- next-number this-number))
          false
          (recur (first (drop index numbers))
                 (second (drop index numbers))
                 (inc index)
                 (= (count numbers) (inc index))))))))

(defn check-flush [hand]
  (= 1 (-> (map :suit hand) distinct count)))

(defn figure-out-hand [hand]
  (if (check-flush hand)
    (if (check-straight hand)
      (println "either straight flush or royal flush")
      (if (check-full-house hand)
        (println "full house")
        (if (check-four-of-a-kind hand)
          (println "four of a kind")
          (println "flush"))))
    (if (check-straight hand)
      (println "got a straight")
      (if (check-full-house hand)
        (println "full house")
        (if (check-four-of-a-kind hand)
          (println "four of a kind")
          ; can be three of a kind, two pairs, one pair or high card



;(check-flush (:first-hand (parse-line "2C 3C 7C JC QC")))

;(check-straight (:first-hand (parse-line "2C 3C 4C 5C 6C")))
