(ns lambduhduh.util)

;TODO: change to macro
(defn log [& args]
  (.log js/console args))