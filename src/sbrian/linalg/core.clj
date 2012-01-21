(ns sbrian.linalg.core
  (:use (clojure.contrib
         [condition
          :only (handler-case print-stack-trace raise *condition*)])))

(defn- validate-matrix [a]
  (reduce
    (fn [a b]
       (if (not (= (count a) (count b))) (raise :matrix-error :invalid-matrix :arg 'a :value a))
       b) a))
                                                                    
(defn- add- [a b]
  (map (fn [a b]
    (map (fn [a b] (+ a b)) a b)) a b))

(defn add [a b]
  (vectorize (add- a b)))

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


