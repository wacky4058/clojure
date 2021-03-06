(ns clojure-problem)



;;21 Write a function which returns the Nth element from a sequence.
;; (= (__ '(4 5 6 7) 2) 6)
;; (= (__ [:a :b :c] 0) :a)
;; (= (__ [1 2 3 4] 1) 2)
;; (= (__ '([1 2] [3 4] [5 6]) 2) [5 6])
(fn [a b]
  (first (drop b a)))

;;22
;; Write a function which returns the total number of elements in a sequence.
;; (= (__ '(1 2 3 3 1)) 5)
;; (= (__ "Hello World") 11)
;; (= (__ [[1 2] [3 4] [5 6]]) 3)
;; (= (__ '(13)) 1)
;; (= (__ '(:a :b :c)) 3)
(fn [a]
  (reduce + (map (fn [x] 1) a)))

;;23
;; Write a function which reverses a sequence.
;; (= (__ [1 2 3 4 5]) [5 4 3 2 1])
;; (= (__ (sorted-set 5 7 2 7)) '(7 5 2))
;; (= (__ [[1 2][3 4][5 6]]) [[5 6][3 4][1 2]])
reduce #(cons %2 %1) '()


;;24
;; Write a function which returns the sum of a sequence of numbers.
;; (= (__ [1 2 3]) 6)
;; (= (__ (list 0 -2 5 5)) 8)
;; (= (__ #{4 2 1}) 7)
;; (= (__ '(0 0 -1)) -1)
;; (= (__ '(1 10 3)) 14)
apply +

;;25
;; Write a function which returns only the odd numbers from a sequence.
;; (= (__ #{1 2 3 4 5}) '(1 3 5))
;; (= (__ [4 2 1 6]) '(1))
;; (= (__ [2 2 4 6]) '())
;; = (__ [1 1 1 3]) '(1 1 1 3))
(fn [col]
  (filter odd? col))

;;27
;; Write a function which returns true if the given sequence is a palindrome.
;; Hint: "racecar" does not equal '(\r \a \c \e \c \a \r)
;; (false? (__ '(1 2 3 4 5)))
;; (true? (__ "racecar"))
;; (true? (__ [:foo :bar :foo]))
;; (true? (__ '(1 1 3 3 1 1)))
;; (false? (__ '(:a :b :c)))
(fn [x]
  (= (seq x) (reverse (seq x))))


;; 28
;; Write a function which flattens a sequence.
;; (= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
;; (= (__ ["a" ["b"] "c"]) '("a" "b" "c"))
;; (= (__ '((((:a))))) '(:a))
(fn ff [col]
  (reduce (fn f* [acc x]
            (if (coll? x)
              (reduce f* acc x)
              (conj acc x))) []  col))

;;30
;;Write a function which removes consecutive duplicates from a sequence.
;; (= (apply str (__ "Leeeeeerrroyyy")) "Leroy")
;; (= (__ [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
;; (= (__ [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))

(fn [col]
  (let [f* (fn [acc s*]
             (if (not= (last acc) s*)
               (conj acc s*)
               acc))]
    (reduce f* [] col)))



;;32
;;Write a function which duplicates each element of a sequence.
;; (= (__ [1 2 3]) '(1 1 2 2 3 3))
;; (= (__ [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
;; (= (__ [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
;; (= (__ [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
(fn f [s]
  (let [f*
        (fn [acc s*]
          (conj acc s* s*))]
    (reduce f* [] s)))

;;34
;; Write a function which creates a list of all integers in a given range.
;; (= (__ 1 4) '(1 2 3))
;; (= (__ -2 2) '(-2 -1 0 1))
;; (= (__ 5 8) '(5 6 7))
(fn [a b]
  (loop [x a acc []]
    (if (< x b)
      (recur (inc x)(conj acc x))
      acc)))

;;39
;; Write a function which takes two sequences and returns the first item from each, then the second item from each, then the third, etc.
;; (= (__ [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))
;; (= (__ [1 2] [3 4 5 6]) '(1 3 2 4))
;; (= (__ [1 2 3 4] [5]) [1 5])
;; (= (__ [30 20] [25 15]) [30 25 20 15])

(fn [col1 col2]
  (let [count1 (count col1)
        count2 (count col2)
        counts (if (> count1 count2)
                 count2
                 count1)]
    (loop [x 0 acc []]
      (if (< x counts)
        (recur (inc x) (conj acc (nth col1 x) (nth col2 x)))
        acc))))



;;42
;; Write a function which calculates factorials.
;; (= (__ 1) 1)
;; (= (__ 3) 6)
;; (= (__ 5) 120)
;; (= (__ 8) 40320)
(fn [s]
  (reduce *  1 (range 1 (+ 1 s))))






;;134  Write a function which, given a key and map, returns true iff the map contains an entry with that key and its value is nil.
;; (true?  (__ :a {:a nil :b 2}))
;; (false? (__ :b {:a nil :b 2}))
;; (false? (__ :c {:a nil :b 2}))
(fn [a b]
  (and (contains? b a) (nil? (get b a))))

;;156
;; When retrieving values from a map, you can specify default values in case the key is not found:
;; (= 2 (:foo {:bar 0, :baz 1} 2))
;; However, what if you want the map itself to contain the default values? Write a function which takes a default value and a sequence of keys and constructs a map.
;; (= (__ 0 [:a :b :c]) {:a 0 :b 0 :c 0})
;; (= (__ "x" [1 2 3]) {1 "x" 2 "x" 3 "x"})
;; (= (__ [:a :b] [:foo :bar]) {:foo [:a :b] :bar [:a :b]})

(fn [a col]
  (loop [n 0 maps {}]
    (if (< n (count col))
      (recur (inc n) (assoc maps (nth col n) a))
      maps)))






