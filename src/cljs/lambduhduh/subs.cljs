(ns lambduhduh.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :name
 (fn [db]
   (:code (:a (:bricks-map db)))))

;(re-frame/reg-sub
;  :brick
;  (fn [db]
;    (:name db)))
