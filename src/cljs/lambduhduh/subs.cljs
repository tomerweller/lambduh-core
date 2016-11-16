(ns lambduhduh.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :brick
 (fn [db]
   (:name db)))
