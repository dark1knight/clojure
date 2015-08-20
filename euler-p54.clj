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

(defn check-straight [hand]
  (map :number hand)

(defn check-flush [hand]
  (= 1 (-> (map :suit hand) distinct count)))

(check-flush (:first-hand (parse-line "2C 3C 7C JC QC")))
