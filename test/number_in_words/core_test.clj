(ns number-in-words.core-test
  (:require [clojure.test :refer :all]
            [number-in-words.conversion :refer :all :as core]))

(deftest number-conversions
  (testing "Zero to Twenty"
    (is (= "zero" (convert 0)))
    (is (= "one" (convert 1)))
    (is (= "two" (convert 2)))
    (is (= "three" (convert 3)))
    (is (= "four" (convert 4)))
    (is (= "five" (convert 5)))
    (is (= "six" (convert 6)))
    (is (= "seven" (convert 7)))
    (is (= "eight" (convert 8)))
    (is (= "nine" (convert 9)))
    (is (= "ten" (convert 10)))
    (is (= "eleven" (convert 11)))
    (is (= "twelve" (convert 12)))
    (is (= "thirteen" (convert 13)))
    (is (= "fourteen" (convert 14)))
    (is (= "fifteen" (convert 15)))
    (is (= "sixteen" (convert 16)))
    (is (= "seventeen" (convert 17)))
    (is (= "eighteen" (convert 18)))
    (is (= "nineteen" (convert 19)))
    (is (= "twenty" (convert 20))))

  (testing "Round tens under 100"
    (is (= "thirty" (convert 30)))
    (is (= "forty" (convert 40)))
    (is (= "fifty" (convert 50)))
    (is (= "sixty" (convert 60)))
    (is (= "seventy" (convert 70)))
    (is (= "eighty" (convert 80)))
    (is (= "ninety" (convert 90))))

  (testing "Random numbers between 21 and 99"
    (is (= "twenty-five" (convert 25)))
    (is (= "fifty-two" (convert 52)))
    (is (= "seventy-seven" (convert 77)))
    (is (= "eighty-eight" (convert 88)))
    (is (= "ninety-one" (convert 91))))

  (testing "Random round hundreds"
    (is (= "one hundred" (convert 100)))
    (is (= "five hundred" (convert 500)))
    (is (= "nine hundred" (convert 900))))

  (testing "Random numbers between 100 and 999"
    (is (= "one hundred and twenty-three" (convert 123)))
    (is (= "two hundred and fifty-seven" (convert 257)))
    (is (= "eight hundred and eighty-eight" (convert 888)))
    (is (= "nine hundred and ninety-nine" (convert 999))))

  (testing "One thousand"
    (is (= "one thousand" (convert 1000))))

  (testing "Out fo boundaries"
    (is (= out-of-boundary-error-message (convert -1)))
    (is (= out-of-boundary-error-message (convert 1001)))))

(deftest extracting-the-units
  (testing "Extracting the units"
    (let [extract-units #'core/extract-units]
      (is (= 3 (extract-units 3)))
      (is (= 4 (extract-units 14)))
      (is (= 9 (extract-units 99)))
      (is (= 8 (extract-units 188)))
      (is (= 3 (extract-units 1013))))))

(deftest extracting-the-tens
  (testing "Extracting the tens"
    (let [extract-tens #'core/extract-tens]
      (is (= 0 (extract-tens 3)))
      (is (= 10 (extract-tens 13)))
      (is (= 10 (extract-tens 10)))
      (is (= 90 (extract-tens 99)))
      (is (= 0 (extract-tens 101))))))

(deftest extracting-the-hundreds
  (testing "Extracting the hundreds"
    (let [extract-hundreds #'core/extract-hundreds]
      (is (= 0 (extract-hundreds 3)))
      (is (= 0 (extract-hundreds 13)))
      (is (= 100 (extract-hundreds 100)))
      (is (= 100 (extract-hundreds 145)))
      (is (= 200 (extract-hundreds 235)))
      (is (= 200 (extract-hundreds 3235))))))

(deftest round-tens
  (testing "Are they rounds of tens?"
    (let [round-tens? #'core/round-tens?]
      (is (round-tens? 0))
      (is (round-tens? 10))
      (is (round-tens? 50))
      (is (not (round-tens? 11)))
      (is (not (round-tens? -34)))
      (is (not (round-tens? 456))))))

(deftest round-hundreds
  (testing "Are they rounds of hundreds?"
    (let [round-hundreds? #'core/round-hundreds?]
      (is (round-hundreds? 0))
      (is (round-hundreds? 100))
      (is (round-hundreds? 500))
      (is (not (round-hundreds? 110)))
      (is (not (round-hundreds? -340)))
      (is (not (round-hundreds? 1456))))))
