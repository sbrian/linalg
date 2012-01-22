(ns sbrian.linalg.shapes)

(defn rectangle
  "Returns a rectangle where x, y is the location of the bottom
   left corner.  The result is an array of vectors that trace
   the border of the rectangle starting at x,y and going up
   and around"
  [x, y, h-size, v-size, point-spacing]
  (let [
        x-max (+ x (- h-size 1))
        y-max (+ y (- v-size 1))
        ]
  (apply vector
    (concat
    (loop [n y vects []]
      (if (>= n y-max)
        vects
        (recur (+ n point-spacing) (conj vects [x n]))))
    (loop [n x vects []]
      (if (>= n x-max 1)
        vects
        (recur (+ n point-spacing) (conj vects [n y-max]))))
    (loop [n y-max vects []]
      (if (<= n y)
        vects
        (recur (- n point-spacing) (conj vects [x-max n]))))
    (loop [n x-max vects []]
      (if (<= n x)
        vects
        (recur (- n point-spacing) (conj vects [n y]))))))))



