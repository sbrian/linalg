(ns sbrian.linalg.visual
  (:use sbrian.linalg.core)
  (:use sbrian.plotting.core)
  (:use sbrian.plotting.shapes))

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

(defn rotate-rectangle []
  (timed-rotate-m (rectangle 5 5 10 5 2) 2000))
  
  