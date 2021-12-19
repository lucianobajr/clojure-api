(ns start.test)

(def nome "Cesar")

(def x (+ 2 3))

(def my-addition (fn [operand1 operand2] (+ operand1 operand2)))

(println nome x (str "Hello, " "World!") (. Math PI))

(println (my-addition 100 2300))

(def hello (fn [] (str "Hello " (read-line))))

(println (hello))