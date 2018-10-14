(ns number-in-words.conversion)

(def out-of-boundary-error-message "Error: Out of boundaries - number should be between 0 and 1000 inclusive")

(def one-to-twenty ["zero" "one"  "two"  "three"  "four"  "five"  "six"  "seven"  "eight"  "nine"
                    "ten"  "eleven"  "twelve"  "thirteen"  "fourteen"  "fifteen"  "sixteen"  "seventeen"  "eighteen"  "nineteen"  "twenty"])

(def tens ["zero" "ten" "twenty" "thirty" "forty" "fifty" "sixty" "seventy" "eighty" "ninety"])

(defn- round-tens? [n] (and (< n 100) (= 0 (mod n 10))))
(defn- round-hundreds? [n] (and (< n 1000) (= 0 (mod n 100))))

(defn- extract-units
  "Returns the units for any number n"
  [n]
  (mod n 10))

(defn- extract-tens
  "Returns the tens for the number. Eg 30 for 34, or 90 for 99"
  [n]
  (-> (- n (extract-units n))
      (mod 100)))

(defn- extract-hundreds
  "Returns the hundreds for the number. Eg 100 for 178, or 400 for 444"
  [n]
  (-> (- n (mod n 100))
      (mod 1000)))

(defn convert
  "Converts a number into English words."
  [n]
  (cond (not (<= 0 n 1000)) out-of-boundary-error-message
        (<= 0 n 20) (get one-to-twenty n)
        (round-tens? n) (get tens (/ n 10))
        (<= 21 n 99) (str (convert (extract-tens n)) "-" (convert (extract-units n)))
        (round-hundreds? n) (str (convert (/ n 100)) " hundred")
        (<= 101 n 999) (str (convert (extract-hundreds n)) " and " (convert (mod n 100)))
        (= n 1000) "one thousand"))
