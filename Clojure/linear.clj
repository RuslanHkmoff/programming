(defn vector-operations [f]
      (fn [vec1 vec2]
          (mapv f vec1 vec2)))
(def v+ (vector-operations +))
(def v- (vector-operations -))
(def v* (vector-operations *))
(def vd (vector-operations /))
(defn scalar [vec1 vec2]
      (reduce + (map * vec1 vec2)))
(defn vect [vec1 vec2]
      [(- (* (get vec1 1) (get vec2 2)) (* (get vec1 2) (get vec2 1)))
       (- (* (get vec1 2) (get vec2 0)) (* (get vec1 0) (get vec2 2)))
       (- (* (get vec1 0) (get vec2 1)) (* (get vec1 1) (get vec2 0)))])
(defn v*s [vector s]
      (mapv #(* % s) vector))
(defn m*s [matrix s]
      (mapv #(v*s % s) matrix))
(defn matrix-operations [f]
      (fn [mat1 mat2]
          (mapv (fn [v1 v2] (f v1 v2)) mat1 mat2)))
(def m+ (matrix-operations v+))
(def m- (matrix-operations v-))
(def m* (matrix-operations v*))
(def md (matrix-operations vd))
(defn transpose [matrix]
      (apply mapv vector matrix))
(defn m*v [matrix vector]
      (mapv (partial scalar vector) matrix))
(defn m*m [mat1 mat2]
      (let [mat2-tr (transpose mat2)]
           (mapv (fn [row]
                     (mapv #(scalar row %) mat2-tr))
                 mat1)))
(defn shapeless-operations [f]
      (fn [sh1 sh2] (cond
                      (not (number? sh1))
                      (mapv #((shapeless-operations f) %1 %2) sh1 sh2)
                      :else
                      (f sh1 sh2))))
(def s+ (shapeless-operations +))
(def s- (shapeless-operations -))
(def s* (shapeless-operations *))
(def sd (shapeless-operations /))