(ns sbrian.linalg.test.shapes-tests
  (:use clojure.test)
  (:use sbrian.linalg.shapes))

(deftest test-rectangle
  (is (= [[0 0][0 1][0 2][0 3][1 3][2 3][3 3][3 2][3 1][3 0][2 0][1 0]]
         (rectangle 0 0 4 4 1))))

(deftest test-rectangle2
  (is (= [[0 0][0 2][0 3][2 3][3 3][3 1][3 0][1 0]]
         (rectangle 0 0 4 4 2))))

(deftest test-rectangle3
  (is (= [[0 0][0 1][1 1][2 1][3 1][3 0][2 0][1 0]]
         (rectangle 0 0 4 2 1))))

