(ns sbrian.linalg.euclidean-vector
  (:use sbrian.linalg.vector-protocols))

(defrecord EVector [_v]
  
  IVector
  
  (add [this v] (map (fn [a b] (+ a b)) _v (:_v v)))
  (additive-identity [this] (repeat (count _v) 0))
  (additive-inverse [this] (scalar-product this -1))
  (scalar-product [this s] (EVector. (map #(* s %) _v)))
  
  IInnerProductVector
  
  (inner-product [this v] (reduce + (map * _v (:_v v)))))

