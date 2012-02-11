(ns sbrian.linalg.core
  (:use (clojure.contrib
         [condition
          :only (handler-case print-stack-trace raise *condition*)])))

(defn- validate-matrix [a]
  (reduce
    (fn [a b]
       (if (not (= (count a) (count b))) (raise :matrix-error :invalid-matrix :arg 'a :value a))
       b) a))
                                                                    
(defn scalar [scalar a]
  (validate-matrix a)
  (map (fn [a]
    (map #(* scalar %) a)) a))

(defn- transpose- [a]
      (partition (count a) (apply interleave a)))

(def ^{:private true} vectorize (fn this[a]
  (if (and (not (vector? a)) (seq? a))
    (apply vector (map this a))
    a)))

(defn- add- [a b]
  (map (fn [a b]
    (map (fn [a b] (+ a b)) a b)) a b))

(defn add [a b]
  (vectorize (add- a b)))

(defn transpose [a]
  (validate-matrix a)
  (vectorize (transpose- a)))

(defn dot-prod [a b]
  (reduce + (map * a b)))

(defn- matrix-prod- [a b]
  (let [aa (transpose a)]
    (map
     (fn [r] (map (fn [c] (dot-prod c r)) aa))
     b)))

(defn matrix-prod [a b]
  (validate-matrix a)
  (validate-matrix b)
  (vectorize (matrix-prod- a b)))

(defn rotation-matrix-2d [a]
  [[(Math/cos a) (Math/sin a)] [(- 0 (Math/sin a)) (Math/cos a)]])

(defn rotate-2d [m a]
  (matrix-prod (rotation-matrix-2d a) m))

(defn echelon-column? [m c]
  "Returns true if in echelon form for given column, otherwise returns
   vector with indices of first two non-zero rows"
  (let [tr (count (m 0))]
    (loop [r 0 found-not-zero []]
      (if (= r tr) true
        (let [not-zero (not (= ((m c) r) 0))]
          (if (and not-zero (not (empty? found-not-zero))) (conj found-not-zero r)
            (recur (inc r) (if not-zero (conj found-not-zero r) found-not-zero))))))))

(defn subtract-multiple-of-row-from-row [m r r1 scalar]
  (vectorize (map (fn [c] (assoc c r (- (c r) (* (c r1) scalar)))) m)))

(defn make-echelon-column [m c]
  "Returns the matrix modified so the given column is in echelon form"
  (loop [mm m]
    (let [echelon-info (echelon-column? mm c)]
    (if (= true echelon-info) mm
      (recur (subtract-multiple-of-row-from-row
        mm
        (echelon-info 1)
        (echelon-info 0)
        (/ ((mm c) (echelon-info 1)) ((mm c) (echelon-info 0)))))))))




