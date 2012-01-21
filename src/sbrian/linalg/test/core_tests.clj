(ns sbrian.linalg.test.core-tests
  (:use clojure.test)
  (:use sbrian.linalg.core))

(deftest test-add

  (is (= [[2 2][2 2]]  (add [[1 1][1 1]] [[1 1] [1 1]])))
  
  (is (= [[4 3][11 23]]  (add [[1 1][1 1]] [[3 2] [10 22]])))
  
  (is (= [[1 3][11 23]]  (add [[-2 1][1 1]] [[3 2] [10 22]]))))

(deftest test-scalar
  
  (is (= [[4 2][8 24]] (scalar 2 [[2 1][4 12]])))
  
  (is (= [[2 1][4 12]] (scalar 1/2 [[4 2][8 24]]))))

(deftest test-matrix-prod
  
  (is (= [[55 42] [20 12]] (matrix-prod [[2 1][3 3][9 5]] [[5 9 2][4 1 1]]))))

(deftest test-transpose
  
  (is (= [[55 42] [20 12]] (transpose [[55 20] [42 12]]))))



