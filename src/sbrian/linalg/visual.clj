(ns sbrian.linalg.visual
  (import com.sbrian.simplegraphics.View))

(def ^{:private true} view (new View))

(defn show-v [v]
  (. view setVisible true)
  (. view (setPoint (v 0) (v 1))))

(defn hide-v [v]
  (. view (unsetPoint (v 0) (v 1))))

(defn show-m [a]
  (. view setVisible true)
  (map show-v a))

(defn hide-m [a]
  (map hide-v a))

