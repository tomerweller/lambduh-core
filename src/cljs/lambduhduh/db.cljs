(ns lambduhduh.db)

(def default-db
  {:bricks-map
   {:a {:code "(defn square [x] (* x x))" :ast {:a "test"}}
    :b {:code "(square 5)" :ast {:b "test"}}}
   :result "(none)"})