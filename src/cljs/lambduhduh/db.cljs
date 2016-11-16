(ns lambduhduh.db)

(def default-db
  {:bricks-map
   {:a {:code "(defn square [x] (* x x))"}
    :b {:code "(square 5)"}}})