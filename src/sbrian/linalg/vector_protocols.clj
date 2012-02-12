(ns sbrian.linalg.vector-protocols)

(defprotocol IVector
  
  (add [this v]
  "Addition must be commutative and associative")
  
  (additive-identity [this]
  "Addition must have an identity element")
  
  (additive-inverse [this]
  "Every element must have an additive inverse")
  
  (scalar-product [this s]
  "Scalar product must be distributive with respect to addition
   and must be compatible with field product"))

(comment
  At this point, should also define IScalar, but scalars are really a field,
  which should be IField)

(defprotocol IInnerProductVector
  (inner-product [this v]))

