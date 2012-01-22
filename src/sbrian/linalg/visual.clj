(ns sbrian.linalg.visual
  (:use sbrian.linalg.core)
  (import com.sbrian.simplegraphics.View))

(def ^{:private true} view (new View))

(defn clear-view []
  (. view clear))

(defn show-v [v]
  (. view setVisible true)
  (. view (setPoint (v 0) (v 1)))
  v)

(defn hide-v [v]
  (. view (unsetPoint (v 0) (v 1)))
  v)

(defn show-m [a]
    (. view setVisible true)
    (doall (map show-v a))
    a)

(defn hide-m [a]
  (doall (map hide-v a))
  a)

(defn timed-rotate-m [m ms]
  (show-m m)
  (loop [mm m rotated 0]
    (if (>= rotated (* Math/PI 2))
    (do
      (hide-m mm) m)
    (do
      (Thread/sleep ms)
      (hide-m mm)
      (recur (show-m (rotate-2d mm 0.1)) (+ 0.1 rotated))))))




