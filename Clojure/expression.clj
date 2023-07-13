;(load-file "proto.clj")

(defn bin-op [f]
      (fn [left right]
          (fn [x]
              (apply f [(left x) (right x)]))))
(def add (bin-op +))
(def subtract (bin-op -))
(def multiply (bin-op *))
(defn negate [f]
      (comp - f))
(defn exp [f]
      (fn [x]
          (Math/exp (f x))))
(defn ln [f]
      (fn [x]
          (Math/log (abs (f x)))))
(defn divide [f g]
      (fn [x] (/ (f x) (double (g x)))))
(def constant constantly)
(defn variable [x]
      (fn [m] (m x)))
(def map-operators {
                    '+      add
                    '-      subtract
                    '*      multiply
                    '/      divide
                    'negate negate
                    'exp    exp
                    'ln     ln
                    })
(defn parse-fun [token]
      (cond
        (symbol? token) (variable (str token))
        (number? token) (constant token)
        :else (apply (map-operators (first token)) (map parse-fun (rest token)))
        ))
(defn parseFunction [expression]
      (parse-fun (read-string expression)))

(defn toString [expression] (.toStr expression))
(defn evaluate [expression varNames] ((.calc expression) varNames))
(definterface AbstractExpression
              (toStr [])
              (calc []))
(deftype Const [cnst]
         AbstractExpression
         (toStr [_] (str cnst))
         (calc [_] (fn [varNames] (double cnst))))
(deftype Var [var]
         AbstractExpression
         (toStr [_] (str var))
         (calc [_] (fn [varNames] (varNames var))))

(defn Variable [value] (Var. value))
(defn Constant [value] (Const. value))

(deftype BinaryOperation [op symbol left right]
         AbstractExpression
         (toStr [_] (str "(" symbol " " (toString left) " " (toString right) ")"))
         (calc [_] (fn [varNames] (op (double ((.calc left) varNames)) (double ((.calc right) varNames)))))
         )
(deftype UnaryOperation [op symbol expr]
         AbstractExpression
         (toStr [_] (str "(" symbol " " (toString expr) ")"))
         (calc [_] (fn [varNames] (op ((.calc expr) varNames))))
         )
(defn Negate [expr]
      (UnaryOperation. - 'negate expr))

(defn Cos [expr]
      (UnaryOperation. #(Math/cos %) 'cos expr))

(defn Sin [expr]
      (UnaryOperation. #(Math/sin %) 'sin expr))
(defn Add [left right]
      (BinaryOperation. + '+ left right))

(defn Subtract [left right]
      (BinaryOperation. - '- left right))

(defn Multiply [left right]
      (BinaryOperation. * '* left right))

(deftype Div [left right]
         AbstractExpression
         (toStr [_] (str "(/ " (toString left) " " (toString right) ")"))
         (calc [_] (fn [varNames] (/ ((.calc left) varNames) (double ((.calc right) varNames))))))
(defn Divide [left right] (Div. left right))

(def object-operators {
                       '+      Add
                       '-      Subtract
                       '*      Multiply
                       '/      Divide
                       'negate Negate
                       'sin    Sin
                       'cos    Cos
                       })

(defn parse-ob [token]
      (cond
        (symbol? token) (Variable (str token))
        (number? token) (Constant token)
        :else (apply (object-operators (first token)) (map parse-ob (rest token)))
        ))
(defn parseObject [expression]
      (parse-ob (read-string expression)))
